package com.gxjtkyy.quality.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * 评审审核对象
 * @Package com.gxjtkyy.quality.domain.vo
 * @Author lizhenhua
 * @Date 2018/6/4 19:02
 */
@Data
public class CommentAuditVO extends BaseVO{

    /**id*/
    private Integer id;

    /**用户姓名*/
    private String userName;

    /**评论内容*/
    private String content;

    /**评论时间*/
    private Date createTime;

    /**IP*/
    private String ip;

    /**状态*/
    private Integer status;

    /**审核员*/
    private String auditor;

    /**审核时间*/
    private Date auditTime;

    /**审核意见*/
    private String auditOpinion;



}
