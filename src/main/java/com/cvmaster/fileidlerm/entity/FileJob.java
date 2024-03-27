package com.cvmaster.fileidlerm.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author cvmaster
 * @version v.1.0.0
 * @desc (FileJob)表实体类
 * @create 2023-06-11 17:49:46
 */
@Data
@TableName("file_job")
@Accessors(chain = true)
public class FileJob extends Model<FileJob> implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 任务名称
     */
    @NotBlank(message = "cron表达式不能为空")
    private String jobName;
    /**
     * 任务分组
     */
    @NotBlank(message = "cron表达式不能为空")

    private String jobGroup;
    /**
     * cron表达式
     */
    @NotBlank(message = "cron表达式不能为空")

    private String jobCron;
    /**
     * 任务执行参数
     */
    private String parameter;
    /**
     * 任务描述
     */
    private String description;
    /**
     * 服务器文件根目录忽略
     */
    private String rootIgnore;
    /**
     * 服务器文件根目录
     */
    @NotBlank(message = "服务器根目录不能为空")
    private String rootDirectory;
    /**
     * 执行状态Y/N
     */
    private String status;
    /**
     * 回滚设置Y/N
     */
    @NotNull(message = "回滚设置未配置")
    private String isRollback;
    private String isAbsolutePath;
    private String absolutePath;
    /**
     * 回滚文件路径
     */
    private String rollbackPath;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
