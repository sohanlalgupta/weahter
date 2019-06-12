package com.codefly.weather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */

public class Condition implements Parcelable
{

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("code")
    @Expose
    private long code;
    public final static Parcelable.Creator<Condition> CREATOR = new Creator<Condition>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Condition createFromParcel(Parcel in) {
            return new Condition(in);
        }

        public Condition[] newArray(int size) {
            return (new Condition[size]);
        }

    }
            ;

    protected Condition(Parcel in) {
        this.text = ((String) in.readValue((String.class.getClassLoader())));
        this.icon = ((String) in.readValue((String.class.getClassLoader())));
        this.code = ((long) in.readValue((long.class.getClassLoader())));
    }

    public Condition() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(text);
        dest.writeValue(icon);
        dest.writeValue(code);
    }

    public int describeContents() {
        return 0;
    }
}