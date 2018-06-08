package com.gxjtkyy.quality.service;

import com.alibaba.fastjson.JSON;
import com.gxjtkyy.quality.domain.vo.PageResultVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Package com.gxjtkyy.quality.service
 * @Author lizhenhua
 * @Date 2018/5/31 17:39
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void getBriefArticles() throws Exception {
//        PageResultVO vo = articleService.getBriefArticles(1,1,12);
//        System.out.println(JSON.toJSON(vo));
    }

    @Test
    public void getArticle() throws Exception {

    }

}