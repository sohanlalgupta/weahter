package com.codefly.weather.utils;

import io.reactivex.Observable;

/**
 * Created by YATRAONLINE\sohan.gupta on 27/5/19.
 */



public class ApiTestUtil<T> {

    public Observable<T> createCall(T data) {
        return Observable.just(data);
    }
}
