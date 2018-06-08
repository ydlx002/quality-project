package com.gxjtkyy.quality.domain.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章审核实体
 * @Package com.gxjtkyy.quality.domain.vo
 * @Author lizhenhua
 * @Date 2018/6/2 14:16
 */
@Setter
@Getter
@ToString
public class ArticleAuditVO implements Serializable{

    /**序号*/
    private Integer id;

    /**栏目*/
    private String colName;

    /**标题*/
    private String title;

    /**作者*/
    private String author;

    /**状态*/
    private Integer status;

    /**创建时间*/
    private Date createTime;

    /**审核员*/
    private String auditor;

    /**审核意见*/
    private String auditOpinion;

    /**审核时间*/
    private Date auditTime;


}
