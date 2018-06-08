package com.gxjtkyy.quality.service.impl;

import com.gxjtkyy.quality.constants.QuaqlityConstant;
import com.gxjtkyy.quality.constants.ResultType;
import com.gxjtkyy.quality.dao.ArticleMapper;
import com.gxjtkyy.quality.dao.ColumnMapper;
import com.gxjtkyy.quality.dao.CommentMapper;
import com.gxjtkyy.quality.dao.LikeMapper;
import com.gxjtkyy.quality.domain.UserInfo;
import com.gxjtkyy.quality.domain.dto.*;
import com.gxjtkyy.quality.domain.vo.ArticleAuditVO;
import com.gxjtkyy.quality.domain.vo.ColDictVO;
import com.gxjtkyy.quality.domain.vo.PageResultVO;
import com.gxjtkyy.quality.domain.vo.ResponseVO;
import com.gxjtkyy.quality.domain.vo.request.AddCommentRequest;
import com.gxjtkyy.quality.domain.vo.request.AddLikeRequest;
import com.gxjtkyy.quality.domain.vo.request.AuditRequest;
import com.gxjtkyy.quality.domain.vo.request.QueryCommentPageRequest;
import com.gxjtkyy.quality.exception.SystemException;
import com.gxjtkyy.quality.service.CommentService;
import com.gxjtkyy.quality.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 公共服务接口实现层
 * @Package com.gxjtkyy.quality.service.impl
 * @Author lizhenhua
 * @Date 2018/6/4 19:31
 */
@Service
@Slf4j
public class CommonServiceImpl implements CommonService{

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private ColumnMapper columnMapper;

    @Resource
    private LikeMapper likeMapper;

    @Override
    @Transactional
    public ResponseVO doAudit(AuditRequest request, UserInfo userInfo) {
        ResponseVO response = new ResponseVO();
        try {
            if (userInfo.getUserType() <= 2) {
                throw new SystemException(ResultType.PERMISSION_DENY);
            }
            AuditDTO dto = new AuditDTO();
            dto.setStatus(request.getStatus());
            dto.setAuditOpinion(request.getAuditOpinion());
            dto.setAuditTime(new Date());
            dto.setId(request.getTargetId());
            dto.setAuditorId(userInfo.getUserId());
            //文章审核
            if (request.getTargetType() == QuaqlityConstant.AUDIT_TARGET_TYPE_ARTICLE) {
                articleMapper.updateStatus(dto);
            } else if (request.getTargetType() == QuaqlityConstant.AUDIT_TARGET_TYPE_COMMENT) {
                CommentDTO comment = commentMapper.getComment(request.getTargetId());
                if(comment != null ){
                    if(!comment.getStatus().equals(QuaqlityConstant.STATUS_APPROVED_AUDIT)){
                        //评论审核
                        commentMapper.updateStatus(dto);
                        //评论通过审核，新增评论数
                        if (dto.getStatus().equals(QuaqlityConstant.STATUS_APPROVED_AUDIT)) {
                            articleMapper.addCommentCount(comment.getArticleId());
                        }
                    }
                    return response;
                }else{
                    return new ResponseVO(ResultType.COMMENT_EXISTS);
                }
            }
        } catch (SystemException e) {
            response.invoke(e);
            log.error("审核异常", e);
        }
        return response;
    }

    @Override
    public ResponseVO getColDicts() {
        ResponseVO response = new ResponseVO();
        List<ColDictVO> list = columnMapper.getColDict();
        response.setData(list);
        return response;
    }

    @Override
    @Transactional
    public ResponseVO doLike(AddLikeRequest request, String  userId) {
        ResponseVO response = new ResponseVO();
        try {
            int count = likeMapper.isExists(request.getTargetId(), request.getTargetType(), userId);
            if(count > 0){
                log.info("用户已点赞---->{}",request);
                return new ResponseVO(ResultType.SUCCESS);
            }
            if(request.getTargetType()== QuaqlityConstant.AUDIT_TARGET_TYPE_ARTICLE) {
                LikeDTO dto = new LikeDTO();
                dto.setTargetId(request.getTargetId());
                dto.setUserId(userId);
                dto.setTargetType(request.getTargetType());
                likeMapper.insert(dto);
                articleMapper.addLikeCount(request.getTargetId());
            }else if(request.getTargetType() == QuaqlityConstant.AUDIT_TARGET_TYPE_COMMENT){
                commentMapper.addLikeCount(request.getTargetId());
            }
        } catch (Exception e) {
            log.error("获取审核列表异常", e);
            return new ResponseVO(ResultType.FAIL);
        }
        return response;
    }
}
