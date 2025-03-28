package io.toolisticon.aptk.tools.matcher.impl;

import io.toolisticon.aptk.tools.AbstractUnitTestAnnotationProcessorClass;
import io.toolisticon.aptk.tools.TestAnnotation;
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
 * Unit test for {@link ByRawTypeMatcher}.
 */
public class ByRawTypeMatcherTest {


    private CompileTestBuilder.UnitTestBuilder unitTestBuilder = CompileTestBuilder
            .unitTest()
            .useSource(JavaFileObjectUtils.readFromResource("/AnnotationClassAttributeTestClass.java"));


    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }

    @Test
    public void getStringRepresentationOfPassedCharacteristic_happyPath() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                MatcherAssert.assertThat("Should return cannonical class name of annotation class", AptkCoreMatchers.BY_RAW_TYPE.getMatcher().getStringRepresentationOfPassedCharacteristic(ByRawTypeMatcherTest.class).equals(ByRawTypeMatcherTest.class.getCanonicalName()));

            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

    @Test
    public void getStringRepresentationOfPassedCharacteristic_passedNullValue_shouldReturnNull() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                MatcherAssert.assertThat("Should return null for null valued parameter", AptkCoreMatchers.BY_RAW_TYPE.getMatcher().getStringRepresentationOfPassedCharacteristic(null) == null);

            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

    @Test
    public void checkForMatchingCharacteristic_match() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                TypeElement tmpElement = TypeUtils.TypeRetrieval.getTypeElement(ByRawTypeMatcherTest.class);
                MatcherAssert.assertThat("Should find match correctly", AptkCoreMatchers.BY_RAW_TYPE.getMatcher().checkForMatchingCharacteristic(tmpElement, ByRawTypeMatcherTest.class));

            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

    @Test
    public void checkForMatchingCharacteristic_mismatch() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                TypeElement tmpElement = TypeUtils.TypeRetrieval.getTypeElement(String.class);
                MatcherAssert.assertThat("Should find match correctly", !AptkCoreMatchers.BY_RAW_TYPE.getMatcher().checkForMatchingCharacteristic(tmpElement, ByRawTypeMatcherTest.class));

            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

    @Test
    public void checkForMatchingCharacteristic_nullValuedElement() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                MatcherAssert.assertThat("Should return false in case of null valued element", !AptkCoreMatchers.BY_RAW_TYPE.getMatcher().checkForMatchingCharacteristic(null, TestAnnotation.class));

            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

    @Test
    public void checkForMatchingCharacteristic_nullValuedRawType() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                MatcherAssert.assertThat("Should return false in case of null valued annotation", !AptkCoreMatchers.BY_RAW_TYPE.getMatcher().checkForMatchingCharacteristic(element, null));

            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }

    @Test
    public void checkForMatchingCharacteristic_nullValuedElementAndRawType() {

        unitTestBuilder.useProcessor(new AbstractUnitTestAnnotationProcessorClass() {
            @Override
            protected void testCase(TypeElement element) {

                MatcherAssert.assertThat("Should return false in case of null valued parameters", !AptkCoreMatchers.BY_RAW_TYPE.getMatcher().checkForMatchingCharacteristic(null, null));


            }
        })
                .compilationShouldSucceed()
                .executeTest();
    }


}





