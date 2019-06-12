package com.codefly.weather.di.modules;

import com.codefly.weather.views.WeatherActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by YATRAONLINE\sohan.gupta on 26/5/19.
 */

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = {FragmentModule.class})
    abstract WeatherActivity contributeWeatherActivity();
}