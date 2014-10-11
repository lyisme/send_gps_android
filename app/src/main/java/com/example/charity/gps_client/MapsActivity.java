package com.example.charity.gps_client;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements Settings{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    Handler mHandler = new Handler();
    Context mContext = this;
    GPSLocation mGPSLocation;

    Location currentLocation;
    LatLng currentLatLng = new LatLng(0, 0);
    Marker currentMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        initialize();
        mHandler.postDelayed(updateGPS, TIME_UPDATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
//        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    private void initialize(){
        mGPSLocation = new GPSLocation(this);
        currentMarker = mMap.addMarker(new MarkerOptions().position(currentLatLng));
    }

    private Runnable updateGPS = new Runnable() {
        @Override
        public void run() {
            currentLocation = mGPSLocation.getLocation();
            if (currentLocation != null){
                LatLng mLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLng(mLatLng));

                // Zoom in the Google Map
                mMap.animateCamera(CameraUpdateFactory.zoomTo(ZOOM_LEVEL));
                currentMarker.setPosition(mLatLng);

                // Send request to server
                new MapHttpRequest().execute(mLatLng);

                Toast.makeText(mContext, "Longitude: " + String.valueOf(currentLocation.getLongitude()) + ", Latitude: " + String.valueOf(currentLocation.getLatitude()), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(mContext, "Can not get current location", Toast.LENGTH_LONG);
            }

            mHandler.postDelayed(updateGPS, TIME_UPDATE);
        }
    };
}
