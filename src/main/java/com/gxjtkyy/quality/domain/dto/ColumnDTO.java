package com.gxjtkyy.quality.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Package com.gxjtkyy.domain.dto
 * @Author lizhenhua
 * @Date 2018/5/31 8:33
 */
@Setter
@Getter
@ToString
public class ColumnDTO implements Serializable{

    /**序号*/
    private Integer id;

    /**父级ID*/
    private Integer parentId;

    /**栏目名称*/
    private String colName;


}
