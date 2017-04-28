package fr.guddy.myannotation.compiler;

import android.util.Log;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;

import java.util.Iterator;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public final class GeneratedMethod {
    //region Fields
    private final TypeElement mClassElement;
    private final ExecutableElement mMethodElement;
    private final boolean mIsStaticMethod;
    //endregion

    //region Constructor
    public GeneratedMethod(final TypeElement pMethodElement, final ExecutableElement pClassElement) {
        mClassElement = pMethodElement;
        mMethodElement = pClassElement;
        mIsStaticMethod = mMethodElement.getModifiers().contains(Modifier.STATIC);
    }
    //endregion

    //region Visible API
    public MethodSpec build() {
        final MethodSpec.Builder lMethodBuilder = buildSignature();
        appendBody(lMethodBuilder);
        return lMethodBuilder.build();
    }
    //endregion

    //region Specific job
    private MethodSpec.Builder buildSignature() {
        final MethodSpec.Builder lBuilder = MethodSpec.methodBuilder(mMethodElement.getSimpleName().toString())
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC);

        if (!mIsStaticMethod) {
            lBuilder.addParameter(ParameterSpec.builder(ClassName.get(mClassElement), "instance").build());
        }

        for (final VariableElement lParamElement : mMethodElement.getParameters()) {
            lBuilder.addParameter(ParameterSpec.get(lParamElement));
        }

        return lBuilder;
    }

    private void appendBody(final MethodSpec.Builder pMethodBuilder) {
        pMethodBuilder.addCode("$T.d(", Log.class);
        pMethodBuilder.addCode("$S, $S", mClassElement.getQualifiedName() + "." + mMethodElement.getSimpleName(), "called");

        if (!mIsStaticMethod) {
            pMethodBuilder.addCode(" + $S + $N", " on ", "instance");
        }

        pMethodBuilder.addCode(" + $S", " with ");

        final Iterator<? extends VariableElement> lIterator = mMethodElement.getParameters().iterator();
        while (lIterator.hasNext()) {
            final VariableElement lParamElement = lIterator.next();
            // add "key=" + value
            pMethodBuilder.addCode(" + $S + $N", lParamElement.getSimpleName() + "=", lParamElement.getSimpleName());
            // add "," for next parameter
            if (lIterator.hasNext()) {
                pMethodBuilder.addCode(" + $S", ", ");
            }
        }

        pMethodBuilder.addCode(");\n");
    }
    //endregion
}
