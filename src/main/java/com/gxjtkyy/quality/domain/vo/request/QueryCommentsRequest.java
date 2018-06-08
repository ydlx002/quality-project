package com.gxjtkyy.quality.domain.vo.request;

import lombok.Data;

/**
 * 评论列表查询请求体
 * @Package com.gxjtkyy.quality.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/4 12:17
 */
@Data
public class QueryCommentsRequest extends Request{

    /**文章ID*/
    private Integer articleId;
    
}
