package com.gxjtkyy.quality.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 * Created by gxjtkyy on 2017/6/22.
 */
@Setter
@Getter
@ToString
public class UserDTO implements Serializable{

    /**用户ID*/
    private String userId;

    /**系统账号*/
    private String userAccount;

    /**用户姓名*/
    private String userName;

    /**联系电话*/
    private String mobile;

    /**用户类型*/
    private Integer userType;

    /**部门*/
    private String department;

    /**职位*/
    private String position;

    /**创建时间*/
    private Date createTime;

    /**密码*/
    private String password;

    /**备注*/
    private String remark;

    /**电子邮箱*/
    private String email;




}
