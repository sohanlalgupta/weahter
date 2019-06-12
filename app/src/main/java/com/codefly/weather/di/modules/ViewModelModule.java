package com.codefly.weather.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.codefly.weather.di.ViewModelKey;
import com.codefly.weather.viewmodels.WeatherViewModel;
import com.codefly.weather.viewmodels.factory.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */


@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel.class)
    protected abstract ViewModel weatherViewModel(WeatherViewModel viewModel);
}
