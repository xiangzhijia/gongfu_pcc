package com.gongfu.model;

/**
 * Created by feng on 2017/2/8.
 */
public class Page {

    private Integer limit;

    private Integer offset;

    private String order;

    private String sort;

    public boolean valid() {
        if (limit != null && limit != null) {
            return true;
        }
        return false;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

}
