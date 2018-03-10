package com.startup.app;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by julienchahoud on 3/10/18.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}