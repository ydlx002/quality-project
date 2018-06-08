package com.gxjtkyy.quality.exception;

import com.gxjtkyy.quality.constants.ResultType;
import com.gxjtkyy.quality.domain.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * @Package com.gxjtkyy.quality.exception
 * @Author lizhenhua
 * @Date 2018/6/6 12:40
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVO Handle(Exception e){

        if (e instanceof SystemException){
            SystemException systemException = (SystemException) e;
            return new ResponseVO().invoke(systemException);
        }else {
            //将系统异常以打印出来
            log.error("[系统异常]{}",e);
            return new ResponseVO(ResultType.FAIL);
        }
    }
}
