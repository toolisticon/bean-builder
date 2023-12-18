package io.toolisticon.beanbuilder.integrationtest;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

/**
 * Test to check if builder is immutable.s
 */
public class ImmutableBuilderTest {

    @Test
    public void testImmutableBuilder() {
        TestBeanBuilder testBeanBuilder1 = TestBeanBuilder.createBuilder();

        TestBeanBuilder testBeanBuilder2 = testBeanBuilder1.withFieldA("A");
        MatcherAssert.assertThat(testBeanBuilder1, CoreMatchers.not(testBeanBuilder2));

        TestBeanBuilder testBeanBuilder3 = testBeanBuilder2.withFieldB(5L);
        MatcherAssert.assertThat(testBeanBuilder2, CoreMatchers.not(testBeanBuilder3));

        testBeanBuilder3.withFieldA("B").withFieldB(6L);

        TestBean testBean = testBeanBuilder3.build();

        MatcherAssert.assertThat(testBean.getFieldA(), CoreMatchers.is("A"));
        MatcherAssert.assertThat(testBean.getFieldB(), CoreMatchers.is(5L));


    }

}
