package com.gxjtkyy.quality.domain.vo;

import lombok.Data;

/**
 * 用户信息请求实体类，主要用户向前台展示用户信息以及新增或编辑用户信息
 * Created by gxjtkyy on 2017/5/30.
 */
@Data
public class UserVO extends BaseVO{

    /**userId*/
    private String userId;

    /**登录账号*/
    private String userAccount;

    /**用户姓名*/
    private String userName;

    /**手机号码*/
    private String mobile;

    /**部门*/
    private String department;

    /**备注*/
    private String remark;

    /**岗位*/
    private String position;

    /**用户类型*/
    private Integer userType;
    
    /**邮箱*/
    private String email;


}
