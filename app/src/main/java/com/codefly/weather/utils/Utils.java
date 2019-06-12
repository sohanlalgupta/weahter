package com.codefly.weather.utils;

import java.util.Calendar;

/**
 * Created by YATRAONLINE\sohan.gupta on 27/5/19.
 */

public class Utils {
     enum Day {
        Mon(1),Tue(2),Wed(3),Thu(4),Fri(5),Sat(6),Sun(7);

        int day;

        Day(int index){
            day=index;
        }

        public int getValue(){
            return day;
        }

        public static String getStringValueFromDayIndex(int index){
            for (Day day : Day.values()) {
                if (day.getValue() == index) {
                    return day.toString();
                }
            }
            return null;
        }

    }
    public static String getDayFromTimeStamp(long timestamp){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp*1000);
        int day=cal.get(Calendar.DAY_OF_WEEK);
        return Day.getStringValueFromDayIndex(day);
    }
}
