package com.cvmaster.fileidlerm.entity;

import lombok.Data;

import java.util.Set;

/**
 * @ClassName:RmTableData
 * @Desc:TODO
 * @Author: wenchao
 * @CreateTime:2024/3/5 23:39
 * @Version:1.0
 **/
@Data
public class RmTableData {
    private Boolean isAbsolutePath;
    private String absolutePath;
    private Set<String> dataList;

}
