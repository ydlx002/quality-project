package com.gxjtkyy.quality.domain.vo.request;

import lombok.Data;

/**
 * 更新用户
 * @Package com.gxjtkyy.quality.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/5 18:36
 */
@Data
public class UpdateUserRequest extends Request{

    /**用户ID*/
    private String userId;

    /**部门*/
    private String department;

    /**职位*/
    private String position;

    /**手机号*/
    private String mobile;

    /**用户类型*/
    private Integer userType;



}
