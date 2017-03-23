package com.gongfu.web.user.service.system;

import com.gongfu.base.BaseService;
import com.gongfu.model.user.User;

import java.util.List;

/**
 * Created by a on 2017/3/17.
 */
public interface UserService extends BaseService<User> {
    Integer save(User user);

    List<User> getUserInfo(String beginDate, String endDate, int size, int page);

    Long countUserInfo(String beginDate, String endDate);
}
