package com.gongfu.mapper.user;


import com.gongfu.base.PccBaseMapper;
import com.gongfu.model.user.User;
import com.gongfu.web.auth.controller.req.LoginReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by feng on 2017/1/23.
 */
public interface UserMapper extends PccBaseMapper<User> {

    List<User> getUserInfo(@Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("offset") int offset, @Param("limit") int limit);

    List<User> getUserPageHelper(@Param("beginDate") String beginDate, @Param("endDate") String endDate);

    Long countUserInfo(@Param("beginDate") String beginDate, @Param("endDate") String endDate);

    User login(@Param("loginReq") LoginReq loginReq);
}
