package com.cvmaster.fileidlerm.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

/**
 * @author cvmaster
 * @version v.1.0.0
 * @desc (FileRmDatasource)表实体类
 * @create 2023-06-12 01:05:13
 */
@Data
@TableName("file_rm_datasource")
@Accessors(chain = true)

public class FileRmDatasource implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 定时任务,file_job.id
     */
    private Integer fkJob;
    /**
     * 数据源：用户名
     */
    private String username;
    /**
     * 数据源：密码
     */
    private String password;
    /**
     * 数据源：url
     */
    private String url;
    /**
     * 数据源：类型
     */
    private String type;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
