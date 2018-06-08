package com.gxjtkyy.quality.domain.vo.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询审核列表
 * @Package com.gxjtkyy.quality.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/4 19:15
 */
@Data
public class QueryCommentPageRequest implements Serializable{

    /**当前页*/
    private int currentPage = 1;

    /**页大小*/
    private int pageSize = 10;

    /**文章状态*/
    private Integer status;

}
