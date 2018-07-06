package io.toolisticon.spiap.processor.tests;

import io.toolisticon.beanbuilder.api.BeanBuilder;

@BeanBuilder
public abstract class TestcaseInvalidUsageAbstractClass {

    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}