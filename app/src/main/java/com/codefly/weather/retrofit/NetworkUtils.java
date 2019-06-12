package com.codefly.weather.retrofit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */

public class NetworkUtils {
    public static final String BASE_URL=" http://api.apixu.com/v1/";
    public static final String END_POINT_FORECAST="forecast.json";
    public static final String KEY_AUTHENTICATION="key";
    public static final String KEY_CITY="q";
    public static final String KEY_DAYS="days";
    public static final String VALUE_DAYS="5";
    public static final String VALUE_AUTHENTICATION="b99f2876cc904a80bc0100143192505";

    public static  final Map<String,String> QUERY_PARAM_MAP;

    static {
        QUERY_PARAM_MAP = new ConcurrentHashMap<>();
        QUERY_PARAM_MAP.put(NetworkUtils.KEY_DAYS, NetworkUtils.VALUE_DAYS);
        QUERY_PARAM_MAP.put(NetworkUtils.KEY_AUTHENTICATION,NetworkUtils.VALUE_AUTHENTICATION);
    }
}
