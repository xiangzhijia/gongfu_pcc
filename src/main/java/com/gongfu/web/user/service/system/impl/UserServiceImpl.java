package com.gongfu.web.user.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gongfu.base.BaseServiceImpl;
import com.gongfu.config.code.StringCode;
import com.gongfu.mapper.user.UserMapper;
import com.gongfu.model.user.User;
import com.gongfu.web.auth.controller.req.LoginReq;
import com.gongfu.web.user.service.system.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by feng on 2017/1/28.
 */
@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(LoginReq loginReq) {
        return userMapper.login(loginReq);
    }

    @Override
    public List<User> getUserInfo(String beginDate, String endDate, int size, int page) {
        return userMapper.getUserInfo(beginDate + StringCode.BEGIN, endDate + StringCode.END, page * size, size);
    }

    @Override
    public PageInfo getUserPageHelper(String beginDate, String endDate, int size, int page) {
        PageHelper.startPage(page, size);
        List<User> users = userMapper.getUserPageHelper(beginDate + StringCode.BEGIN, endDate + StringCode.END);
        PageInfo pageInfo = new PageInfo(users);
        log.info("Execute method asynchronously.1" + Thread.currentThread().getName());
        toThread();
        return pageInfo;
    }

    @Override
    public Long countUserInfo(String beginDate, String endDate) {
        return userMapper.countUserInfo(beginDate + StringCode.BEGIN, endDate + StringCode.END);
    }

    @Async
    private void toThread() {
        log.info("Execute method asynchronously.2" + Thread.currentThread().getName());
    }
}
