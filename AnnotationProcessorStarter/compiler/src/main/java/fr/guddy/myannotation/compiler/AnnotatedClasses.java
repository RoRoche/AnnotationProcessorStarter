package fr.guddy.myannotation.compiler;

import java.util.ArrayList;

import javax.lang.model.element.TypeElement;

public final class AnnotatedClasses extends ArrayList<AnnotatedClass> {

    //region Visible API
    public AnnotatedClass annotatedClassForClass(final TypeElement pClass) {
        // get existing one
        for (final AnnotatedClass lAnnotatedClass : this) {
            if (lAnnotatedClass.enclosingClass() == pClass) {
                return lAnnotatedClass;
            }
        }
        // or create a new one if not exist
        final AnnotatedClass lAnnotatedClass = new AnnotatedClass(pClass);
        add(lAnnotatedClass);
        return lAnnotatedClass;
    }
    //endregion
}
