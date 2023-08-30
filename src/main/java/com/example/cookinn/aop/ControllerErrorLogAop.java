package com.example.cookinn.aop;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerErrorLogAop {

    @AfterThrowing(value = "execution(* com.example.cookinn.controller..*(..))", throwing = "ex")
    public void errorLog(JoinPoint joinPoint, Exception ex) {
        String controllerName = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.error("컨트롤러 에러 -> {}.{}: {}", controllerName, methodName, ex.getMessage());
    }
}
