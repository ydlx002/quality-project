package com.gxjtkyy.quality.domain.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章视图对象
 * @Package com.gxjtkyy.quality.domain.vo
 * @Author lizhenhua
 * @Date 2018/5/31 12:08
 */
@Setter
@Getter
@ToString
public class ArticleVO implements Serializable{

    /**文章ID*/
    private Integer id;

    /**文章标题*/
    private String title;

    /**文章内容*/
    private String content;

    /**作者*/
    private String author;

    /**来源*/
    private String source;

    /**阅读量*/
    private Integer viewCount;

    /**创建时间*/
    private Date createTime;
}
