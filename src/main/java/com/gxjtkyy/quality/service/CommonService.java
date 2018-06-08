package com.gxjtkyy.quality.service;

import com.gxjtkyy.quality.domain.UserInfo;
import com.gxjtkyy.quality.domain.vo.ResponseVO;
import com.gxjtkyy.quality.domain.vo.request.AddLikeRequest;
import com.gxjtkyy.quality.domain.vo.request.AuditRequest;

/**
 * 公共服务接口
 * @Package com.gxjtkyy.quality.service
 * @Author lizhenhua
 * @Date 2018/6/4 19:30
 */
public interface CommonService {

    /**
     * 审核
     * @param request
     * @param userInfo
     * @return
     */
    ResponseVO doAudit(AuditRequest request, UserInfo userInfo);

    /**
     * 获取栏目字典
     * @return
     */
    ResponseVO getColDicts();


    /**
     * 点赞
     * @param request
     * @param userId
     * @return
     */
    ResponseVO doLike(AddLikeRequest request, String userId);
}
