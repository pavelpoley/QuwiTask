package com.quwi.task.di.module;

import android.content.Context;

import com.quwi.task.data.network.service.Api;
import com.quwi.task.data.repository.AuthRepository;
import com.quwi.task.data.repository.ClientRepository;
import com.quwi.task.data.repository.SharedPrefRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    public AuthRepository authRepository(Api api, SharedPrefRepository sharedPrefRepository) {
        return new AuthRepository(api, sharedPrefRepository);
    }

    @Provides
    public ClientRepository clientRepository(Api api) {
        return new ClientRepository(api);
    }

    @Provides
    public SharedPrefRepository sharedPrefRepository(Context context) {
        return new SharedPrefRepository(context);
    }
}
