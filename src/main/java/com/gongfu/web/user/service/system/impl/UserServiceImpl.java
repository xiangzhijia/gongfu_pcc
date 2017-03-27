package com.gongfu.web.user.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gongfu.base.BaseServiceImpl;
import com.gongfu.config.code.StringCode;
import com.gongfu.mapper.user.UserMapper;
import com.gongfu.model.user.User;
import com.gongfu.web.user.service.message.MessageService;
import com.gongfu.web.user.service.system.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by feng on 2017/1/28.
 */
@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageService messageService;

    @Override
    public User login(String username) {
        return userMapper.login(username);
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
        log.info("Execute method asynchronously" + Thread.currentThread().getName());
        Future<String> future = messageService.openThreadOne();
        messageService.openThreadTwo();

        try {
            log.info("future: {}", future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return pageInfo;
    }

    @Override
    public Long countUserInfo(String beginDate, String endDate) {
        return userMapper.countUserInfo(beginDate + StringCode.BEGIN, endDate + StringCode.END);
    }
}
