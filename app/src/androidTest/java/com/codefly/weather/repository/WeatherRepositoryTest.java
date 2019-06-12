package com.codefly.weather.repository;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.codefly.weather.utils.MockTestUtil;
import com.codefly.weather.databases.dao.WeatherDao;
import com.codefly.weather.models.Weather;
import com.codefly.weather.retrofit.NetworkUtils;
import com.codefly.weather.retrofit.rest.WeatherApiService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by YATRAONLINE\sohan.gupta on 27/5/19.
 */

@RunWith(MockitoJUnitRunner.class)
public class WeatherRepositoryTest {

    @Mock
    WeatherDao weatherDao;

    @Mock
    WeatherApiService weatherApiService;

    private WeatherRepository repository;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void init() {
        repository = new WeatherRepository(weatherDao, weatherApiService);
    }


    @Test
    public void loadWeather() {
        Flowable<Weather> loadFromDB = Flowable.empty();

        when(weatherDao.getWeather())
                .thenReturn(new Weather());

        Weather mockResponse = MockTestUtil.mockWeatherApiResponse();

        when(weatherApiService.getWeather(NetworkUtils.END_POINT_FORECAST,
                MockTestUtil.QUERY_PARAM_MAP))
                .thenReturn(Observable.just(mockResponse));

        Observable<Resource<Weather>> data = repository.loadWeather(MockTestUtil.CITY_BANGALORE);
        verify(weatherDao).getWeather();
        verify(weatherApiService).getWeather(NetworkUtils.END_POINT_FORECAST,
                MockTestUtil.QUERY_PARAM_MAP);

        TestObserver testObserver = new TestObserver();
        data.subscribe(testObserver);
        testObserver.assertNoErrors();
    }
}
