package io.toolisticon.aptk.tools.matcher.impl;

import io.toolisticon.aptk.tools.AbstractUnitTestAnnotationProcessorClass;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.aptk.tools.TypeUtils;
import io.toolisticon.aptk.tools.corematcher.AptkCoreMatchers;
import io.toolisticon.cute.CompileTestBuilder;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import javax.lang.model.element.TypeElement;


/**
 * Unit test for {@link IsAnnotationTypeMatcher}.
 */

public class IsAnnotationTypeMatcherTest {

    private CompileTestBuilder.UnitTestBuilder unitTestBuilder = CompileTestBuilder
            .unitTest()
            .useSource(JavaFileObjectUtils.readFromResource("/AnnotationClassAttributeTestClass.java"));


    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }

    @Test
    public void checkMatchingAnnotationType() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                TypeElement typeElement = TypeUtils.TypeRetrieval.getTypeElement(Override.class);


                MatcherAssert.assertThat("Should return true for annotation type : ", AptkCoreMatchers.IS_ANNOTATION_TYPE.getMatcher().check(typeElement));

            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

    @Test
    public void checkMismatchingAnnotationType() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                MatcherAssert.assertThat("Should return false for non annotation (class) : ", !AptkCoreMatchers.IS_ANNOTATION_TYPE.getMatcher().check(element));


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

                MatcherAssert.assertThat("Should return false for null valued element : ", !AptkCoreMatchers.IS_ANNOTATION_TYPE.getMatcher().check(null));

            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }


}
