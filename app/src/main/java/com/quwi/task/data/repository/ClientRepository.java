package com.quwi.task.data.repository;

import com.quwi.task.data.network.model.request.UpdateRequest;
import com.quwi.task.data.network.model.response.ProjectListResponse;
import com.quwi.task.data.network.service.Api;

import io.reactivex.Completable;
import io.reactivex.Single;

public class ClientRepository {

    private final Api api;

    public ClientRepository(Api api) {
        this.api = api;
    }

    public Single<ProjectListResponse> getProjectList() {
        return api.getProjectList();
    }

    public Completable update(int id, UpdateRequest request) {
        return api.update(id, request);
    }
}
