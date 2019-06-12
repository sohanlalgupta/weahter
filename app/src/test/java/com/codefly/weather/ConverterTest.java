package com.codefly.weather;

import com.codefly.weather.utils.MockTestUtil;
import com.codefly.weather.databases.converter.Converter;
import com.codefly.weather.models.Current;
import com.codefly.weather.models.Forecast;
import com.codefly.weather.models.Location;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

/**
 * Created by YATRAONLINE\sohan.gupta on 28/5/19.
 */

@RunWith(MockitoJUnitRunner.class)
public class ConverterTest {

    @Test
    public void toStringFromLocationTest() {
        Location location= MockTestUtil.mockLocation();
        String json=new Gson().toJson(location,Location.class);
        String jsonFromConverter=Converter.toStringFromLocation(location);
        Assert.assertEquals(json,jsonFromConverter);
    }

    public void toLocationFromStringTest() {
        String json=MockTestUtil.mockLocationJson();
        Location location=new Gson().fromJson(json,Location.class);
        Location convert_location=Converter.toLocationFromString(json);
        Assert.assertEquals(location,convert_location);
    }

    @Test
    public void toStringFromCurrentWeatherTest() throws IOException {
        Current current=MockTestUtil.mockCurrentWeather();
        String json=new Gson().toJson(current,Current.class);
        String convert_weather=Converter.toStringFromCurrentWeather(current);
        Assert.assertEquals(json,convert_weather);
    }

    @Test
    public void toCurrentWeatherFromStringTest() throws IOException {
        String json=MockTestUtil.mockCurrentWeatherJson();
        Current weather=new Gson().fromJson(json,Current.class);
        Current convert_weather=Converter.toCurrentWeatherFromString(json);
        Assert.assertEquals(weather.getWindDir(), convert_weather.getWindDir());
    }

    @Test
    public  void toStringFromForecastTest() throws IOException {
        Forecast forecast=MockTestUtil.mockForecast();
        String json=new Gson().toJson(forecast,Forecast.class);
        String convert_forecast=Converter.toStringFromForecast(forecast);
        Assert.assertEquals(json,convert_forecast);
    }

    @Test
    public void toForecastFromStringTest() throws IOException {
        String json=MockTestUtil.mockForecastJson();
        Forecast forecast=new Gson().fromJson(json,Forecast.class);
        Forecast convert_forecast=Converter.toForecastFromString(json);
        Assert.assertEquals(forecast.getForecastday().get(0).getDateEpoch(),
                convert_forecast.getForecastday().get(0).getDateEpoch());
    }


}
