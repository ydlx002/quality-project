package com.gxjtkyy.quality.aop;

import com.alibaba.fastjson.JSON;
import com.gxjtkyy.quality.domain.vo.request.Request;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面
 *
 * @Package com.gxjtkyy.quality.aop
 * @Author lizhenhua
 * @Date 2018/6/6 15:56
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private HttpServletRequest httpRequest;

    @Pointcut("execution(public * com.gxjtkyy.quality.controller..*(..))")
    private void log() {
    }


    @Around("log()")  // 使用上面定义的切入点
    public Object saveLog(ProceedingJoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Object request = null;
            for (Object obj : args) {
                if (obj instanceof Request) {
                    request = obj;
                    break;
                }
            }
            Object obj = joinPoint.proceed();
            log.debug("path-->{},\n ip-->{}, \n request-->{},\n response-->{}",httpRequest.getRequestURI(), httpRequest.getRemoteAddr(), JSON.toJSON(request), JSON.toJSON(obj));
            return obj;
        } catch (Throwable throwable) {
            log.error("拦截日志异常", throwable);
        }
        return null;
    }
}
