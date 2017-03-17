package com.gongfu.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gongfu.config.code.ErrorCode;
import com.gongfu.config.interceptor.constant.AppConstant;
import com.gongfu.config.interceptor.support.Auth;
import com.gongfu.config.interceptor.support.AuthInfo;
import com.gongfu.config.interceptor.support.AuthInfo.ClientInfo;
import com.gongfu.web.auth.controller.AuthController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.validation.ValidationException;

/**
 * 2017年1月9日
 *
 * @向治家 yhqb
 * AuthResolver.java
 * 配合注解{@link Auth}自动注入controller的方法参数{@link AuthInfo}}
 * 定义一个AuthResolver，来对HttpServletRequest的body进行解析：
 **/
@Slf4j
public class AuthResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private AuthController authRest;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("AuthResolver→{supportsParameter}");
        return parameter.hasParameterAnnotation(Auth.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        log.info("AuthResolver→{resolveArgument}");

        /**
         * 请求头:
         * x-yhqz-client: {"role":"adviser","version":"v1.1.1","deviceId":"1234fads"}
         * x-yhqz-token: dobkvu7ukcmd3mjv9e53d86mio
         */
        String token = webRequest.getHeader(AppConstant.HTTP_HEADER_TOKEN);
        JSONObject clientInfo = JSON.parseObject(webRequest.getHeader(AppConstant.HTTP_HEADER_CLIENT));

        if (clientInfo == null || clientInfo.getString("role") == null) {
            throw new ValidationException(ErrorCode.errorMsg(ErrorCode.ERR_LACK_CLIENT_HEADER));
        }

        clientInfo.put("role", clientInfo.getString("role").toUpperCase());
        AuthInfo authInfo = new AuthInfo();
        authInfo.setClientInfo(JSON.parseObject(clientInfo.toString(), ClientInfo.class));
        //根据前端传递过来的token获取Uid
        authInfo.setUid(authRest.getUid(token));
        authInfo.setToken(token);
        return authInfo;
    }

}
