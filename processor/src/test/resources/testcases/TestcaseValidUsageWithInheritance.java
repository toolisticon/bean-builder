package io.toolisticon.spiap.processor.tests;

import io.toolisticon.beanbuilder.api.BeanBuilder;
import io.toolisticon.beanbuilder.processor.TestcaseBeanSuperClass;

@BeanBuilder
public class TestcaseValidUsageWithInheritance extends TestcaseBeanSuperClass {

    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}