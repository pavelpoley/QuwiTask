package com.quwi.task.di.module;

import com.quwi.task.ui.projects.ProjectListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = {ProjectListModule.FragmentModule.class})
public class ProjectListModule {

    @Module
    public interface FragmentModule {

        @ContributesAndroidInjector
        ProjectListFragment fragment();
    }
}
