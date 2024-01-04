package io.toolisticon.beanbuilder.processor;

import io.toolisticon.aptk.tools.AbstractAnnotationProcessor;
import io.toolisticon.aptk.tools.corematcher.AptkCoreMatchers;
import io.toolisticon.aptk.tools.fluentvalidator.FluentElementValidator;
import io.toolisticon.aptk.tools.wrapper.TypeElementWrapper;
import io.toolisticon.beanbuilder.api.BeanBuilder;
import io.toolisticon.spiap.api.SpiService;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Set;

/**
 * Annotation Processor for {@link io.toolisticon.beanbuilder.api.BeanBuilder}.
 */
@SpiService(Processor.class)
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
                    .is(AptkCoreMatchers.IS_CLASS)
                    .applyValidator(AptkCoreMatchers.BY_MODIFIER).hasNoneOf(Modifier.ABSTRACT)
                    .applyValidator(AptkCoreMatchers.HAS_PUBLIC_NOARG_CONSTRUCTOR)
                    .validateAndIssueMessages();

            // It's safe to cast to TypeElement now
            TypeElementWrapper typeElement = TypeElementWrapper.wrap((TypeElement) element);

            // get annotation - safe not to check Optional
            BeanBuilder beanBuilderAnnotation = typeElement.getAnnotation(BeanBuilder.class).get();

            // Now get all attributes
            createBuilderClass(typeElement, beanBuilderAnnotation, CommonUtils.getAttributes(typeElement, beanBuilderAnnotation.inheritFields()));


        }

        return false;

    }


    /**
     * Generates the builder class
     *
     * @param typeElementWrapper    The TypeElement representing the class to generate the builder for
     * @param beanBuilderAnnotation The BeanBuilder annotation
     * @param attributes            The attributes provided by the builder
     */
    private void createBuilderClass(TypeElementWrapper typeElementWrapper, BeanBuilder beanBuilderAnnotation, List<Attribute> attributes) {


        // Now create builder class with attributes
        String packageName = beanBuilderAnnotation.packageName().isEmpty() ? typeElementWrapper.getPackageName() : beanBuilderAnnotation.packageName();
        String baseClassName = typeElementWrapper.getSimpleName();
        String builderClassName = beanBuilderAnnotation.className().isEmpty() ?
                typeElementWrapper.getSimpleName() + "Builder"
                : beanBuilderAnnotation.className();

        // create the Builder class
        CommonUtils.createBuilderClass(typeElementWrapper, packageName, baseClassName, builderClassName, "public", attributes);

    }

}
