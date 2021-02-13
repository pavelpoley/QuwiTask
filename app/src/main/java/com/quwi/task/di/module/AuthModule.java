package com.quwi.task.di.module;

import com.quwi.task.ui.auth.AuthFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = {AuthModule.FragmentModule.class})
public class AuthModule {

    @Module
    public interface FragmentModule {

        @ContributesAndroidInjector
        AuthFragment fragment();
    }
}
