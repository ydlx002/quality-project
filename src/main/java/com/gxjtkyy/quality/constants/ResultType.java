package com.gxjtkyy.quality.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 1000  系统错误
 * 2000  用户管理错误
 * 3000  角色管理错误
 * 4000  权限管理错误
 * 5000  菜单管理
 * 9000  参数错误
 * Create by gxjtkyy on 2017/5/3.
 */
@AllArgsConstructor
@Getter
public enum ResultType {
    FAIL(9999, "fail"),
    SUCCESS(1000, "success"),
    INVALIDTOKEN(2001, "登录凭证无效或已过期"),
    LOGINERROR(2002, "账号或密码错误"),
    INVALID_CURRENT_PAGE(2003, "currentPage无效"),
    INVALID_PAGESIZE(2004, "pageSize无效"),
    INVALID_COLID(2005, "colId无效"),
    PERMISSION_DENY(2006, "您无权访问"),
    ARTICLE_NO_EXISTS(2007, "文章不存在"),
    COLUMN_NO_EXISTS(2008, "栏目不存在"),
    INVALID_ARTICLE(2009, "文章不存在或审核未通过"),
    INVALID_COMMENT(2010, "评论不存在或审核未通过"),
    NULL_FOR_ARTICLEID_AND_COMMENTID(2011, "articleId和commentId不能同时为空"),
    LIKES_ALREADY_EXISTS(2012, "已点赞"),
    COMMENT_EXISTS(2013, "评论不存在"),
    ACCOUNT_EXISTS(2014, "账号已存在"),
    ACCOUNT_NO_EXISTS(2015, "账号不存在")
    ;

    private int code;

    private String Msg;

}
