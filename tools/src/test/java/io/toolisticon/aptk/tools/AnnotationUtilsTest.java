package io.toolisticon.aptk.tools;

import io.toolisticon.aptk.tools.annotationutilstestclasses.ClassArrayAttributeAnnotation;
import io.toolisticon.aptk.tools.annotationutilstestclasses.ClassAttributeAnnotation;
import io.toolisticon.aptk.tools.annotationutilstestclasses.DefaultValueAnnotation;
import io.toolisticon.aptk.tools.annotationutilstestclasses.NoAttributeAnnotation;
import io.toolisticon.aptk.tools.corematcher.AptkCoreMatchers;
import io.toolisticon.aptk.tools.fluentfilter.FluentElementFilter;
import io.toolisticon.cute.CompileTestBuilder;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import java.util.Arrays;
import java.util.List;


/**
 * Integration test for {@link ElementUtils}.
 * <p/>
 * Test is executed at compile time of a test class.
 */
public class AnnotationUtilsTest {

    private CompileTestBuilder.UnitTestBuilder unitTestBuilder = CompileTestBuilder
            .unitTest()
            .useSource(JavaFileObjectUtils.readFromResource("/AnnotationClassAttributeTestClass.java"));

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }

    // -----------------------------------
    // Single class attribute
    // -----------------------------------


    @Test
    public void annotationUtilsTest_classAttribute_emptyValue() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                List<? extends Element> result = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                        .applyFilter(AptkCoreMatchers.BY_ANNOTATION).filterByAllOf(ClassAttributeAnnotation.class)
                        .applyFilter(AptkCoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                        .getResult();

                Element testElement = FluentElementFilter.createFluentElementFilter(result)
                        .applyFilter(AptkCoreMatchers.BY_NAME).filterByOneOf("test_classAttribute_empty")
                        .getResult().get(0);


                // shouldn't find nonexisting
                MatcherAssert.assertThat(AnnotationUtils.getClassAttributeFromAnnotationAsFqn(testElement, ClassAttributeAnnotation.class), Matchers.nullValue());


            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }


    @Test
    public void annotationUtilsTest_classAttribute_StringClassValue() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                List<? extends Element> result = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                        .applyFilter(AptkCoreMatchers.BY_ANNOTATION).filterByAllOf(ClassAttributeAnnotation.class)
                        .applyFilter(AptkCoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                        .getResult();

                Element testElement = FluentElementFilter.createFluentElementFilter(result)
                        .applyFilter(AptkCoreMatchers.BY_NAME).filterByOneOf("test_classAttribute_atDefaultValue")
                        .getResult().get(0);


                // shouldn't find nonexisting
                MatcherAssert.assertThat(AnnotationUtils.getClassAttributeFromAnnotationAsFqn(testElement, ClassAttributeAnnotation.class), Matchers.equalTo(String.class.getCanonicalName()));


            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }


    @Test
    public void annotationUtilsTest_classAttributeWithExplicitAttributeName_LongClassValue() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                List<? extends Element> result = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                        .applyFilter(AptkCoreMatchers.BY_ANNOTATION).filterByAllOf(ClassAttributeAnnotation.class)
                        .applyFilter(AptkCoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                        .getResult();

                Element testElement = FluentElementFilter.createFluentElementFilter(result)
                        .applyFilter(AptkCoreMatchers.BY_NAME).filterByOneOf("test_classAttribute_atNamedAttribute")
                        .getResult().get(0);


                // shouldn't find nonexisting
                MatcherAssert.assertThat(AnnotationUtils.getClassAttributeFromAnnotationAsFqn(testElement, ClassAttributeAnnotation.class, "classAttribute"), Matchers.equalTo(Long.class.getCanonicalName()));


            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }


    // -----------------------------------
    // array class attribute
    // -----------------------------------

    @Test
    public void annotationUtilsTest_arrayClassAttribute_emptyValue() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                List<? extends Element> result = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                        .applyFilter(AptkCoreMatchers.BY_ANNOTATION).filterByAllOf(ClassArrayAttributeAnnotation.class)
                        .applyFilter(AptkCoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                        .getResult();

                Element testElement = FluentElementFilter.createFluentElementFilter(result)
                        .applyFilter(AptkCoreMatchers.BY_NAME).filterByOneOf("test_classArrayAttribute_empty")
                        .getResult().get(0);


                // shouldn't find nonexisting
                MatcherAssert.assertThat(AnnotationUtils.getClassArrayAttributeFromAnnotationAsFqn(testElement, ClassArrayAttributeAnnotation.class), Matchers.arrayWithSize(0));


            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

    @Test
    public void annotationUtilsTest_arrayClassAttribute_StringDoubleFloatValues() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                List<? extends Element> result = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                        .applyFilter(AptkCoreMatchers.BY_ANNOTATION).filterByAllOf(ClassArrayAttributeAnnotation.class)
                        .applyFilter(AptkCoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                        .getResult();

                Element testElement = FluentElementFilter.createFluentElementFilter(result)
                        .applyFilter(AptkCoreMatchers.BY_NAME).filterByOneOf("test_classArrayAttribute_atDefaultValue")
                        .getResult().get(0);


                // shouldn't find nonexisting
                MatcherAssert.assertThat(Arrays.asList(AnnotationUtils.getClassArrayAttributeFromAnnotationAsFqn(testElement, ClassArrayAttributeAnnotation.class)), Matchers.contains(String.class.getCanonicalName(), Double.class.getCanonicalName(), Float.class.getCanonicalName()));


            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }


    @Test
    public void annotationUtilsTest_arrayClassAttributeWithExplicitAttributeName_LongIntegerValues() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                List<? extends Element> result = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                        .applyFilter(AptkCoreMatchers.BY_ANNOTATION).filterByAllOf(ClassArrayAttributeAnnotation.class)
                        .applyFilter(AptkCoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                        .getResult();

                Element testElement = FluentElementFilter.createFluentElementFilter(result)
                        .applyFilter(AptkCoreMatchers.BY_NAME).filterByOneOf("test_classArrayAttribute_atNamedAttribute")
                        .getResult().get(0);


                // shouldn't find nonexisting
                MatcherAssert.assertThat(Arrays.asList(AnnotationUtils.getClassArrayAttributeFromAnnotationAsFqn(testElement, ClassArrayAttributeAnnotation.class, "classArrayAttribute")), Matchers.contains(Long.class.getCanonicalName(), Integer.class.getCanonicalName()));


            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

    @Test
    public void annotationUtilsTest_arrayClassAttributeWithExplicitAttributeName_LongIntegerAnnotationClassAttributeTestClassValues() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                List<? extends Element> result = FluentElementFilter.createFluentElementFilter(element.getEnclosedElements())
                        .applyFilter(AptkCoreMatchers.BY_ANNOTATION).filterByAllOf(ClassArrayAttributeAnnotation.class)
                        .applyFilter(AptkCoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                        .getResult();

                Element testElement = FluentElementFilter.createFluentElementFilter(result)
                        .applyFilter(AptkCoreMatchers.BY_NAME).filterByOneOf("test_classArrayAttribute_atNamedAttribute_withUncompiledClass")
                        .getResult().get(0);


                // shouldn't find nonexisting
                MatcherAssert.assertThat(Arrays.asList(AnnotationUtils.getClassArrayAttributeFromAnnotationAsFqn(testElement, ClassArrayAttributeAnnotation.class, "classArrayAttribute")), Matchers.contains(Long.class.getCanonicalName(), Integer.class.getCanonicalName(), "io.toolisticon.annotationprocessor.AnnotationClassAttributeTestClass"));


            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }


    // --------------------------------------------
    // -- getAnnotationValueOfAttribute
    // --------------------------------------------


    @Test
    public void annotationUtilsTest_getAnnotationValueOfAttribute_getImplicitlySetAnnotationValueMustReturnNull() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                // precondition
                AnnotationMirror annotationMirror = AnnotationUtils.getAnnotationMirror(element, DefaultValueAnnotation.class);
                MatcherAssert.assertThat(annotationMirror, Matchers.notNullValue());

                // test
                AnnotationValue value = AnnotationUtils.getAnnotationValueOfAttribute(annotationMirror);

                // shouldn't find nonexisting
                MatcherAssert.assertThat(value, Matchers.nullValue());

            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }


    // --------------------------------------------
    // -- getAnnotationValueOfAttributeWithDefaults
    // --------------------------------------------

    @Test
    public void annotationUtilsTest_getAnnotationValueOfAttributeWithDefaults_getImplicitlySetAnnotationValue_defaultValue() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                // precondition
                AnnotationMirror annotationMirror = AnnotationUtils.getAnnotationMirror(element, DefaultValueAnnotation.class);
                MatcherAssert.assertThat(annotationMirror, Matchers.notNullValue());

                // test
                AnnotationValue value = AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(annotationMirror);

                // shouldn't find nonexisting
                MatcherAssert.assertThat((Long) value.getValue(), Matchers.is(5L));

            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

    // --------------------------------------------
    // -- getMandatoryAttributeValueNames
    // --------------------------------------------

    @Test
    public void annotationUtilsTest_getMandatoryAttributeValueNames_getMandatoryAttributeValueNames() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                // precondition
                AnnotationMirror annotationMirror = AnnotationUtils.getAnnotationMirror(element, DefaultValueAnnotation.class);
                MatcherAssert.assertThat(annotationMirror, Matchers.notNullValue());

                String[] names = AnnotationUtils.getMandatoryAttributeValueNames(annotationMirror);

                // shouldn't find nonexisting
                MatcherAssert.assertThat(Arrays.asList(names), Matchers.contains("mandatoryValue"));

            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }


    // --------------------------------------------
    // -- getOptionalAttributeValueNames
    // --------------------------------------------

    @Test
    public void annotationUtilsTest_getOptionalAttributeValueNames_getOptionalAttributeValueNames() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                // precondition
                AnnotationMirror annotationMirror = AnnotationUtils.getAnnotationMirror(element, DefaultValueAnnotation.class);
                MatcherAssert.assertThat(annotationMirror, Matchers.notNullValue());

                // test
                String[] names = AnnotationUtils.getOptionalAttributeValueNames(annotationMirror);

                // shouldn't find nonexisting
                MatcherAssert.assertThat(Arrays.asList(names), Matchers.contains("value"));

            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

    // --------------------------------------------
    // -- getElementForAnnotationMirror
    // --------------------------------------------

    @Test
    public void getElementForAnnotationMirror_getElement() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                // precondition
                AnnotationMirror annotationMirror = AnnotationUtils.getAnnotationMirror(element, DefaultValueAnnotation.class);
                MatcherAssert.assertThat(annotationMirror, Matchers.notNullValue());

                // test
                TypeElement result = (TypeElement) AnnotationUtils.getElementForAnnotationMirror(annotationMirror);

                MatcherAssert.assertThat(result, Matchers.notNullValue());
                MatcherAssert.assertThat(result.toString(), Matchers.is(DefaultValueAnnotation.class.getCanonicalName()));

            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

    // --------------------------------------------
    // -- getClassAttributeFromAnnotationAsTypeMirror
    // -- and
    // -- getClassAttributeFromAnnotationAsFqn
    // --------------------------------------------

    @ClassAttributeAnnotation(value = String.class, classAttribute = Long.class)
    static class ClassAttributeTestcase_WithCorrectClassAttribute {

    }

    @DefaultValueAnnotation(mandatoryValue = 3L)
    @NoAttributeAnnotation
    static class ClassAttributeTestcase_NonClassAttribute {

    }

    @Test
    public void getClassAttributeFromAnnotationAsTypeMirror_shouldGetClassAttributeSuccefully() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                // precondition
                TypeElement typeElement = TypeUtils.TypeRetrieval.getTypeElement(ClassAttributeTestcase_WithCorrectClassAttribute.class);
                MatcherAssert.assertThat("PRECONDITION : TypeElement must exist", typeElement, Matchers.notNullValue());

                // test - for value
                TypeMirror result = AnnotationUtils.getClassAttributeFromAnnotationAsTypeMirror(typeElement, ClassAttributeAnnotation.class);

                MatcherAssert.assertThat(result, Matchers.notNullValue());
                MatcherAssert.assertThat("Type must match String : ", TypeUtils.TypeComparison.isTypeEqual(result, String.class));

                // test - for value
                result = AnnotationUtils.getClassAttributeFromAnnotationAsTypeMirror(typeElement, ClassAttributeAnnotation.class, "classAttribute");

                MatcherAssert.assertThat(result, Matchers.notNullValue());
                MatcherAssert.assertThat("Type must match Long : ", TypeUtils.TypeComparison.isTypeEqual(result, Long.class));

            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

    @Test
    public void getClassAttributeFromAnnotationAsTypeMirror_shouldReturnNullForNonMatchingClassAttributes() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                // precondition
                TypeElement typeElement = TypeUtils.TypeRetrieval.getTypeElement(ClassAttributeTestcase_WithCorrectClassAttribute.class);
                MatcherAssert.assertThat("PRECONDITION : TypeElement must exist", typeElement, Matchers.notNullValue());

                // test - no class based attribute
                TypeMirror result = AnnotationUtils.getClassAttributeFromAnnotationAsTypeMirror(typeElement, DefaultValueAnnotation.class);
                MatcherAssert.assertThat(result, Matchers.nullValue());

                // test - no class based attribute
                result = AnnotationUtils.getClassAttributeFromAnnotationAsTypeMirror(typeElement, DefaultValueAnnotation.class, "mandatoryValue");
                MatcherAssert.assertThat(result, Matchers.nullValue());

                // test - annotation not found
                result = AnnotationUtils.getClassAttributeFromAnnotationAsTypeMirror(typeElement, ClassArrayAttributeAnnotation.class);
                MatcherAssert.assertThat(result, Matchers.nullValue());

                // test - annotation doesn't take attributes
                result = AnnotationUtils.getClassAttributeFromAnnotationAsTypeMirror(typeElement, NoAttributeAnnotation.class);
                MatcherAssert.assertThat(result, Matchers.nullValue());


            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }


    @Test
    public void getClassAttributeFromAnnotationAsFqn_shouldGetClassAttributeSuccefully() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                // precondition
                TypeElement typeElement = TypeUtils.TypeRetrieval.getTypeElement(ClassAttributeTestcase_WithCorrectClassAttribute.class);
                MatcherAssert.assertThat("PRECONDITION : TypeElement must exist", typeElement, Matchers.notNullValue());

                // test - for value
                String result = AnnotationUtils.getClassAttributeFromAnnotationAsFqn(typeElement, ClassAttributeAnnotation.class);

                MatcherAssert.assertThat(result, Matchers.notNullValue());
                MatcherAssert.assertThat("Type must match String : ", String.class.getCanonicalName().equals(result));

                // test - for value
                result = AnnotationUtils.getClassAttributeFromAnnotationAsFqn(typeElement, ClassAttributeAnnotation.class, "classAttribute");

                MatcherAssert.assertThat(result, Matchers.notNullValue());
                MatcherAssert.assertThat("Type must match Long : ", Long.class.getCanonicalName().equals(result));

                // test - for value
                result = AnnotationUtils.getClassAttributeFromAnnotationAsFqn(AnnotationUtils.getAnnotationMirror(typeElement, ClassAttributeAnnotation.class));

                MatcherAssert.assertThat(result, Matchers.notNullValue());
                MatcherAssert.assertThat("Type must match String : ", String.class.getCanonicalName().equals(result));

                // test - for value
                result = AnnotationUtils.getClassAttributeFromAnnotationAsFqn(AnnotationUtils.getAnnotationMirror(typeElement, ClassAttributeAnnotation.class), "classAttribute");

                MatcherAssert.assertThat(result, Matchers.notNullValue());
                MatcherAssert.assertThat("Type must match Long : ", Long.class.getCanonicalName().equals(result));
            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

    @Test
    public void getClassAttributeFromAnnotationAsFqn_shouldReturnNullForNonMatchingClassAttributes() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                // precondition
                TypeElement typeElement = TypeUtils.TypeRetrieval.getTypeElement(ClassAttributeTestcase_WithCorrectClassAttribute.class);
                MatcherAssert.assertThat("PRECONDITION : TypeElement must exist", typeElement, Matchers.notNullValue());

                // test - no class based attribute
                String result = AnnotationUtils.getClassAttributeFromAnnotationAsFqn(typeElement, DefaultValueAnnotation.class);
                MatcherAssert.assertThat(result, Matchers.nullValue());

                // test - no class based attribute
                result = AnnotationUtils.getClassAttributeFromAnnotationAsFqn(typeElement, DefaultValueAnnotation.class, "mandatoryValue");
                MatcherAssert.assertThat(result, Matchers.nullValue());

                // test - annotation not found
                result = AnnotationUtils.getClassAttributeFromAnnotationAsFqn(typeElement, ClassArrayAttributeAnnotation.class);
                MatcherAssert.assertThat(result, Matchers.nullValue());

                // test - annotation doesn't take attributes
                result = AnnotationUtils.getClassAttributeFromAnnotationAsFqn(typeElement, NoAttributeAnnotation.class);
                MatcherAssert.assertThat(result, Matchers.nullValue());


            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

}
