package com.gxjtkyy.quality.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Package com.gxjtkyy.domain.dto
 * @Author lizhenhua
 * @Date 2018/5/31 8:54
 */
@Setter
@Getter
@ToString
public class LikeDTO implements Serializable{
    
    /**序号*/
    private Integer id;
    
    /**目标类型*/
    private Integer targetType;
    
    /**目标ID*/
    private Integer targetId;
    
    /**用户ID*/
    private String userId;
    

}
