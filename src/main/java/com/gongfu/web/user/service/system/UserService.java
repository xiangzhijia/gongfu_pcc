package com.gongfu.web.user.service.system;

import com.github.pagehelper.PageInfo;
import com.gongfu.base.BaseService;
import com.gongfu.model.user.User;
import com.gongfu.web.auth.controller.req.LoginReq;

import java.util.List;

/**
 * Created by a on 2017/3/17.
 */
public interface UserService extends BaseService<User> {

    User login(LoginReq loginReq);

    Integer save(User user);

    List<User> getUserInfo(String beginDate, String endDate, int size, int page);

    PageInfo getUserPageHelper(String beginDate, String endDate, int size, int page);

    Long countUserInfo(String beginDate, String endDate);
}
