package com.gongfu.web.user.controller;

import com.gongfu.base.BaseController;
import com.gongfu.base.RestModel;
import com.gongfu.model.user.User;
import com.gongfu.util.ValidatorUtil;
import com.gongfu.web.user.controller.req.UserReq;
import com.gongfu.web.user.service.system.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * Created by a on 2017/3/17.
 */
@RestController
@RequestMapping(path = BaseController.API_ADMIN + "/user", produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = "用户操作")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增", notes = "用户新增接口")
    @RequestMapping(path = {"/add"}, method = POST)
    public RestModel saveUser(@ApiParam(value = "用户实体", required = true) @RequestBody @Valid UserReq userReq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidatorUtil.handleBingResult(bindingResult);
        }
        User user = new User();
        user.setName(userReq.getName());
        user.setNick(userReq.getNick());
        user.setCreatedAt(new Date());
        user.setPassword(userReq.getPassword());
        Integer result = 0;
        if (result == 0) {
            return RestModel.create().errorMsg(1007, "操作失败了");
            //throw new ValidationException("添加失败!");
        }
        return RestModel.create().succ();
    }
}
