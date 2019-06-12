package com.codefly.weather.views;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.codefly.weather.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class WeatherActivityTest {

    @Rule
    public ActivityTestRule<WeatherActivity> mActivityTestRule = new ActivityTestRule<>(WeatherActivity.class);

    @Test
    public void testHappyCondition() {

        mActivityTestRule.launchActivity(new Intent());

        Espresso.onView(withId(R.id.progress_img_view)).check(matches(not(isDisplayed())));
        Espresso.onView(withId(R.id.btn_retry)).check(matches(not(isDisplayed())));
        Espresso.onView(withId(R.id.main_weather_lay)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.error_lay)).check(matches(not(isDisplayed())));

    }
}
