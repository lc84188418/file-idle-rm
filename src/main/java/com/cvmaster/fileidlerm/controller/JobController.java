package com.cvmaster.fileidlerm.controller;


import com.alibaba.fastjson.JSONObject;
import com.cvmaster.fileidlerm.entity.R;
import com.cvmaster.fileidlerm.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public R queryAllJob() {
        return JSONObject.parseObject(quartzService.queryAllJob().toString(),R.class);

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
    public R queryRunJob() {
        return JSONObject.parseObject(quartzService.queryRunJob().toString(),R.class);
    }
    @DeleteMapping("/job/{name}/{group}")
    public R deleteJob(@PathVariable("group")String group,@PathVariable("name")String name) {
        quartzService.deleteJob(name, group);
        return new R<>().ok();
    }
}
