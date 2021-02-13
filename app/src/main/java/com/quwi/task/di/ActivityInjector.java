package com.quwi.task.di;

import com.quwi.task.di.module.ProjectDetailsModule;
import com.quwi.task.ui.MainActivity;
import com.quwi.task.di.module.AuthModule;
import com.quwi.task.di.module.ProjectListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {
        AndroidSupportInjectionModule.class
})
public abstract class ActivityInjector {

    @ContributesAndroidInjector(modules = {
            AuthModule.class,
            ProjectListModule.class,
            ProjectDetailsModule.class
    })
    abstract MainActivity mainActivity();
}
