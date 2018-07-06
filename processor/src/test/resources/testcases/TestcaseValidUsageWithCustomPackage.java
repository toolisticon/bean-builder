package io.toolisticon.spiap.processor.tests;

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