package ${ packageName };

!{for import : imports}
import ${ import };
!{/for}


/**
 * A Builder for {@link ${baseClassName}}.
 */
${visibility} class ${ builderClassName }${typeArgumentDeclaration}{

!{for attribute : attributes}
    private ${attribute.shortTypeName} ${attribute.fieldName};
!{/for}


    /**
     * Hidden constructor.
     */
    private ${ builderClassName }() {

    }

    private ${ builderClassName }${typeArguments} createNextBuilder() {
        ${ builderClassName }${typeArguments} builder = new ${ builderClassName }();

!{for attribute : attributes}
        builder.${attribute.fieldName} = this.${attribute.fieldName};
!{/for}

        return builder;
    }

    /**
     * Creates the instance.
     */
    public ${baseClassName}${typeArguments} build() {

        ${baseClassName} returnValue = new ${baseClassName}();

!{for attribute : attributes}
        returnValue.set${attribute.getCCFieldName}( this.${attribute.fieldName} );
!{/for}

        return returnValue;

    }

!{for attribute : attributes}
    public ${ builderClassName }${typeArguments} with${attribute.getCCFieldName} (${attribute.shortTypeName} value){
        ${ builderClassName } nextBuilder = createNextBuilder();
        nextBuilder.${attribute.fieldName} = value;
        return nextBuilder;
    }

!{/for}


    /**
     * Creates the builder instance.
     */
    public static ${typeArgumentDeclaration}${ builderClassName }${typeArguments} createBuilder () {
        return new ${ builderClassName }();
    }

}
