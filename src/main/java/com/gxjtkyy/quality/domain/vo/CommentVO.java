package com.gxjtkyy.quality.domain.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论视图对象
 * @Package com.gxjtkyy.quality.domain.vo
 * @Author lizhenhua
 * @Date 2018/5/31 13:05
 */
@Setter
@Getter
@ToString
public class CommentVO implements Serializable{

    /**评论ID*/
    private Integer commentId;

    /**用户姓名*/
    private String userName;

    /**用户类型*/
    private String userType;

    /**评论时间*/
    private Date createTime;

    /**评论内容*/
    private String content;

    /**回复*/
    List<Reply> replies = new ArrayList<>();

}
