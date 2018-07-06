package io.toolisticon.spiap.processor.tests;

import io.toolisticon.beanbuilder.api.BeanBuilder;
import io.toolisticon.beanbuilder.api.Service;
import io.toolisticon.beanbuilder.processor.serviceprocessortest.TestSpi;

@BeanBuilder
public class TestcaseInvalidUsageNoNoargConstructor {

    private String field;

    public  TestcaseInvalidUsageNoNoargConstructor (String arg) {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}