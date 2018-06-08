package com.gxjtkyy.quality.domain.vo.request;

import lombok.Data;

/**
 * 点赞请求体
 * @Package com.gxjtkyy.quality.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/4 19:36
 */
@Data
public class AddLikeRequest extends Request{
    
    /**目标ID*/
    private int targetId;

    /**0 文章  1评论*/
    private int targetType =0 ;
}
