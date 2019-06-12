package com.codefly.weather.models;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */


public class Forecast implements Parcelable
{

    @SerializedName("forecastday")
    @Expose
    private List<Forecastday> forecastday = new ArrayList<Forecastday>();
    public final static Parcelable.Creator<Forecast> CREATOR = new Creator<Forecast>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Forecast createFromParcel(Parcel in) {
            return new Forecast(in);
        }

        public Forecast[] newArray(int size) {
            return (new Forecast[size]);
        }

    }
            ;

    protected Forecast(Parcel in) {
        in.readList(this.forecastday, (com.codefly.weather.models.Forecastday.class.getClassLoader()));
    }

    public Forecast() {
    }

    public List<Forecastday> getForecastday() {
        return forecastday;
    }

    public void setForecastday(List<Forecastday> forecastday) {
        this.forecastday = forecastday;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(forecastday);
    }

    public int describeContents() {
        return 0;
    }

}
