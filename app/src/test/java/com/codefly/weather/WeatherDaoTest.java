package com.codefly.weather;

import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory;
import android.arch.persistence.room.Room;
import android.support.annotation.WorkerThread;

import com.codefly.weather.utils.MockTestUtil;
import com.codefly.weather.databases.WeatherDatabase;
import com.codefly.weather.databases.dao.WeatherDao;
import com.codefly.weather.di.components.AppComponent;
import com.codefly.weather.di.components.DaggerAppComponent;
import com.codefly.weather.di.modules.ApiServiceModule;
import com.codefly.weather.di.modules.DBModule;
import com.codefly.weather.models.Weather;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

/**
 * Created by YATRAONLINE\sohan.gupta on 27/5/19.
 */

@RunWith(MockitoJUnitRunner.class)
public class WeatherDaoTest {

    @Mock
    WeatherDao weatherDao;
    protected WeatherDatabase db;

    @Mock
    WeatherApplication application;

    @Before
    public void initDb() {
        AppComponent appComponent= DaggerAppComponent.builder()
                .application(application)
                .apiModule(new ApiServiceModule())
                .dbModule(new DBModule())
                .build();
        appComponent.inject(application);

        db = Room.inMemoryDatabaseBuilder(application,
                WeatherDatabase.class).openHelperFactory(new FrameworkSQLiteOpenHelperFactory())
                .build();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @WorkerThread
    @Test
    public void insertAndReadWeatherTest() throws IOException {
        Weather weather = MockTestUtil.mockWeather();
        weather.setCity(weather.getCity());
        weatherDao.add(weather);
        Weather weather1 = weatherDao.getWeather();
    }


    @WorkerThread
    @Test
    public void updateAndReadWeatherTest() throws IOException {
        weatherDao.deleteAll();
        Weather weather = MockTestUtil.mockWeather();
        weather.setCity(weather.getCity());
        weatherDao.add(weather);
        weather.setCity(MockTestUtil.CITY_BHOPAL);
        weatherDao.add(weather);
        Weather weather1=weatherDao.getWeather();
        /*db.getWeatherDao().add(weather);

        Weather weather1= db.getWeatherDao().getWeather();
        Assert.assertEquals(1, weather1.getId());
        Assert.assertEquals(MockTestUtil.CITY_BHOPAL, weather1.getCity());*/

    }

    @WorkerThread
    @Test
    public void deleteWeatherAndReadTest() throws IOException {
        Weather weather = MockTestUtil.mockWeather();
        weather.setCity(weather.getCity());
        weatherDao.add(weather);
        weatherDao.delete(weather);
    }
    @WorkerThread
    @Test
    public void deleteAllWeatherAndReadTest(){
        weatherDao.deleteAll();
        weatherDao.getWeather();
    }
}

