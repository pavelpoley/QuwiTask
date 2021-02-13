package com.quwi.task.data.network.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginRequest {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
