package io.toolisticon.aptk.tools.matcher.impl;

import io.toolisticon.aptk.tools.AbstractUnitTestAnnotationProcessorClass;
import io.toolisticon.aptk.tools.ElementUtils;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.aptk.tools.TypeUtils;
import io.toolisticon.aptk.tools.corematcher.AptkCoreMatchers;
import io.toolisticon.cute.CompileTestBuilder;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.List;


/**
 * Unit test for {@link IsClassMatcher}.
 */

public class IsClassMatcherTest {

    public enum TestEnum {
        TEST
    }


    private CompileTestBuilder.UnitTestBuilder unitTestBuilder = CompileTestBuilder
            .unitTest()
            .useSource(JavaFileObjectUtils.readFromResource("/AnnotationProcessorTestClass.java"));


    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }

    @Test
    public void checkMatchingCase() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                MatcherAssert.assertThat("Should return true for class : ", AptkCoreMatchers.IS_CLASS.getMatcher().check(element));


            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

    @Test
    public void checkMismatchingClass_enum() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                TypeElement typeElement = TypeUtils.TypeRetrieval.getTypeElement(IsClassMatcherTest.class);
                List<Element> enums = ElementUtils.AccessEnclosedElements.getEnclosedElementsOfKind(typeElement, ElementKind.ENUM);
                MatcherAssert.assertThat("Precondition: must have found a enum", enums.size() >= 1);


                MatcherAssert.assertThat("Should return false for enum : ", !AptkCoreMatchers.IS_CLASS.getMatcher().check(enums.get(0)));

            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

    @Test
    public void checkNullValuedElement() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                MatcherAssert.assertThat("Should return false for null valued element : ", !AptkCoreMatchers.IS_CLASS.getMatcher().check(null));

            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }


}
