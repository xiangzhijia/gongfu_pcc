package com.gongfu.web.auth.controller;


import com.gongfu.config.interceptor.support.AuthFree;
import com.gongfu.web.BaseController;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 2017年1月9日
 *
 * @向治家 yhqb
 * AuthRest.java
 **/
@RestController
@RequestMapping(path = BaseController.API_ADMIN + "/auth", produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = "授权认证")
public class AuthController extends BaseController {
    @Autowired
    private Producer captchaProducer;

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    @AuthFree
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

    public String getUid(String token) {
        return "123456";
    }
}
