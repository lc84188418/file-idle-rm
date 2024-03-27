package com.cvmaster.fileidlerm.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cvmaster.fileidlerm.entity.FileJob;
import com.cvmaster.fileidlerm.entity.R;
import com.cvmaster.fileidlerm.enums.StatusEnum;
import com.cvmaster.fileidlerm.executer.FileIdleRemoveJob;
import com.cvmaster.fileidlerm.service.FileJobService;
import com.cvmaster.fileidlerm.service.QuartzService;
import com.cvmaster.fileidlerm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName:JobController
 * @Desc: TODO
 * @Author: wenchao
 * @CreateTime:2023/6/6/006 21:02
 * @Version:1.0
 **/
@RestController
public class JobController {
    @Autowired
    private QuartzService quartzService;
    @Autowired
    private FileJobService fileJobService;
    /**
    *@desc: 添加定时任务
    *@author: wenchao
    *@createTime: 2023/6/11/011 15:46
    *@param: [jobEntity]
    *@version: 1.0.0.v
    *@return: void
    **/
    @PostMapping("/job")
    public R addJob(@Valid @RequestBody FileJob fileJob) {
        //添加默认值
        fileJob.setStatus(StatusEnum.ok.status());
        if(StringUtils.isBlank(fileJob.getIsRollback())){
            fileJob.setIsRollback(StatusEnum.ok.status());
        }
        fileJobService.save(fileJob);
        quartzService.addJob(FileIdleRemoveJob.class, fileJob.getJobName(), fileJob.getJobGroup(), fileJob.getJobCron());
        return new R().ok();
    }

    /**
    *@desc: 修改定时任务
    *@author: wenchao
    *@createTime: 2023/6/11/011 15:46
    *@param: [jobEntity]
    *@version: 1.0.0.v
    *@return: void
    **/
    @PutMapping("/job")
    public R updatejob(@Valid @RequestBody FileJob fileJob) {
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("job_group",fileJob.getJobGroup());
        updateWrapper.eq("job_name",fileJob.getJobName());
        fileJobService.update(fileJob,updateWrapper);
        quartzService.updateJob(fileJob.getJobName(), fileJob.getJobGroup(), fileJob.getJobCron());
        return new R().ok();
    }

    /**
    *@desc: 删除定时任务
    *@author: wenchao
    *@createTime: 2023/6/11/011 15:46
    *@param: [group, name]
    *@version: 1.0.0.v
    *@return: void
    **/
    @DeleteMapping("/job/{group}/{name}")
    public R deletejob(@PathVariable("group")String group,@PathVariable("name")String name) {
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", StatusEnum.error.status());
        updateWrapper.eq("job_group",group);
        updateWrapper.eq("job_name",name);
        fileJobService.update(updateWrapper);
        quartzService.deleteJob(name, group);
        return new R().ok();
    }

    /**
    *@desc: 暂停定时任务
    *@author: wenchao
    *@createTime: 2023/6/11/011 15:45
    *@param: []
    *@version: 1.0.0.v
    *@return: void
    **/
    @PutMapping("/job/pause/{group}/{name}")
    public R pauseJob(@PathVariable("group")String group,@PathVariable("name")String name) {
        quartzService.pauseJob(name, group);
        return new R().ok();
    }


    /**
    *@desc: 重启定时任务
    *@author: wenchao
    *@createTime: 2023/6/11/011 15:45
    *@param: []
    *@version: 1.0.0.v
    *@return: void
    **/
    @PutMapping("/job/resume/{group}/{name}")
    public R resumeJob(@PathVariable("group")String group,@PathVariable("name")String name) {
        quartzService.resumeJob(name, group);
        return new R().ok();
    }

    /**
    *@desc: 查询所有的定时任务
    *@author: wenchao
    *@createTime: 2023/6/11/011 15:45
    *@param: []
    *@version: 1.0.0.v
    *@return: java.lang.Object
    **/
    @GetMapping("/queryAllJob")
    public Object queryAllJob() {
        return quartzService.queryAllJob();
    }


    /**
    *@desc: 查询正在运行的定时任务
    *@author: wenchao
    *@createTime: 2023/6/11/011 15:45
    *@param: []
    *@version: 1.0.0.v
    *@return: java.lang.Object
    **/
    @GetMapping("/queryRunJob")
    public Object queryRunJob() {
        return quartzService.queryRunJob();
    }
}
