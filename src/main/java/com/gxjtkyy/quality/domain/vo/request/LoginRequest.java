package com.gxjtkyy.quality.domain.vo.request;

import lombok.Data;

/**
 * 请求登录
 * @Package com.gxjtkyy.quality.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/6 11:20
 */
@Data
public class LoginRequest extends Request{
    
    /**登录账号*/
    private String userAccount;
    
    /**密码*/
    private String password;
    

}
