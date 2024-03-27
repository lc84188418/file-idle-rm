package com.cvmaster.fileidlerm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cvmaster.fileidlerm.entity.FileRmDatasource;
import com.cvmaster.fileidlerm.entity.R;
import com.cvmaster.fileidlerm.service.FileRmDatasourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @desc (FileRmDatasource)表控制层
 * @author cvmaster
 * @version v.1.0.0
 * @create 2023-06-12 01:05:13
 */
@Slf4j
@RestController
@RequestMapping("fileRmDatasource")
public class FileRmDatasourceController{
    /**
     * 服务对象
     */
    @Resource
    private FileRmDatasourceService fileRmDatasourceService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param fileRmDatasource 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<FileRmDatasource> page, FileRmDatasource fileRmDatasource) {
        return new R().ok(this.fileRmDatasourceService.page(page, new QueryWrapper<>(fileRmDatasource)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public R selectOne(@PathVariable Serializable id) {
        return new R().ok(this.fileRmDatasourceService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param fileRmDatasource 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody FileRmDatasource fileRmDatasource) {
        return new R().ok(this.fileRmDatasourceService.save(fileRmDatasource));
    }

    /**
     * 修改数据
     *
     * @param fileRmDatasource 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody FileRmDatasource fileRmDatasource) {
        return new R().ok(this.fileRmDatasourceService.updateById(fileRmDatasource));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return new R().ok(this.fileRmDatasourceService.removeByIds(idList));
    }
}

