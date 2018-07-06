package io.toolisticon.beanbuilder.processor;

import io.toolisticon.annotationprocessortoolkit.AbstractAnnotationProcessor;
import io.toolisticon.annotationprocessortoolkit.tools.BeanUtils;
import io.toolisticon.annotationprocessortoolkit.tools.ElementUtils;
import io.toolisticon.annotationprocessortoolkit.tools.FilerUtils;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.annotationprocessortoolkit.tools.corematcher.CoreMatchers;
import io.toolisticon.annotationprocessortoolkit.tools.fluentvalidator.FluentElementValidator;
import io.toolisticon.annotationprocessortoolkit.tools.generators.SimpleJavaWriter;
import io.toolisticon.beanbuilder.api.BeanBuilder;
import io.toolisticon.spiap.api.Service;
import io.toolisticon.spiap.processor.SpiProcessorMessages;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
            createBuilderClass(typeElement, getAttributes(typeElement, beanBuilderAnnotation.inheritFields()));





        }

        return false;

    }

    /**
     * Get all attributes
     *
     * @param typeElement the typeElement to get the attributes for
     * @return a list containing all attributes
     */
    protected List<Attribute> getAttributes(TypeElement typeElement, boolean inheritFields) {
        BeanUtils.AttributeResult[] attributes = inheritFields ? BeanUtils.getAttributesWithInheritance(typeElement) : BeanUtils.getAttributes(typeElement);

        List<Attribute> result = new ArrayList<Attribute>();
        Set<String> addedFieldNames = new HashSet<String>();
        if (attributes != null) {

            for (BeanUtils.AttributeResult attribute : attributes) {

                if (!addedFieldNames.contains(attribute.getFieldName())) {
                    addedFieldNames.add(attribute.getFieldName());
                    result.add(new Attribute(attribute));
                }

            }

        }

        return result;
    }

    protected void createBuilderClass(TypeElement typeElement, List<Attribute> attributes) {


        // Now create builder class with attributes
        String packageName = ((PackageElement) ElementUtils.AccessEnclosingElements.getFirstEnclosingElementOfKind(typeElement, ElementKind.PACKAGE)).getQualifiedName().toString();
        String baseClassName = typeElement.getSimpleName().toString();
        String builderClassName = typeElement.getSimpleName().toString() + "Builder";

        // fill imports
        Set<String> imports = new HashSet<String>();
        imports.add(typeElement.getQualifiedName().toString());
        for (Attribute attribute : attributes) {
            imports.add(attribute.getFQTypeName());
        }

        // Fill Model
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("packageName", packageName);
        model.put("imports", imports);
        model.put("baseClassName", baseClassName);
        model.put("builderClassName", builderClassName);
        model.put("attributes", attributes);


        // create the Builder class
        String filePath = packageName + "." + builderClassName;
        try {
            SimpleJavaWriter javaWriter = FilerUtils.createSourceFile(filePath, typeElement);
            javaWriter.writeTemplate("/BeanBuilder.tpl", model);
            javaWriter.close();
        } catch (IOException e) {
            MessagerUtils.error(typeElement, SpiProcessorMessages.ERROR_COULD_NOT_CREATE_SERVICE_LOCATOR.getMessage(), filePath);
        }

    }

}
