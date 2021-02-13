package com.quwi.task.ui.common;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.quwi.task.ui.common.response.Response;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class BaseViewModel extends ViewModel {

    protected final CompositeDisposable disposable = new CompositeDisposable();

    protected <T> void subscribe(Single<T> single, MutableLiveData<Response> response) {
        disposable.add(
                single.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(disposable -> response.setValue(Response.loading()))
                        .doFinally(() -> response.setValue(Response.idle()))
                        .subscribe(
                                result -> response.setValue(Response.success(result)),
                                e -> response.setValue(Response.error(e))
                        )
        );
    }

    protected void subscribe(Completable completable, MutableLiveData<Response> response) {
        disposable.add(
                completable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(disposable -> response.setValue(Response.loading()))
                        .doFinally(() -> response.setValue(Response.idle()))
                        .subscribe(
                                () -> response.setValue(Response.success(true)),
                                e -> response.setValue(Response.error(e))
                        )
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
