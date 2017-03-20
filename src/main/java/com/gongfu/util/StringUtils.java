package com.gongfu.util;

/**
 * 2017年1月13日
 *
 * @向治家
 **/
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 获取匿名手机号
     *
     * @param mobile 手机号
     * @return 返回匿名手机号
     */
    public static String getMobileAnonym(String mobile) {
        if (mobile != null && mobile.length() > 7) {
            return mobile.substring(0, 3) + "****" + mobile.substring(mobile.length() - 4, mobile.length());
        } else {
            return "****";
        }
    }
}
