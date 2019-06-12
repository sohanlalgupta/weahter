package com.codefly.weather.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.codefly.weather.databases.DatabaseUtils;
import com.codefly.weather.databases.converter.Converter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */

@Entity(tableName = DatabaseUtils.TABLE_NAME)
public class Weather implements Parcelable
{
    @PrimaryKey(autoGenerate = true)
    public int id;

    @SerializedName("city")
    @Expose
    public String city;

    @SerializedName("location")
    @Expose
    @TypeConverters(Converter.class)
    private Location location;
    @SerializedName("current")
    @Expose
    @TypeConverters(Converter.class)
    private Current current;
    @SerializedName("forecast")
    @Expose
    @TypeConverters(Converter.class)
    private Forecast forecast;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public final static Parcelable.Creator<Weather> CREATOR = new Creator<Weather>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        public Weather[] newArray(int size) {
            return (new Weather[size]);
        }

    }
            ;

    protected Weather(Parcel in) {
        this.location = ((Location) in.readValue((Location.class.getClassLoader())));
        this.current = ((Current) in.readValue((Current.class.getClassLoader())));
        this.forecast = ((Forecast) in.readValue((Forecast.class.getClassLoader())));
    }

    public Weather() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(location);
        dest.writeValue(current);
        dest.writeValue(forecast);
    }

    public int describeContents() {
        return 0;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }


}
