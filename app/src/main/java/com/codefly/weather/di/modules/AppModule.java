package com.codefly.weather.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    public Application provideApplication(Application application){
        return application;
    }
}
