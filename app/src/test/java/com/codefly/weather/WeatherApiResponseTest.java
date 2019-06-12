package com.codefly.weather;

import com.codefly.weather.repository.Resource;
import com.codefly.weather.repository.Status;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class WeatherApiResponseTest {

    @Test
    public void exception() {
        Exception exception = new Exception("test_error");
        Resource apiResponse = Resource.error(exception.getMessage(), exception);
        Assert.assertEquals("test_error", apiResponse.message);
        Assert.assertEquals(Status.ERROR, apiResponse.status);
    }

    @Test
    public void success() {
        Resource apiResponse = Resource.success("test_success");
        Assert.assertEquals("test_success", apiResponse.data);
        Assert.assertEquals(Status.SUCCESS, apiResponse.status);
    }
}
