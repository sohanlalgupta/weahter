package com.codefly.weather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */

public class Current implements Parcelable
{

    @SerializedName("last_updated_epoch")
    @Expose
    private long lastUpdatedEpoch;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @SerializedName("temp_c")
    @Expose
    private double tempC;
    @SerializedName("temp_f")
    @Expose
    private double tempF;
    @SerializedName("is_day")
    @Expose
    private long isDay;
    @SerializedName("condition")
    @Expose
    private Condition condition;
    @SerializedName("wind_mph")
    @Expose
    private double windMph;
    @SerializedName("wind_kph")
    @Expose
    private double windKph;
    @SerializedName("wind_degree")
    @Expose
    private long windDegree;
    @SerializedName("wind_dir")
    @Expose
    private String windDir;
    @SerializedName("pressure_mb")
    @Expose
    private double pressureMb;
    @SerializedName("pressure_in")
    @Expose
    private double pressureIn;
    @SerializedName("precip_mm")
    @Expose
    private double precipMm;
    @SerializedName("precip_in")
    @Expose
    private double precipIn;
    @SerializedName("humidity")
    @Expose
    private long humidity;
    @SerializedName("cloud")
    @Expose
    private long cloud;
    @SerializedName("feelslike_c")
    @Expose
    private double feelslikeC;
    @SerializedName("feelslike_f")
    @Expose
    private double feelslikeF;
    @SerializedName("vis_km")
    @Expose
    private double visKm;
    @SerializedName("vis_miles")
    @Expose
    private double visMiles;
    @SerializedName("uv")
    @Expose
    private double uv;
    @SerializedName("gust_mph")
    @Expose
    private double gustMph;
    @SerializedName("gust_kph")
    @Expose
    private double gustKph;
    public final static Parcelable.Creator<Current> CREATOR = new Creator<Current>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Current createFromParcel(Parcel in) {
            return new Current(in);
        }

        public Current[] newArray(int size) {
            return (new Current[size]);
        }

    }
            ;

    protected Current(Parcel in) {
        this.lastUpdatedEpoch = ((long) in.readValue((long.class.getClassLoader())));
        this.lastUpdated = ((String) in.readValue((String.class.getClassLoader())));
        this.tempC = ((double) in.readValue((double.class.getClassLoader())));
        this.tempF = ((double) in.readValue((double.class.getClassLoader())));
        this.isDay = ((long) in.readValue((long.class.getClassLoader())));
        this.condition = ((Condition) in.readValue((Condition.class.getClassLoader())));
        this.windMph = ((double) in.readValue((double.class.getClassLoader())));
        this.windKph = ((double) in.readValue((double.class.getClassLoader())));
        this.windDegree = ((long) in.readValue((long.class.getClassLoader())));
        this.windDir = ((String) in.readValue((String.class.getClassLoader())));
        this.pressureMb = ((double) in.readValue((double.class.getClassLoader())));
        this.pressureIn = ((double) in.readValue((double.class.getClassLoader())));
        this.precipMm = ((double) in.readValue((double.class.getClassLoader())));
        this.precipIn = ((double) in.readValue((double.class.getClassLoader())));
        this.humidity = ((long) in.readValue((long.class.getClassLoader())));
        this.cloud = ((long) in.readValue((long.class.getClassLoader())));
        this.feelslikeC = ((double) in.readValue((double.class.getClassLoader())));
        this.feelslikeF = ((double) in.readValue((double.class.getClassLoader())));
        this.visKm = ((double) in.readValue((double.class.getClassLoader())));
        this.visMiles = ((double) in.readValue((double.class.getClassLoader())));
        this.uv = ((double) in.readValue((double.class.getClassLoader())));
        this.gustMph = ((double) in.readValue((double.class.getClassLoader())));
        this.gustKph = ((double) in.readValue((double.class.getClassLoader())));
    }

    public Current() {
    }

    public long getLastUpdatedEpoch() {
        return lastUpdatedEpoch;
    }

    public void setLastUpdatedEpoch(long lastUpdatedEpoch) {
        this.lastUpdatedEpoch = lastUpdatedEpoch;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public double getTempC() {
        return tempC;
    }

    public void setTempC(double tempC) {
        this.tempC = tempC;
    }

    public double getTempF() {
        return tempF;
    }

    public void setTempF(double tempF) {
        this.tempF = tempF;
    }

    public long getIsDay() {
        return isDay;
    }

    public void setIsDay(long isDay) {
        this.isDay = isDay;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public double getWindMph() {
        return windMph;
    }

    public void setWindMph(double windMph) {
        this.windMph = windMph;
    }

    public double getWindKph() {
        return windKph;
    }

    public void setWindKph(double windKph) {
        this.windKph = windKph;
    }

    public long getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(long windDegree) {
        this.windDegree = windDegree;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public double getPressureMb() {
        return pressureMb;
    }

    public void setPressureMb(double pressureMb) {
        this.pressureMb = pressureMb;
    }

    public double getPressureIn() {
        return pressureIn;
    }

    public void setPressureIn(double pressureIn) {
        this.pressureIn = pressureIn;
    }

    public double getPrecipMm() {
        return precipMm;
    }

    public void setPrecipMm(double precipMm) {
        this.precipMm = precipMm;
    }

    public double getPrecipIn() {
        return precipIn;
    }

    public void setPrecipIn(double precipIn) {
        this.precipIn = precipIn;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    public long getCloud() {
        return cloud;
    }

    public void setCloud(long cloud) {
        this.cloud = cloud;
    }

    public double getFeelslikeC() {
        return feelslikeC;
    }

    public void setFeelslikeC(double feelslikeC) {
        this.feelslikeC = feelslikeC;
    }

    public double getFeelslikeF() {
        return feelslikeF;
    }

    public void setFeelslikeF(double feelslikeF) {
        this.feelslikeF = feelslikeF;
    }

    public double getVisKm() {
        return visKm;
    }

    public void setVisKm(double visKm) {
        this.visKm = visKm;
    }

    public double getVisMiles() {
        return visMiles;
    }

    public void setVisMiles(double visMiles) {
        this.visMiles = visMiles;
    }

    public double getUv() {
        return uv;
    }

    public void setUv(double uv) {
        this.uv = uv;
    }

    public double getGustMph() {
        return gustMph;
    }

    public void setGustMph(double gustMph) {
        this.gustMph = gustMph;
    }

    public double getGustKph() {
        return gustKph;
    }

    public void setGustKph(double gustKph) {
        this.gustKph = gustKph;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lastUpdatedEpoch);
        dest.writeValue(lastUpdated);
        dest.writeValue(tempC);
        dest.writeValue(tempF);
        dest.writeValue(isDay);
        dest.writeValue(condition);
        dest.writeValue(windMph);
        dest.writeValue(windKph);
        dest.writeValue(windDegree);
        dest.writeValue(windDir);
        dest.writeValue(pressureMb);
        dest.writeValue(pressureIn);
        dest.writeValue(precipMm);
        dest.writeValue(precipIn);
        dest.writeValue(humidity);
        dest.writeValue(cloud);
        dest.writeValue(feelslikeC);
        dest.writeValue(feelslikeF);
        dest.writeValue(visKm);
        dest.writeValue(visMiles);
        dest.writeValue(uv);
        dest.writeValue(gustMph);
        dest.writeValue(gustKph);
    }

    public int describeContents() {
        return 0;
    }
}