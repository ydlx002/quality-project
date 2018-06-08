package com.gxjtkyy.quality.domain.vo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 新增评论请求体
 * @Package com.gxjtkyy.quality.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/4 11:32
 */
@Setter
@Getter
@ToString
public class AddCommentRequest extends Request{

    /**文章ID*/
    private Integer articleId;

    /**评论ID*/
    private Integer commentId;

    /**content*/
    private String content;


}
