package com.quwi.task.di.module;

import android.util.Log;

import com.google.gson.Gson;
import com.quwi.task.BuildConfig;
import com.quwi.task.data.network.ApiRequestInterceptor;
import com.quwi.task.data.network.service.Api;
import com.quwi.task.data.repository.SharedPrefRepository;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = AppModule.class)
public class NetworkModule {

    public static final String TAG = "network";

    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.HOSTNAME)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, ApiRequestInterceptor apiRequestInterceptor) {
        return new OkHttpClient
                .Builder()
                .addInterceptor(apiRequestInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    public HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor
                = new HttpLoggingInterceptor(message -> Log.d(TAG, message));
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }

    @Provides
    public Api api(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @Provides
    public ApiRequestInterceptor apiRequestInterceptor(SharedPrefRepository sharedPrefRepository) {
        return new ApiRequestInterceptor(sharedPrefRepository);
    }
}
