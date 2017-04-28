package fr.guddy.myannotation.compiler;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.TypeElement;

public final class AnnotatedClasses {
    //region Fields
    private final List<AnnotatedClass> mAnnotatedClasses;
    //endregion

    //region Constructor
    public AnnotatedClasses() {
        mAnnotatedClasses = new ArrayList<>();
    }
    //endregion

    //region Visible API
    public AnnotatedClass annotatedClassForClass(final TypeElement pClass) {
        // get existing one
        for (final AnnotatedClass lAnnotatedClass : mAnnotatedClasses) {
            if (lAnnotatedClass.enclosingClass() == pClass) {
                return lAnnotatedClass;
            }
        }
        // or create a new one if not exist
        final AnnotatedClass lAnnotatedClass = new AnnotatedClass(pClass);
        mAnnotatedClasses.add(lAnnotatedClass);
        return lAnnotatedClass;
    }

    public List<AnnotatedClass> annotatedClasses() {
        return mAnnotatedClasses;
    }
    //endregion
}
