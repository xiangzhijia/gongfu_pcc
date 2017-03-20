package com.gongfu.web.user.controller.req;

import com.gongfu.config.code.RegexpCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

/**
 * Created by a on 2017/3/17.
 */
@Data
public class UserReq {
    @ApiModelProperty(value = "用户名")
    @NotNull(message = "姓名不能为空", groups = {Default.class})
    private String name;
    @ApiModelProperty(value = "时间")
    @Pattern(regexp = RegexpCode.DATE, message = "时间格式不正确", groups = {Default.class})
    private String createdAt;
    @ApiModelProperty(value = "昵称")
    private String nick;
    @ApiModelProperty(value = "密码")
    private String password;
    @NotNull(message = "邮箱不能为空", groups = {Default.class})
    @Pattern(regexp = RegexpCode.EMAIL, message = "邮箱格式不正确", groups = {Default.class})
    @ApiModelProperty(value = "邮箱")
    private String email;
}
