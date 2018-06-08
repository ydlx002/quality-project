package com.gxjtkyy.quality.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论
 * @Package com.gxjtkyy.domain.dto
 * @Author lizhenhua
 * @Date 2018/5/31 8:35
 */
@Setter
@Getter
@ToString
public class CommentDTO implements Serializable {

    /**序号*/
    private Integer id;

    /**父级评论*/
    private Integer parentId;

    /**文章id*/
    private Integer articleId;

    /**用户ID*/
    private String userId;

    /**用户姓名*/
    private String userName;

    /**用户类型*/
    private Integer userType;

    /**评论内容*/
    private String content;
    
    /**创建时间*/
    private Date createTime;
    
    /**0 未审核 1审核通过 2审核未通过*/
    private Integer status;

    /**IP*/
    private String ip;

    /**审核者ID*/
    private String auditorId;

    /**审核时间*/
    private Date auditTime;

    /**审核意见*/
    private String auditOpinion;
    
}
