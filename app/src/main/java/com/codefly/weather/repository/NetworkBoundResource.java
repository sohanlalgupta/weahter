package com.codefly.weather.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */

public abstract class NetworkBoundResource<ResultType,RequestType> {
    private Observable<Resource<ResultType>> result;

    @MainThread
    public NetworkBoundResource(){
        Observable<Resource<ResultType>> source;
        if(shouldFetch()){
            source=createCall().subscribeOn(Schedulers.io())
                    .doOnNext(response->saveCallResult(response.data))
                    .flatMap(response->loadFromDatabase().toObservable().map(Resource::success))
                    .doOnError(t->onFetchFailed())
                    .onErrorResumeNext(t->{return loadFromDatabase().
                            toObservable().map(data -> Resource.error(t.getMessage(), data));})
                     .observeOn(AndroidSchedulers.mainThread());
        }else{
            source = loadFromDatabase()
                    .toObservable()
                    .map(Resource::success);
        }

        result = Observable.concat(
                loadFromDatabase().toObservable().map(Resource::loading).take(1), source);
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType response);

    @MainThread
    protected boolean shouldFetch(){
        return true;
    }
    protected void onFetchFailed() {}

    @NonNull
    @MainThread
    protected abstract Flowable<ResultType> loadFromDatabase();

    @NonNull
    @MainThread
    protected abstract Observable<Resource<RequestType>> createCall();

    public final Observable<Resource<ResultType>> getAsObservable() {
        return result;
    }
}
