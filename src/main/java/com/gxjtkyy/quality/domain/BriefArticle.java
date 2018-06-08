package com.gxjtkyy.quality.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 摘要文章
 * @Package com.gxjtkyy.domain.vo
 * @Author lizhenhua
 * @Date 2018/5/31 9:14
 */
@Setter
@Getter
@ToString
public class BriefArticle implements Serializable{

    /**文章ID*/
    private Integer articleId;

    /**文章标题*/
    private String articleTitle;

    /**文章摘要*/
    private String summary;
    
    /**文章评论数*/
    private int commentCount;
    
    /**点赞数*/
    private int likeCount;


}
