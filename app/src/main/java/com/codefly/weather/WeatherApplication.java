package com.codefly.weather;

import android.app.Activity;
import android.app.Application;

import com.codefly.weather.di.components.AppComponent;
import com.codefly.weather.di.components.DaggerAppComponent;
import com.codefly.weather.di.modules.ApiServiceModule;
import com.codefly.weather.di.modules.DBModule;

import javax.inject.Inject;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */

public class WeatherApplication extends Application implements HasActivityInjector{

    @Inject
    DispatchingAndroidInjector<Activity> mActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        getComponent();
    }

    public AppComponent getComponent(){
       AppComponent appComponent= DaggerAppComponent.builder()
                .application(this)
                .apiModule(new ApiServiceModule())
                .dbModule(new DBModule())
                .build();
                appComponent.inject(this);
                return appComponent;
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return mActivityInjector;
    }
}
