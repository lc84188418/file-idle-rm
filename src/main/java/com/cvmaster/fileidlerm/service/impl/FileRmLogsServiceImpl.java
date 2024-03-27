package com.cvmaster.fileidlerm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cvmaster.fileidlerm.dao.FileRmLogsDao;
import com.cvmaster.fileidlerm.entity.FileRmLogs;
import com.cvmaster.fileidlerm.service.FileRmLogsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @desc (FileRmLogs)表服务实现类
 * @author cvmaster
 * @version v.1.0.0
 * @create 2023-06-12 01:05:13
 */
@Service("fileRmLogsService")
@Slf4j
public class FileRmLogsServiceImpl extends ServiceImpl<FileRmLogsDao, FileRmLogs> implements FileRmLogsService {

@Autowired
private FileRmLogsDao fileRmLogsDao;
}

