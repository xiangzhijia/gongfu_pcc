package com.gongfu.config.interceptor.support;

import com.gongfu.config.interceptor.support.enums.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 2017年1月9日
 *
 * @向治家
 **/
@Data
public class ClientInfo {

    private String token;
    
    @ApiModelProperty("权限角色")
    private Role role;

    @ApiModelProperty("版本号")
    private String version;

    @ApiModelProperty("设备号")
    private String deviceId;

    @ApiModelProperty("登陆来源ip，app层计算")
    private String ip;
}
