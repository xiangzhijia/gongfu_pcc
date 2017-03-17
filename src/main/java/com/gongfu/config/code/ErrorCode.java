package com.gongfu.config.code;

import java.util.HashMap;
import java.util.Map;

/**
 * 2017年1月8日
 *
 * @向治家 yhqb
 * ErrorCode.java
 **/
public class ErrorCode {

    private static Map<Long, String> ERRORS = new HashMap<>();

    public static String errorMsg(long errorCode) {
        return ERRORS.get(errorCode);
    }

    public final static int ERR_SYS = 0;// 系统错误
    public final static int ERR_LACK_PARAM = 5;// 缺少必需的参数
    public final static int ERR_LACK_CLIENT_HEADER = 6;// 缺少必须的请求头信息(x-yhqz-client)
}
