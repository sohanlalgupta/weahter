package com.codefly.weather;

import com.codefly.weather.models.Weather;
import com.codefly.weather.retrofit.NetworkUtils;
import com.codefly.weather.retrofit.rest.WeatherApiService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.Map;

/**
 * Created by YATRAONLINE\sohan.gupta on 27/5/19.
 */

public class WeatherApiServiceTest extends WeatherApiServiceAbstract<WeatherApiService> {

    private WeatherApiService service;

    @Before
    public void initService() {
        this.service = createService(WeatherApiService.class);
    }

    @Test
    public void fetchWeatherDetailsTest() throws IOException {
        enqueueResponse("weather.json");
        Map<String,String> map=NetworkUtils.QUERY_PARAM_MAP;
        String city="Bangalore";
        map.put(NetworkUtils.KEY_CITY,city);

        Weather weather=service.getWeather(NetworkUtils.END_POINT_FORECAST,map).blockingFirst();
        Assert.assertEquals(city,weather.getLocation().getName());
    }

}
