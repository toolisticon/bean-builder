package io.toolisticon.example.beanbuilderapexample;

import io.toolisticon.example.murks.CustomPackageTestBeanBuilder;

import java.util.Arrays;
import java.util.List;

public class TestUsageClass {

    public static void main(String[] args) {
        TestBean testBean = TestBeanBuilder.createBuilder()
                .withLongField(5L)
                .withStringField("TEST")
                .build();
        System.out.println(testBean.getStringField() + " := " + testBean.getLongField());

        TestBeanWithInheritance testBeanWithInheritance = TestBeanWithInheritanceBuilder.createBuilder()
                .withLongField(5L)
                .withStringField("TEST")
                .withMyField("YUP")
                .build();
        System.out.println(testBeanWithInheritance.getMyField() + "|" + testBeanWithInheritance.getStringField() + " := " + testBeanWithInheritance.getLongField());

        CustomClassNameTestBean testBeanWithCustomClassName = MurcksBuilder.createBuilder()
                .withLongField(5L)
                .withStringField("TEST")
                .build();
        System.out.println(testBeanWithCustomClassName.getStringField() + " := " + testBeanWithCustomClassName.getLongField());

        CustomPackageTestBean testBeanWithCustomPackage = CustomPackageTestBeanBuilder.createBuilder()
                .withLongField(5L)
                .withStringField("TEST")
                .build();
        System.out.println(testBeanWithCustomPackage.getStringField() + " := " + testBeanWithCustomPackage.getLongField());


        List<String> stringList = Arrays.asList("A","B");
        GenericClassTestBean<String, List<String>> genericClassTestBean = GenericsBuilder.<String,List<String>>createBuilder().withCollection(stringList).build();
        System.out.println("Generic List: " + genericClassTestBean.getCollection());

    }


}
