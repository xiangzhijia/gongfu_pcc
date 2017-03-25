package com.gongfu.web.auth.controller.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 向志佳
 * 登录返回实体
 */
@Data
public class LoginRes {
    @ApiModelProperty(value = "token")
    private String accessToken;
}
