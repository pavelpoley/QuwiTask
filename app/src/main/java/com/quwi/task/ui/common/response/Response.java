package com.quwi.task.ui.common.response;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.quwi.task.ui.common.response.Status.ERROR;
import static com.quwi.task.ui.common.response.Status.IDLE;
import static com.quwi.task.ui.common.response.Status.LOADING;
import static com.quwi.task.ui.common.response.Status.SUCCESS;


/**
 * Response holder provided to the UI
 */
public class Response<T> {

    private final Status mStatus;

    @Nullable
    private final T mData;

    @Nullable
    private final Throwable mError;

    private Response(Status status, @Nullable T data, @Nullable Throwable error) {
        mStatus = status;
        mData = data;
        mError = error;
    }

    public Status getStatus() {
        return mStatus;
    }

    @Nullable
    public T getData() {
        return mData;
    }

    @Nullable
    public Throwable getError() {
        return mError;
    }

    @Nullable
    public String getErrorMessage() {
        if (mError == null || TextUtils.isEmpty(mError.getMessage())) {
            return "Something went wrong";
        }
        return mError.getMessage();
    }

    public static <T> Response idle() {
        return new Response<T>(IDLE, null, null);
    }

    public static <T> Response loading() {
        return new Response<T>(LOADING, null, null);
    }

    public static <T> Response success(@NonNull T data) {
        return new Response<>(SUCCESS, data, null);
    }

    public static <T> Response error(@NonNull Throwable error) {
        return error(null, error);
    }

    public static <T> Response error(@Nullable T data, @NonNull Throwable error) {
        return new Response<T>(ERROR, data, error);
    }
}
