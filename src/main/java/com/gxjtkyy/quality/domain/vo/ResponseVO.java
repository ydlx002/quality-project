package com.gxjtkyy.quality.domain.vo;

import com.gxjtkyy.quality.constants.ResultType;
import com.gxjtkyy.quality.exception.SystemException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 响应基础类
 * @Package com.gxjtkyy.domain.vo
 * @Author lizhenhua
 * @Date 2018/5/29 8:53
 */
@Setter
@Getter
@ToString
public class ResponseVO extends BaseVO{

    public ResponseVO(){
        code = 1000;
        msg = "success";
    }

    public ResponseVO(ResultType resultType){
        code = resultType.getCode();
        msg = resultType.getMsg();
    }

    /**响应码*/
    private Integer code;

    /**响应描述*/
    private String msg;

    /**响应数据*/
    private Object data;

    public ResponseVO invoke(SystemException e){
        this.setCode(e.getCode());
        this.setMsg(e.getMsg());
        return this;
    }

    public ResponseVO invoke(ResultType resultType){
        this.setCode(resultType.getCode());
        this.setMsg(resultType.getMsg());
        return this;
    }

}
