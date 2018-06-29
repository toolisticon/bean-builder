package io.toolisticon.example.beanbuilderapexample;

import io.toolisticon.beanbuilder.api.BeanBuilder;
import lombok.Data;

@BeanBuilder
@Data
public class TestBean {

    private String stringField;
    private Long longField;

}
