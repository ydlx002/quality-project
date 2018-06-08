package com.gxjtkyy.quality.domain.vo.request;

import lombok.Data;

/**
 * 分页查询用户列表
 * @Package com.gxjtkyy.quality.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/5 18:39
 */
@Data
public class QueryUserPageRequest extends Request{

    /**当前页*/
    private int currentPage = 1;

    /**页大小*/
    private int pageSize = 10;


    /**用户姓名*/
    private String userName;

    /**用户类型*/
    private Integer userType;


}
