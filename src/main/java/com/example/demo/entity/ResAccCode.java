package com.example.demo.entity;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResAccCode {
    @AliasFor("value")
    String resCode() default "";

    @AliasFor("resCode")
    String value() default "";

    String redirectUrl() default "/";
}
