package io.toolisticon.beanbuilder.processor;

import io.toolisticon.aptk.tools.AbstractAnnotationProcessor;
import io.toolisticon.aptk.tools.AnnotationUtils;
import io.toolisticon.aptk.tools.TypeUtils;
import io.toolisticon.aptk.tools.corematcher.CoreMatchers;
import io.toolisticon.aptk.tools.fluentvalidator.FluentElementValidator;
import io.toolisticon.beanbuilder.api.ThirdPartyBeanBuilder;
import io.toolisticon.spiap.api.SpiService;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Annotation Processor for {@link io.toolisticon.beanbuilder.api.ThirdPartyBeanBuilder}.
 */
@SpiService(Processor.class)
public class ThirdPartyBeanBuilderProcessor extends AbstractAnnotationProcessor {

    private final static Set<String> SUPPORTED_ANNOTATIONS = createSupportedAnnotationSet(ThirdPartyBeanBuilder.class);

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return SUPPORTED_ANNOTATIONS;
    }

    @Override
    public boolean processAnnotations(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        // process Services annotation
        for (Element element : roundEnv.getElementsAnnotatedWith(ThirdPartyBeanBuilder.class)) {

            PackageElement packageElement = (PackageElement) element;

            TypeMirror[] typeMirrorsToGenerateBuildersFor = AnnotationUtils.getClassArrayAttributeFromAnnotationAsTypeMirror(element, ThirdPartyBeanBuilder.class);

            Set<TypeMirror> doubletFilter = new HashSet<TypeMirror>();

            for (TypeMirror typeMirror : typeMirrorsToGenerateBuildersFor) {

                if (doubletFilter.contains(typeMirror)) {
                    continue;
                } else {
                    doubletFilter.add(typeMirror);
                }

                TypeElement typeElement = TypeUtils.TypeRetrieval.getTypeElement(typeMirror);


                // first do some validations if annotation have been placed correctly
                FluentElementValidator.createFluentElementValidator(typeElement)
                        .is(CoreMatchers.IS_CLASS)
                        .applyValidator(CoreMatchers.BY_MODIFIER).hasNoneOf(Modifier.ABSTRACT)
                        .applyValidator(CoreMatchers.HAS_PUBLIC_NOARG_CONSTRUCTOR)
                        .validateAndIssueMessages();


                // Now get all attributes
                createBuilderClass(packageElement, typeElement, CommonUtils.getAttributes(typeElement, true));


            }


        }

        return false;

    }


    /**
     * Generates the builder class
     *
     * @param packageElement The PackageElement representing the target package for the builders
     * @param typeElement    The TypeElement representing the class to generate the builder for
     * @param attributes     The attributes provided by the builder
     */
    private void createBuilderClass(PackageElement packageElement, TypeElement typeElement, List<Attribute> attributes) {


        // Now create builder class with attributes
        String packageName = packageElement.getQualifiedName().toString();
        String baseClassName = typeElement.getSimpleName().toString();
        String builderClassName = typeElement.getSimpleName().toString() + "Builder";

        // create the Builder class
        CommonUtils.createBuilderClass(typeElement, packageName, baseClassName, builderClassName, "", attributes);


    }

}

