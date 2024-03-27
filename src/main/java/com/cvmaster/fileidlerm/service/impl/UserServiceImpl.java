package com.cvmaster.fileidlerm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cvmaster.fileidlerm.dao.UserDao;
import com.cvmaster.fileidlerm.entity.User;
import com.cvmaster.fileidlerm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @desc (User)表服务实现类
 * @author cvmaster
 * @version v.1.0.0
 * @create 2023-06-12 23:09:32
 */
@Service("userService")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;
}

