package com.gongfu.model;

import lombok.Data;

/**
 * Created by feng on 2017/2/8.
 */
@Data
public class Page {
    private Integer limit;
    private Integer offset;
    private String order;
    private String sort;

    public boolean valid() {
        if (limit != null && offset != null) {
            return true;
        }
        return false;
    }
}
