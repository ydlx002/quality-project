package com.gxjtkyy.quality.service.impl;

import com.alibaba.fastjson.JSON;
import com.gxjtkyy.quality.domain.vo.CommentVO;
import com.gxjtkyy.quality.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Package com.gxjtkyy.quality.service.impl
 * @Author lizhenhua
 * @Date 2018/5/31 17:55
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void addComment() throws Exception {

    }

    @Test
    public void getComments() throws Exception {
//        List<CommentVO> list = commentService.getComments(1);
//        System.out.println(JSON.toJSON(list));
    }

}