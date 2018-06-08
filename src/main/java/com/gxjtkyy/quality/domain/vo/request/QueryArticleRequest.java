package com.gxjtkyy.quality.domain.vo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 文章查询请求对象
 * @Package com.gxjtkyy.quality.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/2 15:09
 */
@Setter
@Getter
@ToString
public class QueryArticleRequest implements Serializable{

    /**当前页*/
    private int currentPage = 1;

    /**页大小*/
    private int pageSize = 10;

    /**文章标题*/
    private String title;

    /**文章状态*/
    private Integer status;

    /**栏目ID*/
    private Integer colId;


}
