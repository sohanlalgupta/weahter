package com.codefly.weather;

import com.codefly.weather.WeatherApplication;
import com.codefly.weather.di.components.AppComponent;
import com.codefly.weather.di.components.DaggerAppComponent;
import com.codefly.weather.di.modules.ApiServiceModuleTest;

/**
 * Created by YATRAONLINE\sohan.gupta on 28/5/19.
 */

public class WeatherApplicationTest extends WeatherApplication {
    @Override
    public AppComponent getComponent() {
        AppComponent appComponent= DaggerAppComponent.builder()
                .application(this)
                .apiModule(new ApiServiceModuleTest())
                .build();
        appComponent.inject(this);
        return appComponent;
    }
}
