package com.codefly.weather.utils;

import com.codefly.weather.WeatherApiServiceAbstract;
import com.codefly.weather.models.Current;
import com.codefly.weather.models.Forecast;
import com.codefly.weather.models.Location;
import com.codefly.weather.models.Weather;
import com.codefly.weather.retrofit.NetworkUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import okio.BufferedSource;
import okio.Okio;
import okio.Source;

/**
 * Created by YATRAONLINE\sohan.gupta on 27/5/19.
 */

public class MockTestUtil {

    public static final String CITY_BANGALORE="Bangalore";
    public static final String CITY_BHOPAL="Bhopal";
    public static final Map<String,String> QUERY_PARAM_MAP;

    static {
        QUERY_PARAM_MAP = new HashMap<>();
        QUERY_PARAM_MAP.put(NetworkUtils.KEY_DAYS, NetworkUtils.VALUE_DAYS);
        QUERY_PARAM_MAP.put(NetworkUtils.KEY_CITY,CITY_BANGALORE);
        QUERY_PARAM_MAP.put(NetworkUtils.KEY_AUTHENTICATION,NetworkUtils.VALUE_AUTHENTICATION);
    }

    public static Weather mockWeatherApiResponse() {
        Weather weatherApiResponse = new Weather();
        weatherApiResponse=mockWeather(MockTestUtil.CITY_BANGALORE);
        return weatherApiResponse;
    }

    public static Weather mockWeather(String city) {
        Current current=new Current();
        Location location=new Location();
        Forecast forecast=new Forecast();
        Weather weather=new Weather();
        weather.setId(1);
        weather.setCity(city);
        weather.setCurrent(current);
        weather.setLocation(location);
        weather.setForecast(forecast);
        return weather;
    }

    public static Location mockLocation(){
        Location location=new Location();
        location.setCountry("india");
        location.setLat(12.34);
        location.setLon(12.34);
        Calendar calendar=Calendar.getInstance();
        location.setLocaltime(calendar.getTime().toString());
        location.setLocaltimeEpoch(System.currentTimeMillis());
        location.setName("bangalore");
        location.setRegion("xyz");
        location.setTzId(calendar.getTimeZone().toString());
        return location;
    }


    public static String mockLocationJson(){
        Location location=mockLocation();
        return new Gson().toJson(location,Location.class);
    }

    public static Forecast mockForecast() throws IOException {
        InputStream inputStream = WeatherApiServiceAbstract.class.getClassLoader().getResourceAsStream( "json/weather.json");
        Source source = Okio.buffer(Okio.source(inputStream));
        String res=((BufferedSource) source).readString(StandardCharsets.UTF_8);
        Weather weather=new Gson().fromJson(res,Weather.class);

        return weather.getForecast();
    }

    public static String mockForecastJson() throws IOException {
        Forecast forecast=mockForecast();
        return new Gson().toJson(forecast,Forecast.class);
    }

    public static Current mockCurrentWeather() throws IOException {
        InputStream inputStream = WeatherApiServiceAbstract.class.getClassLoader().getResourceAsStream( "json/weather.json");
        Source source = Okio.buffer(Okio.source(inputStream));
        String res=((BufferedSource) source).readString(StandardCharsets.UTF_8);
        Weather weather=new Gson().fromJson(res,Weather.class);

        return weather.getCurrent();
    }

    public static String mockCurrentWeatherJson() throws IOException {
        Current current=mockCurrentWeather();
        return new Gson().toJson(current,Current.class);
    }

    public static Weather mockWeather() throws IOException {
        InputStream inputStream = WeatherApiServiceAbstract.class.getClassLoader().getResourceAsStream( "json/weather.json");
        Source source = Okio.buffer(Okio.source(inputStream));
        String res=((BufferedSource) source).readString(StandardCharsets.UTF_8);
        Weather weather=new Gson().fromJson(res,Weather.class);
        return weather;
    }
}
