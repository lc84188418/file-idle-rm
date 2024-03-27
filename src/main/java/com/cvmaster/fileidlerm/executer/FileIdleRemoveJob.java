package com.cvmaster.fileidlerm.executer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cvmaster.fileidlerm.dao.*;
import com.cvmaster.fileidlerm.entity.*;
import com.cvmaster.fileidlerm.enums.StatusEnum;
import com.cvmaster.fileidlerm.utils.FileUtil;
import com.cvmaster.fileidlerm.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @ClassName:TestJob1
 * @Desc: TODO
 * @Author: wenchao
 * @CreateTime:2023/6/6/006 22:16
 * @Version:1.0
 **/
@Slf4j
public class FileIdleRemoveJob extends QuartzJobBean {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private FileJobDao fileJobDao;
    @Resource
    private FileRmLogsDao fileRmLogsDao;
    @Resource
    private FileRmDatasourceDao fileRmDatasourceDao;
    @Resource
    private FileRmIgnoreDirectoryDao fileRmIgnoreDirectoryDao;
    @Resource
    private FileRmTableDao fileRmTableDao;
    private static volatile ConcurrentHashMap<Object,Object> triggerMap = new ConcurrentHashMap<>();
    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
        Trigger trigger = arg0.getTrigger();
        String jobGroup = trigger.getJobKey().getGroup();
        String jobName = trigger.getJobKey().getName();
        log.info("定时任务:{}.{}开始执行",jobGroup,jobName);
        System.out.println(triggerMap);
        if(triggerMap.get(trigger.getJobKey()) != null){
            //说明该定时任务还未执行完毕，直接返回，或其他处理方式
            log.warn("当前存在相同定时任务未执行完毕，此次任务已跳过执行---------------");
            return;
        }
        triggerMap.put(trigger.getJobKey(),trigger.getJobKey());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        QueryWrapper<FileJob> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("job_name", jobName);
        FileJob fileJob = fileJobDao.selectOne(queryWrapper);

        QueryWrapper<FileRmDatasource> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("fk_job", fileJob.getId());
        FileRmDatasource fileRmDatasource = fileRmDatasourceDao.selectOne(queryWrapper2);

        QueryWrapper<FileRmTable> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("fk_datasource", fileRmDatasource.getId());
        List<FileRmTable> fileRmTables = fileRmTableDao.selectList(queryWrapper3);

        //需要被忽略的文件目录，如果配置了这些目录，文件扫描时会跳过，则这些目录下的文件均不会移除
        QueryWrapper<FileRmIgnoreDirectory> ignoreDirectoryQueryWrapper = new QueryWrapper<>();
        ignoreDirectoryQueryWrapper.select("ignore_directory").eq("fk_job", fileJob.getId());
        List<FileRmIgnoreDirectory> ignoreDirectories = fileRmIgnoreDirectoryDao.selectList(ignoreDirectoryQueryWrapper);
        List<String> ignoreDirectoryList = ignoreDirectories.stream().map(FileRmIgnoreDirectory::getIgnoreDirectory).collect(Collectors.toList());

        //文件根目录忽略 如：E:\test
        String rootIgnore = fileJob.getRootIgnore()==null?"":fileJob.getRootIgnore();

        //文件根目录 如：E:\test\file_idle_rm
        String rootDirectory = fileJob.getRootDirectory();
        if (StringUtils.isBlank(rootIgnore) && StringUtils.isBlank(rootDirectory)  ){
            triggerMap.remove(trigger.getJobKey());
            throw new JobExecutionException("The root directory does not exist！");
        }
        //是否回滚
        boolean isRollback = fileJob.getIsRollback().equals(StatusEnum.ok.status())?true:false;
        //数据库文件存储是否绝对路径Y/N
        boolean isAbsolutePath = fileJob.getIsAbsolutePath().equals(StatusEnum.ok.status())?true:false;
        String absolutePath = fileJob.getAbsolutePath();
        //回滚文件路径
        String rollbackPath = fileJob.getRollbackPath();
        if(StringUtils.isBlank(rollbackPath)){
            //默认回滚文件路径，与根目录同级，规则是：根目录末尾加 "_file_idle_rm" 最后回滚路径为：E:\test\file_idle_rm_file_idle_rm
            rollbackPath = rootDirectory + "_file_idle_rm";
        }
        System.out.println(triggerMap);
        /**
         * 1.遍历服务器的文件目录，找出所有文件，从根目录开始 E:\test\file_idle_rm
         * 必须先执行这一步，防止在执行过程中若查询出数据库的数据，本地又添加了文件，这些文件就会被移除
         */
        stopWatch.stop();
        stopWatch.start();
        List<String> fileListByRoot = FileUtil.getFileListByRoot(rootDirectory,ignoreDirectoryList);
        stopWatch.stop();
        log.info("遍历服务器文件耗时：{}毫秒",stopWatch.getLastTaskTimeMillis());
        stopWatch.start();
        if(CollectionUtils.isEmpty(fileListByRoot)){
            triggerMap.remove(trigger.getJobKey());
            log.info("No files exist in the current root directory");
            return;
        }
//        System.out.println(fileListByRoot.size());
        /**
         * 2.查询出所有存放了文件的表及其字段
         */
        List<String> totalList = new LinkedList<>();
        for (FileRmTable table : fileRmTables) {
            StringJoiner sql = new StringJoiner(" ");
            sql.add("select");
            sql.add(table.getRmTableColumn());
            sql.add("from");
            sql.add(table.getRmTable());
            sql.add("where");
            sql.add(table.getRmTableColumn());
            sql.add("is not null and");
            sql.add(table.getRmTableColumn());
            sql.add("!= ''");
            List<String> maps = jdbcTemplate.queryForList(sql.toString(),String.class);
            totalList.addAll(maps);
        }
        //list转set，进行去重，减少后续遍历次数
        Set<String> totalSet = new HashSet<>(totalList);
        stopWatch.stop();
        log.info("获取数据库文件数据耗时：{}毫秒",stopWatch.getLastTaskTimeMillis());
        stopWatch.start();

        /**
         * 3.以文件列表开始遍历，找出数据库中没有存放的文件
         */
        List<String> noExistFileList = null;
        if(isAbsolutePath){
            noExistFileList = FileUtil.findNotExistFile_byAbsolutePath(rootIgnore,fileListByRoot,totalSet,absolutePath);
        }else {
            noExistFileList = FileUtil.findNotExistFile(rootIgnore,fileListByRoot,totalSet);
        }
        stopWatch.stop();
        log.info("遍历文件,找出数据库中没有存放的文件耗时：{}毫秒",stopWatch.getLastTaskTimeMillis());
        stopWatch.start();

//        List<String> noExistFileList = null;
        if(CollectionUtils.isEmpty(noExistFileList)){
            triggerMap.remove(trigger.getJobKey());
            log.info("All files in the server have been referenced in the database");
            return;
        }
//        log.info("需要操作的文件：{}",fileListByRoot);
        /**
         * 4.将不需要用到的文件，从文件中删除
         */
        FileRmLogs fileRmLogs = new FileRmLogs();
        fileRmLogs.setTotalCount(noExistFileList.size());
        if(isRollback){
            try {
                //1.使用移动文件的方式
//                fileRmLogs = FileUtil.moveFile(noExistFileList,rootDirectory,rollbackPath);
                //2先拷贝再删除文件的方式
                Set<String> copyAndRemoveFailList = FileUtil.copyAndRemoveFile(noExistFileList,rootDirectory,rollbackPath,fileRmLogs);
                System.out.println(copyAndRemoveFailList.size());
                //排除拷贝失败的文件
//                noExistFileList.removeAll(copyList);
//                fileRmLogs = FileUtil.removeFile(noExistFileList);
            }catch (Exception e){
                e.printStackTrace();
                triggerMap.remove(trigger.getJobKey());
                //如果移动/拷贝文件出现异常，不再进行文件的操作
                throw new JobExecutionException("File backup encountered an exception");
            }
        }else {
            //不需要回滚，会直接将文件移除，找不回数据，谨慎使用！
            FileUtil.removeFile(noExistFileList);
        }
        stopWatch.stop();
        fileRmLogs.setFkJob(fileJob.getId());
        fileRmLogs.setTimeConsuming(stopWatch.getTotalTimeMillis());
        fileRmLogsDao.insert(fileRmLogs);
        log.info("This timed task execution took a total of {} milliseconds",stopWatch.getTotalTimeMillis());
        triggerMap.remove(trigger.getJobKey());

    }

}