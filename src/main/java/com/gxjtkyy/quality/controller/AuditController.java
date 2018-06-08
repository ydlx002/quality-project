package com.gxjtkyy.quality.controller;

import com.gxjtkyy.quality.constants.QuaqlityConstant;
import com.gxjtkyy.quality.constants.ResultType;
import com.gxjtkyy.quality.domain.UserInfo;
import com.gxjtkyy.quality.domain.vo.ResponseVO;
import com.gxjtkyy.quality.domain.vo.request.AddArticleRequest;
import com.gxjtkyy.quality.domain.vo.request.AuditRequest;
import com.gxjtkyy.quality.domain.vo.request.QueryArticleRequest;
import com.gxjtkyy.quality.domain.vo.request.QueryCommentPageRequest;
import com.gxjtkyy.quality.service.ArticleService;
import com.gxjtkyy.quality.service.CommentService;
import com.gxjtkyy.quality.service.CommonService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package com.gxjtkyy.quality.controller
 * @Author lizhenhua
 * @Date 2018/6/4 18:49
 */
@RestController
@RequestMapping("/audit")
@Slf4j
public class AuditController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommonService commonService;


    /**
     * 根据文章ID获取文章内容
     * @param request
     * @return
     */
    @ApiOperation(value="新增文章", notes="新增文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "登录凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "request", value = "新增文章请求体", required = true, dataType = "AddArticleRequest", paramType = "body")
    })
    @PostMapping(value = "/article/add")
    @ResponseBody
    public ResponseVO add(@RequestBody AddArticleRequest request, HttpServletRequest httpRequest){
        Claims claims = (Claims) httpRequest.getAttribute(QuaqlityConstant.CLAIMS);
        if(null == claims){
            return new ResponseVO(ResultType.PERMISSION_DENY);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(String.valueOf(claims.get("userId")));
        userInfo.setUserName(String.valueOf(claims.get("userName")));
        userInfo.setUserType(Integer.valueOf((String)claims.get("userType")));
        return articleService.addArticle(request, userInfo);
    }


    /**
     * 分页获取栏目摘要列表
     * @return
     */
    @ApiOperation(value="分页获取文章审核列表", notes="分页获取文章审核列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "登录凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "request", value = "分页请求体", required = true, dataType = "QueryArticleRequest", paramType = "body")
    })
    @PostMapping(value = "/article/getArticles", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseVO getArticles(@RequestBody QueryArticleRequest request, HttpServletRequest httpRequest){
        Claims claims = (Claims) httpRequest.getAttribute(QuaqlityConstant.CLAIMS);
        if(null == claims){
            return new ResponseVO(ResultType.PERMISSION_DENY);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(String.valueOf(claims.get("userId")));
        userInfo.setUserName(String.valueOf(claims.get("userName")));
        userInfo.setUserType(Integer.valueOf((String)claims.get("userType")));
        return articleService.getArticles(request, userInfo);
    }

    /**
     * 分页评论审核列表
     * @return
     */
    @ApiOperation(value="分页评论审核列表", notes="分页评论审核列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "登录凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "request", value = "分页请求体", required = true, dataType = "QueryCommentPageRequest", paramType = "body")
    })
    @PostMapping(value = "/comment/getComments", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseVO getComments(@RequestBody QueryCommentPageRequest request, HttpServletRequest httpRequest){
        Claims claims = (Claims) httpRequest.getAttribute(QuaqlityConstant.CLAIMS);
        if(null == claims){
            return new ResponseVO(ResultType.PERMISSION_DENY);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(String.valueOf(claims.get("userId")));
        userInfo.setUserName(String.valueOf(claims.get("userName")));
        userInfo.setUserType(Integer.valueOf((String)claims.get("userType")));
        return commentService.getCommentsByPage(request, userInfo);
    }

    /**
     * 审核
     * @return
     */
    @ApiOperation(value="审核文章或评论", notes="审核文章或评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "登录凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "request", value = "审核请求体", required = true, dataType = "AuditRequest", paramType = "body")
    })
    @PostMapping(value = "/doAudit", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseVO doAudit(@RequestBody AuditRequest request, HttpServletRequest httpRequest){
        Claims claims = (Claims) httpRequest.getAttribute(QuaqlityConstant.CLAIMS);
        if(null == claims){
            return new ResponseVO(ResultType.PERMISSION_DENY);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(String.valueOf(claims.get("userId")));
        userInfo.setUserName(String.valueOf(claims.get("userName")));
        userInfo.setUserType(Integer.valueOf((String)claims.get("userType")));
        return commonService.doAudit(request, userInfo);
    }
}
