package com.codefly.weather.databases.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.codefly.weather.databases.DatabaseUtils;
import com.codefly.weather.models.Weather;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */

@Dao
public abstract class WeatherDao {
    @Query("select * from "+ DatabaseUtils.TABLE_NAME)
    public abstract Weather getWeather();

    @Query("select * from "+ DatabaseUtils.TABLE_NAME+" where id = :id")
    public abstract Weather getWeatherById(String id);

    @Insert(onConflict = REPLACE)
    public abstract void add(Weather weather);

    @Delete
    public abstract void delete(Weather weather);

    @Query("DELETE FROM "+ DatabaseUtils.TABLE_NAME)
    public abstract void deleteAll();
}
