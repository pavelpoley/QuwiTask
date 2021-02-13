package com.quwi.task.data.network.model.request;

import com.google.gson.annotations.SerializedName;

public class UpdateRequest {

    @SerializedName("name")
    private final String name;

    public UpdateRequest(String name) {
        this.name = name;
    }
}
