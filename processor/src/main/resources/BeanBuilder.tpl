package ${ packageName };

!{for import : imports}
import ${ import };
!{/for}


/**
 * A Builder for {@link ${baseClassName}}.
 */
${visibility} class ${ builderClassName } {

!{for attribute : attributes}
    private ${attribute.shortTypeName} ${attribute.fieldName};
!{/for}


    /**
     * Hidden constructor.
     */
    private ${ builderClassName }() {

    }

    /**
     * Creates the instance.
     */
    public ${baseClassName} build() {

        ${baseClassName} returnValue = new ${baseClassName}();

!{for attribute : attributes}
        returnValue.set${attribute.getCCFieldName}( this.${attribute.fieldName} );
!{/for}

        return returnValue;

    }

!{for attribute : attributes}
    public ${ builderClassName } with${attribute.getCCFieldName} (${attribute.shortTypeName} value){
        this.${attribute.fieldName} = value;
        return this;
    }

!{/for}


    /**
     * Creates the builder instance.
     */
    public static ${ builderClassName } createBuilder () {
        return new ${ builderClassName }();
    }

}
