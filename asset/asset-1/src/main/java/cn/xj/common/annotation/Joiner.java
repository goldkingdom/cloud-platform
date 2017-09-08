package cn.xj.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Welink on 2017/4/1.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Joiner {

    //引用的Class
    String refClass() default "";

    //用什么键来关联
    String key() default "";

    //关联的键
    String refKey() default "";

}
