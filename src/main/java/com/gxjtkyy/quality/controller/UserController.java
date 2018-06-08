package com.gxjtkyy.quality.controller;

import com.gxjtkyy.quality.constants.QuaqlityConstant;
import com.gxjtkyy.quality.constants.ResultType;
import com.gxjtkyy.quality.domain.UserInfo;
import com.gxjtkyy.quality.domain.vo.ResponseVO;
import com.gxjtkyy.quality.domain.vo.request.QueryBriefArticleRequest;
import com.gxjtkyy.quality.domain.vo.request.QueryUserPageRequest;
import com.gxjtkyy.quality.domain.vo.request.RegistRequest;
import com.gxjtkyy.quality.domain.vo.request.UpdateUserRequest;
import com.gxjtkyy.quality.service.UserService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理
 * @Package com.gxjtkyy.quality.controller
 * @Author lizhenhua
 * @Date 2018/6/5 19:00
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页获取用户列表
     * @return
     */
    @ApiOperation(value="分页获取用户列表", notes="分页获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "登录凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "request", value = "分页查询请求体", required = true, dataType = "QueryUserPageRequest", paramType = "body")
    })
    @PostMapping(value = "/queryUserList")
    @ResponseBody
    public ResponseVO queryUserList(@RequestBody QueryUserPageRequest request, HttpServletRequest httpRequest){
        Claims claims = (Claims) httpRequest.getAttribute(QuaqlityConstant.CLAIMS);
        if(null == claims){
            return new ResponseVO(ResultType.INVALIDTOKEN);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(String.valueOf(claims.get("userId")));
        userInfo.setUserName(String.valueOf(claims.get("userName")));
        userInfo.setUserType(Integer.valueOf((String)claims.get("userType")));
        return userService.getUserByPage(request, userInfo);
    }

    /**
     * 分页获取用户列表
     * @return
     */
    @ApiOperation(value="更新用户信息", notes="更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "登录凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "request", value = "更新用户信息请求体", required = true, dataType = "UpdateUserRequest", paramType = "body")
    })
    @PostMapping(value = "/updateUser")
    @ResponseBody
    public ResponseVO updateUser(@RequestBody UpdateUserRequest request, HttpServletRequest httpRequest){
        Claims claims = (Claims) httpRequest.getAttribute(QuaqlityConstant.CLAIMS);
        if(null == claims){
            return new ResponseVO(ResultType.INVALIDTOKEN);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(String.valueOf(claims.get("userId")));
        userInfo.setUserName(String.valueOf(claims.get("userName")));
        userInfo.setUserType(Integer.valueOf((String)claims.get("userType")));
        return userService.updateUser(request, userInfo);
    }


    /**
     * 分页获取用户列表
     * @return
     */
    @ApiOperation(value="注册用户信息", notes="注册用户信息")
    @ApiImplicitParam(name = "request", value = "注册用户信息请求体", required = true, dataType = "RegistRequest", paramType = "body")
    @PostMapping(value = "/registUser")
    @ResponseBody
    public ResponseVO registUser(@RequestBody RegistRequest request ){
        return userService.addUser(request);
    }

}
