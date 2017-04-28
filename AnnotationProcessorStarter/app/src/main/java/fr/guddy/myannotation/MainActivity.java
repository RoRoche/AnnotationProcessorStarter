package fr.guddy.myannotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //region Lifecycle
    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_main);
        doStuff(12);
    }
    //endregion

    //region Specific job
    @MyAnnotation
    public void doStuff(final int pAnInt) {
        MainActivity$$MyAnnotation.doStuff(this, pAnInt);
    }
    //endregion
}
