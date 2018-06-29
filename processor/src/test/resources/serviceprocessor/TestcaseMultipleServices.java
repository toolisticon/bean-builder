package io.toolisticon.spiap.processor.tests;

import io.toolisticon.beanbuilder.api.Service;
import io.toolisticon.beanbuilder.api.Services;
import io.toolisticon.beanbuilder.processor.serviceprocessortest.AnotherTestSpi;
import io.toolisticon.beanbuilder.processor.serviceprocessortest.TestSpi;

@Services({
        @Service(TestSpi.class),
        @Service(AnotherTestSpi.class)
})

public class TestcaseMultipleServices implements TestSpi, AnotherTestSpi {
    public String doSomething() {
        return "";
    }

    public void anotherThingToDo() {

    }

}