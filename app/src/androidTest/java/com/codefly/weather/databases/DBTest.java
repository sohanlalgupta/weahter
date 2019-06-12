package com.codefly.weather.databases;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;

/**
 * Created by YATRAONLINE\sohan.gupta on 27/5/19.
 */

public abstract class DBTest {

    protected WeatherDatabase db;

    @Before
    public void initDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                WeatherDatabase.class).build();
    }

    @After
    public void closeDb() {
        db.close();
    }
}
