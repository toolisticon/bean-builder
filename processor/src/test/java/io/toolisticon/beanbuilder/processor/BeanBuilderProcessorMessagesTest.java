package io.toolisticon.beanbuilder.processor;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Unit test for {@link BeanBuilderProcessorMessages}.
 */
public class BeanBuilderProcessorMessagesTest {

    @Test
    public void test_enum() {

        MatcherAssert.assertThat(BeanBuilderProcessorMessages.ERROR_COULD_NOT_CREATE_BUILDER_CLASS.getCode(), Matchers.startsWith("SERVICE_ERROR"));
        MatcherAssert.assertThat(BeanBuilderProcessorMessages.ERROR_COULD_NOT_CREATE_BUILDER_CLASS.getMessage(), Matchers.containsString("Builder"));

    }


}
