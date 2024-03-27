package com.cvmaster.fileidlerm.service;

import com.cvmaster.fileidlerm.entity.FileRmTable;
import com.cvmaster.fileidlerm.utils.StringUtils;
import org.springframework.stereotype.Service;
import xyz.erupt.annotation.fun.DataProxy;
import xyz.erupt.jpa.dao.EruptDao;

import javax.annotation.Resource;

/**
 * @ClassName:FileRmTableService
 * @Desc:TODO
 * @Author: wenchao
 * @CreateTime:2024/3/5 15:43
 * @Version:1.0
 **/
@Service
public class FileRmTableService implements DataProxy<FileRmTable> {
    @Resource
    private EruptDao eruptDao;
    @Override
    public void beforeAdd(FileRmTable fileRmTable) {
        if (fileRmTable.getIsAbsolutePath() && StringUtils.isBlank(fileRmTable.getAbsolutePath())) {
            //数据库文件存储是绝对路径必须设置绝对路径
            throw new RuntimeException("文件存储是绝对路径方式必须设置绝对路径!");
        }
        //同数据源，同表，同字段，只能有一条数据
        String sql = "SELECT COUNT(*) FROM file_rm_table where rm_table = '"
                +fileRmTable.getRmTable()+"' and rm_table_column='" +fileRmTable.getRmTableColumn()+"' and fk_datasource_id = "+fileRmTable.getFkDatasource().getId();
        Integer count = eruptDao.getJdbcTemplate().queryForObject(sql,Integer.class);
        System.out.println(count);
        if(count > 0){
            throw new RuntimeException("当前数据源已存在该表中字段!");
        }
    }
}
