package com.codefly.weather.databases;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import com.codefly.weather.databases.converter.Converter;
import com.codefly.weather.databases.dao.WeatherDao;
import com.codefly.weather.models.Weather;

/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */

@Database(entities = {Weather.class}, version = 1, exportSchema = false)
@TypeConverters(Converter.class)
public abstract class WeatherDatabase extends RoomDatabase {
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    public abstract WeatherDao getWeatherDao();

}
