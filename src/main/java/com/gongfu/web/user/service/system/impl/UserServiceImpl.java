package com.gongfu.web.user.service.system.impl;

import com.gongfu.base.BaseServiceImpl;
import com.gongfu.mapper.user.UserMapper;
import com.gongfu.model.user.User;
import com.gongfu.web.user.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by feng on 2017/1/28.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;
}
