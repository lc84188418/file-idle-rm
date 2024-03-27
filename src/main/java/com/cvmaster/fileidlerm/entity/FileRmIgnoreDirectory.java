package com.cvmaster.fileidlerm.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cvmaster
 * @version v.1.0.0
 * @desc 文件目录忽略(FileRmIgnoreDirectory)表实体类
 * @create 2023-06-12 21:02:58
 */
@Data
@TableName("file_rm_ignore_directory")
public class FileRmIgnoreDirectory implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 定时任务,file_job.id
     */
    private String fkJob;
    /**
     * 忽略的文件目录
     */
    private String ignoreDirectory;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
