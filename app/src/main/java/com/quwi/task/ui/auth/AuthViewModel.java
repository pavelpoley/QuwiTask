package com.quwi.task.ui.auth;

import com.quwi.task.data.network.model.request.LoginRequest;
import com.quwi.task.data.repository.AuthRepository;
import com.quwi.task.ui.common.BaseViewModel;
import com.quwi.task.ui.common.response.ResponseLiveData;

import javax.inject.Inject;

public class AuthViewModel extends BaseViewModel {

    private final AuthRepository authRepository;
    private final ResponseLiveData loginLiveData = new ResponseLiveData();

    @Inject
    public AuthViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void login(String email, String password) {
        subscribe(
                authRepository.login(new LoginRequest(email, password)),
                loginLiveData
        );
    }

    public ResponseLiveData getLoginLiveData() {
        return loginLiveData;
    }
}