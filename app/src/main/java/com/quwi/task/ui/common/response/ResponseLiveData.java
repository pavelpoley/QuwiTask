package com.quwi.task.ui.common.response;

import androidx.lifecycle.MutableLiveData;

public class ResponseLiveData extends MutableLiveData<Response> {

    public ResponseLiveData() {
        setValue(Response.idle());
    }
}
