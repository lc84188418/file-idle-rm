package com.cvmaster.fileidlerm.entity;


import com.cvmaster.fileidlerm.service.FileRmDatasourceService;
import lombok.Data;
import lombok.experimental.Accessors;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ReferenceTableType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.MetaModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author cvmaster
 * @version v.1.0.0
 * @desc (FileRmDatasource)表实体类
 * @create 2023-06-12 01:05:13
 */
@Data
@Table(name="file_rm_datasource")
@Erupt(
        name = "数据源配置",
        dataProxy = {FileRmDatasourceService.class}
)
@Entity
@Accessors(chain = true)
public class FileRmDatasource extends MetaModel {
    @ManyToOne
    @EruptField(
            views = @View(title = "所属定时任务", column = "jobName"),
            edit = @Edit(title = "所属定时任务",
                    type = EditType.REFERENCE_TABLE,
                    referenceTableType = @ReferenceTableType(id = "id", label = "jobName"),
                    search = @Search)
    )
    private FileJob fkJob;
    /**
     * 数据源：用户名
     */
    @EruptField(
            views = @View(title ="数据源：用户名"),
            edit = @Edit(title = "数据源：用户名",notNull = true)
    )
    private String username;
    /**
     * 数据源：密码
     */
    @EruptField(
            views = @View(title ="数据源：密码"),
            edit = @Edit(title = "数据源：密码",notNull = true)
    )
    private String password;
    /**
     * 数据源：url
     */
    @EruptField(
            views = @View(title ="数据源：url"),
            edit = @Edit(title = "数据源：url",notNull = true)
    )
    private String url;
    /**
     * 数据源：类型
     */
    @EruptField(
            views = @View(title ="数据源：类型"),
            edit = @Edit(title = "数据源：类型",notNull = true)
    )
    private String type;
}
