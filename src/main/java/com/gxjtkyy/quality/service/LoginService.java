package com.gxjtkyy.quality.service;

import com.gxjtkyy.quality.domain.vo.ResponseVO;

/**
 * 登录接口
 * @Package com.gxjtkyy.quality.service
 * @Author lizhenhua
 * @Date 2018/6/1 22:15
 */
public interface LoginService {

    ResponseVO login(String userAccount, String password);
}
