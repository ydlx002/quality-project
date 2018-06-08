package com.gxjtkyy.quality.service;

import com.gxjtkyy.quality.domain.UserInfo;
import com.gxjtkyy.quality.domain.dto.CommentDTO;
import com.gxjtkyy.quality.domain.vo.CommentVO;
import com.gxjtkyy.quality.domain.vo.ResponseVO;
import com.gxjtkyy.quality.domain.vo.request.AddCommentRequest;
import com.gxjtkyy.quality.domain.vo.request.QueryArticleRequest;
import com.gxjtkyy.quality.domain.vo.request.QueryCommentPageRequest;

import java.util.List;

/**
 * 评论管理接口
 * @Package com.gxjtkyy.quality.service
 * @Author lizhenhua
 * @Date 2018/5/31 13:00
 */
public interface CommentService {

    /**
     * 新增评论
     * @param request 新增评论请求体
     * @param userInfo 用户信息
     * @return
     */
    ResponseVO addComment( AddCommentRequest request ,UserInfo userInfo);


    /***
     * 根据文章获取评论列表
     * @param articleId 文章ID
     * @return
     */
    ResponseVO getComments(Integer articleId);


    /**
     * 分页查询文章列表 for 审核
     * @param request
     * @return
     * @throws Exception
     */
    ResponseVO getCommentsByPage(QueryCommentPageRequest request, UserInfo userInfo);
}
