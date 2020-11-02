package io.toolisticon.example.beanbuilderapexample;

import io.toolisticon.beanbuilder.api.BeanBuilder;
import lombok.Data;

@BeanBuilder(packageName = "io.toolisticon.example.murks")
@Data
public class CustomPackageTestBean {
    private Long longField;
    private String stringField;
}
