package io.toolisticon.beanbuilder.processor;

import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.annotationprocessortoolkit.tools.corematcher.CoreMatcherValidationMessages;
import io.toolisticon.compiletesting.CompileTestBuilder;
import io.toolisticon.compiletesting.JavaFileObjectUtils;
import org.junit.Before;
import org.junit.Test;

import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;


/**
 * Tests of {@link io.toolisticon.beanbuilder.api.BeanBuilder}.
 */

public class BeanBuilderProcessorTest {


    CompileTestBuilder.CompilationTestBuilder compileTestBuilder;

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);

        compileTestBuilder = CompileTestBuilder
                .compilationTest()
                .addProcessors(BeanBuilderProcessor.class);
    }


    @Test
    public void test_valid_usage() {

        compileTestBuilder
                .addSources("testcases/TestcaseValidUsage.java")
                .compilationShouldSucceed()
                .testCompilation();
    }

    @Test
    public void test_valid_usage_with_custom_classname() {

        compileTestBuilder
                .addSources("testcases/TestcaseValidUsageWithCustomClassName.java")
                .compilationShouldSucceed()
                .testCompilation();
    }


    @Test
    public void test_valid_usage_with_custom_packagename() {
        compileTestBuilder
                .addSources("testcases/TestcaseValidUsageWithCustomPackage.java")
                .compilationShouldSucceed()
                .testCompilation();
    }


    @Test
    public void test_valid_usage_with_inheritance() {

        compileTestBuilder
                .addSources("testcases/TestcaseValidUsageWithInheritance.java")
                .compilationShouldSucceed()
                .testCompilation();
    }

    @Test
    public void test_valid_usage_without_inheritance() {

        compileTestBuilder
                .addSources("testcases/TestcaseValidUsageWithInheritance.java")
                .compilationShouldSucceed()
                .testCompilation();
    }

    @Test
    public void test_invalid_usage_with_abstract_class() {

        compileTestBuilder
                .addSources("testcases/TestcaseInvalidUsageAbstractClass.java")
                .compilationShouldFail()
                .expectedErrorMessages(CoreMatcherValidationMessages.BY_MODIFIER.getCode())
                .testCompilation();
    }

    @Test
    public void test_invalid_usage_without_noarg_constructor() {

        compileTestBuilder
                .addSources("testcases/TestcaseInvalidUsageNoNoargConstructor.java")
                .compilationShouldFail()
                .expectedErrorMessages(CoreMatcherValidationMessages.HAS_PUBLIC_NOARG_CONSTRUCTOR.getCode())
                .testCompilation();
    }

    @Test
    public void test_invalid_usage_on_enum() {

        compileTestBuilder
                .addSources("testcases/TestcaseInvalidUsageOnEnum.java")
                .compilationShouldFail()
                .expectedErrorMessages(CoreMatcherValidationMessages.IS_CLASS.getCode())
                .testCompilation();
    }

    @Test
    public void test_Test_invalid_usage_on_interface() {

        compileTestBuilder
                .addSources("testcases/TestcaseInvalidUsageOnInterface.java")
                .compilationShouldFail()
                .expectedErrorMessages(CoreMatcherValidationMessages.IS_CLASS.getCode())
                .testCompilation();
    }

    @Test
    public void test_Test_valid_of_third_party_bean_builder_with_doublet() {

        CompileTestBuilder
                .compilationTest()
                .addProcessors(ThirdPartyBeanBuilderProcessor.class)
                .addSources("testcases/thirdpartybeanbuilderwithdoublet/package-info.java")
                .compilationShouldSucceed()
                .expectedJavaFileObjectExists(StandardLocation.SOURCE_OUTPUT, "io.toolisticon.beanbuilder.processor.ObjectBuilder", JavaFileObject.Kind.SOURCE)
                .testCompilation();
    }

}