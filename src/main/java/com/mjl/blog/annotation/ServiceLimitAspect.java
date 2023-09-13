package com.mjl.blog.annotation;

import com.google.common.util.concurrent.RateLimiter;
import com.mjl.blog.common.exception.ServerException;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static com.mjl.blog.common.enums.GlobalErrorCodeConstants.TOO_MANY_REQUESTS;
import static com.mjl.blog.common.exception.utils.ServiceExceptionUtil.exception;

@Aspect
@Component
public class ServiceLimitAspect {
    private RateLimiter rateLimiter = RateLimiter.create(10);

    @Pointcut("@annotation(com.mjl.blog.annotation.ServiceLimit)")
    public void serviceLimit() {

    }

    @Around("serviceLimit()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Boolean flag = rateLimiter.tryAcquire();
        Object obj = null;
        if (flag) {
            try {
                obj = joinPoint.proceed();
            }catch (Throwable e) {
                e.printStackTrace();
            }
        }else{
            throw new ServerException(TOO_MANY_REQUESTS);
        }

        return obj;
    }
}
