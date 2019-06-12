package com.codefly.weather.repository;

import android.support.annotation.NonNull;
import com.codefly.weather.databases.dao.WeatherDao;
import com.codefly.weather.models.Weather;
import com.codefly.weather.retrofit.NetworkUtils;
import com.codefly.weather.retrofit.rest.WeatherApiService;
import java.util.Map;
import javax.inject.Singleton;
import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */

@Singleton
public class WeatherRepository {
    private WeatherDao mWeatherDao;
    private WeatherApiService mApiService;

    public WeatherRepository(WeatherDao dao, WeatherApiService service){
        mWeatherDao=dao;
        mApiService=service;
    }

    public Observable<Resource<Weather>> loadWeather(final String city){
        return new NetworkBoundResource<Weather,Weather>(){

            @Override
            protected void saveCallResult(@NonNull Weather response) {
                mWeatherDao.deleteAll();
                response.setCity(response.getLocation().getName());
                mWeatherDao.add((Weather) response);
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @Override
            protected Flowable<Weather> loadFromDatabase() {
                Weather weather=mWeatherDao.getWeather();
                if(weather==null){
                    return Flowable.empty();
                }
                return Flowable.just(weather);
            }

            @Override
            protected Observable<Resource<Weather>> createCall() {
                Map<String,String> map=NetworkUtils.QUERY_PARAM_MAP;
                map.put(NetworkUtils.KEY_CITY,city);

                return mApiService.getWeather(NetworkUtils.END_POINT_FORECAST,map).
                        flatMap(response->Observable.just(response==null?
                                Resource.error("",response):Resource.success(response)));
            }
        }.getAsObservable();
    }
}
