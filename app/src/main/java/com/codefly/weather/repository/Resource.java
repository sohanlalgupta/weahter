package com.codefly.weather.repository;

import android.support.annotation.NonNull;
import io.reactivex.annotations.Nullable;
import static com.codefly.weather.repository.Status.ERROR;
import static com.codefly.weather.repository.Status.LOADING;
import static com.codefly.weather.repository.Status.SUCCESS;

/**
 * Created by YATRAONLINE\sohan.gupta on 26/5/19.
 */

public class Resource<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable public final String message;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    public boolean isLoading(){
        return status==LOADING;
    }
}
