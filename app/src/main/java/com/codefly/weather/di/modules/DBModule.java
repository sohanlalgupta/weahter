package com.codefly.weather.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import com.codefly.weather.databases.AppLocation;
import com.codefly.weather.databases.DatabaseUtils;
import com.codefly.weather.databases.WeatherDatabase;
import com.codefly.weather.databases.dao.WeatherDao;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */

@Module
public class DBModule {

    @Provides
    @Singleton
    WeatherDatabase provideDatabase(@NonNull Application application){
        return Room.databaseBuilder(application, WeatherDatabase.class,
                DatabaseUtils.DATA_BASE_NAME)
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    WeatherDao provideWeatherDao(@NonNull WeatherDatabase database){
        return database.getWeatherDao();
    }

    @Provides
    @Singleton
    AppLocation provideAppLocation(Application application){
        return new AppLocation(application);
    }
}
