package com.codefly.weather;

import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YATRAONLINE\sohan.gupta on 27/5/19.
 */


public class WeatherApiServiceAbstract<T> {

    private MockWebServer mockWebServer;

    @Before
    public void startMockServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @After
    public void stopMockServer() throws IOException {
        mockWebServer.shutdown();
    }

    public void enqueueResponse(String fileName) throws IOException {
        enqueueResponse(fileName, Collections.EMPTY_MAP);
    }


    private void enqueueResponse(String fileName, Map<String, String> headers) throws IOException {
        InputStream inputStream = WeatherApiServiceAbstract.class.getClassLoader().getResourceAsStream( "json/weather.json");

        Source source = Okio.buffer(Okio.source(inputStream));
        MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseCode(200);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            mockResponse.addHeader(entry.getKey(), entry.getValue());
        }

        mockResponse.setBody(((BufferedSource) source).readString(StandardCharsets.UTF_8));
        mockWebServer.enqueue(mockResponse);
    }

    public T createService(Class<T> clazz) {
        return new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(clazz);
    }
}
