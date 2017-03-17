package com.gongfu.config.interceptor.support;

import com.gongfu.config.interceptor.support.enums.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 2017年1月9日
 *
 * @向治家 yhqb AuthInfo.java
 **/
@Data
public class AuthInfo {

    @ApiModelProperty("uid")
    private String uid;

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("clientInfo")
    private ClientInfo clientInfo;

    public boolean isAdivser() {
        return clientInfo.getRole() == Role.ADVISER;
    }

    public boolean isUser() {
        return clientInfo.getRole() == Role.USER;
    }

    @Data
    public static class ClientInfo {

        @ApiModelProperty("权限角色")
        private Role role;

        @ApiModelProperty("版本号")
        private String version;

        @ApiModelProperty("设备号")
        private String deviceId;

        @ApiModelProperty("登陆来源ip，app层计算")
        private String ip;
    }
}
