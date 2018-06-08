package com.gxjtkyy.quality;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @Package com.gxjtkyy.quality
 * @Author lizhenhua
 * @Date 2018/6/8 9:51
 */
public class WarApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(QualityApplication.class);
    }

}
