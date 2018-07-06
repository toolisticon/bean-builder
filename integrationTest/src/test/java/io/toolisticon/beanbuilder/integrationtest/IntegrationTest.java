package io.toolisticon.beanbuilder.integrationtest;

import io.toolisticon.customPackage.TestcaseValidUsageWithCustomPackageBuilder;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class IntegrationTest {

    @Test
    public void testValidUsage() {

        TestcaseValidUsage testcaseValidUsage = TestcaseValidUsageBuilder.createBuilder()
                .withField("field")
                .build();

        MatcherAssert.assertThat(testcaseValidUsage.getField(), Matchers.is("field"));

    }

    @Test
    public void testValidUsageWithCustomClassname() {

        TestcaseValidUsageWithCustomClassName testcaseValidUsage = CustomTestBeanBuilder.createBuilder()
                .withField("field")
                .build();

        MatcherAssert.assertThat(testcaseValidUsage.getField(), Matchers.is("field"));

    }

    @Test
    public void testValidUsageWithCustomPackage() {

        TestcaseValidUsageWithCustomPackage testcaseValidUsage = TestcaseValidUsageWithCustomPackageBuilder.createBuilder()
                .withField("field")
                .build();

        MatcherAssert.assertThat(testcaseValidUsage.getField(), Matchers.is("field"));

    }

    @Test
    public void testValidUsageWithInheritance() {

        TestcaseValidUsageWithInheritance testcaseValidUsage = TestcaseValidUsageWithInheritanceBuilder.createBuilder()
                .withField("field")
                .withInheritedField("inheritedField")
                .build();

        MatcherAssert.assertThat(testcaseValidUsage.getField(), Matchers.is("field"));
        MatcherAssert.assertThat(testcaseValidUsage.getInheritedField(), Matchers.is("inheritedField"));

    }

    @Test
    public void testValidUsageWithoutInheritance() {

        TestcaseValidUsageWithoutInheritance testcaseValidUsage = TestcaseValidUsageWithoutInheritanceBuilder.createBuilder()
                .withField("field")
                .build();

        MatcherAssert.assertThat(testcaseValidUsage.getField(), Matchers.is("field"));

    }


}
