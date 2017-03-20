package com.gongfu.mapper.user;


import com.gongfu.base.PccBaseMapper;
import com.gongfu.model.user.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by feng on 2017/1/23.
 */
public interface UserMapper extends PccBaseMapper<User> {

    void updatePassword(@Param("user") User user);

    void updateStatus(@Param("staffId") Long id, @Param("status") String status);

}
