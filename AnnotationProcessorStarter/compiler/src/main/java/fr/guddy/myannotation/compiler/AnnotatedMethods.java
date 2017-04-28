package fr.guddy.myannotation.compiler;

import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import fr.guddy.myannotation.MyAnnotation;

public final class AnnotatedMethods {
    //region Fields
    private final RoundEnvironment mRoundEnvironment;
    //endregion

    //region Constructor
    public AnnotatedMethods(final RoundEnvironment pRoundEnvironment) {
        mRoundEnvironment = pRoundEnvironment;
    }
    //endregion

    //region Visible API
    public AnnotatedClasses enclosingClasses() {
        final Set<? extends Element> lElements = mRoundEnvironment.getElementsAnnotatedWith(MyAnnotation.class);

        final AnnotatedClasses lAnnotatedClasses = new AnnotatedClasses();

        for (final Element lElement : lElements) {
            if (lElement instanceof ExecutableElement) {
                final ExecutableElement lMethod = (ExecutableElement) lElement;
                final TypeElement lClass = (TypeElement) lMethod.getEnclosingElement();

                final AnnotatedClass lAnnotatedClass = lAnnotatedClasses.annotatedClassForClass(lClass);
                lAnnotatedClass.addMethod(lMethod);
            }
        }

        return lAnnotatedClasses;
    }
    //endregion

}
