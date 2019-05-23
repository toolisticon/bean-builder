package io.toolisticon.beanbuilder.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * BeanBuilder annotation.
 * Can be used to create builder of annotated class.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(value = {ElementType.TYPE})
@Documented
public @interface BeanBuilder {
    /**
     * The target package name to create the builder in.
     * Defaults to same package as the annotated class.
     *
     * @return the target package name
     */
    String packageName() default "";

    /**
     * The target class name of the builder.
     * Defaults to the annotated class name with additional Builder suffix.
     *
     * @return the target class name
     */
    String className() default "";

    /**
     * Defines if inherited fields should also be handled by the builder.
     *
     * @return defaults to true
     */
    boolean inheritFields() default true;

}
