package com.cvmaster.fileidlerm.entity;


import com.cvmaster.fileidlerm.service.FileJobService;
import lombok.Data;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.BoolType;
import xyz.erupt.annotation.sub_field.sub_edit.InputType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.MetaModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author cvmaster
 * @version v.1.0.0
 * @desc (FileJob)表实体类
 * @create 2023-06-11 17:49:46
 */
@Erupt(
        name = "定时任务",
        orderBy = "FileJob.status desc",
        dataProxy = {FileJobService.class}
)
@Table(name="file_job")
@Entity
@Data
public class FileJob extends MetaModel {
    /**
     * 任务名称
     */
    @Column(unique = true,name = "job_name")
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
            edit =@Edit(title = "文件根目录忽略",desc = "文件访问地址url中需要忽略的目录段，如：E:\\\\test")
    )
    private String rootIgnore;
    /**
     * 服务器文件根目录
     */
    @EruptField(
            views = @View(title ="文件根目录"),
            edit =@Edit(title = "文件根目录",notNull = true,desc = "服务器存储文件的根路径，如：E:\\\\test\\\\file_idle_rm")
    )
    private String rootDirectory;
    /**
     * 执行状态Y/N
     */
    @EruptField(
            views = @View(title ="执行状态Y/N"),
            edit =@Edit(title = "执行状态Y/N",inputType = @InputType(regex="[Y,N]"),desc = "Y运行中，N关闭定时任务")
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
                    desc = "启用该设置，可在空闲文件移除后进行恢复，初步使用建议开启！",
                    type = EditType.BOOLEAN,
                    notNull = true,
                    boolType = @BoolType(
                            trueText = "启用",
                            falseText = "禁用"
                    )
            )
    )
    private Boolean isRollback;
    /**
     * 回滚文件路径
     */
    @EruptField(
            views = @View(title ="回滚文件路径"),
            edit =@Edit(title = "回滚文件路径",desc = "若启用了回滚设置，则可以配置 文件服务器中用来备份的目录，默认为：文件目录名+‘_file_idle_rm’")
    )
    private String rollbackPath;

}
