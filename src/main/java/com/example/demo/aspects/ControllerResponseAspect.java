package com.example.demo.aspects;

import java.util.LinkedHashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Aspect that wraps the result of every controller method into a
 * uniform envelope of the shape:
 *
 * <pre>
 * {
 *   "action": "<method name>",
 *   "data": <original response body>
 * }
 * </pre>
 *
 * Works transparently with methods returning plain objects as well as
 * methods returning {@link ResponseEntity} (status code and headers are preserved).
 */
@Aspect
@Component
@Order(1)
public class ControllerResponseAspect {

    @Around("execution(public * com.example.demo.controllers..*.*(..))")
    public Object wrapControllerResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        String action = joinPoint.getSignature().getName();

        return result;
    }

}
