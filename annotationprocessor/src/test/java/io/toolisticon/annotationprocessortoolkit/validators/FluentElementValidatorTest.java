package io.toolisticon.annotationprocessortoolkit.validators;

import io.toolisticon.annotationprocessortoolkit.AbstractUnitTestAnnotationProcessorClass;
import io.toolisticon.annotationprocessortoolkit.tools.ElementUtils;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.annotationprocessortoolkit.tools.corematcher.CoreMatchers;
import io.toolisticon.annotationprocessortoolkit.tools.fluentfilter.FluentElementFilter;
import io.toolisticon.annotationprocessortoolkit.tools.fluentvalidator.FluentElementValidator;
import io.toolisticon.cute.CompileTestBuilder;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.util.List;

/**
 * Unit test for {@link io.toolisticon.annotationprocessortoolkit.tools.fluentvalidator.FluentElementValidator}.
 */
public class FluentElementValidatorTest {


    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }

    private CompileTestBuilder.UnitTestBuilder unitTestBuilder = CompileTestBuilder
            .unitTest()
            .useSource(JavaFileObjectUtils.readFromResource("/AnnotationProcessorTestClass.java"));


    @Test
    public void shouldValidateSuccessfullyWithAllOf() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {


                        // check null value
                        List<? extends Element> elements = FluentElementFilter.createFluentElementFilter(ElementUtils.AccessEnclosedElements.getEnclosedElementsByName(element, "synchronizedMethod"))
                                .applyFilter(CoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                                .getResult();
                        MatcherAssert.assertThat("precondition : must have found unique testelement", elements.size() == 1);
                        ExecutableElement testElement = ElementUtils.CastElement.castMethod(elements.get(0));


                        // Test
                        MatcherAssert.assertThat("Should be validated as true", FluentElementValidator.createFluentElementValidator(testElement).applyValidator(CoreMatchers.BY_MODIFIER).hasAllOf(Modifier.SYNCHRONIZED, Modifier.PUBLIC).validateAndIssueMessages());

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void shouldValidateWithFailureWithAllOf() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {


                        // check null value
                        List<? extends Element> elements = FluentElementFilter.createFluentElementFilter(ElementUtils.AccessEnclosedElements.getEnclosedElementsByName(element, "synchronizedMethod"))
                                .applyFilter(CoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                                .getResult();
                        MatcherAssert.assertThat("precondition : must have found unique testelement", elements.size() == 1);
                        ExecutableElement testElement = ElementUtils.CastElement.castMethod(elements.get(0));


                        // Test
                        MatcherAssert.assertThat("Should be validated as false", !FluentElementValidator.createFluentElementValidator(testElement).applyValidator(CoreMatchers.BY_MODIFIER).hasAllOf(Modifier.SYNCHRONIZED, Modifier.PUBLIC, Modifier.FINAL).validateAndIssueMessages());

                    }
                })

                .compilationShouldFail()
                .executeTest();

    }

    @Test
    public void shouldValidateSuccessfullyWithAtLeastOneOf() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {


                        // check null value
                        List<? extends Element> elements = FluentElementFilter.createFluentElementFilter(ElementUtils.AccessEnclosedElements.getEnclosedElementsByName(element, "synchronizedMethod"))
                                .applyFilter(CoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                                .getResult();
                        MatcherAssert.assertThat("precondition : must have found unique testelement", elements.size() == 1);
                        ExecutableElement testElement = ElementUtils.CastElement.castMethod(elements.get(0));


                        // Test
                        MatcherAssert.assertThat("Should be validated as true", FluentElementValidator.createFluentElementValidator(testElement).applyValidator(CoreMatchers.BY_MODIFIER).hasAtLeastOneOf(Modifier.SYNCHRONIZED, Modifier.PUBLIC).validateAndIssueMessages());

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void shouldValidateWithFailureWithAtLeastOneOf() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {


                        // check null value
                        List<? extends Element> elements = FluentElementFilter.createFluentElementFilter(ElementUtils.AccessEnclosedElements.getEnclosedElementsByName(element, "synchronizedMethod"))
                                .applyFilter(CoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                                .getResult();
                        MatcherAssert.assertThat("precondition : must have found unique testelement", elements.size() == 1);
                        ExecutableElement testElement = ElementUtils.CastElement.castMethod(elements.get(0));


                        // Test
                        MatcherAssert.assertThat("Should be validated as false", !FluentElementValidator.createFluentElementValidator(testElement).applyValidator(CoreMatchers.BY_MODIFIER).hasAtLeastOneOf(Modifier.STATIC, Modifier.FINAL).validateAndIssueMessages());

                    }
                })

                .compilationShouldFail()
                .executeTest();

    }


    @Test
    public void shouldValidateSuccessfullyWithOneOf() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {


                        // check null value
                        List<? extends Element> elements = FluentElementFilter.createFluentElementFilter(ElementUtils.AccessEnclosedElements.getEnclosedElementsByName(element, "synchronizedMethod"))
                                .applyFilter(CoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                                .getResult();
                        MatcherAssert.assertThat("precondition : must have found unique testelement", elements.size() == 1);
                        ExecutableElement testElement = ElementUtils.CastElement.castMethod(elements.get(0));


                        // Test
                        MatcherAssert.assertThat("Should be validated as true", FluentElementValidator.createFluentElementValidator(testElement).applyValidator(CoreMatchers.BY_MODIFIER).hasOneOf(Modifier.SYNCHRONIZED, Modifier.FINAL, Modifier.ABSTRACT).validateAndIssueMessages());

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void shouldValidateWithFailureWithOneOf() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {


                        // check null value
                        List<? extends Element> elements = FluentElementFilter.createFluentElementFilter(ElementUtils.AccessEnclosedElements.getEnclosedElementsByName(element, "synchronizedMethod"))
                                .applyFilter(CoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                                .getResult();
                        MatcherAssert.assertThat("precondition : must have found unique testelement", elements.size() == 1);
                        ExecutableElement testElement = ElementUtils.CastElement.castMethod(elements.get(0));


                        // Test
                        MatcherAssert.assertThat("Should be validated as false", !FluentElementValidator.createFluentElementValidator(testElement).applyValidator(CoreMatchers.BY_MODIFIER).hasNoneOf(Modifier.SYNCHRONIZED, Modifier.PUBLIC).validateAndIssueMessages());

                    }
                })

                .compilationShouldFail()
                .executeTest();

    }


    @Test
    public void shouldValidateSuccessfullyWithNoneOf() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {


                        // check null value
                        List<? extends Element> elements = FluentElementFilter.createFluentElementFilter(ElementUtils.AccessEnclosedElements.getEnclosedElementsByName(element, "synchronizedMethod"))
                                .applyFilter(CoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                                .getResult();
                        MatcherAssert.assertThat("precondition : must have found unique testelement", elements.size() == 1);
                        ExecutableElement testElement = ElementUtils.CastElement.castMethod(elements.get(0));


                        // Test
                        MatcherAssert.assertThat("Should be validated as true", FluentElementValidator.createFluentElementValidator(testElement).applyValidator(CoreMatchers.BY_MODIFIER).hasNoneOf(Modifier.FINAL, Modifier.STATIC).validateAndIssueMessages());

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void shouldValidateWithFailureWithNoneOf() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {


                        // check null value
                        List<? extends Element> elements = FluentElementFilter.createFluentElementFilter(ElementUtils.AccessEnclosedElements.getEnclosedElementsByName(element, "synchronizedMethod"))
                                .applyFilter(CoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                                .getResult();
                        MatcherAssert.assertThat("precondition : must have found unique testelement", elements.size() == 1);
                        ExecutableElement testElement = ElementUtils.CastElement.castMethod(elements.get(0));


                        // Test
                        MatcherAssert.assertThat("Should be validated as false", !FluentElementValidator.createFluentElementValidator(testElement).applyValidator(CoreMatchers.BY_MODIFIER).hasNoneOf(Modifier.SYNCHRONIZED, Modifier.PUBLIC).validateAndIssueMessages());

                    }

                })

                .compilationShouldFail()
                .executeTest();

    }

    @Test
    public void shouldValidateSuccessfullyIsMatcher() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {


                        // check null value
                        List<? extends Element> elements = FluentElementFilter.createFluentElementFilter(ElementUtils.AccessEnclosedElements.getEnclosedElementsByName(element, "synchronizedMethod"))
                                .applyFilter(CoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                                .getResult();
                        MatcherAssert.assertThat("precondition : must have found unique testelement", elements.size() == 1);


                        // Test
                        MatcherAssert.assertThat("Should be successfully validated", FluentElementValidator.createFluentElementValidator(elements.get(0)).is(CoreMatchers.IS_EXECUTABLE_ELEMENT).validateAndIssueMessages())
                        ;

                    }
                })

                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void shouldValidateWithFailureIsMatcher() {

        unitTestBuilder.useProcessor(
                new AbstractUnitTestAnnotationProcessorClass() {
                    @Override
                    protected void testCase(TypeElement element) {


                        // check null value
                        List<? extends Element> elements = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                                .applyFilter(CoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.FIELD)
                                .getResult();
                        MatcherAssert.assertThat("precondition : must have found unique testelement", elements.size() >= 1);


                        // Test
                        MatcherAssert.assertThat("Should be validated as false", !FluentElementValidator.createFluentElementValidator(elements.get(0)).is(CoreMatchers.IS_EXECUTABLE_ELEMENT).applyValidator(CoreMatchers.BY_MODIFIER).hasAllOf(Modifier.PUBLIC, Modifier.STATIC).validateAndIssueMessages())
                        ;

                    }
                })

                .compilationShouldFail()
                .executeTest();

    }

}
