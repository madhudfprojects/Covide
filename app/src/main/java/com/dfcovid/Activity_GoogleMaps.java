package com.dfcovid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

//import androidx.core.app.FragmentActivity;
/*import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;*/

public class Activity_GoogleMaps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    //Class_GPSTracker gpstracker_obj1;

    Double double_currentlatitude = 0.0;
    Double double_currentlongitude = 0.0;


    Toolbar toolbar;
    ImageView add_newfarmpond_iv;


    String location;
    Button search_bt, submit_bt, cancel_bt;

    Marker myMarker;

    String str_fromname, str_pondmarked, str_latlong;

    String str_lat, str_long;

    PlaceAutocompleteFragment searchautocomplete_fragment;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemaps);
        //Places.initialize(getApplicationContext(), "YOUR_API_KEY");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
         mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        searchautocomplete_fragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.searchautocomplete_fragment);


        submit_bt = (Button) findViewById(R.id.submit_bt);
        cancel_bt = (Button) findViewById(R.id.cancel_bt);





        Intent intent = getIntent();
        str_fromname = intent.getStringExtra("from");


        str_pondmarked = "no";



       // Places.initialize(getApplicationContext(), "YOUR_API_KEY");
       // Places.initialize(getApplicationContext(),"@string/API_KEY");

        cancel_bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(Activity_GoogleMaps.this);
                dialog.setCancelable(false);
                dialog.setTitle(R.string.alert);
                dialog.setMessage("Are you sure want to go back");

                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();
                        finish();
                    }
                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                                dialog.dismiss();
                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
                        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#004D40"));
                    }
                });
                alert.show();

                // finish();
            }
        });


    }// end of OnCreate()

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this case, we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device.
     * This method will only be triggered once the user has installed
     Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(final GoogleMap googleMap)
    {





      /*  mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng TutorialsPoint = new LatLng(15.583767, 75.7622434);
        LatLng TutorialsPoint1 = new LatLng(15.383767, 75.122434);

        mMap.addMarker(new
                MarkerOptions().position(TutorialsPoint).title("maps.com"));



        mMap.moveCamera(CameraUpdateFactory.newLatLng(TutorialsPoint));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(TutorialsPoint1));*/



       /* mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng Currentlocation = new LatLng(double_currentlatitude, double_currentlongitude);

        mMap.addMarker(new
                MarkerOptions().position(Currentlocation).title("You are here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Currentlocation));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //float zoomLevel = 16.0f; //This goes up to 21
        float zoomLevel = 20.0f; //This goes up to 21
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Currentlocation, zoomLevel));*/


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);

        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        mMap = googleMap;
        //15.363289994348667, 75.132790658344
        double_currentlatitude=15.363289994348667;
        double_currentlongitude=75.132790658344;
        // Add a marker in Sydney and move the camera
        LatLng Currentlocation = new LatLng(double_currentlatitude, double_currentlongitude);


        /*myMarker = googleMap.addMarker(new MarkerOptions()
                .position(Currentlocation)
                .title("You are here")
                .snippet(String.valueOf(Currentlocation)));*/


        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(double_currentlatitude, double_currentlongitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = (addresses.get(0).getAddressLine(0));
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String knownName = addresses.get(0).getFeatureName();

        Log.e("Address",address+"|City "+city+"|State "+state+"knownName"+knownName);

        myMarker = googleMap.addMarker(new MarkerOptions()
                .position(Currentlocation)
                .title("You are here"));



        mMap.moveCamera(CameraUpdateFactory.newLatLng(Currentlocation));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //float zoomLevel = 16.0f; //This goes up to 21
        float zoomLevel = 20.0f; //This goes up to 21
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Currentlocation, zoomLevel));






        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng)
            {
               /* googleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Your marker title")
                        .snippet("Your marker snippet"));*/

               /* googleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("You are here"));

                Toast.makeText(getApplicationContext(),""+String.valueOf(latLng),Toast.LENGTH_SHORT).show();*/

              //  myMarker=null;

                if (myMarker == null)
                {
                    Log.e("if","if");
                    str_latlong="";
                    // Marker was not set yet. Add marker:
                    myMarker = googleMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("You are here")
                            .snippet(String.valueOf(latLng)));

                    str_pondmarked="yes";
                    str_latlong= String.valueOf(latLng);
                }
                else {
                    Log.e("else","else");

                    str_latlong="";
                    myMarker.remove();

                    myMarker = googleMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("You are here")
                            .snippet(String.valueOf(latLng)));

                    // Marker already exists, just update it's position
                   /* myMarker.setPosition(latLng);
                    myMarker.setTitle("You are here");
                   myMarker.setSnippet(String.valueOf(latLng));*/

                    str_pondmarked="yes";
                    str_latlong= String.valueOf(latLng);
                   // Log.e("else",String.valueOf(latLng));
                    //lat/lng: (15.370835294151595,75.1237140968442)

                }



            }


        });


    }





    @Override
    public void onBackPressed()
    {

       Toast.makeText(getApplicationContext(),"Kindly Click on Back Button", Toast.LENGTH_LONG).show();
    }




}