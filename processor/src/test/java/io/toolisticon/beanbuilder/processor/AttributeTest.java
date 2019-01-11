package io.toolisticon.beanbuilder.processor;

import io.toolisticon.annotationprocessortoolkit.tools.BeanUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit test for {@link Attribute}.
 */
public class AttributeTest {


    @Test
    public void getWrappedAttribute_shouldReturnWrappedAttribute() {

        BeanUtils.AttributeResult attributeResult = Mockito.mock(BeanUtils.AttributeResult.class);
        Attribute unit = new Attribute(attributeResult);

        MatcherAssert.assertThat(unit.getWrappedAttribute(), Matchers.is(attributeResult));

    }

}
