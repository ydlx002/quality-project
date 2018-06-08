package com.gxjtkyy.quality.domain.vo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 注册请求体
 * @Package com.gxjtkyy.quality.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/5 18:22
 */
@Setter
@Getter
@ToString
public class RegistRequest extends Request {

    /**用户账号*/
    private String userAccount;

    /**密码*/
    private String password;

    /**用户姓名*/
    private String userName;

    /**手机号码*/
    private String mobile;

    /**部门*/
    private String department;

    /**职位*/
    private String position;
    
    /**邮箱*/
    private String email;


}
