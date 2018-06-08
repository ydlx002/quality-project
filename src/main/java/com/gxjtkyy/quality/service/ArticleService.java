package com.gxjtkyy.quality.service;

import com.gxjtkyy.quality.domain.BriefArticle;
import com.gxjtkyy.quality.domain.UserInfo;
import com.gxjtkyy.quality.domain.dto.ArticleDTO;
import com.gxjtkyy.quality.domain.vo.ArticleAuditVO;
import com.gxjtkyy.quality.domain.vo.ArticleVO;
import com.gxjtkyy.quality.domain.vo.PageResultVO;
import com.gxjtkyy.quality.domain.vo.ResponseVO;
import com.gxjtkyy.quality.domain.vo.request.AddArticleRequest;
import com.gxjtkyy.quality.domain.vo.request.AuditRequest;
import com.gxjtkyy.quality.domain.vo.request.QueryArticleRequest;
import com.gxjtkyy.quality.domain.vo.request.QueryBriefArticleRequest;

/**
 * 文章服务接口
 * @Package com.gxjtkyy.quality.service
 * @Author lizhenhua
 * @Date 2018/5/31 10:22
 */
public interface ArticleService {

    /**
     * 分页获取栏目列表
     * @return
     */
    ResponseVO getBriefArticles(QueryBriefArticleRequest request) ;


    /**
     * 根据文章ID获取文章内容
     * @param articleId
     * @return
     */
    ResponseVO getArticle(Integer articleId, UserInfo userInfo);


    /**
     * 新增文章
     * @param reuquest 请求
     * @return
     */
    ResponseVO addArticle(AddArticleRequest reuquest, UserInfo userInfo);


    /**
     * 分页查询文章列表 for 审核
     * @param request
     * @return
     * @throws Exception
     */
    ResponseVO getArticles(QueryArticleRequest request, UserInfo userInfo);

}
