package io.toolisticon.beanbuilder.integrationtest;

import io.toolisticon.beanbuilder.api.BeanBuilder;

@BeanBuilder(packageName = "io.toolisticon.customPackage")
public class TestcaseValidUsageWithCustomPackage {

    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}