package com.gongfu.web.user.service.system.impl;

import com.gongfu.base.BaseServiceImpl;
import com.gongfu.config.code.StringCode;
import com.gongfu.mapper.user.UserMapper;
import com.gongfu.model.user.User;
import com.gongfu.web.user.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by feng on 2017/1/28.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserInfo(String beginDate, String endDate, int size, int page) {
        return userMapper.getUserInfo(beginDate + StringCode.BEGIN, endDate + StringCode.END, page * size, size);
    }

    @Override
    public Long countUserInfo(String beginDate, String endDate) {
        return userMapper.countUserInfo(beginDate + StringCode.BEGIN, endDate + StringCode.END);
    }
}
