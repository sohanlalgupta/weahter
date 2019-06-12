package com.codefly.weather.databases.converter;

import android.arch.persistence.room.TypeConverter;

import com.codefly.weather.models.Current;
import com.codefly.weather.models.Forecast;
import com.codefly.weather.models.Location;
import com.google.gson.Gson;


public class Converter {
    static Gson gson=new Gson();

    @TypeConverter
    public static String toStringFromLocation(Location location) {
        return gson.toJson(location,Location.class);
    }

    @TypeConverter
    public static Location toLocationFromString(String location) {
        return gson.fromJson(location,Location.class);
    }

    @TypeConverter
    public static String toStringFromCurrentWeather(Current weather) {
        return gson.toJson(weather,Current.class);
    }

    @TypeConverter
    public static Current toCurrentWeatherFromString(String weather) {
        return gson.fromJson(weather,Current.class);
    }

    @TypeConverter
    public static String toStringFromForecast(Forecast weather) {
        return gson.toJson(weather,Forecast.class);
    }

    @TypeConverter
    public static Forecast toForecastFromString(String weather) {
        return gson.fromJson(weather,Forecast.class);
    }
}
