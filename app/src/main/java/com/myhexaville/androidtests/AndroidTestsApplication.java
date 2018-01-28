package com.myhexaville.androidtests;

import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;
import com.google.firebase.FirebaseApp;

public class AndroidTestsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        ViewTarget.setTagId(R.id.glide_tag);
    }
}
