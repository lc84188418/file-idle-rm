package com.cvmaster.fileidlerm.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cvmaster
 * @version v.1.0.0
 * @desc (FileRmLogs)表实体类
 * @create 2023-06-12 01:05:13
 */
@Data
@TableName("file_rm_logs")
@Accessors(chain = true)

public class FileRmLogs implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 关联定时任务主键，file_job.id
     */
    private Integer fkJob;
    /**
     * 任务执行时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 任务总量
     */
    private Integer totalCount;
    /**
     * 成功量
     */
    private Integer successCount;
    /**
     * 失败量
     */
    private Integer failCount;
    /**
     * 耗时（毫秒）
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long timeConsuming;
}
