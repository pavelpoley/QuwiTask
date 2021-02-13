package com.quwi.task.di;

import android.app.Application;

import com.quwi.task.App;
import com.quwi.task.di.module.NetworkModule;
import com.quwi.task.di.module.RepositoryModule;
import com.quwi.task.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        ActivityInjector.class,
        NetworkModule.class,
        RepositoryModule.class,
        ViewModelModule.class,
})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }

}
