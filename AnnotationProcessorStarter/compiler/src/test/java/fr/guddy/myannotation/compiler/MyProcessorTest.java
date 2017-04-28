package fr.guddy.myannotation.compiler;

import com.google.testing.compile.JavaFileObjects;
import com.google.testing.compile.JavaSourcesSubjectFactory;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import javax.tools.JavaFileObject;

import static com.google.common.truth.Truth.assert_;

public class MyProcessorTest {
    @Test
    public void process_A_with_annotated_method() throws IOException {
        final JavaFileObject lInput = JavaFileObjects.forResource("assets/A.java");
        final JavaFileObject lOutput = JavaFileObjects.forResource("assets/A$$MyAnnotation.java");

        assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(Arrays.asList(lInput))
                .processedWith(new MyProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(lOutput);
    }
}