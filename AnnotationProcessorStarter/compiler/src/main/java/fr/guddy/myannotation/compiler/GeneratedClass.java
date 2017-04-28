package fr.guddy.myannotation.compiler;

import com.squareup.javapoet.TypeSpec;

import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import fr.guddy.myannotation.MyAnnotation;

public final class GeneratedClass {
    //region Fields
    private final TypeElement mClassElement;
    private final List<ExecutableElement> mMethodElements;
    //endregion

    //region Constructor
    public GeneratedClass(final TypeElement pClassElement, final List<ExecutableElement> pMethodElements) {
        mClassElement = pClassElement;
        mMethodElements = pMethodElements;
    }
    //endregion

    //region Visible API
    public TypeSpec buildTypeSpec() {
        final String lClassName = String.format("%s$$%s", mClassElement.getSimpleName(), MyAnnotation.class.getSimpleName());
        final TypeSpec.Builder lClassBuilder = TypeSpec.classBuilder(lClassName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL);

        for (final ExecutableElement lMethodElement : mMethodElements) {
            lClassBuilder.addMethod(new GeneratedMethod(mClassElement, lMethodElement).build());
        }
        return lClassBuilder.build();
    }

    public String packageName() {
        final String lQualifiedName = mClassElement.getQualifiedName().toString();
        return lQualifiedName.substring(0, lQualifiedName.lastIndexOf("."));
    }
    //endregion
}
