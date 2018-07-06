package io.toolisticon.example.beanbuilderapexample;

public class TestUsageClass {

    public static void main(String[] args) {
        TestBean testBean = TestBeanBuilder.createBuilder()
                .withLongField(5L)
                .withStringField("TEST")
                .build();
        System.out.println( testBean.getStringField() + " := " + testBean.getLongField());

        TestBeanWithInheritance testBeanWithInheritance = TestBeanWithInheritanceBuilder.createBuilder()
                .withLongField(5L)
                .withStringField("TEST")
                .withMyField("YUP")
                .build();
        System.out.println(  testBeanWithInheritance.getMyField() + "|" +testBean.getStringField() + " := " + testBean.getLongField());


    }

}
