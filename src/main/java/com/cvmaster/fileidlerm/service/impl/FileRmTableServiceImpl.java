package com.cvmaster.fileidlerm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cvmaster.fileidlerm.dao.FileRmTableDao;
import com.cvmaster.fileidlerm.entity.FileRmTable;
import com.cvmaster.fileidlerm.service.FileRmTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @desc (FileRmTable)表服务实现类
 * @author cvmaster
 * @version v.1.0.0
 * @create 2023-06-12 01:05:13
 */
@Service("fileRmTableService")
@Slf4j
public class FileRmTableServiceImpl extends ServiceImpl<FileRmTableDao, FileRmTable> implements FileRmTableService {

@Autowired
private FileRmTableDao fileRmTableDao;
}

