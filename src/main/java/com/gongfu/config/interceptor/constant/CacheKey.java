package com.gongfu.config.interceptor.constant;

/**
 * 2017年1月11日
 *
 * @向治家 yhqb
 * CacheKey.java
 * 缓存相关配置
 **/
public final class CacheKey {

    /**
     * 用户信息
     */
    public static final String REGION_USER = "user";


    /********************************
     * child region method
     ********************************/
    public static String getSubRegionKey(String region, String... subRegions) {
        StringBuffer sb = new StringBuffer().append(region);
        for (String subRegion : subRegions) {
            sb.append("_").append(subRegion);
        }
        return sb.toString();
    }
}
