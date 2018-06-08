package com.gxjtkyy.quality.controller;

import com.gxjtkyy.quality.constants.QuaqlityConstant;
import com.gxjtkyy.quality.constants.ResultType;
import com.gxjtkyy.quality.domain.BriefArticle;
import com.gxjtkyy.quality.domain.UserInfo;
import com.gxjtkyy.quality.domain.vo.CommentVO;
import com.gxjtkyy.quality.domain.vo.PageResultVO;
import com.gxjtkyy.quality.domain.vo.ResponseVO;
import com.gxjtkyy.quality.domain.vo.request.AddCommentRequest;
import com.gxjtkyy.quality.domain.vo.request.QueryCommentsRequest;
import com.gxjtkyy.quality.service.ArticleService;
import com.gxjtkyy.quality.service.CommentService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 评论区控制器
 * @Package com.gxjtkyy.quality.controller
 * @Author lizhenhua
 * @Date 2018/5/31 16:08
 */
@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;


    /**
     * 新增评论
     * @param request 请求
     * @param httpRequest
     * @return
     */
    @ApiOperation(value="添加评论", notes="添加文章评论或评论回复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "登录凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "request", value = "添加评论请求体", required = true, dataType = "AddCommentRequest", paramType = "body")
    })
    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseVO add(@RequestBody AddCommentRequest request, HttpServletRequest httpRequest){
        Claims claims = (Claims) httpRequest.getAttribute(QuaqlityConstant.CLAIMS);
        if(null == claims){
            return new ResponseVO(ResultType.INVALIDTOKEN);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(String.valueOf(claims.get("userId")));
        userInfo.setUserName(String.valueOf(claims.get("userName")));
        userInfo.setUserType(Integer.valueOf((String)claims.get("userType")));
        userInfo.setIp(httpRequest.getRemoteAddr());
        return commentService.addComment(request, userInfo);
    }


    /**
     * 获取评论列表
     * @param request
     * @return
     */
    @ApiOperation(value="获取文章评论列表", notes="查询文章评论列表")
    @ApiImplicitParam(name = "request", value = "评论列表请求体", required = true, dataType = "QueryCommentsRequest", paramType = "body")
    @PostMapping(value = "/getComments")
    @ResponseBody
    public ResponseVO getComments(@RequestBody QueryCommentsRequest request ){
        return commentService.getComments(request.getArticleId());
    }
}
