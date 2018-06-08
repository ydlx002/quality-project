package com.gxjtkyy.quality.domain.vo.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 审核请求体
 * @Package com.gxjtkyy.quality.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/4 17:55
 */
@Data
public class AuditRequest implements Serializable{

    /**审核ID*/
    private Integer targetId;

    /**审核ID*/
    private Integer targetType;

    /**审核意见*/
    private String auditOpinion;

    /**审核状态*/
    private Integer status;
}
