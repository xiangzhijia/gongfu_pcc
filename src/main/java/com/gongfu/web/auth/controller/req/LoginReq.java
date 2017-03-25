package com.gongfu.web.auth.controller.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

/**
 * 向志佳
 * 登录实体
 */
@Data
public class LoginReq {
    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不能为空", groups = {Default.class})
    private String username;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空", groups = {Default.class})
    private String password;
}
