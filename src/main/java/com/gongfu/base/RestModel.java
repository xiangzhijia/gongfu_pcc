package com.gongfu.base;

import com.gongfu.config.code.ErrorCode;

import java.util.LinkedHashMap;


public class RestModel extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = 8542653644934668818L;

    public static RestModel create() {
        return new RestModel();
    }

    public RestModel errorMsg(long errorCode) {
        this.put("status", "fail");
        this.put("code", errorCode);
        this.put("message", ErrorCode.errorMsg(errorCode));
        this.remove("data");
        return this;
    }

    public RestModel errorMsg(String errorMsg) {
        this.put("status", "fail");
        this.put("code", 0);
        this.put("message", errorMsg);
        this.remove("data");
        return this;
    }

    public RestModel errorMsg(long errorCode, String errorMsg) {
        this.put("status", "fail");
        this.put("code", errorCode);
        this.put("message", errorMsg);
        this.remove("data");
        return this;
    }

    public RestModel error() {
        this.put("status", "fail");
        this.put("code", 0);
        this.put("message", "操作失败");
        this.remove("data");
        return this;
    }

    public RestModel body(Object body) {
        succ();
        if (body != null) {
            put("data", body);
        }
        return this;
    }

    public RestModel body(Object body, Integer totalRecord) {
        succ();
        if (body != null) {
            put("data", body);
            put("totalRecord", totalRecord);
        }
        return this;
    }

    public RestModel succ() {
        this.put("status", "succ");
        return this;
    }

    public RestModel succ(String message) {
        this.put("message", message);
        return succ();
    }
}
