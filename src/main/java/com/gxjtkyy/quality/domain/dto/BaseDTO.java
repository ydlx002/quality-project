package com.gxjtkyy.quality.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gxjtkyy on 2017/6/22.
 */
@Setter
@Getter
@ToString
public class BaseDTO implements Serializable{

    /**操作员*/
    private String operator;

    /**操作员ID*/
    private String operatorId;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

}
