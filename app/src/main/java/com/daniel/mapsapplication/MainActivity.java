package com.daniel.mapsapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private Location mLocation;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 98;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView coordinatesView = findViewById(R.id.textView);
        Button locateButton = findViewById(R.id.button);
        locateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check permission
                try {
                    askPermission(context);
                    mLocationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,0,0, mLocationListener);
                    if (mLocation != null) {
                        coordinatesView.setText(mLocation.getLatitude() + ", " + mLocation.getLongitude());
                    }
                    else {
                        coordinatesView.setText("Paikka oli null, yritä uudelleen");
                    }

                }
                catch (SecurityException e) {
                    Log.d("MYLOCATIONTAG", "Virhe, ei GPS oikeuksia: " + e.toString());
                }
            }
        });

        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("MYLOCATIONTAG", "Paikka on muuttunut: " +
                        location.getLatitude() + ", " + location.getLongitude());
                mLocation = location;
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

    }

    public boolean askPermission (final Context context) {
        Log.d("MYLOCATIONTAG", "askPermission() called");

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Log.d("MYLOCATIONTAG", "permission asked and denied");
            }
            else {
                Log.d("MYLOCATIONTAG", "asking permission");

                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

            }
            return false;
        }
        else {
            Log.d("MYLOCATIONTAG", "permission already granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantresults) {
        Log.d("MYLOCATIONTAG", "onRequestPermissionsResult() called");

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                //If permission not granted, grantresult length is zero
                if (grantresults.length > 0 && grantresults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("MYLOCATIONTAG", "permission granted");
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        Log.d("MYLOCATIONTAG", "location updated every now and then");
                        //mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,mLocationListener);
                    }

                }
                else {
                    Log.d("MYLOCATIONTAG", "permission denied");
                }
                return;
            }
        }
    }

    public void showMap(View view) {
        Intent mapsIntent = new Intent(this, MapsActivity.class);
        mapsIntent.putExtra("MY_LOCATION", mLocation);
        startActivity(mapsIntent);
    }
}
