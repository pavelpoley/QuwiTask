package com.quwi.task.data.network;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.quwi.task.App;
import com.quwi.task.data.repository.SharedPrefRepository;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.quwi.task.data.repository.SharedPrefRepository.PrefKeys;

public class ApiRequestInterceptor implements Interceptor {

    public static final String HEADER_NO_AUTH_KEY = "NO-AUTH";
    public static final String HEADER_NO_AUTH = HEADER_NO_AUTH_KEY + ": true";

    public static final String HEADER_ACCEPT = "accept";
    public static final String HEADER_CONTENT_TYPE = "content-type";
    public static final String HEADER_VALUE_APPLICATION_JSON = "application/json;charset=UTF-8";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String AUTH_BEARER = "Bearer ";

    private final SharedPrefRepository sharedPrefRepository;

    public ApiRequestInterceptor(SharedPrefRepository sharedPrefRepository) {
        this.sharedPrefRepository = sharedPrefRepository;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder();
        requestBuilder.header(HEADER_ACCEPT, HEADER_VALUE_APPLICATION_JSON);
        requestBuilder.header(HEADER_CONTENT_TYPE, HEADER_VALUE_APPLICATION_JSON);


        boolean auth = original.header(HEADER_NO_AUTH_KEY) == null;
        if (auth) {
            String token = sharedPrefRepository.get(PrefKeys.TOKEN, "");
            if (TextUtils.isEmpty(token)) {
                return onNotAuthorized();
            }
            requestBuilder.header(HEADER_AUTHORIZATION, AUTH_BEARER + token);
        }

        Response response = chain.proceed(requestBuilder.build());

        if (response.code() == 401 && auth) {
            return onNotAuthorized();
        }

        return response;
    }

    private Response onNotAuthorized() {
        App.getInstance().signOut();
        throw new IllegalStateException("Not authorized");
    }
}
