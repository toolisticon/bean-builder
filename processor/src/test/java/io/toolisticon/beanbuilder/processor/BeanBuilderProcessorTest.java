package io.toolisticon.beanbuilder.processor;

import io.toolisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorIntegrationTest;
import io.toolisticon.annotationprocessortoolkit.testhelper.integrationtest.AnnotationProcessorIntegrationTestConfiguration;
import io.toolisticon.annotationprocessortoolkit.testhelper.integrationtest.AnnotationProcessorIntegrationTestConfigurationBuilder;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.annotationprocessortoolkit.tools.corematcher.CoreMatcherValidationMessages;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;


/**
 * Tests of {@link io.toolisticon.beanbuilder.api.BeanBuilder}.
 */
@RunWith(Parameterized.class)
public class BeanBuilderProcessorTest extends AbstractAnnotationProcessorIntegrationTest<BeanBuilderProcessor> {


    public BeanBuilderProcessorTest(String description, AnnotationProcessorIntegrationTestConfiguration configuration) {
        super(configuration);
    }

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }

    @Override
    protected BeanBuilderProcessor getAnnotationProcessor() {
        return new BeanBuilderProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {
                        "Test valid usage",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("testcases/TestcaseValidUsage.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test valid usage with custom classname",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("testcases/TestcaseValidUsageWithCustomClassName.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test valid usage with custom packagename",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("testcases/TestcaseValidUsageWithCustomPackage.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test valid usage with inheritance",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("testcases/TestcaseValidUsageWithInheritance.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test valid usage without inheritance",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("testcases/TestcaseValidUsageWithInheritance.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test invalid usage with abstract class",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("testcases/TestcaseInvalidUsageAbstractClass.java")
                                .compilationShouldFail()
                                .addMessageValidator()
                                .setErrorChecks(CoreMatcherValidationMessages.BY_MODIFIER.getCode())
                                .finishMessageValidator()
                                .build()
                },
                {
                        "Test invalid usage without noarg constructor",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("testcases/TestcaseInvalidUsageNoNoargConstructor.java")
                                .compilationShouldFail()
                                .addMessageValidator()
                                .setErrorChecks(CoreMatcherValidationMessages.HAS_PUBLIC_NOARG_CONSTRUCTOR.getCode())
                                .finishMessageValidator()
                                .build()
                },
                {
                        "Test invalid usage on enum",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("testcases/TestcaseInvalidUsageOnEnum.java")
                                .compilationShouldFail()
                                .addMessageValidator()
                                .setErrorChecks(CoreMatcherValidationMessages.IS_CLASS.getCode())
                                .finishMessageValidator()
                                .build()
                },
                {
                        "Test invalid usage on interface",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("testcases/TestcaseInvalidUsageOnInterface.java")
                                .compilationShouldFail()
                                .addMessageValidator()
                                .setErrorChecks(CoreMatcherValidationMessages.IS_CLASS.getCode())
                                .finishMessageValidator()
                                .build()
                },
        });

    }


    @Test
    public void testCorrectnessOfAdviceArgumentAnnotation() {
        super.test();
    }


}