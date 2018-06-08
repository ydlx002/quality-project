package com.gxjtkyy.quality.domain.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * token
 * @Package com.gxjtkyy.quality.domain.vo
 * @Author lizhenhua
 * @Date 2018/6/2 11:03
 */
@Setter
@Getter
@ToString
public class TokenVO implements Serializable{

    /**token*/
    private String token;

    /**token超时时间*/
    private long expire;

    /**用户姓名*/
    private String userName;

    /**用户类型*/
    private Integer userType;


}
