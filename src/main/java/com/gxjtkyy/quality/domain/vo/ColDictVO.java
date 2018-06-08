package com.gxjtkyy.quality.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 栏目
 * @Package com.gxjtkyy.quality.domain.vo
 * @Author lizhenhua
 * @Date 2018/6/4 12:37
 */
@Data
public class ColDictVO implements Serializable{
    
    /**栏目ID*/
    private Integer colId;
    
    /**栏目名称*/
    private String colName;
    

}
