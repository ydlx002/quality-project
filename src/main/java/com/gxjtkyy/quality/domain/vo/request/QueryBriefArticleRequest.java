package com.gxjtkyy.quality.domain.vo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 查询摘要列表
 * @Package com.gxjtkyy.quality.domain.vo
 * @Author lizhenhua
 * @Date 2018/6/2 15:30
 */
@Setter
@Getter
@ToString
public class QueryBriefArticleRequest implements Serializable{

    /**当前页*/
    private int currentPage = 1;

    /**页大小*/
    private int pageSize = 10;

    /**栏目ID*/
    private Integer colId;
}
