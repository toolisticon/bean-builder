package io.toolisticon.example.beanbuilderapexample.thridpartybeanbuilderexample;

import io.toolisticon.example.beanbuilderapexample.TestBean;

/**
 * This class demonstrates the usage of the bean builder class created by the {@link io.toolisticon.beanbuilder.api.ThirdPartyBeanBuilder} annotation processor.
 */
public class ThirdPartyBeanBuilderExample {

    public static void main(String[] args) {

        TestBean testBean = TestBeanBuilder.createBuilder().withLongField(1L).withStringField("TEST").build();


    }
}
