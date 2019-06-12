package com.codefly.weather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */

public class Forecastday implements Parcelable
{

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("date_epoch")
    @Expose
    private long dateEpoch;
    @SerializedName("day")
    @Expose
    private Day day;
    @SerializedName("astro")
    @Expose
    private Astro astro;
    public final static Parcelable.Creator<Forecastday> CREATOR = new Creator<Forecastday>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Forecastday createFromParcel(Parcel in) {
            return new Forecastday(in);
        }

        public Forecastday[] newArray(int size) {
            return (new Forecastday[size]);
        }

    }
            ;

    protected Forecastday(Parcel in) {
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.dateEpoch = ((long) in.readValue((long.class.getClassLoader())));
        this.day = ((Day) in.readValue((Day.class.getClassLoader())));
        this.astro = ((Astro) in.readValue((Astro.class.getClassLoader())));
    }

    public Forecastday() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getDateEpoch() {
        return dateEpoch;
    }

    public void setDateEpoch(long dateEpoch) {
        this.dateEpoch = dateEpoch;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(date);
        dest.writeValue(dateEpoch);
        dest.writeValue(day);
        dest.writeValue(astro);
    }

    public int describeContents() {
        return 0;
    }

}