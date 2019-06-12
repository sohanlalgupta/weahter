package com.codefly.weather.retrofit.rest;

import com.codefly.weather.models.Weather;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */


public interface WeatherApiService {

    @GET
    Observable<Weather> getWeather(@Url String url, @QueryMap Map<String,String> params);
}

