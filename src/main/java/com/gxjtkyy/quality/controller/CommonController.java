package com.gxjtkyy.quality.controller;

import com.gxjtkyy.quality.constants.QuaqlityConstant;
import com.gxjtkyy.quality.constants.ResultType;
import com.gxjtkyy.quality.domain.vo.ResponseVO;
import com.gxjtkyy.quality.domain.vo.request.AddArticleRequest;
import com.gxjtkyy.quality.domain.vo.request.AddLikeRequest;
import com.gxjtkyy.quality.service.CommonService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Locale.ROOT;

/**
 * 公共控制器
 * @Package com.gxjtkyy.quality.controller
 * @Author lizhenhua
 * @Date 2018/6/4 19:51
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private ResourceLoader resourceLoader;

    private static final String filePath = "upload/images";

    /**
     * 根据文章ID获取文章内容
     * @param request
     * @return
     */
    @ApiOperation(value="点赞", notes="点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "登录凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "request", value = "新增点赞请求体", required = true, dataType = "AddLikeRequest", paramType = "body")
    })
    @PostMapping(value = "/addLike")
    @ResponseBody
    public ResponseVO addLike(@RequestBody AddLikeRequest request, HttpServletRequest httpRequest){
        Claims claims = (Claims) httpRequest.getAttribute(QuaqlityConstant.CLAIMS);
        if(null == claims){
            return new ResponseVO(ResultType.INVALIDTOKEN);
        }
        String userId = String.valueOf(claims.get("userId"));
        return commonService.doLike(request, userId);
    }


    /**
     * 处理文件上传
     * @param file
     * @return
     */
    @ApiOperation(value="上传图片", notes="上传图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "登录凭证", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "image", value = "图片流", required = true, dataType = "MultipartFile", paramType = "form")
    })
    @PostMapping(value="/upload")
    public @ResponseBody ResponseVO upload(@RequestParam("image") MultipartFile file, HttpServletRequest httpRequest) {
        ResponseVO response = new ResponseVO();
        Claims claims = (Claims) httpRequest.getAttribute(QuaqlityConstant.CLAIMS);
        if(null == claims){
            return new ResponseVO(ResultType.INVALIDTOKEN);
        }
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString().replace("-","")+suffix;

        try {
            Files.copy(file.getInputStream(), Paths.get("upload/images",newFileName));
            Map<String, String> map = new HashMap<>();
            map.put("path","/image/"+newFileName);
            response.setData(map);
        } catch (IOException e) {
            log.error("上传异常", e);
            response.invoke(ResultType.FAIL);
        }
        //返回json
        return response;
    }

}
