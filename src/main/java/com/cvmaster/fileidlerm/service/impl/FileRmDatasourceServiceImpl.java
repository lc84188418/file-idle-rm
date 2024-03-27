package com.cvmaster.fileidlerm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cvmaster.fileidlerm.dao.FileRmDatasourceDao;
import com.cvmaster.fileidlerm.entity.FileRmDatasource;
import com.cvmaster.fileidlerm.service.FileRmDatasourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @desc (FileRmDatasource)表服务实现类
 * @author cvmaster
 * @version v.1.0.0
 * @create 2023-06-12 01:05:13
 */
@Service("fileRmDatasourceService")
@Slf4j
public class FileRmDatasourceServiceImpl extends ServiceImpl<FileRmDatasourceDao, FileRmDatasource> implements FileRmDatasourceService {

@Autowired
private FileRmDatasourceDao fileRmDatasourceDao;
}

