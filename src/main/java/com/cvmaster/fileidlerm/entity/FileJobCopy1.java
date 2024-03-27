package com.cvmaster.fileidlerm.entity;


import org.hibernate.annotations.GenericGenerator;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.BoolType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author cvmaster
 * @version v.1.0.0
 * @desc (FileJob)表实体类
 * @create 2023-06-11 17:49:46
 */
@Erupt(name="定时任务")
@Table(name="file_job")
@Entity
public class FileJobCopy1 implements Serializable {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator",strategy = "native")
    @Column(name = "id")
    @EruptField
    private Integer id;
    /**
     * 任务名称
     */
    @Column(unique = true)
    @EruptField(
            views = @View(title ="任务名称"),
            edit =@Edit(title = "任务名称",notNull = true,search = @Search(vague = true))
    )
    private String jobName;
    /**
     * 任务分组
     */
    @EruptField(
            views = @View(title ="任务分组"),
            edit =@Edit(title = "任务分组",notNull = true, search = @Search(vague = true))
    )
    private String jobGroup;
    /**
     * cron表达式
     */
    @EruptField(
            views = @View(title ="cron表达式"),
            edit =@Edit(title = "cron表达式",notNull = true, search = @Search(vague = true))
    )
    private String jobCron;
    /**
     * 任务执行参数
     */
    @EruptField(
            views = @View(title ="任务执行参数"),
            edit =@Edit(title = "任务执行参数")
    )
    private String parameter;
    /**
     * 任务描述
     */
    @EruptField(
            views = @View(title ="任务描述"),
            edit =@Edit(title = "任务描述")
    )
    private String description;
    /**
     * 服务器文件根目录忽略
     */
    @EruptField(
            views = @View(title ="文件根目录忽略"),
            edit =@Edit(title = "文件根目录忽略")
    )
    private String rootIgnore;
    /**
     * 服务器文件根目录
     */
    @EruptField(
            views = @View(title ="文件根目录"),
            edit =@Edit(title = "文件根目录",notNull = true)
    )
    private String rootDirectory;
    /**
     * 执行状态Y/N
     */
    @EruptField(
            views = @View(title ="执行状态Y/N"),
            edit =@Edit(title = "执行状态Y/N")
    )
    private String status;
    /**
     * 回滚设置Y/N
     */
    @EruptField(
            views = {@View(
                    title = "回滚设置"
            )},
            edit = @Edit(
                    title = "回滚设置",
                    type = EditType.BOOLEAN,
                    notNull = true,
                    boolType = @BoolType(
                            trueText = "启用",
                            falseText = "禁用"
                    )
            )
    )
    private Boolean isRollback;
    private String isAbsolutePath;
    private String absolutePath;
    /**
     * 回滚文件路径
     */
    @EruptField(
            views = @View(title ="回滚文件路径"),
            edit =@Edit(title = "回滚文件路径")
    )
    private String rollbackPath;
    private Date createTime;
    private Date updateTime;
}
