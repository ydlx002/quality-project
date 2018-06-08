package com.gxjtkyy.quality.service.impl;

import com.gxjtkyy.quality.constants.QuaqlityConstant;
import com.gxjtkyy.quality.constants.ResultType;
import com.gxjtkyy.quality.dao.ArticleMapper;
import com.gxjtkyy.quality.dao.CommentMapper;
import com.gxjtkyy.quality.domain.UserInfo;
import com.gxjtkyy.quality.domain.dto.ArticleDTO;
import com.gxjtkyy.quality.domain.dto.CommentDTO;
import com.gxjtkyy.quality.domain.dto.CondictionDTO;
import com.gxjtkyy.quality.domain.vo.*;
import com.gxjtkyy.quality.domain.vo.request.AddCommentRequest;
import com.gxjtkyy.quality.domain.vo.request.QueryCommentPageRequest;
import com.gxjtkyy.quality.exception.SystemException;
import com.gxjtkyy.quality.service.ArticleService;
import com.gxjtkyy.quality.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * 评论服务实现类
 * @Package com.gxjtkyy.quality.service.impl
 * @Author lizhenhua
 * @Date 2018/5/31 15:13
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService{

    @Resource
    private CommentMapper commentMapper;

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public ResponseVO addComment(AddCommentRequest request, UserInfo userInfo) {
        ResponseVO response = new ResponseVO();
        try {
            if(0 == request.getArticleId() && 0 == request.getCommentId()){
                throw new SystemException(ResultType.NULL_FOR_ARTICLEID_AND_COMMENTID);
            }
            CommentDTO comment = new CommentDTO();
            if(null != request.getArticleId() && 0 != request.getArticleId()){
                ArticleDTO dto = articleMapper.getById(request.getArticleId());
                if(null == dto || !dto.getStatus().equals(QuaqlityConstant.STATUS_APPROVED_AUDIT)){
                    throw new SystemException(ResultType.INVALID_ARTICLE);
                }
                comment.setArticleId(request.getArticleId());
                comment.setParentId(0);
            }
            if(null != request.getCommentId() && 0 != request.getCommentId()){
                CommentDTO dto = commentMapper.getComment(request.getCommentId());
                if(null == dto || !dto.getStatus().equals(QuaqlityConstant.STATUS_APPROVED_AUDIT)){
                    throw new SystemException(ResultType.INVALID_COMMENT);
                }
                comment.setArticleId(dto.getArticleId());
                comment.setParentId(dto.getId());
            }

            comment.setStatus(QuaqlityConstant.STATUS_NO_AUDIT);
            comment.setContent(request.getContent());
            comment.setUserId(userInfo.getUserId());
            comment.setCreateTime(new Date());
            comment.setIp(userInfo.getIp());
            if(!commentMapper.insert(comment)){
                return new ResponseVO(ResultType.FAIL);
            }
        } catch (Exception e) {
            if(e instanceof SystemException){
                response.invoke((SystemException)e);
            }else{
                log.error("新增评论", e);
                return new ResponseVO(ResultType.FAIL);
            }
        }
        return response;
    }

    @Override
    public ResponseVO getComments(Integer articleId) {
        ResponseVO response = new ResponseVO();
        List<CommentVO> voList = null;
        try {
            List<CommentDTO> list = commentMapper.getList(articleId, QuaqlityConstant.STATUS_APPROVED_AUDIT);
            Map<Integer, CommentDTO> dataMap = new TreeMap<>();
            Map<Integer, CommentVO> voMap = new TreeMap<>();
            for(CommentDTO dto : list){
                CommentVO commentVO = new CommentVO();
                commentVO.setCommentId(dto.getId());
                commentVO.setContent(dto.getContent());
                commentVO.setCreateTime(dto.getCreateTime());
                commentVO.setUserName(dto.getUserName());
                voMap.put(dto.getId(), commentVO);
                dataMap.put(dto.getId(), dto);
            }

            voList = new ArrayList<>();
            for (Map.Entry<Integer, CommentDTO> entry : dataMap.entrySet()) {
                CommentDTO currentComment = entry.getValue();
                if(currentComment.getParentId()==0){
                   voList.add(voMap.get(currentComment.getId()));
                }else{
                    //获取评论对象(为评论)
                    CommentVO reviewerComment = voMap.get(currentComment.getParentId());
                    //封装当前评论到Reply中
                    Reply reply = new Reply();
                    reply.setCommentId(currentComment.getId());
                    reply.setContent(currentComment.getContent());
                    reply.setCreateTime(currentComment.getCreateTime());
                    reply.setReviewer(reviewerComment.getUserName());
                    reply.setResponder(currentComment.getUserName());  //当前评论者作为回复者
                    //评论对象中添加回复
                    reviewerComment.getReplies().add(reply);
                    //取父级评论者作为本次回复对象
                }
            }
            response.setData(voList);
        } catch (Exception e) {
            log.error("查询评论列表异常",e);
        }
        return response;
    }

    @Override
    public ResponseVO getCommentsByPage(QueryCommentPageRequest request, UserInfo userInfo) {
        ResponseVO response = new ResponseVO();
        try {
            if (userInfo.getUserType() <= 2) {
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
            CommentDTO dto = new CommentDTO();
            dto.setStatus(request.getStatus());
            CondictionDTO condiction = new CondictionDTO();
            condiction.setStart((currentPage - 1) * pageSize);
            condiction.setPageSize(pageSize);
            condiction.setDto(dto);
            int count = commentMapper.countComment(condiction);
            List<CommentVO> list = commentMapper.getListByPage(condiction);
            PageResultVO<CommentVO> page = new PageResultVO<>();
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
