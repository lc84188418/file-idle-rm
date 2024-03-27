package com.cvmaster.fileidlerm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cvmaster.fileidlerm.entity.FileRmIgnoreDirectory;
import com.cvmaster.fileidlerm.entity.R;
import com.cvmaster.fileidlerm.service.FileRmIgnoreDirectoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @desc 文件目录忽略(FileRmIgnoreDirectory)表控制层
 * @author cvmaster
 * @version v.1.0.0
 * @create 2023-06-12 21:02:58
 */
@Slf4j
@RestController
@RequestMapping("fileRmIgnoreDirectory")
public class FileRmIgnoreDirectoryController{
    /**
     * 服务对象
     */
    @Resource
    private FileRmIgnoreDirectoryService fileRmIgnoreDirectoryService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param fileRmIgnoreDirectory 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<FileRmIgnoreDirectory> page, FileRmIgnoreDirectory fileRmIgnoreDirectory) {
        return new R().ok(this.fileRmIgnoreDirectoryService.page(page, new QueryWrapper<>(fileRmIgnoreDirectory)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public R selectOne(@PathVariable Serializable id) {
        return new R().ok(this.fileRmIgnoreDirectoryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param fileRmIgnoreDirectory 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody FileRmIgnoreDirectory fileRmIgnoreDirectory) {
        return new R().ok(this.fileRmIgnoreDirectoryService.save(fileRmIgnoreDirectory));
    }

    /**
     * 修改数据
     *
     * @param fileRmIgnoreDirectory 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody FileRmIgnoreDirectory fileRmIgnoreDirectory) {
        return new R().ok(this.fileRmIgnoreDirectoryService.updateById(fileRmIgnoreDirectory));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return new R().ok(this.fileRmIgnoreDirectoryService.removeByIds(idList));
    }
}

