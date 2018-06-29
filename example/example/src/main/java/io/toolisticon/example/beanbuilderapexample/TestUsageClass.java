package io.toolisticon.example.beanbuilderapexample;

public class TestUsageClass {

    public static void main(String[] args) {
        TestBean testBean = TestBeanBuilder.createBuilder()
                .withLongField(5L)
                .withStringField("TEST")
                .build();
        System.out.println( testBean.getStringField() + " := " + testBean.getLongField());

    }

}
