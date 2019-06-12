package com.codefly.weather.di.components;

import android.app.Application;

import com.codefly.weather.WeatherApplication;
import com.codefly.weather.di.modules.ActivityModule;
import com.codefly.weather.di.modules.ApiServiceModule;
import com.codefly.weather.di.modules.AppModule;
import com.codefly.weather.di.modules.DBModule;
import com.codefly.weather.di.modules.FragmentModule;
import com.codefly.weather.di.modules.ViewModelModule;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */

/**
 *  Declares this interface as component and defines all the modules.
 *
 */

@Component(modules={ApiServiceModule.class,
        DBModule.class, ViewModelModule.class,
        AndroidSupportInjectionModule.class, ActivityModule.class,FragmentModule.class})
@Singleton
public interface AppComponent {


    /* We will call this builder interface from our custom Application class.
     * This will set our application object to the AppComponent.
     * So inside the AppComponent the application instance is available.
     * So this application instance can be accessed by our modules
     * such as ApiServiceModule when needed
     * */
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder apiModule(ApiServiceModule apiModule);

        @BindsInstance
        Builder dbModule(DBModule dbModule);

        AppComponent build();
    }

    /*
     * WeatherApplication:This is custom Application class
     * */
    void inject(WeatherApplication application);
}
