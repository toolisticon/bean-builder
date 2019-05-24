package io.toolisticon.beanbuilder.processor;

import io.toolisticon.annotationprocessortoolkit.AbstractAnnotationProcessor;
import io.toolisticon.annotationprocessortoolkit.tools.ElementUtils;
import io.toolisticon.annotationprocessortoolkit.tools.corematcher.CoreMatchers;
import io.toolisticon.annotationprocessortoolkit.tools.fluentvalidator.FluentElementValidator;
import io.toolisticon.beanbuilder.api.BeanBuilder;
import io.toolisticon.spiap.api.Service;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Set;

/**
 * Annotation Processor for {@link io.toolisticon.beanbuilder.api.BeanBuilder}.
 */
@Service(Processor.class)
public class BeanBuilderProcessor extends AbstractAnnotationProcessor {

    private final static Set<String> SUPPORTED_ANNOTATIONS = createSupportedAnnotationSet(BeanBuilder.class);

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return SUPPORTED_ANNOTATIONS;
    }

    @Override
    public boolean processAnnotations(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        // process Services annotation
        for (Element element : roundEnv.getElementsAnnotatedWith(BeanBuilder.class)) {

            // first do some validations if annotation have been placed correctly
            FluentElementValidator.createFluentElementValidator(element)
                    .is(CoreMatchers.IS_CLASS)
                    .applyValidator(CoreMatchers.BY_MODIFIER).hasNoneOf(Modifier.ABSTRACT)
                    .applyValidator(CoreMatchers.HAS_PUBLIC_NOARG_CONSTRUCTOR)
                    .validateAndIssueMessages();

            // It's safe to cast to TypeElement now
            TypeElement typeElement = (TypeElement) element;

            // get annotation
            BeanBuilder beanBuilderAnnotation = typeElement.getAnnotation(BeanBuilder.class);

            // Now get all attributes
            createBuilderClass(typeElement, beanBuilderAnnotation, CommonUtils.getAttributes(typeElement, beanBuilderAnnotation.inheritFields()));


        }

        return false;

    }


    /**
     * Generates the builder class
     *
     * @param typeElement           The TypeElement representing the class to generate the builder for
     * @param beanBuilderAnnotation The BeanBuilder annotation
     * @param attributes            The attributes provided by the builder
     */
    private void createBuilderClass(TypeElement typeElement, BeanBuilder beanBuilderAnnotation, List<Attribute> attributes) {


        // Now create builder class with attributes
        String packageName = beanBuilderAnnotation.packageName().isEmpty() ?
                ((PackageElement) ElementUtils.AccessEnclosingElements.getFirstEnclosingElementOfKind(typeElement, ElementKind.PACKAGE)).getQualifiedName().toString()
                : beanBuilderAnnotation.packageName();
        String baseClassName = typeElement.getSimpleName().toString();
        String builderClassName = beanBuilderAnnotation.className().isEmpty() ?
                typeElement.getSimpleName().toString() + "Builder"
                : beanBuilderAnnotation.className();

        // create the Builder class
        CommonUtils.createBuilderClass(typeElement, packageName, baseClassName, builderClassName, "public", attributes);

    }

}
