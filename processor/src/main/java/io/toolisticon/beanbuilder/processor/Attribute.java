package io.toolisticon.beanbuilder.processor;


import io.toolisticon.annotationprocessortoolkit.tools.BeanUtils;

public class Attribute {

    private final BeanUtils.AttributeResult wrappedAttribute;

    public Attribute(BeanUtils.AttributeResult wrappedAttribute) {
        this.wrappedAttribute = wrappedAttribute;
    }

    public String getFieldName() {
        return wrappedAttribute.getFieldName();
    }

    public String getCCFieldName () {
        return wrappedAttribute.getFieldName().substring(0,1).toUpperCase() + (wrappedAttribute.getFieldName().length() > 1 ? wrappedAttribute.getFieldName().substring(1) : "");
    }

    public String getFQTypeName () {
        return wrappedAttribute.getFieldTypeElement().getQualifiedName().toString();
    }

    public String getShortTypeName () {
        return wrappedAttribute.getFieldTypeElement().getSimpleName().toString();
    }

    public BeanUtils.AttributeResult getWrappedAttribute() {
        return wrappedAttribute;
    }
}
