package io.toolisticon.beanbuilder.processor;

import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.aptk.tools.corematcher.CoreMatcherValidationMessages;
import io.toolisticon.cute.Cute;
import io.toolisticon.cute.CuteApi;
import org.junit.Before;
import org.junit.Test;

import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;


/**
 * Tests of {@link io.toolisticon.beanbuilder.api.BeanBuilder}.
 */

public class BeanBuilderProcessorTest {


    CuteApi.BlackBoxTestSourceFilesInterface compileTestBuilder;

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);

        compileTestBuilder = Cute
                .blackBoxTest().given().processors(BeanBuilderProcessor.class);
    }


    @Test
    public void test_valid_usage() {
        compileTestBuilder
                .andSourceFiles("testcases/TestcaseValidUsage.java")
                .whenCompiled()
                .thenExpectThat().compilationSucceeds()
                .andThat().generatedSourceFile("io.toolisticon.spiap.processor.tests.TestcaseValidUsageBuilder").exists()
                .executeTest();
    }


    @Test
    public void test_valid_usage_withTypeArguments() {
        compileTestBuilder
                .andSourceFiles("testcases/TestcaseValidUsageWithTypeParameters.java")
                .whenCompiled()
                .thenExpectThat().compilationSucceeds()
                .andThat().generatedSourceFile("io.toolisticon.spiap.processor.tests.TestcaseValidUsageWithTypeParametersBuilder").exists()
                .executeTest();
    }

    @Test
    public void test_valid_usage_with_custom_classname() {

        compileTestBuilder
                .andSourceFiles("testcases/TestcaseValidUsageWithCustomClassName.java")
                .whenCompiled()
                .thenExpectThat().compilationSucceeds()
                .andThat().generatedSourceFile("io.toolisticon.spiap.processor.tests.CustomTestBeanBuilder").exists()
                .executeTest();
    }


    @Test
    public void test_valid_usage_with_custom_packagename() {
        compileTestBuilder
                .andSourceFiles("testcases/TestcaseValidUsageWithCustomPackage.java")
                .whenCompiled()
                .thenExpectThat().compilationSucceeds()
                .andThat().generatedSourceFile("io.toolisticon.customPackage.TestcaseValidUsageWithCustomPackageBuilder").exists()
                .executeTest();
    }


    @Test
    public void test_valid_usage_with_inheritance() {

        compileTestBuilder
                .andSourceFiles("testcases/TestcaseValidUsageWithInheritance.java")
                .whenCompiled()
                .thenExpectThat().compilationSucceeds()
                .andThat().generatedSourceFile("io.toolisticon.spiap.processor.tests.TestcaseValidUsageWithInheritanceBuilder").exists()
                .executeTest();
    }

    @Test
    public void test_valid_usage_without_inheritance() {

        compileTestBuilder
                .andSourceFiles("testcases/TestcaseValidUsageWithoutInheritance.java")
                .whenCompiled()
                .thenExpectThat().compilationSucceeds()
                .andThat().generatedSourceFile("io.toolisticon.spiap.processor.tests.TestcaseValidUsageWithoutInheritanceBuilder").exists()
                .executeTest();
    }

    @Test
    public void test_invalid_usage_with_abstract_class() {

        compileTestBuilder
                .andSourceFiles("testcases/TestcaseInvalidUsageAbstractClass.java")
                .whenCompiled()
                .thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(CoreMatcherValidationMessages.BY_MODIFIER.getCode())
                .executeTest();
    }

    @Test
    public void test_invalid_usage_without_noarg_constructor() {

        compileTestBuilder
                .andSourceFiles("testcases/TestcaseInvalidUsageNoNoargConstructor.java")
                .whenCompiled()
                .thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(CoreMatcherValidationMessages.HAS_PUBLIC_NOARG_CONSTRUCTOR.getCode())
                .executeTest();
    }

    @Test
    public void test_invalid_usage_on_enum() {

        compileTestBuilder
                .andSourceFiles("testcases/TestcaseInvalidUsageOnEnum.java")
                .whenCompiled()
                .thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(CoreMatcherValidationMessages.IS_CLASS.getCode())
                .executeTest();
    }

    @Test
    public void test_Test_invalid_usage_on_interface() {

        compileTestBuilder
                .andSourceFiles("testcases/TestcaseInvalidUsageOnInterface.java")
                .whenCompiled()
                .thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(CoreMatcherValidationMessages.IS_CLASS.getCode())
                .executeTest();
    }

    @Test
    public void test_Test_valid_of_third_party_bean_builder_with_doublet() {

        Cute
                .blackBoxTest().given()
                .processors(ThirdPartyBeanBuilderProcessor.class)
                .andSourceFiles("testcases/thirdpartybeanbuilderwithdoublet/package-info.java")
                .whenCompiled().thenExpectThat()
                .compilationSucceeds()
                .andThat().javaFileObject(StandardLocation.SOURCE_OUTPUT, "io.toolisticon.beanbuilder.processor.ObjectBuilder", JavaFileObject.Kind.SOURCE).exists()
                .executeTest();
    }

}