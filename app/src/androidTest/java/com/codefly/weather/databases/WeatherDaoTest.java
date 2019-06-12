package com.codefly.weather.databases;

import android.support.test.runner.AndroidJUnit4;
import com.codefly.weather.utils.MockTestUtil;
import com.codefly.weather.models.Weather;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by YATRAONLINE\sohan.gupta on 27/5/19.
 */

@RunWith(AndroidJUnit4.class)
public class WeatherDaoTest extends DBTest {

    @Test
    public void insertAndReadWeatherTest() {
        Weather weather = MockTestUtil.mockWeather(MockTestUtil.CITY_BANGALORE);
        db.getWeatherDao().add(weather);
        Weather weather1 = db.getWeatherDao().getWeather();
        Assert.assertEquals(1, weather1.getId());
    }


    @Test
    public void updateAndReadWeatherTest() {
        Weather weather=MockTestUtil.mockWeather(MockTestUtil.CITY_BHOPAL);

        db.getWeatherDao().add(weather);

        Weather weather1= db.getWeatherDao().getWeather();
        Assert.assertEquals(1, weather1.getId());
        Assert.assertEquals(MockTestUtil.CITY_BHOPAL, weather1.getCity());

    }
}

