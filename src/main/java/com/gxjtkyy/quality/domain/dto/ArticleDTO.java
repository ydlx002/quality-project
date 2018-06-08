package com.gxjtkyy.quality.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章类DTO
 * @Package com.gxjtkyy.domain.dto
 * @Author lizhenhua
 * @Date 2018/5/31 8:25
 */
@Setter
@Getter
@ToString
public class ArticleDTO implements Serializable{

    /**文章ID*/
    private Integer id;

    /**栏目ID*/
    private Integer colId;

    /**文章标题*/
    private String title;

    /**摘要*/
    private String summary;

    /**文章内容*/
    private String content;

    /**作者ID*/
    private String authorId;

    /**作者*/
    private String author;

    /**来源*/
    private String source;

    /**阅读量*/
    private Integer viewCount;

    /**创建时间*/
    private Date createTime;

    /**文章状态*/
    private Integer status;

    /**审核者ID*/
    private String auditorId;

    /**审核时间*/
    private Date auditTime;

    /**审核意见*/
    private String auditOpinion;


}
