package com.quwi.task;


import android.content.Intent;

import com.quwi.task.data.repository.AuthRepository;
import com.quwi.task.di.DaggerAppComponent;
import com.quwi.task.ui.MainActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class App extends DaggerApplication {

    private static App app;

    @Inject
    AuthRepository authRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .application(this)
                .build();
    }

    public static App getInstance() {
        return app;
    }

    public void signOut() {
        authRepository.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
