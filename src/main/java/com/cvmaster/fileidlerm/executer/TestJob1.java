package com.cvmaster.fileidlerm.executer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @ClassName:TestJob1
 * @Desc: TODO
 * @Author: wenchao
 * @CreateTime:2023/6/6/006 22:16
 * @Version:1.0
 **/
public class TestJob1 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {

        System.out.println(new Date() + "    job执行");
    }

}