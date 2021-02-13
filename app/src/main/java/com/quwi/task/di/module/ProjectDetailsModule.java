package com.quwi.task.di.module;

import com.quwi.task.ui.details.ProjectDetailsFragment;
import com.quwi.task.ui.projects.ProjectListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = {ProjectDetailsModule.FragmentModule.class})
public class ProjectDetailsModule {

    @Module
    public interface FragmentModule {

        @ContributesAndroidInjector
        ProjectDetailsFragment fragment();
    }
}
