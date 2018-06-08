package com.gxjtkyy.quality.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 注册后台页面控制器
 * @Package com.gxjtkyy.quality.config
 * @Author lizhenhua
 * @Date 2018/6/2 13:17
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/admin/index").setViewName("index");
        registry.addViewController("/admin/article").setViewName("article");
    }

}
