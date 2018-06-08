package com.gxjtkyy.quality.controller;

import com.gxjtkyy.quality.constants.QuaqlityConstant;
import com.gxjtkyy.quality.constants.ResultType;
import com.gxjtkyy.quality.domain.UserInfo;
import com.gxjtkyy.quality.domain.vo.ArticleVO;
import com.gxjtkyy.quality.domain.vo.ResponseVO;
import com.gxjtkyy.quality.domain.vo.request.AddArticleRequest;
import com.gxjtkyy.quality.domain.vo.request.QueryArticleRequest;
import com.gxjtkyy.quality.domain.vo.request.QueryBriefArticleRequest;
import com.gxjtkyy.quality.domain.vo.request.QueryDetailArticleRequest;
import com.gxjtkyy.quality.service.ArticleService;
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
 * 文章管理控制器
 * @Package com.gxjtkyy.quality.controller
 * @Author lizhenhua
 * @Date 2018/5/31 10:59
 */
@RestController
@RequestMapping("/column")
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommonService commonService;


    /**
     * 分页获取栏目摘要列表
     * @return
     */
    @ApiOperation(value="分页查询栏目文章摘要", notes="根据栏目ID分页查询栏目文章摘要")
    @ApiImplicitParam(name = "request", value = "栏目摘要查询请求体", required = true, dataType = "QueryBriefArticleRequest", paramType = "body")
    @PostMapping(value = "/article/getBriefArticles")
    @ResponseBody
    public ResponseVO getBriefArticles(@RequestBody QueryBriefArticleRequest request){
        return articleService.getBriefArticles(request);
    }


    /**
     * 根据文章ID获取文章内容
     * @param request 文章id
     * @return
     */
    @ApiOperation(value="获取文章内容", notes="根据文章ID获取文章内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "登录凭证",  dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "request", value = "详细文章查询请求体", required = true, dataType = "QueryDetailArticleRequest", paramType = "body")
    })
    @PostMapping(value = "/article/getArticle")
    @ResponseBody
    public ResponseVO getArticle(@RequestBody QueryDetailArticleRequest request, HttpServletRequest httpRequest){
        Claims claims = (Claims) httpRequest.getAttribute(QuaqlityConstant.CLAIMS);
        UserInfo userInfo = null;
        if(null != claims){
        userInfo = new UserInfo();
        userInfo.setUserId(String.valueOf(claims.get("userId")));
        userInfo.setUserName(String.valueOf(claims.get("userName")));
        userInfo.setUserType(Integer.valueOf((String)claims.get("userType")));
        }
        return articleService.getArticle(request.getArticleId(), userInfo);
    }


    /**
     * 分页获取栏目摘要列表
     * @return
     */
    @ApiOperation(value="获取栏目名称", notes="获取栏目序号和名称")
    @PostMapping(value = "/getColDicts")
    @ResponseBody
    public ResponseVO getColDicts(){
        return commonService.getColDicts();
    }
}
