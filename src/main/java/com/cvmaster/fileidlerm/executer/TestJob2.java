package com.cvmaster.fileidlerm.executer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName:TestJob2
 * @Desc: TODO
 * @Author: wenchao
 * @CreateTime:2023/6/6/006 22:17
 * @Version:1.0
 **/
@Component
public class TestJob2 extends QuartzJobBean {

    //注入业务service，完成定时任务逻辑

    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println(new Date() + "    job2执行");
    }

}
