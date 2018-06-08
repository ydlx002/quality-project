package com.gxjtkyy.quality.controller;

import com.gxjtkyy.quality.ueditor.ActionEnter;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 富文本编辑器控制器
 * @Package com.gxjtkyy.quality.controller
 * @Author lizhenhua
 * @Date 2018/6/7 9:52
 */
@Controller
@Slf4j
@RequestMapping("/editor")
public class UeditorController {

    @ApiOperation(value="ueditor后台", notes="ueditor后台")
    @ApiImplicitParam(name = "action", value = "操作", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String token = request.getHeader("Authorization");
        System.out.println("------------>"+token);
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            log.error("配置异常",e);
        }
    }

    @GetMapping("/index")
    public ModelAndView add(HttpServletRequest request){
        String token = request.getParameter("token");
        ModelAndView mav = new ModelAndView("editor");
        mav.addObject("token",token);
        return mav;
    }
}
