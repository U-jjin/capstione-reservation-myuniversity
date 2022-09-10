package com.example.myuniversity3.ExternalFacility;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.myuniversity3.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ExternalInfomap extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    //필요한 객체: 가게 이름, 가게 주소, 가게 경도와 위도.
    String store;
    String addr;
    Double mapx =null;
    Double mapy = null;
    private Context context;

    //위도 경도
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        Toolbar mytoolbar =findViewById(R.id.map_toolbar);
        setSupportActionBar(mytoolbar);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_map_back);

        store = getIntent().getStringExtra("intent_name");
        TextView name_tv = findViewById(R.id.man_appbar_tv);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.External_map);

        mapFragment.getMapAsync(this);


    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);



        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(37.317661, 126.950011);

        MarkerOptions makerOptions = new MarkerOptions();
//        makerOptions.position(location).title(store);
//        makerOptions.snippet(addr);

        makerOptions.position(location).title(store);
        mMap.addMarker(makerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,18));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home: finish();
        }
        return super.onOptionsItemSelected(item);
    }


}


//    //필요한 객체: 가게 이름, 가게 주소, 가게 경도와 위도.
//    String store;
//    String addr;
//    Double mapx =null;
//    Double mapy = null;
//    private Context context;
//
//
//    ExternalInfoDialog nameformap = new ExternalInfoDialog(context);
//
//    store = nameformap.getstorename().getText().toString();
//    addr = nameformap.getstorename().getText().toString();

