package com.gongfu.web.user.controller;

import com.github.pagehelper.PageInfo;
import com.gongfu.base.BaseController;
import com.gongfu.base.RestModel;
import com.gongfu.config.interceptor.support.AuthPassport;
import com.gongfu.config.interceptor.support.enums.Role;
import com.gongfu.model.user.User;
import com.gongfu.util.ValidatorUtil;
import com.gongfu.web.user.controller.req.UserReq;
import com.gongfu.web.user.rpc.client.PccClient;
import com.gongfu.web.user.service.system.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


/**
 * Created by a on 2017/3/17.
 */
@RestController
@RequestMapping(path = BaseController.API_ADMIN + "/user", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
@Api(description = "用户管理")
public class UserController extends BaseController {
    @Autowired
    private PccClient pccClient;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增", notes = "用户新增接口")
    @RequestMapping(path = {"/add"}, method = POST)
    public RestModel saveUser(@ApiParam(value = "用户实体", required = true) @RequestBody @Valid UserReq userReq, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ValidatorUtil.handleBingResult(bindingResult);
        }
        User user = new User();
        user.setUserName(userReq.getName());
        user.setNick(userReq.getNick());
        user.setCreatedAt(new Date());
        user.sha256HexPassword(userReq.getPassword());
        user.setLastLoginIp(getRealIp(request));
        Integer result = userService.save(user);
        if (result == 0) {
            return RestModel.create().errorMsg(1007, "操作失败了");
        }
        return RestModel.create().succ();
    }

    @ApiOperation(value = "修改密码", notes = "根据用户名修改密码")
    @RequestMapping(value = "update/password", method = PUT)
    @AuthPassport
    public RestModel updatePassword(@ApiParam(value = "用户id", required = true, defaultValue = "") @RequestParam("id") Long id,
                                    @ApiParam(value = "密码", required = true, defaultValue = "") @RequestParam("password") String password) {
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        Integer result = userService.updateById(user);
        if (result == 0) {
            return RestModel.create().errorMsg(1007, "操作失败了");
        }
        return RestModel.create().succ();
    }

    @ApiOperation(value = "删除用户", notes = "根据用户名ID")
    @RequestMapping(value = "delete/user/{id}", method = DELETE)
    @AuthPassport(role = Role.ADMIN)
    public RestModel deleteUser(@PathVariable("id") Long id, HttpServletRequest request) {
        Integer result = userService.deleteById(id);
        if (result == 0) {
            return RestModel.create().errorMsg(1007, "操作失败了");
        }
        return RestModel.create().succ();
    }

    @ApiOperation(value = "查询用户", notes = "用户列表(普通分页)")
    @RequestMapping(value = "user/info", method = GET)
    @AuthPassport
    public RestModel getUserInfo(@ApiParam(value = "当前时间", required = true) @RequestParam(value = "beginDate") String beginDate,
                                 @ApiParam(value = "结束时间", required = true) @RequestParam("endDate") String endDate,
                                 @ApiParam(value = "分页大小", required = true, defaultValue = "10") @RequestParam("size") int size,
                                 @ApiParam(value = "第几页，从0开始", required = true, defaultValue = "0") @RequestParam("page") int page) throws Exception {
        List<User> list = userService.getUserInfo(beginDate, endDate, size, page);
        Long count = userService.countUserInfo(beginDate, endDate);
        return RestModel.create().body(list, count);
    }

    @ApiOperation(value = "查询用户", notes = "用户列表(PageHelper分页)")
    @RequestMapping(value = "user/page-helper", method = GET)
    @AuthPassport
    public RestModel getUserPageHelper(@ApiParam(value = "当前时间", required = true) @RequestParam(value = "beginDate") String beginDate,
                                       @ApiParam(value = "结束时间", required = true) @RequestParam("endDate") String endDate,
                                       @ApiParam(value = "分页大小", required = true, defaultValue = "10") @RequestParam("size") int size,
                                       @ApiParam(value = "第几页", required = true, defaultValue = "1") @RequestParam("page") int page) {
        PageInfo list = userService.getUserPageHelper(beginDate, endDate, size, page);
        return RestModel.create().body(list.getList(), list.getTotal());
    }


    @ApiOperation(value = "测试RPC", notes = "测试RPC")
    @RequestMapping(value = "rpc", method = GET)
    public RestModel test() {
        return RestModel.create().body(pccClient.test(1, "12qw你好"));
    }
}
