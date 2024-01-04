package io.toolisticon.spiap.processor.tests;

import io.toolisticon.beanbuilder.api.BeanBuilder;
import java.util.Collection;

@BeanBuilder
public class TestcaseValidUsageWithTypeParameters <T extends Collection<?>> {

    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }


    private boolean primitiveValue;

    public boolean isPrimitiveValue() {
        return primitiveValue;
    }

    public void setPrimitiveValue(boolean primitiveValue) {
        this.primitiveValue = primitiveValue;
    }

    public T typeVarValue;

    public T getTypeVarValue() {
        return typeVarValue;
    }

    public void setTypeVarValue(T typeVarValue) {
        this.typeVarValue = typeVarValue;
    }

}