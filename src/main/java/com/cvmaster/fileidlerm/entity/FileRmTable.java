package com.cvmaster.fileidlerm.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author cvmaster
 * @version v.1.0.0
 * @desc (FileRmTable)表实体类
 * @create 2023-06-12 01:05:13
 */
@Data
@TableName("file_rm_table")
@Accessors(chain = true)

public class FileRmTable implements Serializable {
    @TableId(type = IdType.AUTO)

    private Integer id;
    private String rmTable;
    private String rmTableColumn;
    /**
     * 关联的数据源，file_rm_datasource
     */
    private Integer fkDatasource;
}
