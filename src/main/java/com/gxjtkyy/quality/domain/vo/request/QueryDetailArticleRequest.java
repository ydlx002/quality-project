package com.gxjtkyy.quality.domain.vo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 文章查询请求
 * @Package com.gxjtkyy.quality.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/4 9:37
 */
@Setter
@Getter
@ToString
public class QueryDetailArticleRequest extends Request{

    /**文章ID*/
    private Integer articleId;


}
