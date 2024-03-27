package com.cvmaster.fileidlerm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cvmaster.fileidlerm.entity.FileRmLogs;
import com.cvmaster.fileidlerm.entity.R;
import com.cvmaster.fileidlerm.service.FileRmLogsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @desc (FileRmLogs)表控制层
 * @author cvmaster
 * @version v.1.0.0
 * @create 2023-06-12 01:05:13
 */
@Slf4j
@RestController
@RequestMapping("fileRmLogs")
public class FileRmLogsController{
    /**
     * 服务对象
     */
    @Resource
    private FileRmLogsService fileRmLogsService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param fileRmLogs 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<FileRmLogs> page, FileRmLogs fileRmLogs) {
        return new R().ok(this.fileRmLogsService.page(page, new QueryWrapper<>(fileRmLogs)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public R selectOne(@PathVariable Serializable id) {
        return new R().ok(this.fileRmLogsService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param fileRmLogs 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody FileRmLogs fileRmLogs) {
        return new R().ok(this.fileRmLogsService.save(fileRmLogs));
    }

    /**
     * 修改数据
     *
     * @param fileRmLogs 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody FileRmLogs fileRmLogs) {
        return new R().ok(this.fileRmLogsService.updateById(fileRmLogs));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return new R().ok(this.fileRmLogsService.removeByIds(idList));
    }
}

