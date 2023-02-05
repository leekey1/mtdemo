package cn.com.hyun.common.auth;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by zhg
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthCode {
    String[] codes() default {""};
    boolean required() default false;
}
