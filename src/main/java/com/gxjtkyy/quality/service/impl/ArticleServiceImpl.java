package com.gxjtkyy.quality.service.impl;

import com.gxjtkyy.quality.constants.QuaqlityConstant;
import com.gxjtkyy.quality.constants.ResultType;
import com.gxjtkyy.quality.dao.ArticleMapper;
import com.gxjtkyy.quality.dao.ColumnMapper;
import com.gxjtkyy.quality.dao.CommentMapper;
import com.gxjtkyy.quality.domain.BriefArticle;
import com.gxjtkyy.quality.domain.UserInfo;
import com.gxjtkyy.quality.domain.dto.ArticleDTO;
import com.gxjtkyy.quality.domain.dto.AuditDTO;
import com.gxjtkyy.quality.domain.dto.CondictionDTO;
import com.gxjtkyy.quality.domain.vo.*;
import com.gxjtkyy.quality.domain.vo.request.AddArticleRequest;
import com.gxjtkyy.quality.domain.vo.request.AuditRequest;
import com.gxjtkyy.quality.domain.vo.request.QueryArticleRequest;
import com.gxjtkyy.quality.domain.vo.request.QueryBriefArticleRequest;
import com.gxjtkyy.quality.exception.SystemException;
import com.gxjtkyy.quality.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 文章接口实现
 *
 * @Package com.gxjtkyy.quality.service.impl
 * @Author lizhenhua
 * @Date 2018/5/31 10:28
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ColumnMapper columnMapper;


    @Override
    public ResponseVO getBriefArticles(QueryBriefArticleRequest request) {
        ResponseVO response = new ResponseVO();
        try {
            int currentPage = request.getCurrentPage();
            int pageSize = request.getPageSize();
            if (request.getColId() < 0) {
                throw new SystemException(ResultType.INVALID_COLID);
            }
            if (currentPage < 0) {
                throw new SystemException(ResultType.INVALID_CURRENT_PAGE);
            }
            if (pageSize < 0) {
                throw new SystemException(ResultType.INVALID_PAGESIZE);
            }
            int count = articleMapper.count(request.getColId(), QuaqlityConstant.STATUS_APPROVED_AUDIT);
            int start = (currentPage - 1) * pageSize;
            List<BriefArticle> list = articleMapper.getBriefList(request.getColId(), QuaqlityConstant.STATUS_APPROVED_AUDIT, start, pageSize);
            PageResultVO<BriefArticle> page = new PageResultVO<>();
            page.setTotalElements(count);
            page.setCurrentPage(currentPage);
            page.setContent(list);
            response.setData(page);
        } catch (Exception e) {
            if (e instanceof SystemException) {
                response.invoke((SystemException) e);
            } else {
                log.error("查询栏目摘要异常", e);
                return new ResponseVO(ResultType.FAIL);
            }
        }
        return response;
    }

    @Override
    public ResponseVO getArticle(Integer articleId, UserInfo userInfo) {
        ResponseVO response = new ResponseVO();
        try {
            ArticleDTO dto = articleMapper.getById(articleId);
            if (null == dto) {
                throw new SystemException(ResultType.ARTICLE_NO_EXISTS);
            }
            //审核未通过,非管理员不可查看
            if (dto.getStatus() != 1) {
                if (userInfo == null || userInfo.getUserType() <= 2) {
                    throw new SystemException(ResultType.ARTICLE_NO_EXISTS);
                }
            } else {
                //通过审核的文章，没展开一次，阅览数增1
                articleMapper.addViewCount(articleId);
            }
            ArticleVO vo = new ArticleVO();
            BeanUtils.copyProperties(dto, vo);
            response.setData(vo);
        } catch (Exception e) {
            if (e instanceof SystemException) {
                response.invoke((SystemException) e);
            } else {
                log.error("查询文章异常", e);
                return new ResponseVO(ResultType.FAIL);
            }
        }
        return response;
    }

    @Override
    public ResponseVO addArticle(AddArticleRequest request, UserInfo userInfo) {
        try {
            if(userInfo.getUserType() <=0){
                return new ResponseVO(ResultType.PERMISSION_DENY);
            }
            if (columnMapper.countById(request.getColId()) == 0) {
                return new ResponseVO(ResultType.COLUMN_NO_EXISTS);
            }
            ArticleDTO dto = new ArticleDTO();
            dto.setAuthorId(userInfo.getUserId());
            dto.setColId(request.getColId());
            dto.setTitle(request.getTitle());
            dto.setSummary(request.getSummary());
            dto.setSource(request.getSource());
            dto.setContent(request.getContent());
            dto.setStatus(0);
            dto.setViewCount(0);
            dto.setCreateTime(new Date());
            if (articleMapper.insert(dto)) {
                return new ResponseVO(ResultType.SUCCESS);
            } else {
                return new ResponseVO(ResultType.FAIL);
            }
        } catch (Exception e) {
            log.error("新增文章异常", e);
            return new ResponseVO(ResultType.FAIL);
        }
    }

    @Override
    public ResponseVO getArticles(QueryArticleRequest request, UserInfo userInfo) {
        ResponseVO response = new ResponseVO();
        try {
            if (userInfo.getUserType() <= 1) {
                throw new SystemException(ResultType.PERMISSION_DENY);
            }
            int currentPage = request.getCurrentPage();
            int pageSize = request.getPageSize();
            if (currentPage < 0) {
                throw new SystemException(ResultType.INVALID_CURRENT_PAGE);
            }
            if (pageSize < 0) {
                throw new SystemException(ResultType.INVALID_PAGESIZE);
            }
            ArticleDTO dto = new ArticleDTO();
            dto.setTitle(request.getTitle());
            dto.setStatus(request.getStatus());
            dto.setColId(request.getColId());
            CondictionDTO condiction = new CondictionDTO();
            condiction.setStart((currentPage - 1) * pageSize);
            condiction.setPageSize(pageSize);
            condiction.setDto(dto);
            int count = articleMapper.countArticle(condiction);
            List<ArticleAuditVO> list = articleMapper.getAuditList(condiction);
            PageResultVO<ArticleAuditVO> page = new PageResultVO<>();
            page.setTotalElements(count);
            page.setCurrentPage(currentPage);
            page.setContent(list);
            response.setData(page);
        } catch (SystemException e) {
            response.invoke(e);
            log.error("获取审核列表异常", e);
        }
        return response;
    }
}
