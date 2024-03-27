package com.cvmaster.fileidlerm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cvmaster.fileidlerm.dao.FileRmIgnoreDirectoryDao;
import com.cvmaster.fileidlerm.entity.FileRmIgnoreDirectory;
import com.cvmaster.fileidlerm.service.FileRmIgnoreDirectoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @desc 文件目录忽略(FileRmIgnoreDirectory)表服务实现类
 * @author cvmaster
 * @version v.1.0.0
 * @create 2023-06-12 21:02:58
 */
@Service("fileRmIgnoreDirectoryService")
@Slf4j
public class FileRmIgnoreDirectoryServiceImpl extends ServiceImpl<FileRmIgnoreDirectoryDao, FileRmIgnoreDirectory> implements FileRmIgnoreDirectoryService {

@Autowired
private FileRmIgnoreDirectoryDao fileRmIgnoreDirectoryDao;
}

