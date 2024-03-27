package com.cvmaster.fileidlerm.utils;

import com.cvmaster.fileidlerm.entity.FileRmLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.quartz.JobExecutionException;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

/**
 * 遍历目录是操作文件时的一个常见需求。比如写一个程序，需要找到并处理指定目录下的所有JS文件时，
 * 就需要遍历整个目录。该项目教会你如何使用流式编程和lambda表达式， 帮助你进一步熟悉java8特性，并且通过它实现目录遍历。
 *
 * @author Administrator
 */
@Slf4j
public class FileUtil {
    private static List<String> fileList = null;
    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入文件的绝对路径:");
//        String path = sc.next();
        copy_delteFile("E:\\test\\file_idle_rm\\img\\2023\\06\\11\\iu - 副本.jpeg","E:\\test\\file_idle_rm_file_idle_rm\\img\\2023\\06\\11\\iu - 副本.jpeg");

        // String EndName = sc.next();
//        findFileAddToList(path);
//        System.out.println("----------");
//        newMethod(path);
    }
    public static int climbStairs(int n) {
        //这里大小根据自己需要，或者使用 List 也可以
        int[] dp = new int[100000];
        dp[1] = 1;
        dp[2] = 2;
        for( int i = 3;i <= n;++i ){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public static List<String> getFileListByRoot(String path, List<String> ignoreDirectoryList){
        fileList = new LinkedList<>();
        findFileAddToList(path,ignoreDirectoryList);
        return fileList;
    }
    //传统方法-用递归处理
    public static void findFileAddToList(String path,List<String> ignoreDirectoryList) {
        File file = new File(path);
        if (file.isDirectory()) {
//            System.out.println("是目录：" + path);
            if(!CollectionUtils.isEmpty(ignoreDirectoryList)){
                if (ignoreDirectoryList.contains(path)){
                    ignoreDirectoryList.remove(path);
                    return;
                }
            }
            String[] childs = file.list();
            for (String child : childs) {
                findFileAddToList(path + File.separator + child,ignoreDirectoryList);
            }
        }else {
//            System.out.println("是文件：" + path);
            fileList.add(path);
        }
    }
    //利用Stream流式和lambda表达式完成
    public static void newMethod(String path) throws Exception {
        Path start = FileSystems.getDefault().getPath(path);
        Files.walk(start).filter(childpath -> childpath.toFile().isFile())
                // .filter(path -> path.toString().endsWith(EndName))
                .forEach(System.out::println);
    }

    public static List<String> findNotExistFile(String rootIgnore,List<String> fileList, Set<String> dbFileList) {
        if(CollectionUtils.isEmpty(dbFileList)){
            log.info("There are no files stored in the current data source");
            return fileList;
        }
        List<String> notExistFileList = new ArrayList<>(fileList.size());
        Iterator<String> iterator = fileList.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            String s1 = s.substring(rootIgnore.length());
            if(dbFileList.contains(s1)){
                //说明该服务器保存的文件，在数据库中是用到了的。不能删除
                iterator.remove();
            }else {
                //说明该服务器保存的文件，在数据库中没有用到，需要删除
                notExistFileList.add(s);
            }
        }
        return notExistFileList;
    }

    public static List<String> findNotExistFile_byAbsolutePath(String rootIgnore,List<String> fileList, Set<String> dbFileList,String absolutePath) {
        if(CollectionUtils.isEmpty(dbFileList)){
            log.info("There are no files stored in the current data source");
            return fileList;
        }
        List<String> notExistFileList = new ArrayList<>(fileList.size());
        Iterator<String> iterator = fileList.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            String s1 = s.substring(rootIgnore.length());
            if(dbFileList.contains(absolutePath + s1)){
                //说明该服务器保存的文件，在数据库中是用到了的。不能删除
                iterator.remove();
            }else {
                //说明该服务器保存的文件，在数据库中没有用到，需要删除
                notExistFileList.add(s);
            }
        }
        return notExistFileList;
    }

    /**
     *@desc: 该方法有问题
     *@author: wenchao
     *@createTime: 2023/6/12/012 0:52
     *@param: [copyFileList, rootDirectory, rollbackPath]
     *@version: 1.0.0.v
     *@return: void
     **/
    public static Set<String> copyAndRemoveFile(List<String> copyFileList, String rootDirectory, String rollbackPath, FileRmLog fileRmLogs) throws JobExecutionException {
        if(CollectionUtils.isEmpty(copyFileList)){
            log.info("The set of files to be copied is empty");
            return null;
        }
        //先判断回滚的路径是否存在，没有的话创建该目录
        invokeMkdirs(rollbackPath);
        //拷贝失败的文件
        Set<String> copyFailFileList = new HashSet<>();
        long successCount = 0;
        long failCount = 0;
        for (String path : copyFileList) {
            try {
                String copyPath = path.replace(rootDirectory,rollbackPath);
                //1
                mkdirs(copyPath);
                //2
                String copyFail = copy_delteFile(path,copyPath);
                if(StringUtils.isNotBlank(copyFail)){
                    copyFailFileList.add(copyFail);
                    failCount ++ ;
                    continue;
                }
                successCount ++;
            }catch (Exception e){
                failCount ++ ;
                throw new JobExecutionException("File copy failed");
            }
        }
        fileRmLogs.setSuccessCount(successCount);
        fileRmLogs.setFailCount(failCount);
        log.info("All files have been copied. success:{},fail:{}",successCount,failCount);
        return copyFailFileList;
    }
    public static FileRmLog moveFile(List<String> copyFileList, String rootDirectory, String rollbackPath) throws JobExecutionException {
        FileRmLog fileRmLogs = new FileRmLog();
        if(CollectionUtils.isEmpty(copyFileList)){
            log.info("The set of files to be copied is empty");
            fileRmLogs.setTotalCount(Long.valueOf(copyFileList.size())).setSuccessCount(0L).setFailCount(0L);
            return fileRmLogs;
        }
        //先判断回滚的路径是否存在，没有的话创建该目录
        invokeMkdirs(rollbackPath);

        long successCount = 0;
        long failCount = 0;
        for (String path : copyFileList) {
            try {
                String copyPath = path.replace(rootDirectory,rollbackPath);
                //1 创建文件目录
                mkdirs(copyPath);
                //2 移动文件
                FileUtils.moveFile(new File(path),new File(copyPath), StandardCopyOption.REPLACE_EXISTING);
                successCount ++;
            }catch (Exception e){
                e.printStackTrace();
                failCount ++ ;
                throw new JobExecutionException("File copy failed");
            }
        }
        log.info("All files have been copied. success:{},fail:{}",successCount,failCount);
        fileRmLogs.setTotalCount(Long.valueOf(copyFileList.size())).setSuccessCount(successCount).setFailCount(failCount);
        return fileRmLogs;
    }

    public static void mkdirs(String copyPath){
        try{
            File file = new File(copyPath);
            String parent = file.getParent();
            String[] split = parent.split("\\\\");
            StringJoiner stringJoiner = new StringJoiner(File.separator);
            for (String s : split) {
                stringJoiner.add(s);
                invokeMkdirs(stringJoiner.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void invokeMkdirs(String path){
        File copyFile = new File(path);
        if (!copyFile.exists()){
            //如果不存在,创建目录
            copyFile.mkdirs();
//            System.out.println("创建了一个文件目录：" + path);
        }
    }
    public static void throwException()throws IOException{
        throw new IOException();
    }
    public static String copy_delteFile(String filePath,String copyPath){
        try{
            //拷贝文件
            FileUtils.copyFile(new File(filePath),new File(copyPath),true,StandardCopyOption.REPLACE_EXISTING);
            //删除文件
            new File(filePath).delete();
            log.debug("成功拷贝文件：{}并移除原文件：{}",copyPath, filePath);
        } catch (IOException e) {
//            e.printStackTrace();
            log.error("拷贝失败的文件路径：" + copyPath);
            return filePath;
        }
        return null;
    }

    public static FileRmLog removeFile(List<String> deleteFileList) {
        FileRmLog fileRmLogs = new FileRmLog();
        if(CollectionUtils.isEmpty(deleteFileList)){
            log.info("The set of files to be deleted is empty");
            fileRmLogs.setTotalCount(Long.valueOf(deleteFileList.size())).setSuccessCount(0L).setFailCount(0L);
            return fileRmLogs;
        }
        long successCount = 0;
        long failCount = 0;
        for (String path : deleteFileList) {
            try {
                new File(path).delete();
                successCount ++;
                log.info("成功移除文件："+ path);
            }catch (Exception e){
                failCount ++ ;
            }
        }
        log.info("All files have been deleted. success:{},fail:{}",successCount,failCount);
        fileRmLogs.setTotalCount(Long.valueOf(deleteFileList.size())).setSuccessCount(successCount).setFailCount(failCount);
        return fileRmLogs;
    }


}