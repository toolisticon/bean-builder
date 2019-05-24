package io.toolisticon.beanbuilder.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(value = {ElementType.PACKAGE})
@Documented
public @interface ThirdPartyBeanBuilder {

    /**
     * The third party beans to generate builder for.
     *
     * @return defaults to empty array
     */
    Class[] value() default {};

}
