package com.gxjtkyy.quality.domain.vo;

import com.gxjtkyy.quality.domain.BriefArticle;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 栏目视图
 * @Package com.gxjtkyy.domain.vo
 * @Author lizhenhua
 * @Date 2018/5/31 9:00
 */
@Setter
@Getter
@ToString
public class ColumnVO implements Serializable{

    /**栏目ID*/
    private Integer colId;

    /**栏目名称*/
    private String colName;

    /**摘要列表*/
    private List<BriefArticle> briefArticleList;
}
