package io.toolisticon.spiap.processor.tests;

import io.toolisticon.beanbuilder.api.BeanBuilder;

@BeanBuilder(className = "CustomTestBeanBuilder")
public class TestcaseValidUsageWithCustomClassName {

    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}