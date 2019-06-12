package com.codefly.weather.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.codefly.weather.databases.AppLocation;
import com.codefly.weather.databases.DatabaseUtils;
import com.codefly.weather.databases.dao.WeatherDao;
import com.codefly.weather.models.Forecastday;
import com.codefly.weather.models.Weather;
import com.codefly.weather.retrofit.rest.WeatherApiService;
import com.codefly.weather.repository.Resource;
import com.codefly.weather.repository.WeatherRepository;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */

public class WeatherViewModel extends ViewModel {
    private String city;
    private String country;
    private Handler handler;
    @Inject AppLocation appLocation;
    private WeatherRepository repository;
    private MutableLiveData<Resource<Weather>> mWeatherLiveData=new MutableLiveData<>();
    private MutableLiveData<String> cityLiveData=new MutableLiveData<>();

    @Inject
    public WeatherViewModel(WeatherDao dao,WeatherApiService service) {
        repository=new WeatherRepository(dao,service);
    }

    /**
     * Method called by UI to fetch weather
     */
    public void loadWeather(String city){
        repository.loadWeather(city).
                subscribe(resource->getWeatherLiveData().postValue(resource));
    }

    /**
     * LiveData observed by the UI
     */
    public MutableLiveData<Resource<Weather>> getWeatherLiveData(){
        return mWeatherLiveData;
    }

    public MutableLiveData<String> getCity(){
        return cityLiveData;
    }

    public void detectLocation(boolean isGpsEnabled,boolean isNetworkEnabled){
        android.location.Location location=appLocation.getLocation(isGpsEnabled,isNetworkEnabled);

        if(location!=null) {
            appLocation.getAddressFromLocation(location.getLatitude(), location.getLongitude(), handler);
        }
    }

    public class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                     Bundle bundle = message.getData();
                     String newCity= bundle.getString(DatabaseUtils.KEY_CITY_NAME);
                     String newCountry= bundle.getString(DatabaseUtils.KEY_COUNTRY_NAME);

                     if(city!=newCity || country!=newCountry) {
                         cityLiveData.postValue(newCity);
                     }

                    city=newCity;
                    country=newCountry;
                    break;
            }
        }
    }

    public List<Forecastday> getForecastDayData(){
        List<Forecastday> forecastday=mWeatherLiveData.getValue().data.getForecast().getForecastday();
        return forecastday.subList(1,forecastday.size());
    }

    public void setHandler(Handler handler){
        this.handler=handler;
    }
}