package uca.desapmov.econic;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

public class Econic extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setup();
    }

    private void setup() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
