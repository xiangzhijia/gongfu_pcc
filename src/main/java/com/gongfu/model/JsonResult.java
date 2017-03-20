package com.gongfu.model;

import java.util.Collection;

/**
 * Created by feng on 2017/2/8.
 */
public class JsonResult<T> {

    private Long total = 0L;

    private Collection<T> rows;

    public JsonResult(Collection<T> rows) {
        this.rows = rows;
    }

    public JsonResult(Long total, Collection<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Collection<T> getRows() {
        return rows;
    }

    public void setRows(Collection<T> rows) {
        this.rows = rows;
    }
}
