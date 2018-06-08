package com.gxjtkyy.quality.domain.dto;

import com.gxjtkyy.quality.utils.MapUtil;

import java.io.Serializable;

/**
 * 分页查询条件
 * Created by gxjtkyy on 2017/7/25.
 */
public class CondictionDTO implements Serializable{

    /**mysql分页开始行数*/
    private int start;

    /**mysql分页大小*/
    private int pageSize = 100;

    /**查询条件*/
    private Object dto;


    public int getStart() {
        return start;
    }

    public CondictionDTO setStart(int start) {
        this.start = start;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public CondictionDTO setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Object getDto() {
        return dto;
    }

    public CondictionDTO setDto(Object dto) {
        this.dto = MapUtil.convertBeanToMap(dto);
        return this;
    }

}
