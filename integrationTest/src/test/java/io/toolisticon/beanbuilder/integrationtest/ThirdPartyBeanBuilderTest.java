package io.toolisticon.beanbuilder.integrationtest;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

import org.junit.Test;

public class ThirdPartyBeanBuilderTest {

    @Test
    public void test3rdPartyBeanBuilder() {

        TestBean testBean = TestBeanBuilder.createBuilder().withFieldA("A").withFieldB(1L).build();

        MatcherAssert.assertThat(testBean.getFieldA(), CoreMatchers.is("A"));
        MatcherAssert.assertThat(testBean.getFieldB(), CoreMatchers.is(1L));

    }

}
