package com.gxjtkyy.quality.service.impl;

import com.gxjtkyy.quality.config.Audience;
import com.gxjtkyy.quality.constants.ResultType;
import com.gxjtkyy.quality.dao.UserMapper;
import com.gxjtkyy.quality.domain.dto.UserDTO;
import com.gxjtkyy.quality.domain.vo.ResponseVO;
import com.gxjtkyy.quality.domain.vo.TokenVO;
import com.gxjtkyy.quality.exception.SystemException;
import com.gxjtkyy.quality.service.LoginService;
import com.gxjtkyy.quality.utils.JwtUtil;
import com.gxjtkyy.quality.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package com.gxjtkyy.quality.service.impl
 * @Author lizhenhua
 * @Date 2018/6/1 22:21
 */
@Slf4j
@Service
public class LoginServiceIpml implements LoginService{

    @Resource
    private UserMapper userMapper;

    @Autowired
    private Audience audience;

    @Override
    public ResponseVO login(String userAccount, String password) {
        ResponseVO response = new ResponseVO();
        UserDTO user = userMapper.getUser(userAccount, MD5Util.passwordEncoder(password));
        try {
            if(null == user){
                throw new SystemException(ResultType.LOGINERROR);
            }
            String jwtToken = JwtUtil.createJWT(user.getUserId(),
                    user.getUserName(),
                    user.getUserType().toString(),
                    audience.getClientId(),
                    audience.getName(),
                    audience.getExpiresSecond()*1000,
                    audience.getBase64Secret());
            String token = "bearer;" + jwtToken;
            TokenVO tokenVO = new TokenVO();
            tokenVO.setToken(token);
            tokenVO.setExpire(audience.getExpiresSecond());
            tokenVO.setUserName(user.getUserName());
            tokenVO.setUserType(user.getUserType());
            response.setData(tokenVO);
        } catch (SystemException e) {
            log.error("登录异常",e);
            response.invoke(e);
        }
        return response;
    }
}
