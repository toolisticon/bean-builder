package io.toolisticon.example.beanbuilderapexample;


import io.toolisticon.beanbuilder.api.BeanBuilder;
import lombok.Data;

@BeanBuilder(className = "MurcksBuilder")
@Data
public class CustomClassNameTestBean {
    private Long longField;
    private String stringField;
}
