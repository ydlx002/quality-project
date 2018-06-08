package com.gxjtkyy.quality.domain;

import lombok.Data;
import lombok.Setter;

/**
 * 用户信息
 * @Package com.gxjtkyy.quality.domain
 * @Author lizhenhua
 * @Date 2018/6/2 15:39
 */
@Data
public class UserInfo {
    
    /**用户ID*/
    private String userId;
    
    /**用户姓名*/
    private String userName;
    
    /**用户类型*/
    private Integer userType;
    
    /**ip地址*/
    private String ip;
    

}
