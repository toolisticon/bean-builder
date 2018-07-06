package io.toolisticon.example.beanbuilderapexample;

import io.toolisticon.beanbuilder.api.BeanBuilder;
import lombok.Data;

@BeanBuilder
@Data
public class TestBeanWithInheritance extends TestBean{
    private String myField;
    private String stringField;
}
