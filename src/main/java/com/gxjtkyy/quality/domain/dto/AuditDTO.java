package com.gxjtkyy.quality.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 审核
 * @Package com.gxjtkyy.quality.domain.dto
 * @Author lizhenhua
 * @Date 2018/6/4 17:12
 */
@Setter
@Getter
@ToString
public class AuditDTO implements Serializable{

    /**审核ID*/
    private Integer id;
    
    /**审核意见*/
    private String auditOpinion;
    
    /**审核时间*/
    private Date auditTime;
    
    /**审核状态*/
    private Integer status;

    /**审核员ID*/
    private String auditorId;


}
