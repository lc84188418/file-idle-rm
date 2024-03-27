package com.cvmaster.fileidlerm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cvmaster.fileidlerm.dao.FileJobDao;
import com.cvmaster.fileidlerm.entity.FileJob;
import com.cvmaster.fileidlerm.service.FileJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @desc (FileJob)表服务实现类
 * @author cvmaster
 * @version v.1.0.0
 * @create 2023-06-11 17:49:47
 */
@Service("fileJobService")
@Slf4j
public class FileJobServiceImpl extends ServiceImpl<FileJobDao, FileJob> implements FileJobService {

        @Resource
        private FileJobDao fileJobDao;
}

