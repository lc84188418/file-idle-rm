package com.cvmaster.fileidlerm.service;

import com.cvmaster.fileidlerm.entity.FileJob;
import com.cvmaster.fileidlerm.enums.StatusEnum;
import com.cvmaster.fileidlerm.executer.FileIdleRemoveJob;
import com.cvmaster.fileidlerm.utils.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import xyz.erupt.annotation.fun.DataProxy;
import xyz.erupt.jpa.dao.EruptDao;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName:FileJobService
 * @Desc:TODO
 * @Author: wenchao
 * @CreateTime:2024/2/29 23:56
 * @Version:1.0
 **/
@Service
public class FileJobService implements DataProxy<FileJob> {
    @Resource
    private EruptDao eruptDao;
    @Resource
    private QuartzService quartzService;

    @Override
    public void beforeAdd(FileJob fileJob) {
        if(StringUtils.isBlank(fileJob.getStatus())){
            fileJob.setStatus(StatusEnum.ok.status());
        }
        Date date = new Date();
        LocalDateTime localDateTime = LocalDateTime.now();
        fileJob.setCreateTime(localDateTime);
        fileJob.setUpdateTime(localDateTime);
        //参数判断

    }

    @Override
    public void beforeUpdate(FileJob fileJob) {
        Date date = new Date();
        LocalDateTime localDateTime = LocalDateTime.now();
        fileJob.setUpdateTime(localDateTime);
    }

    @Override
    public void afterAdd(FileJob fileJob) {
        quartzService.addJob(FileIdleRemoveJob.class, fileJob.getJobName(), fileJob.getJobGroup(), fileJob.getJobCron());
    }

    @Override
    public void afterUpdate(FileJob fileJob) {
        //查询原来的定时任务状态
        String dbFileJobStatus = eruptDao.getJdbcTemplate().queryForObject("SELECT status FROM file_job WHERE job_name = ?", new BeanPropertyRowMapper<String>(String.class), fileJob.getJobName());

        quartzService.updateJob(fileJob.getJobName(), fileJob.getJobGroup(), fileJob.getJobCron());
        //如果修改了任务的执行状态为暂停
        //dbFileJobStatus.equals(StatusEnum.ok.status()) &&
        if(fileJob.getStatus().equals(StatusEnum.error.status())){
            quartzService.pauseJob(fileJob.getJobName(), fileJob.getJobGroup());
        }
        //dbFileJobStatus.equals(StatusEnum.error.status()) &&
        if(fileJob.getStatus().equals(StatusEnum.ok.status())){
            quartzService.resumeJob(fileJob.getJobName(), fileJob.getJobGroup());
        }
    }

    @Override
    public void afterDelete(FileJob fileJob) {
        quartzService.deleteJob(fileJob.getJobName(), fileJob.getJobGroup());
    }
}
