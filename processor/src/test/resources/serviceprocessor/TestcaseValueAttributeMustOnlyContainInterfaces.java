package io.toolisticon.spiap.processor.tests;

import io.toolisticon.beanbuilder.api.Service;
import io.toolisticon.beanbuilder.processor.serviceprocessortest.TestSpi;

@Service(TestcaseValueAttributeMustOnlyContainInterfaces.class)
public class TestcaseValueAttributeMustOnlyContainInterfaces implements TestSpi {
    public String doSomething() {
        return "";
    }
}