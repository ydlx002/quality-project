package com.gxjtkyy.quality.service;

import com.gxjtkyy.quality.domain.UserInfo;
import com.gxjtkyy.quality.domain.vo.ResponseVO;
import com.gxjtkyy.quality.domain.vo.request.QueryUserPageRequest;
import com.gxjtkyy.quality.domain.vo.request.RegistRequest;
import com.gxjtkyy.quality.domain.vo.request.UpdateUserRequest;

/**
 * @Package com.gxjtkyy.quality.service
 * @Author lizhenhua
 * @Date 2018/6/1 12:56
 */
public interface UserService {

    /**
     * 新增用户
     * @param request
     * @return
     */
    ResponseVO addUser(RegistRequest request);


    /**
     * 更新用户信息
     * @param request
     * @return
     */
    ResponseVO updateUser(UpdateUserRequest request, UserInfo userInfo);


    /**
     * 分页查询用户列表
     * @param request
     * @return
     */
    ResponseVO getUserByPage(QueryUserPageRequest request, UserInfo userInfo);
}
