package com.cvmaster.fileidlerm.service;

import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @ClassName:QuartzService
 * @Desc: TODO
 * @Author: wenchao
 * @CreateTime:2023/6/6/006 22:14
 * @Version:1.0
 **/
public interface QuartzService {

    void addJob(Class<? extends QuartzJobBean> fileIdleRemoveJobClass, String name, String group, String cron);

    void updateJob(String name, String group, String cron);

    void deleteJob(String name, String group);

    void pauseJob(String name, String group);

    void resumeJob(String name, String group);

    Object queryAllJob();

    Object queryRunJob();
}
