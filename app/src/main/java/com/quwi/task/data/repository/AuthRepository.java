package com.quwi.task.data.repository;

import com.quwi.task.data.network.model.request.LoginRequest;
import com.quwi.task.data.network.model.response.LoginResponse;
import com.quwi.task.data.network.service.Api;

import io.reactivex.Single;

import static com.quwi.task.data.repository.SharedPrefRepository.PrefKeys;

public class AuthRepository {

    private final Api api;
    //encrypted
    private final SharedPrefRepository sharedPrefRepository;

    public AuthRepository(Api api, SharedPrefRepository sharedPrefRepository) {
        this.api = api;
        this.sharedPrefRepository = sharedPrefRepository;
    }

    public Single<LoginResponse> login(LoginRequest body) {
        return api.login(body).doOnSuccess(loginResponse -> {
            String token = loginResponse.getToken();
            sharedPrefRepository.add(PrefKeys.TOKEN, token);
        });
    }

    public boolean isAuthorized() {
        return sharedPrefRepository.get(PrefKeys.TOKEN, null) != null;
    }

    public void signOut() {
        sharedPrefRepository.clear();
    }
}
