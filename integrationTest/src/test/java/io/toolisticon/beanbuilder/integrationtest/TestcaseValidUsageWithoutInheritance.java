package io.toolisticon.beanbuilder.integrationtest;

import io.toolisticon.beanbuilder.api.BeanBuilder;

@BeanBuilder(inheritFields = false)
public class TestcaseValidUsageWithoutInheritance extends TestcaseBeanSuperClass {

    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}