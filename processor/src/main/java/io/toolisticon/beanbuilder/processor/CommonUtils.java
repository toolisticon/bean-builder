package io.toolisticon.beanbuilder.processor;

import io.toolisticon.aptk.tools.BeanUtils;
import io.toolisticon.aptk.tools.FilerUtils;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.aptk.tools.TypeMirrorWrapper;
import io.toolisticon.aptk.tools.generators.SimpleJavaWriter;

import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class CommonUtils {

    /**
     * Hidden constructor.
     */
    private CommonUtils() {

    }

    /**
     * Get all attributes
     *
     * @param typeElement the typeElement to get the attributes for
     * @return a list containing all attributes
     */
    static List<Attribute> getAttributes(TypeElement typeElement, boolean inheritFields) {
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
    static void createBuilderClass(TypeElement typeElement, String packageName, String baseClassName, String builderClassName, String visibility, List<Attribute> attributes) {

        // fill imports
        Set<String> imports = new HashSet<String>();
        imports.add(typeElement.getQualifiedName().toString());
        for (Attribute attribute : attributes) {
            imports.addAll(attribute.getWrappedAttribute().getFieldTypeMirror().getImports());
        }

        // Fill Model
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("packageName", packageName);
        model.put("imports", imports);
        model.put("visibility", visibility);
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
            MessagerUtils.error(typeElement, BeanBuilderProcessorMessages.ERROR_COULD_NOT_CREATE_BUILDER_CLASS.getMessage(), filePath, e.getMessage());
        }

    }

}
