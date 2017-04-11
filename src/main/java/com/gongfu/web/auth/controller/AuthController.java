package com.gongfu.web.auth.controller;


import com.gongfu.base.BaseController;
import com.gongfu.base.RestModel;
import com.gongfu.config.interceptor.support.AuthPassport;
import com.gongfu.model.user.User;
import com.gongfu.util.CryptUtils;
import com.gongfu.util.ValidatorUtil;
import com.gongfu.web.auth.controller.req.LoginReq;
import com.gongfu.web.user.service.system.UserService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 2017年1月9日
 *
 * @向治家
 **/
@RestController
@Slf4j
@RequestMapping(path = BaseController.API_ADMIN + "/auth", produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(description = "授权管理")
public class AuthController extends BaseController {
    @Autowired
    private Producer captchaProducer;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "验证码", notes = "刷新验证码")
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // create the text for the image
        String capText = captchaProducer.createText();
        // store the text in the session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        // ServletOutputStream out = repository.getOutputStream();
        // write the data out
        ImageIO.write(bi, "jpg", response.getOutputStream());
    }

    @ApiOperation(value = "登录", notes = "用户登录接口")
    @RequestMapping(path = {"/login"}, method = POST)
    public RestModel login(@ApiParam(value = "登录实体", required = true) @RequestBody @Valid LoginReq loginReq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidatorUtil.handleBingResult(bindingResult);
        }
        User user = userService.login(loginReq.getUsername());
        if (user == null || !user.validPwd(loginReq.getPassword())) {
            return RestModel.create().errorMsg("登录失败,用户名或密码错误");
        }
        //保存登录会话缓存中
        String newToken = CryptUtils.generateToken();
        log.info("newToken : {}",newToken);
        saveSessionRedis(user,newToken);
        return RestModel.create().succ();
    }

    @ApiOperation(value = "登出注销", notes = "平台注销")
    @RequestMapping(path = {"/logout"}, method = POST)
    public RestModel logout(@RequestParam("token") String token) {
        User user=getSessionRedis(token);
        log.info("newToken : {}",user);
        return RestModel.create().body(user);
    }
}
