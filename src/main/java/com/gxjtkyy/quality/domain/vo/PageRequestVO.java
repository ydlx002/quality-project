package com.gxjtkyy.quality.domain.vo;

import java.io.Serializable;

/**
 * Created by gxjtkyy on 2017/5/30.
 */
public class PageRequestVO implements Serializable{

    /**当前页码*/
    private Integer page =1;

    /**每页数据行数*/
    private Integer rows=10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageRequestVO{" +
                "page=" + page +
                ", rows=" + rows +
                '}';
    }
}
