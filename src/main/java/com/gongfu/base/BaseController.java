package com.gongfu.base;

import com.gongfu.config.code.StringCode;
import com.gongfu.model.user.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 2017年1月9日
 *
 * @向治家 yhqb
 * BaseRest.java
 **/
@Slf4j
public class BaseController {
    public final static String API_ADMIN = "/api";

    /**
     * 获取真正的ip地址（考虑到nginx代理）
     */
    public static String getRealIp(HttpServletRequest req) {
        // 使用x-forwarded-for可能会有2个ip，如：218.18.1.129, 183.60.52.5
        return req.getHeader("X-Real-IP") == null ? req.getRemoteAddr() : req.getHeader("X-Real-IP");
    }

    public static String getRealUri(HttpServletRequest req) {
        return req.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI) == null ? req.getRequestURI() : req.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI) + "";
    }

    /**
     * 保存会话
     *
     * @param request
     * @param user
     */
    public void saveSession(HttpServletRequest request, User user) {
        request.getSession().setAttribute(StringCode.USER, user);
    }

    /**
     * 获取会话
     *
     * @param request
     * @return
     */
    public User getSession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(StringCode.USER);
    }

    /**
     * 注销用户
     *
     * @param request
     */
    public void removeSession(HttpServletRequest request) {
        //false代表：不创建session对象，只是从request中获取。
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(StringCode.USER);
    }
}
