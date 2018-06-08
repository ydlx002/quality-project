package com.gxjtkyy.quality.domain.vo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 新增文章请求类
 * @Package com.gxjtkyy.quality.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/2 12:17
 */
@Setter
@Getter
@ToString
public class AddArticleRequest extends Request{

    /**栏目ID*/
    private Integer colId;

    /**文章标题*/
    private String title;

    /**文章概要*/
    private String summary;

    /**文章内容*/
    private String content;

    /**来源*/
    private String source;


}
