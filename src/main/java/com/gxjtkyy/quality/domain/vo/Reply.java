package com.gxjtkyy.quality.domain.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 回复
 * @Package com.gxjtkyy.quality.domain.vo
 * @Author lizhenhua
 * @Date 2018/6/1 8:42
 */
@Setter
@Getter
@ToString
public class Reply implements Serializable{

    /**评论ID*/
    private Integer commentId;

    /**评论者*/
    private String reviewer;

    /**回复者*/
    private String responder;

    /**评论时间*/
    private Date createTime;

    /**评论内容*/
    private String content;

}
