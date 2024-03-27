package com.cvmaster.fileidlerm.entity;


import com.cvmaster.fileidlerm.service.FileRmLogService;
import lombok.Data;
import lombok.experimental.Accessors;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.config.EruptSmartSkipSerialize;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.Readonly;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.DateType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author cvmaster
 * @version v.1.0.0
 * @desc (FileRmLog)表实体类
 * @create 2023-06-12 01:05:13
 */
@Data
@Table(name="file_rm_log")
@Erupt(
        name = "RM日志",
        orderBy = "FileRmLog.createTime desc",
        dataProxy = {FileRmLogService.class}
)
@Entity
@Accessors(chain = true)
public class FileRmLog extends BaseModel {
//    @Id
//    @GeneratedValue(generator = "generator")
//    @GenericGenerator(name = "generator",strategy = "native")
//    @Column(name = "id")
//    @EruptField
//    private Long id;
    /**
     * 关联定时任务主键，file_job.id
     */
    @ManyToOne
    @EruptField(
            views = @View(title = "所属定时任务", column = "jobName")
    )
    private FileJob fkJob;
    /**
     * 任务执行时间
     */
    @EruptField(
            views = @View(title = "任务执行时间", sortable = true),
            edit = @Edit(title = "任务执行时间", readonly = @Readonly, search = @Search(vague = true),dateType = @DateType(type = DateType.Type.DATE_TIME))
    )
    @EruptSmartSkipSerialize
    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;
    /**
     * 任务总量
     */
    @EruptField(
            views = @View(title ="任务总量", sortable = true),
            edit = @Edit(title = "任务总量", readonly = @Readonly)
    )
    private Long totalCount;
    /**
     * 成功量
     */
    @EruptField(
            views = @View(title ="成功量", sortable = true),
            edit = @Edit(title = "成功量", readonly = @Readonly)
    )
    private Long successCount;
    /**
     * 失败量
     */
    @EruptField(
            views = @View(title ="失败量", sortable = true),
            edit = @Edit(title = "失败量", readonly = @Readonly)
    )
    private Long failCount;
    /**
     * 耗时（毫秒）
     */
    @EruptField(
            views = @View(title ="耗时（毫秒）", sortable = true),
            edit = @Edit(title = "耗时（毫秒）", readonly = @Readonly)
    )
//    @JsonSerialize(using = ToStringSerializer.class)
    private Long timeConsuming;
}
