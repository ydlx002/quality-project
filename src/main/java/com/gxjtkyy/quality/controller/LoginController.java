package com.gxjtkyy.quality.controller;

import com.gxjtkyy.quality.domain.vo.ResponseVO;
import com.gxjtkyy.quality.domain.vo.request.LoginRequest;
import com.gxjtkyy.quality.service.LoginService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 * @Package com.gxjtkyy.quality.controller
 * @Author lizhenhua
 * @Date 2018/6/1 10:16
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value="登录", notes="登录")
    @ApiImplicitParam(name = "request", value = "登录请求体", required = true, dataType = "LoginRequest", paramType = "body")
    @PostMapping("/login")
    public @ResponseBody ResponseVO login(@RequestBody LoginRequest request){
        return loginService.login(request.getUserAccount(), request.getPassword());
    }

}
