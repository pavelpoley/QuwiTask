package com.quwi.task.data.network.service;

import com.quwi.task.data.network.ApiRequestInterceptor;
import com.quwi.task.data.network.model.request.LoginRequest;
import com.quwi.task.data.network.model.request.UpdateRequest;
import com.quwi.task.data.network.model.response.LoginResponse;
import com.quwi.task.data.network.model.response.ProjectListResponse;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    String API_V2 = "v2/";

    @Headers({ApiRequestInterceptor.HEADER_NO_AUTH})
    @POST(API_V2 + "auth/login")
    Single<LoginResponse> login(
            @Body LoginRequest request
    );

    @GET(API_V2 + "projects-manage/index")
    Single<ProjectListResponse> getProjectList();

    @POST(API_V2 + "projects-manage/update")
    Completable update
            (@Query("id") int id,
             @Body UpdateRequest request
            );
}
