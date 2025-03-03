package ${state.packageName};
!{for import : atw.imports}
import ${import};
!{/for}
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;
import io.toolisticon.aptk.tools.AnnotationUtils;
import io.toolisticon.aptk.tools.TypeMirrorWrapper;
import io.toolisticon.aptk.tools.TypeUtils;


/**
 * Wrapper class to read attribute values from Annotation ${atw.simpleName}.
 */
${state.visibilityModifier}class ${atw.simpleName}Wrapper {

    private final Element annotatedElement;
    private final AnnotationMirror annotationMirror;

    /**
     * Private constructor.
     * Used to read annotation from Element.
     * @param annotatedElement the annotated Element to annotated with this wrapper annotation
     */
    private ${atw.simpleName}Wrapper (Element annotatedElement) {
        this.annotatedElement = annotatedElement;
        this.annotationMirror = AnnotationUtils.getAnnotationMirror(annotatedElement, ${atw.simpleName}.class);
    }

    /**
     * Private constructor.
     * Mainly used for embedded annotations.
     * @param element the element related with the passed annotationMirror
     * @param annotationMirror the AnnotationMirror to wrap
     */
    private ${atw.simpleName}Wrapper (Element element, AnnotationMirror annotationMirror) {
        this.annotatedElement = element;
        this.annotationMirror = annotationMirror;
    }

    /**
     * Gets the element on which the wrapped annotation is used.
     */
    ${state.visibilityModifier}Element _annotatedElement() {
        return this.annotatedElement;
    }

    /**
     * Gets the wrapped AnnotationMirror.
     */
     ${state.visibilityModifier}AnnotationMirror _annotationMirror() {
        return this.annotationMirror;
     }

!{for attribute : atw.attributes}
!{if !attribute.isArray}!{if attribute.isPrimitiveOrString}
    /**
     * Gets the ${atw.simpleName}.${attribute.name} from wrapped annotation.
     * @return the attribute value
     */
    ${state.visibilityModifier}${attribute.attributeType} ${attribute.name}() {
        return (${attribute.attributeType})AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(annotationMirror, "${attribute.name}").getValue();
    }
!{/if}!{if attribute.isEnum}
    /**
     * Gets the ${atw.simpleName}.${attribute.name} from wrapped annotation.
     * @return the attribute value
     */
    ${state.visibilityModifier}${attribute.attributeType} ${attribute.name}() {
        VariableElement enumValue = ((VariableElement)AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(annotationMirror, "${attribute.name}").getValue());
        return ${attribute.attributeType}.valueOf(enumValue.getSimpleName().toString());
    }
!{/if}!{if attribute.isClass}
    /**
     * Gets the ${atw.simpleName}.${attribute.name} from wrapped annotation.
     * @return the attribute value as a TypeMirror
     */
    ${state.visibilityModifier}TypeMirror ${attribute.name}AsTypeMirror() {
        return (TypeMirror)AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(annotationMirror, "${attribute.name}").getValue();
    }

    /**
     * Gets the ${atw.simpleName}.${attribute.name} from wrapped annotation.
     * @return the attribute value as a TypeMirror
     */
    ${state.visibilityModifier}TypeMirrorWrapper ${attribute.name}AsTypeMirrorWrapper() {
        return TypeMirrorWrapper.wrap((TypeMirror)AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(annotationMirror, "${attribute.name}").getValue());
    }

    /**
     * Gets the ${atw.simpleName}.${attribute.name} from wrapped annotation.
     * @return the attribute value as a fqn
     */
    ${state.visibilityModifier}String ${attribute.name}AsFqn() {
        return TypeUtils.TypeConversion.convertToFqn(${attribute.name}AsTypeMirror());
    }
!{/if}!{if attribute.isAnnotationType}
    /**
     * Gets the ${atw.simpleName}.${attribute.name} from wrapped annotation.
     * @return the attribute value
     */
    ${state.visibilityModifier}AnnotationMirror ${attribute.name}AsAnnotationMirror() {
        return (AnnotationMirror)(AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(annotationMirror, "${attribute.name}").getValue());
    }

!{if attribute.isWrappedAnnotationType}
   /**
     * Gets the ${atw.simpleName}.${attribute.name} from wrapped annotation.
     * @return the attribute value
     */
    ${state.visibilityModifier}${attribute.targetWrapperAnnotationName} ${attribute.name}() {
        return ${attribute.targetWrapperAnnotationName}.wrap(this.annotatedElement, (AnnotationMirror)(AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(annotationMirror, "${attribute.name}").getValue()));
    }
!{/if}!{/if}!{/if}!{if attribute.isArray}!{if attribute.isPrimitiveArrayType}
    /**
    * Gets the ${atw.simpleName}.${attribute.name} from wrapped annotation.
    * @return the attribute value
    */
    ${state.visibilityModifier}${attribute.wrappedTypeMirror.getTypeDeclaration} ${attribute.name}() {

       List<AnnotationValue> values = (List<AnnotationValue>)AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(annotationMirror, "${attribute.name}").getValue();

       ${attribute.getComponentAttributeType}[] result = new ${attribute.getComponentAttributeType}[values.size()];

       for(int i=0 ; i < values.size() ; i++) {
            result[i] = (${attribute.getBoxedType})values.get(i).getValue();
       }

       return result;
    }
!{/if}!{if attribute.isStringArrayType}
     /**
      * Gets the ${atw.simpleName}.${attribute.name} from wrapped annotation.
      * @return the attribute value
      */
     ${state.visibilityModifier}String[] ${attribute.name}() {

         List<String> result = new ArrayList<>();
         for(AnnotationValue value : (List<AnnotationValue>)AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(annotationMirror, "${attribute.name}").getValue() ) {
              result.add((String)value.getValue());
         }

         return result.toArray(new String[result.size()]);
     }
!{/if}!{if attribute.isEnum}
    /**
     * Gets the ${atw.simpleName}.${attribute.name} from wrapped annotation.
     * @return the attribute value
     */
    ${state.visibilityModifier}${attribute.wrappedTypeMirror.getTypeDeclaration} ${attribute.name}() {

        List<${attribute.getComponentAttributeType}> result = new ArrayList<>();
        for(AnnotationValue value : (List<AnnotationValue>)AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(annotationMirror, "${attribute.name}").getValue() ) {
            VariableElement enumValue = ((VariableElement)value.getValue());
            result.add( ${attribute.getComponentAttributeType}.valueOf(enumValue.getSimpleName().toString()));
        }

        return result.toArray(new ${attribute.getComponentAttributeType}[result.size()]);
    }
!{/if}!{if attribute.isClass}
    /**
     * Gets the ${atw.simpleName}.${attribute.name} from wrapped annotation.
     * @return the attribute value as a TypeMirror
     */
    ${state.visibilityModifier}TypeMirror[] ${attribute.name}AsTypeMirror() {

        List<TypeMirror> result = new ArrayList<>();
        for(AnnotationValue value : (List<AnnotationValue>)AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(annotationMirror, "${attribute.name}").getValue() ) {
            result.add( ((TypeMirror)value.getValue()));
        }

        return result.toArray(new TypeMirror[result.size()]);
    }

    /**
     * Gets the ${atw.simpleName}.${attribute.name} from wrapped annotation.
     * @return the attribute value as a TypeMirror
     */
    ${state.visibilityModifier}TypeMirrorWrapper[] ${attribute.name}AsTypeMirrorWrapper() {

        List<TypeMirrorWrapper> result = new ArrayList<>();
        for(AnnotationValue value : (List<AnnotationValue>)AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(annotationMirror, "${attribute.name}").getValue() ) {
            result.add(TypeMirrorWrapper.wrap((TypeMirror)value.getValue()));
        }

        return result.toArray(new TypeMirrorWrapper[result.size()]);
    }

    /**
     * Gets the ${atw.simpleName}.${attribute.name} from wrapped annotation.
     * @return the attribute value as a fqn
     */
    ${state.visibilityModifier}String[] ${attribute.name}AsFqn() {

        List<String> result = new ArrayList<>();
        for(AnnotationValue value : (List<AnnotationValue>)AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(annotationMirror, "${attribute.name}").getValue() ) {
            result.add( TypeUtils.TypeConversion.convertToFqn((TypeMirror)value.getValue()));
        }

        return result.toArray(new String[result.size()]);
    }
!{/if}!{if attribute.isAnnotationType}
    /**
     * Gets the ${atw.simpleName}.${attribute.name} from wrapped annotation.
     * @return the attribute value
     */
    ${state.visibilityModifier}AnnotationMirror[] ${attribute.name}AsAnnotationMirrorArray() {
        List<AnnotationMirror> result = new ArrayList<>();
        for(AnnotationValue value : (List<AnnotationValue>)AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(annotationMirror, "${attribute.name}").getValue() ) {
            result.add( ((AnnotationMirror)value.getValue()));
        }

        return result.toArray(new AnnotationMirror[result.size()]);
    }

!{if attribute.isWrappedAnnotationType}
    /**
     * Gets the ${atw.simpleName}.${attribute.name} from wrapped annotation.
     * @return the attribute value
     */
    ${state.visibilityModifier}${attribute.targetWrapperAnnotationName}[] ${attribute.name}() {
        List<${attribute.targetWrapperAnnotationName}> result = new ArrayList<>();
        for(AnnotationValue value : (List<AnnotationValue>)AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(annotationMirror, "${attribute.name}").getValue() ) {
            result.add( ${attribute.targetWrapperAnnotationName}.wrap(this.annotatedElement, (AnnotationMirror)value.getValue()));
        }

        return result.toArray(new ${attribute.targetWrapperAnnotationName}[result.size()]);
    }
!{/if}
!{/if}!{/if}

!{if attribute.isOptional}
    /**
     * Allows to check if attribute was explicitly set or if default value is used.
     * @return true, if default value is used, otherwise false
     */
    ${state.visibilityModifier}boolean ${attribute.name}IsDefaultValue(){
        return AnnotationUtils.getAnnotationValueOfAttribute(annotationMirror,"${attribute.name}") == null;
    }
!{/if}
!{/for}!{for customCodeMethod : atw.annotationWrapperCustomCode.customMethods}
    ${state.visibilityModifier}${customCodeMethod.methodDeclarationString}{
        ${customCodeMethod.forwardCall};
    }

!{/for}

    /**
     * Checks if passed element is annotated with this wrapper annotation type : ${atw.simpleName}
     * @param element The element to check for wrapped annotation type
     * @return true, if passed element is annotated with ${atw.simpleName} annotation, otherwise false
     */
    ${state.visibilityModifier}static boolean isAnnotated(Element element) {
        return element != null && element.getAnnotation(${atw.simpleName}.class) != null;
    }

     /**
      * Gets the AnnotationMirror from passed element for this wrappers annotation type and creates a wrapper instance.
      * @param element The element to read the annotations from
      * @return The wrapped AnnotationMirror if Element is annotated with this wrappers annotation type, otherwise null.
      */
    ${state.visibilityModifier}static ${atw.simpleName}Wrapper wrap(Element element) {
        return isAnnotated(element) ? new ${atw.simpleName}Wrapper(element) : null;
    }

    /**
     * Wraps an AnnotationMirror.
     * Throws an IllegalArgumentException if passed AnnotationMirror type doesn't match the wrapped annotation type.
     * @param annotationMirror The element annotated with the annotation to wrap
     * @return The wrapper instance
     */
    ${state.visibilityModifier}static ${atw.simpleName}Wrapper wrap(AnnotationMirror annotationMirror) {
        return new ${atw.simpleName}Wrapper(null, annotationMirror);
    }

   /**
     * Wraps an AnnotationMirror.
     * Throws an IllegalArgumentException if passed AnnotationMirror type doesn't match the wrapped annotation type.
     * @param element the element bound to the usage of passed AnnotationMirror
     * @param annotationMirror The AnnotationMirror to wrap
     * @return The wrapper instance
     */
    ${state.visibilityModifier}static ${atw.simpleName}Wrapper wrap(Element element, AnnotationMirror annotationMirror) {
        return new ${atw.simpleName}Wrapper(element, annotationMirror);
    }

}