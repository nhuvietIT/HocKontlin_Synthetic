package com.example.nhuviet.hockontlin_synthetic_signin.View.Home.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.nhuviet.hockontlin_synthetic_signin.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * Created by nhuvi on 24/08/2017.
 */

public class MapGoogle extends AppCompatActivity implements GoogleMap.OnMapLoadedCallback, OnMapReadyCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_frament_cuhang_googlemap);

//        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapLoaded() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
