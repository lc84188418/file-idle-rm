package com.cvmaster.fileidlerm.entity;


import com.cvmaster.fileidlerm.service.FileRmIgnoreDirectoryService;
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
 * @desc 文件目录忽略(FileRmIgnoreDirectory)表实体类
 * @create 2023-06-12 21:02:58
 */
@Data
@Table(name="file_rm_ignore_directory")
@Erupt(
        name = "文件目录忽略",
        dataProxy = {FileRmIgnoreDirectoryService.class}
)
@Entity
@Accessors(chain = true)
public class FileRmIgnoreDirectory extends MetaModel {
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
     * 忽略的文件目录
     */
    @EruptField(
            views = @View(title ="忽略的文件目录"),
            edit = @Edit(title = "忽略的文件目录",notNull = true)
    )
    private String ignoreDirectory;
}
