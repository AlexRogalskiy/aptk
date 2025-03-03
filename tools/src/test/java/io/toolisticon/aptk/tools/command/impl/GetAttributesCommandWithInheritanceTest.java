package io.toolisticon.aptk.tools.command.impl;


import io.toolisticon.aptk.tools.AbstractUnitTestAnnotationProcessorClass;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.aptk.tools.corematcher.AptkCoreMatchers;
import io.toolisticon.aptk.tools.BeanUtils;
import io.toolisticon.aptk.tools.fluentfilter.FluentElementFilter;
import io.toolisticon.cute.CompileTestBuilder;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import javax.lang.model.element.TypeElement;
import java.util.HashSet;
import java.util.Set;

/**
 * Unit Test for {@link GetAttributesCommandWithInheritance}.
 */
public class GetAttributesCommandWithInheritanceTest {

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }

    private CompileTestBuilder.UnitTestBuilder unitTestBuilder = CompileTestBuilder
            .unitTest()
            .useSource(JavaFileObjectUtils.readFromResource("/testcases.commands/GetAttributesCommandWithInheritanceTestClass.java"));

    @Test
    public void shouldExecuteSuccessfullyDataAnnotatedClass() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {

                        TypeElement typeElement = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                                .applyFilter(AptkCoreMatchers.IS_CLASS)
                                .applyFilter(AptkCoreMatchers.BY_REGEX_NAME).filterByOneOf(".*TestDataAnnotatedClass")
                                .getResult().get(0);
                        BeanUtils.AttributeResult[] attributeResult = GetAttributesCommandWithInheritance.INSTANCE.execute(typeElement);

                        MatcherAssert.assertThat(attributeResult.length, Matchers.is(1));
                        MatcherAssert.assertThat(attributeResult[0].getFieldName(), Matchers.is("field1"));

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void shouldExecuteSuccessfullyInheritedDataAnnotatedClass() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {

                        TypeElement typeElement = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                                .applyFilter(AptkCoreMatchers.IS_CLASS)
                                .applyFilter(AptkCoreMatchers.BY_REGEX_NAME).filterByOneOf(".*TestInheritedDataAnnotatedClass")
                                .getResult().get(0);
                        BeanUtils.AttributeResult[] attributeResult = GetAttributesCommandWithInheritance.INSTANCE.execute(typeElement);

                        MatcherAssert.assertThat(attributeResult.length, Matchers.is(2));

                        Set<String> fields = new HashSet<String>();
                        for (BeanUtils.AttributeResult item : attributeResult) {
                            fields.add(item.getFieldName());
                        }

                        MatcherAssert.assertThat(fields, Matchers.containsInAnyOrder("field1", "field3"));

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void shouldExecuteSuccessfullyGetterAnnotatedClass() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {


                        TypeElement typeElement = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                                .applyFilter(AptkCoreMatchers.IS_CLASS)
                                .applyFilter(AptkCoreMatchers.BY_REGEX_NAME).filterByOneOf(".*TestJustGetterAnnotatedClass")
                                .getResult().get(0);

                        BeanUtils.AttributeResult[] attributeResult = GetAttributesCommandWithInheritance.INSTANCE.execute(typeElement);

                        MatcherAssert.assertThat(attributeResult.length, Matchers.is(0));

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void shouldExecuteSuccessfullySetterAnnotatedClass() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {

                        TypeElement typeElement = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                                .applyFilter(AptkCoreMatchers.IS_CLASS)
                                .applyFilter(AptkCoreMatchers.BY_REGEX_NAME).filterByOneOf(".*TestJustSetterAnnotatedClass")
                                .getResult().get(0);


                        BeanUtils.AttributeResult[] attributeResult = GetAttributesCommandWithInheritance.INSTANCE.execute(typeElement);

                        MatcherAssert.assertThat(attributeResult.length, Matchers.is(0));

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }


    @Test
    public void shouldExecuteSuccessfullyGetterAndSetterAnnotatedClass() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {


                        TypeElement typeElement = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                                .applyFilter(AptkCoreMatchers.IS_CLASS)
                                .applyFilter(AptkCoreMatchers.BY_REGEX_NAME).filterByOneOf(".*TestGetterAndSetterAnnotatedClass")
                                .getResult().get(0);


                        BeanUtils.AttributeResult[] attributeResult = GetAttributesCommandWithInheritance.INSTANCE.execute(typeElement);

                        MatcherAssert.assertThat(attributeResult.length, Matchers.is(1));
                        MatcherAssert.assertThat(attributeResult[0].getFieldName(), Matchers.is("field1"));

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }


    @Test
    public void shouldExecuteSuccessfullyMixedGetterAndSetterAnnotatedClassAndField2() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {

                        TypeElement typeElement = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                                .applyFilter(AptkCoreMatchers.IS_CLASS)
                                .applyFilter(AptkCoreMatchers.BY_REGEX_NAME).filterByOneOf(".*TestMixedGetterAndSetterAnnotatedClassAndField1")
                                .getResult().get(0);

                        BeanUtils.AttributeResult[] attributeResult = GetAttributesCommandWithInheritance.INSTANCE.execute(typeElement);

                        MatcherAssert.assertThat(attributeResult.length, Matchers.is(1));
                        MatcherAssert.assertThat(attributeResult[0].getFieldName(), Matchers.is("field1"));

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }


    @Test
    public void shouldExecuteSuccessfullyMixedGetterAndSetterAnnotatedClassAndField2_() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {

                        TypeElement typeElement = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                                .applyFilter(AptkCoreMatchers.IS_CLASS)
                                .applyFilter(AptkCoreMatchers.BY_REGEX_NAME).filterByOneOf(".*TestMixedGetterAndSetterAnnotatedClassAndField2")
                                .getResult().get(0);

                        BeanUtils.AttributeResult[] attributeResult = GetAttributesCommandWithInheritance.INSTANCE.execute(typeElement);

                        MatcherAssert.assertThat(attributeResult.length, Matchers.is(1));
                        MatcherAssert.assertThat(attributeResult[0].getFieldName(), Matchers.is("field1"));

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }


    @Test
    public void shouldExecuteSuccessfullyGetterAnnotatedField() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {

                        TypeElement typeElement = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                                .applyFilter(AptkCoreMatchers.IS_CLASS)
                                .applyFilter(AptkCoreMatchers.BY_REGEX_NAME).filterByOneOf(".*TestJustGetterAnnotatedField")
                                .getResult().get(0);

                        BeanUtils.AttributeResult[] attributeResult = GetAttributesCommandWithInheritance.INSTANCE.execute(typeElement);

                        MatcherAssert.assertThat(attributeResult.length, Matchers.is(0));

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void shouldExecuteSuccessfullySetterAnnotatedField() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {


                        TypeElement typeElement = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                                .applyFilter(AptkCoreMatchers.IS_CLASS)
                                .applyFilter(AptkCoreMatchers.BY_REGEX_NAME).filterByOneOf(".*TestJustSetterAnnotatedField")
                                .getResult().get(0);

                        BeanUtils.AttributeResult[] attributeResult = GetAttributesCommandWithInheritance.INSTANCE.execute(typeElement);

                        MatcherAssert.assertThat(attributeResult.length, Matchers.is(0));

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void shouldExecuteSuccessfullyGetterAndSetterAnnotatedField() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {

                        TypeElement typeElement = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                                .applyFilter(AptkCoreMatchers.IS_CLASS)
                                .applyFilter(AptkCoreMatchers.BY_REGEX_NAME).filterByOneOf(".*TestGetterAndSetterAnnotatedField")
                                .getResult().get(0);

                        BeanUtils.AttributeResult[] attributeResult = GetAttributesCommandWithInheritance.INSTANCE.execute(typeElement);

                        MatcherAssert.assertThat(attributeResult.length, Matchers.is(1));
                        MatcherAssert.assertThat(attributeResult[0].getFieldName(), Matchers.is("field1"));

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void shouldExecuteSuccessfullyGetterAndSetterAnnotatedMethod() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {

                        TypeElement typeElement = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                                .applyFilter(AptkCoreMatchers.IS_CLASS)
                                .applyFilter(AptkCoreMatchers.BY_REGEX_NAME).filterByOneOf(".*TestFieldGetterAndSetterMethods")
                                .getResult().get(0);

                        BeanUtils.AttributeResult[] attributeResult = GetAttributesCommandWithInheritance.INSTANCE.execute(typeElement);

                        MatcherAssert.assertThat(attributeResult.length, Matchers.is(1));
                        MatcherAssert.assertThat(attributeResult[0].getFieldName(), Matchers.is("field1"));

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void shouldExecuteSuccessfullyGetterAndSetterAnnotatedMethodWithInvalidParameterType() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {

                        TypeElement typeElement = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                                .applyFilter(AptkCoreMatchers.IS_CLASS)
                                .applyFilter(AptkCoreMatchers.BY_REGEX_NAME).filterByOneOf(".*TestFieldGetterAndSetterMethodsWithInvalidSetterParameterType")
                                .getResult().get(0);

                        BeanUtils.AttributeResult[] attributeResult = GetAttributesCommandWithInheritance.INSTANCE.execute(typeElement);

                        MatcherAssert.assertThat(attributeResult.length, Matchers.is(0));

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }


}
