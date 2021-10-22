package com.rick.springboot.mapping;

import java.lang.annotation.*;

/**
 * @author Rick
 * @createdAt 2021-10-21 15:48:00
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@RequestMapping
public @interface MyMapping {
    String[] value() default {};
    String version() default "";
}
