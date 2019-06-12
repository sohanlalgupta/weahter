package com.codefly.weather.databases;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by YATRAONLINE\sohan.gupta on 26/5/19.
 */

public class AppLocation implements LocationListener {
    private LocationManager mLocationManager;
    private static final long MIN_DISTANCE_FOR_UPDATE = 10;
    private static final long MIN_TIME_FOR_UPDATE = 1000 * 60 * 2;
    private Application application;
    private Handler handler;

    public AppLocation(Application app) {
        application=app;
        mLocationManager = (LocationManager) application.getSystemService(LOCATION_SERVICE);
    }

    public Location getLocation(boolean isGpsEnable,boolean isNetworkEnable) {
        if(ActivityCompat.checkSelfPermission(application, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }

        Location location=null;

        if (isNetworkEnable) {
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    MIN_TIME_FOR_UPDATE, MIN_DISTANCE_FOR_UPDATE, this);

            if (mLocationManager != null) {
                location = mLocationManager
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        }

        if (isGpsEnable) {
            if (location == null) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        MIN_TIME_FOR_UPDATE, MIN_DISTANCE_FOR_UPDATE, this);

                if (mLocationManager != null) {
                    location = mLocationManager.
                            getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }
            }
        }

        return location;
    }

    public void getAddressFromLocation(final double latitude, final double longitude,
                                       final Handler handler) {
        this.handler=handler;

        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(application, Locale.getDefault());
                Bundle bundle=new Bundle();
                try {
                    List<Address> addressList = geocoder.getFromLocation(
                            latitude, longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = addressList.get(0);

                        if(address.getLocality()!=null && address.getCountryName()!=null){
                            bundle.putInt(DatabaseUtils.KEY_STATUS,DatabaseUtils.SUCCESS);
                            bundle.putString(DatabaseUtils.KEY_CITY_NAME,address.getLocality());
                            bundle.putString(DatabaseUtils.KEY_COUNTRY_NAME,address.getCountryName());
                        }else{
                            bundle.putInt(DatabaseUtils.KEY_STATUS,DatabaseUtils.ERROR);
                        }
                    }
                } catch (IOException e) {
                } finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    message.what = 1;
                    bundle.putDouble(DatabaseUtils.KEY_LAT,latitude);
                    bundle.putDouble(DatabaseUtils.KEY_LONG,longitude);
                    message.setData(bundle);
                    message.sendToTarget();
                }
            }
        };
        thread.start();
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location!=null){
            getAddressFromLocation(location.getLatitude(),location.getLongitude(),handler);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
