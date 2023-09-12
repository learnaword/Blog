package com.mjl.blog.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface SystemLog {

    String description()  default "";

    String userType()  default "管理员";
}
