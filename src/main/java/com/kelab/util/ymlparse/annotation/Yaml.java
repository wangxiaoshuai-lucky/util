package com.kelab.util.ymlparse.annotation;


import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Yaml {
    String value() default "";
}
