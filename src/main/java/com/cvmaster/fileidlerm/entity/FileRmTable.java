package com.cvmaster.fileidlerm.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cvmaster.fileidlerm.service.FileRmTableService;
import lombok.Data;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.BoolType;
import xyz.erupt.annotation.sub_field.sub_edit.ReferenceTableType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author cvmaster
 * @version v.1.0.0
 * @desc (FileRmTable)表实体类
 * @create 2023-06-12 01:05:13
 */
@Erupt(
        name = "RM表配置",
        orderBy = "FileRmTable.rmTable desc",
        dataProxy = {FileRmTableService.class}
)
@Table(name="file_rm_table")
@Entity
@Data
public class FileRmTable extends BaseModel {
    @TableId(type = IdType.AUTO)
    @EruptField(
            views = @View(title ="表名"),
            edit =@Edit(title = "表名",notNull = true,search = @Search(vague = true))
    )
    private String rmTable;
    @EruptField(
            views = @View(title ="字段名"),
            edit =@Edit(title = "字段名",notNull = true,search = @Search(vague = true))
    )
    private String rmTableColumn;
    @EruptField(
            views = {@View(
                    title = "文件是否绝对路径Y/N"
            )},
            edit = @Edit(
                    title = "是否绝对路径Y/N",
                    desc = "数据库存储文件地址是否为绝对路径，即可直接通过该URL访问到文件的地址",
                    type = EditType.BOOLEAN,
                    notNull = true,
                    boolType = @BoolType(
                            trueText = "是",
                            falseText = "否"
                    )
            )
    )
    private Boolean isAbsolutePath;
    @EruptField(
            views = @View(title ="绝对路径"),
            edit =@Edit(title = "绝对路径")
    )
    private String absolutePath;
    @ManyToOne
    @EruptField(
            views = @View(title = "关联数据源", column = "fkJob.jobName"),
            edit = @Edit(title = "关联数据源",
                    type = EditType.REFERENCE_TABLE,
                    notNull = true,
                    referenceTableType = @ReferenceTableType(id = "id", label = "fkJob.jobName"),
                    search = @Search)
    )
    private FileRmDatasource fkDatasource;
}
