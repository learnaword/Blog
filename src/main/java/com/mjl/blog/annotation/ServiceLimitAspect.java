package com.mjl.blog.annotation;

import com.google.common.util.concurrent.RateLimiter;
import com.mjl.blog.common.exception.ServerException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static com.mjl.blog.common.enums.GlobalErrorCodeConstants.TOO_MANY_REQUESTS;

@Aspect
@Component
public class ServiceLimitAspect {
    private RateLimiter rateLimiter = RateLimiter.create(10);

    @Pointcut("@annotation(com.mjl.blog.annotation.ServiceLimit)")
    public void serviceLimit() {

    }

    @Around("serviceLimit()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Boolean flag = rateLimiter.tryAcquire();
        Object obj = null;
        if (flag) {
            //执行目标方法
            //不能在这里添加try-catch，否则GlobalExceptionHandler不处理
            obj = joinPoint.proceed();
        }else{
            throw new ServerException(TOO_MANY_REQUESTS);
        }

        return obj;
    }
}
