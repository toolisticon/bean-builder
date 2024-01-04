package io.toolisticon.beanbuilder.processor;

import io.toolisticon.aptk.tools.BeanUtils;
import io.toolisticon.aptk.tools.FilerUtils;
import io.toolisticon.aptk.tools.TypeMirrorWrapper;
import io.toolisticon.aptk.tools.generators.SimpleJavaWriter;
import io.toolisticon.aptk.tools.wrapper.TypeElementWrapper;
import io.toolisticon.aptk.tools.wrapper.TypeParameterElementWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

final class CommonUtils {

    /**
     * Hidden constructor.
     */
    private CommonUtils() {

    }

    /**
     * Get all attributes
     *
     * @param typeElementWrapper the typeElement to get the attributes for
     * @return a list containing all attributes
     */
    static List<Attribute> getAttributes(TypeElementWrapper typeElementWrapper, boolean inheritFields) {
        BeanUtils.AttributeResult[] attributes = inheritFields ? BeanUtils.getAttributesWithInheritance(typeElementWrapper.unwrap()) : BeanUtils.getAttributes(typeElementWrapper.unwrap());

        List<Attribute> result = new ArrayList<>();
        Set<String> addedFieldNames = new HashSet<>();
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

    /**
     * Generates the builder class
     *
     * @param typeElement      The TypeElement representing the class to generate the builder for
     * @param packageName      The target package name for the builder
     * @param baseClassName    The Base class name
     * @param builderClassName The builders class name
     * @param visibility       The visibility scope to use
     * @param attributes       The attributes provided by the builder
     */
    static void createBuilderClass(TypeElementWrapper typeElement, String packageName, String baseClassName, String builderClassName, String visibility, List<Attribute> attributes) {

        // fill imports
        Set<String> imports = new HashSet<>();
        imports.add(typeElement.getQualifiedName());
        imports.addAll(typeElement.asType().getImports());
        imports.addAll(typeElement.getTypeParameters().stream().map(e -> e.getBounds().stream().map(TypeMirrorWrapper::getQualifiedName).collect(Collectors.toSet())).flatMap(Set::stream).collect(Collectors.toSet()));
        for (Attribute attribute : attributes) {
            imports.addAll(attribute.getWrappedAttribute().getFieldTypeMirror().getImports());
        }

        // Fill Model
        Map<String, Object> model = new HashMap<>();
        model.put("packageName", packageName);
        model.put("imports", imports);
        model.put("visibility", visibility);
        model.put("baseClassName", baseClassName);
        model.put("builderClassName", builderClassName);
        model.put("attributes", attributes);
        model.put("typeArgumentDeclaration", typeElement.getTypeParameters().size() > 0 ? typeElement.getTypeParameters().stream().map(e -> e.toStringWithExtendsAndBounds()).collect(Collectors.joining(", ", "<", ">")) : "");
        model.put("typeArguments", typeElement.getTypeParameters().size() > 0 ? typeElement.getTypeParameters().stream().map(TypeParameterElementWrapper::toString).collect(Collectors.joining(", ", "<", ">")) : "");


        // create the Builder class
        String filePath = packageName + "." + builderClassName;
        try {
            SimpleJavaWriter javaWriter = FilerUtils.createSourceFile(filePath, typeElement.unwrap());
            javaWriter.writeTemplate("/BeanBuilder.tpl", model);
            javaWriter.close();
        } catch (IOException e) {
            typeElement.compilerMessage().asError().write(BeanBuilderProcessorMessages.ERROR_COULD_NOT_CREATE_BUILDER_CLASS.getMessage(), filePath, e.getMessage());
        }

    }

}
