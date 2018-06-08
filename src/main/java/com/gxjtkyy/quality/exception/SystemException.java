package com.gxjtkyy.quality.exception;

import com.gxjtkyy.quality.constants.ResultType;
import lombok.Getter;

/**
 * 自定义系统异常
 * @Package com.gxjtkyy.exception
 * @Author lizhenhua
 * @Date 2018/5/24 16:33
 */

@Getter
public class SystemException extends Exception{

    private int code;

    private String msg;

    public SystemException(){
        super();
    }

    public SystemException(String message){
        super(message);
    }

    public SystemException(String message, Throwable cause){
        super(message, cause);
    }

    public SystemException(Throwable cause){
        super( cause);
    }

    public SystemException(ResultType resultType){
        this.code = resultType.getCode();
        this.msg = resultType.getMsg();
    }


}
