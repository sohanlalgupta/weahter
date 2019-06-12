package com.codefly.weather.viewmodels;

import android.app.Application;
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import android.support.test.InstrumentationRegistry;
import com.codefly.weather.utils.MockTestUtil;
import com.codefly.weather.databases.dao.WeatherDao;
import com.codefly.weather.models.Weather;
import com.codefly.weather.retrofit.NetworkUtils;
import com.codefly.weather.retrofit.rest.WeatherApiService;
import com.codefly.weather.repository.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import io.reactivex.Observable;

import static org.mockito.Mockito.when;

/**
 * Created by YATRAONLINE\sohan.gupta on 27/5/19.
 */

@RunWith(MockitoJUnitRunner.class)
public class WeatherViewModelTest {

    private WeatherViewModel weatherViewModel;

    @Mock
    WeatherDao weatherDao;

    @Mock
    WeatherApiService weatherApiService;

    @Mock
    Observer<Resource<Weather>> observer;

    @Mock
    Observable<Weather> observerWeather;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void init() {
        Application app = (Application) InstrumentationRegistry.getTargetContext()
                        .getApplicationContext();
        weatherViewModel = new WeatherViewModel(weatherDao,weatherApiService);
    }

    @Test
    public void loadWeather() {
        weatherViewModel.getWeatherLiveData().observeForever(observer);
        WeatherViewModel.GeocoderHandler handler=Mockito.mock(WeatherViewModel.GeocoderHandler.class);
        Map map=NetworkUtils.QUERY_PARAM_MAP;
        map.put(NetworkUtils.KEY_CITY,"Bangalore");
        weatherViewModel.setHandler(handler);
        when(weatherApiService.getWeather(NetworkUtils.END_POINT_FORECAST,
               map)).thenReturn(observerWeather);

        weatherViewModel.loadWeather(MockTestUtil.CITY_BANGALORE);

        assert(weatherViewModel.getWeatherLiveData().getValue().isLoading());
        assert(weatherViewModel.getWeatherLiveData().getValue().data ==
                MockTestUtil.mockWeather(MockTestUtil.CITY_BANGALORE));
    }
}

