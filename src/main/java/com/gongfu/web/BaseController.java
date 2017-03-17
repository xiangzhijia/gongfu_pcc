package com.gongfu.web;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

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
        // return req.getHeader("x-forwarded-for") == null ? req.getRemoteAddr() : req.getHeader("x-forwarded-for");
        return req.getHeader("X-Real-IP") == null ? req.getRemoteAddr() : req.getHeader("X-Real-IP");
    }

    public static String getRealUri(HttpServletRequest req) {
        return req.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI) == null ? req.getRequestURI() : req.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI) + "";
    }
}
