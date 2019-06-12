package com.codefly.weather.utils;

import com.codefly.weather.models.Current;
import com.codefly.weather.models.Forecast;
import com.codefly.weather.models.Location;
import com.codefly.weather.models.Weather;
import com.codefly.weather.retrofit.NetworkUtils;
import java.util.HashMap;
import java.util.Map;

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
}
