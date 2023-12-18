package io.toolisticon.beanbuilder.processor;

import io.toolisticon.aptk.tools.BeanUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
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

        MatcherAssert.assertThat(unit.getWrappedAttribute(), CoreMatchers.equalTo(attributeResult));

    }

}
