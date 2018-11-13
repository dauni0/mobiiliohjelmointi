package com.daniel.mapsapplication;

import android.content.Intent;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Location mapsLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent ogIntent = getIntent();
        mapsLocation = ogIntent.getParcelableExtra("MY_LOCATION");

        Log.d("MYLOCATIONTAG", "My location is: " + mapsLocation);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng myLocation = new LatLng(mapsLocation.getLatitude(), mapsLocation.getLongitude());

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(myLocation).title("Marker to where I am"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));

        /*1: World
            5: Landmass/continent
            10: City
            15: Streets
            20: Buildings*/

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15f));
    }
}
