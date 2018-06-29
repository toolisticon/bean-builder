package io.toolisticon.beanbuilder.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(value = {ElementType.TYPE})
public @interface BeanBuilder {

    String packageName() default "";
    String className() default "";
    boolean inheritFields() default true;

}
