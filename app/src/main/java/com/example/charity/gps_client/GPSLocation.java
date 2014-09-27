package com.example.charity.gps_client;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


import com.google.android.gms.maps.GoogleMap;

/**
 * Created by charity on 2014-09-27.
 */
public class GPSLocation{
    private Location currentLocation;
    private Context mContext;
    private LocationManager mLocationManager;

    public GPSLocation(Activity activity){
        mContext = activity;
        mLocationManager = (LocationManager)activity.getSystemService(mContext.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    public Location getLocation(){
        return currentLocation;
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            currentLocation = location;
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
    };
}
