package io.toolisticon.aptk.tools.fluentvalidator.impl;

import io.toolisticon.aptk.tools.AbstractUnitTestAnnotationProcessorClass;
import io.toolisticon.aptk.tools.FilterTestAnnotation1;
import io.toolisticon.aptk.tools.AnnotationUtils;
import io.toolisticon.aptk.tools.ElementUtils;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.aptk.tools.corematcher.PlainValidationMessage;
import io.toolisticon.cute.CompileTestBuilder;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.List;


/**
 * Unit test for {@link FluentValidatorMessage}.
 */
public class FluentValidatorMessageTest {

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }

    private CompileTestBuilder.UnitTestBuilder unitTestBuilder = CompileTestBuilder
            .unitTest()
            .useSource(JavaFileObjectUtils.readFromResource("/AnnotationProcessorTestClass.java"));


    @Test
    public void checkErrorElementMessage() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {

                        FluentValidatorMessage message = new FluentValidatorMessage(element, Diagnostic.Kind.ERROR, PlainValidationMessage.create("TEST ${0}"), "SUCCESS");
                        message.issueMessage();

                    }
                })
                .compilationShouldFail()
                .executeTest();

    }

    @Test
    public void checkWarningElementMessage() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {

                        FluentValidatorMessage message = new FluentValidatorMessage(element, Diagnostic.Kind.WARNING, PlainValidationMessage.create("TEST ${0}"), "SUCCESS");
                        message.issueMessage();

                    }
                })
                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void checkInfoElementMessage() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {

                        FluentValidatorMessage message = new FluentValidatorMessage(element, Diagnostic.Kind.NOTE, PlainValidationMessage.create("TEST ${0}"), "SUCCESS");
                        message.issueMessage();

                    }
                })
                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void checkElementWithAnnotationMirror() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                List<? extends Element> annotatedElement = ElementUtils.AccessEnclosedElements.getEnclosedElementsWithAllAnnotationsOf(element, FilterTestAnnotation1.class);
                MatcherAssert.assertThat("PRECONDITION : Must have found one element", annotatedElement.size() == 1);
                AnnotationMirror annotationMirror = AnnotationUtils.getAnnotationMirror(annotatedElement.get(0), FilterTestAnnotation1.class);
                MatcherAssert.assertThat("PRECONDITION : Must have found one annotation", annotationMirror, Matchers.notNullValue());


                FluentValidatorMessage message = new FluentValidatorMessage(annotatedElement.get(0), annotationMirror, Diagnostic.Kind.NOTE, PlainValidationMessage.create("TEST ${0}"), "SUCCESS");
                message.issueMessage();


            }
        })

                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void checkElementWithAnnotationMirrorAndAnnotationValue() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {

                        List<? extends Element> annotatedElement = ElementUtils.AccessEnclosedElements.getEnclosedElementsWithAllAnnotationsOf(element, FilterTestAnnotation1.class);
                        MatcherAssert.assertThat("PRECONDITION : Must have found one element", annotatedElement.size() == 1);
                        AnnotationMirror annotationMirror = AnnotationUtils.getAnnotationMirror(annotatedElement.get(0), FilterTestAnnotation1.class);
                        MatcherAssert.assertThat("PRECONDITION : Must have found one annotation", annotationMirror, Matchers.notNullValue());
                        MatcherAssert.assertThat("PRECONDITION : Must have found one annotation", annotationMirror.getElementValues().size() == 1);

                        FluentValidatorMessage message = new FluentValidatorMessage(element, annotatedElement.get(0).getAnnotationMirrors().get(0), annotationMirror.getElementValues().get(0), Diagnostic.Kind.NOTE, PlainValidationMessage.create("TEST ${0}"), "SUCCESS");
                        message.issueMessage();

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }


}
