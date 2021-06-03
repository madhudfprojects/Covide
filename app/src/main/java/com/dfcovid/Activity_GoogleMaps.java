package com.dfcovid;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.dfcovid.model.Class_GetUserHospitalList;
import com.dfcovid.remote.Class_ApiUtils;
import com.dfcovid.remote.Interface_userservice;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import androidx.core.app.FragmentActivity;
/*import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;*/

public class Activity_GoogleMaps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    //Class_GPSTracker gpstracker_obj1;

    Double double_currentlatitude = 0.0;
    Double double_currentlongitude = 0.0;

    Double double_lastlatitude=0.0;
    Double double_lastlongitude=0.0;


    Toolbar toolbar;
    ImageView add_newfarmpond_iv;


    String location;
   

    Marker myMarker;

    String str_fromname, str_pondmarked, str_latlong;

    String str_lat, str_long;

    Class_serviceslistResp[] class_serviceslistresp_arrayObj;
    Class_serviceslistResp class_serviceslistresp_Obj;

    Class_hsptaldetalServices_listResp[] class_hosptlDetal_listServis_arrayObj;
    Class_hsptaldetalServices_listResp class_hosptlDetal_listServis_Obj;


    Spinner hospitalservices_sp;

    SupportMapFragment mapFragment;

    String str_flag,loggedinflag;
    LinearLayout dashboard_LL,helplinecenter_LL,googlemaps_LL;
    String str_response;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemaps);
        //Places.initialize(getApplicationContext(), "YOUR_API_KEY");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
         mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        dashboard_LL=(LinearLayout) findViewById(R.id.dashboard_LL);
        helplinecenter_LL=(LinearLayout) findViewById(R.id.helplinecenter_LL);
        googlemaps_LL=(LinearLayout) findViewById(R.id.googlemaps_LL);;
        hospitalservices_sp=(Spinner)findViewById(R.id.hospitalservices_sp);

        str_flag=loggedinflag="";

        str_response="false";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            str_flag= extras.getString("flag");
            loggedinflag= extras.getString("loggedinflag");

            // i.putExtra("loggedinflag","loggegdout");
            Log.e("str_flag", str_flag);
            Log.e("loggedinflag", loggedinflag);
        }

        dashboard_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //commented and added by shivaleela on 3rd june 2021
//                if(str_flag.equals("1")) {
//                    Intent i = new Intent(Activity_GoogleMaps.this, Dashboard_Activity.class);
//                    startActivity(i);
//                    finish();
//                } else{
//                    Intent i = new Intent(Activity_GoogleMaps.this, Dashboard_Activity_New.class);
//                    startActivity(i);
//                    finish();
//
//                }

                if(loggedinflag.equals("loggegdin")) {
                    Intent i = new Intent(Activity_GoogleMaps.this, Dashboard_Activity.class);
                    startActivity(i);
                    finish();
                }else if(loggedinflag.equals("loggegdout")) {
                    Intent i = new Intent(Activity_GoogleMaps.this, Dashboard_Activity_New.class);
                    startActivity(i);
                    finish();
                }
            }
        });


        helplinecenter_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent i = new Intent(Activity_GoogleMaps.this, Activity_HelpLineCenter.class);
                i.putExtra("flag","2");
                i.putExtra("loggedinflag",loggedinflag);
                startActivity(i);
                finish();

            }
        });

        googlemaps_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
            Toast.makeText(getApplicationContext(),"Already in this Page",Toast.LENGTH_SHORT).show();
            }
        });


        hospitalservices_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                class_serviceslistresp_Obj = (Class_serviceslistResp) hospitalservices_sp.getSelectedItem();

              //  Toast.makeText(getApplicationContext(),class_serviceslistresp_Obj.getService_Id().toString(),Toast.LENGTH_SHORT).show();

                String str_serviceid=class_serviceslistresp_Obj.getService_Id().toString();
                AsyncTask_HospitalDetails_withService(str_serviceid);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



       // Places.initialize(getApplicationContext(), "YOUR_API_KEY");
       // Places.initialize(getApplicationContext(),"@string/API_KEY");




        AsyncTask_HospitalServicesList();





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




        mMap = googleMap;
        //15.363289994348667, 75.132790658344
        double_currentlatitude=15.363289994348667;
        double_currentlongitude=75.132790658344;
        // Add a marker in Sydney and move the camera
        //LatLng Currentlocation = new LatLng(double_currentlatitude, double_currentlongitude);

        LatLng Currentlocation = new LatLng(15.363289994348667, 75.132790658344);

        LatLng Currentlocation1 = new LatLng( 15.352417641487971, 75.12442586841604);

        /*myMarker = googleMap.addMarker(new MarkerOptions()
                .position(Currentlocation)
                .title("You are here")
                .snippet(String.valueOf(Currentlocation)));*/



       /* myMarker = googleMap.addMarker(new MarkerOptions()
                .position(Currentlocation)
                .title("KIMS")
                .snippet("Govt Hospital,Vidya Nagar, Hubli, Karnataka 580021"));*/



       if(str_response.equalsIgnoreCase("true"))
       {
           if (class_hosptlDetal_listServis_arrayObj.equals(null)) {

           } else {
               int size = class_hosptlDetal_listServis_arrayObj.length;

               Log.e("size", String.valueOf(class_hosptlDetal_listServis_arrayObj.length));

               if (class_hosptlDetal_listServis_arrayObj[0].getWebsite()==null
                       || class_hosptlDetal_listServis_arrayObj[0].getWebsite().equals(""))
               {
                   Log.e("null", "null appeared");
               }


               for (int i = 0; i < size; i++)
               {

                  // Log.e("lat",class_hosptlDetal_listServis_arrayObj[i].getLatitude().toString());
                  drawMarker(googleMap,class_hosptlDetal_listServis_arrayObj[i],i);
               }

           }
       }

       Log.e("entered","here");
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(Currentlocation));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //float zoomLevel = 16.0f; //This goes up to 21
        //float zoomLevel = 20.0f; //This goes up to 21
        float zoomLevel = 12.0f;
        if (Double.compare(double_lastlatitude, double_lastlongitude) == 0)
        {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Currentlocation, zoomLevel));
        }else{
            LatLng Hospitallocationmap= new LatLng( double_lastlatitude, double_lastlongitude);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Hospitallocationmap, zoomLevel));
        }







    }



//class_hosptlDetal_listServis_arrayObj
    //private void drawMarker(LatLng point)
    private void drawMarker(final GoogleMap googleMap,
                            Class_hsptaldetalServices_listResp class_hosptlDetal_listServis_arrayObj,int i)
    {

       // LatLng point=null;
        Double double_hospitallatitude = 0.0;
        Double double_hospitalongitude = 0.0;



        int int_lat,int_long;
        int_lat=int_long=0;

        if(class_hosptlDetal_listServis_arrayObj.getLatitude().isEmpty())
        {
        }
          else
           {
               if (class_hosptlDetal_listServis_arrayObj.getLongitude().isEmpty()) {

               }
               else {

                   char c = '.';
                   String str_lat = class_hosptlDetal_listServis_arrayObj.getLatitude().toString();
                   String str_long = class_hosptlDetal_listServis_arrayObj.getLongitude().toString();

                   /*Log.e("str_lat", String.valueOf(str_lat.length()));
                   Log.e("str_long", String.valueOf(str_long.length()));*/

                   for (int j = 0; j < str_lat.length(); j++) {
                    //   Log.e("str_latChar", String.valueOf(str_lat.charAt(j)));
                       if (str_lat.charAt(j) == c)
                       {
                           int_lat++;
                       }
                   }
                   //Log.e("int_lat", String.valueOf(int_lat));

                   for (int k = 0; k < str_long.length(); k++) {
                       if (str_long.charAt(k) == c) {
                           int_long++;
                       }
                   }

                   //Log.e("str_long", String.valueOf(str_long));

                   if (int_lat == 1 && int_long == 1) {

                      /* Log.e("lat", class_hosptlDetal_listServis_arrayObj.getLatitude().trim());
                       Log.e("long", class_hosptlDetal_listServis_arrayObj.getLongitude().trim());
*/
                       double_hospitallatitude = Double.valueOf(class_hosptlDetal_listServis_arrayObj.getLatitude());
                       double_hospitalongitude = Double.valueOf(class_hosptlDetal_listServis_arrayObj.getLongitude());
                       String str_title = class_hosptlDetal_listServis_arrayObj.getHospitalName();
                       String str_address = class_hosptlDetal_listServis_arrayObj.getHospitalAddress();
                       LatLng Hospitallocation = new LatLng(double_hospitallatitude, double_hospitalongitude);

                       if(i==0)
                       {
                           double_lastlatitude=double_hospitallatitude;
                           double_lastlongitude=double_hospitalongitude;

                       }
       /* myMarker = googleMap.addMarker(new MarkerOptions()
                .position(Currentlocation1)
                .title("Suchirayu")
                .snippet("Gokul Rd, opposite KSRTC Bus Depot,Kallur Layout, Hubli, Karnataka 580030"));*/

                       // Creating an instance of MarkerOptions
                       MarkerOptions markerOptions = new MarkerOptions();

                       // Setting latitude and longitude for the marker
                       markerOptions.position(Hospitallocation);
                       markerOptions.title(str_title);
                       markerOptions.snippet(str_address);

                       // Adding marker on the Google Map
                       googleMap.addMarker(markerOptions);

                   }


            }
        }


    }








    public void AsyncTask_HospitalServicesList()
    {

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(Activity_GoogleMaps.this);
        progressDialog.setMessage("Loading....");
        progressDialog.setTitle("Please wait fetching Details....");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();




        Interface_userservice userService;
        userService = Class_ApiUtils.getUserService();
        Call<Class_servicesdetailsResp> call = userService.Get_ServicesList();


        call.enqueue(new Callback<Class_servicesdetailsResp>() {
            @Override
            public void onResponse(Call<Class_servicesdetailsResp> call, Response<Class_servicesdetailsResp> response)
            {
                Log.e("response", response.toString());

                Log.e("TAG", "HospRes: " + new Gson().toJson(response));
                Log.e("tag","HospResponse body"+ String.valueOf(response.body()));
                //   DefaultResponse error1 = ErrorUtils.parseError(response);
               /* Log.e("response new:",error1.getMsg());
                Log.e("response new status:", String.valueOf(error1.getstatus()));*/

                Class_servicesdetailsResp user_object;
                user_object = (Class_servicesdetailsResp) response.body();

                if (response.isSuccessful())
                {

                    progressDialog.dismiss();

                   // String str_userstatus=user_object.getMessage().trim().toString();
                    if(user_object.getStatus())
                    {


                        List<Class_serviceslistResp> serviceslistResp_list = response.body().getList();


                        class_serviceslistresp_arrayObj= new Class_serviceslistResp[serviceslistResp_list.size()];

                        for (int i = 0; i < serviceslistResp_list.size(); i++)
                        {

                            Class_serviceslistResp  serviceslistResp_innerObj = new Class_serviceslistResp();

                            serviceslistResp_innerObj.setService_Id(user_object.getList().get(i).getService_Id());
                            serviceslistResp_innerObj.setServices_Name(user_object.getList().get(i).getServices_Name());
                            class_serviceslistresp_arrayObj[i]=serviceslistResp_innerObj;
                        }

                        if(serviceslistResp_list.size()>0) {
                            ArrayAdapter dataAdapter = new ArrayAdapter(Activity_GoogleMaps.this, R.layout.support_simple_spinner_dropdown_item, class_serviceslistresp_arrayObj);
                            dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            hospitalservices_sp.setAdapter(dataAdapter);
                        }

                    }




                } else {


                    DefaultResponse error = ErrorUtils.parseError(response);
                    // … and use it to show error information

                    // … or just log the issue like we’re doing :)
                    Log.d("responseerror", error.getMsg());

                    Toast.makeText(Activity_GoogleMaps.this, "WS:error", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t)
            {
                progressDialog.dismiss();
                Log.d("retrofiteerror", t.toString());
                Toast.makeText(Activity_GoogleMaps.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });// end of call

    }



//AsyncTask_HospitalDetails_withService



    public void AsyncTask_HospitalDetails_withService(String str_serviceid)
    {

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(Activity_GoogleMaps.this);
        progressDialog.setMessage("Loading....");
        progressDialog.setTitle("Please wait fetching Details....");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();




        Interface_userservice userService;
        userService = Class_ApiUtils.getUserService();
      //  Call<Class_hospitaldetalServices_resp> call = userService.Get_Hospital_DetailsServices1(str_serviceid);


        Call<Class_hospitaldetalServices_resp> call = userService.Get_Hospital_DetailsServices2(str_serviceid);

        call.enqueue(new Callback<Class_hospitaldetalServices_resp>() {
            @Override
            public void onResponse(Call<Class_hospitaldetalServices_resp> call, Response<Class_hospitaldetalServices_resp> response)
            {
                Log.e("response", response.toString());

                Log.e("TAG", "HospDetRes: " + new Gson().toJson(response));
                Log.e("tag","HospDetResponse body"+ String.valueOf(response.body()));
                //   DefaultResponse error1 = ErrorUtils.parseError(response);
               /* Log.e("response new:",error1.getMsg());
                Log.e("response new status:", String.valueOf(error1.getstatus()));*/

                Class_hospitaldetalServices_resp user_object;
                user_object = (Class_hospitaldetalServices_resp) response.body();

                if (response.isSuccessful())
                {

                    progressDialog.dismiss();

                    // String str_userstatus=user_object.getMessage().trim().toString();
                    if(user_object.getStatus())
                    {


                        List<Class_hsptaldetalServices_listResp> hsptaldetalServices_listResp_list = response.body().getHospital_Details();

                        class_hosptlDetal_listServis_arrayObj= new Class_hsptaldetalServices_listResp[hsptaldetalServices_listResp_list.size()];

                        for (int i = 0; i < hsptaldetalServices_listResp_list.size(); i++)
                        {

                            Class_hsptaldetalServices_listResp  hsptaldetalServices_listResp_innerObj = new Class_hsptaldetalServices_listResp();

                            hsptaldetalServices_listResp_innerObj.setHospitalName(user_object.getHospital_Details().get(i).getHospitalName());
                            hsptaldetalServices_listResp_innerObj.setLatitude(user_object.getHospital_Details().get(i).getLatitude());
                            hsptaldetalServices_listResp_innerObj.setLongitude(user_object.getHospital_Details().get(i).getLongitude());
                            hsptaldetalServices_listResp_innerObj.setHospitalAddress(user_object.getHospital_Details().get(i).getHospitalAddress());


                            class_hosptlDetal_listServis_arrayObj[i]=hsptaldetalServices_listResp_innerObj;


                        }

                        str_response="true";


                        Log.e("Lenght", String.valueOf(class_hosptlDetal_listServis_arrayObj.length));

                        mapFragment.getMapAsync(Activity_GoogleMaps.this::onMapReady);

                    }





                } else {


                    DefaultResponse error = ErrorUtils.parseError(response);
                    // … and use it to show error information

                    // … or just log the issue like we’re doing :)
                    Log.e("responseerror", error.getMsg());

                    Toast.makeText(Activity_GoogleMaps.this, "WS:error", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t)
            {
                progressDialog.dismiss();
                Log.e("retrofiteerror", t.toString());
                Toast.makeText(Activity_GoogleMaps.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });// end of call

    }










  /*  @Override
    public void onBackPressed()
    {

       Toast.makeText(getApplicationContext(),"Kindly Click on Bottom button", Toast.LENGTH_LONG).show();
    }*/






    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //commented and added by shivaleela on 3rd june 2021
//        if(str_flag.equals("1")) {
//            Intent i = new Intent(Activity_GoogleMaps.this, Dashboard_Activity.class);
//            startActivity(i);
//            finish();
//        }else if(str_flag.equals("2"))
//        {
//            Intent i = new Intent(Activity_GoogleMaps.this, Activity_HelpLineCenter.class);
//            i.putExtra("flag","2");
//            i.putExtra("loggedinflag",loggedinflag);
//        }else{
//
//            Intent i = new Intent(Activity_GoogleMaps.this, Dashboard_Activity_New.class);
//            startActivity(i);
//            finish();
//        }

        if(loggedinflag.equals("loggegdin") && str_flag.equals("1")) {
            Intent i = new Intent(Activity_GoogleMaps.this, Dashboard_Activity.class);
            startActivity(i);
            finish();
        }else if(loggedinflag.equals("loggegdout") && str_flag.equals("0")) {
            Intent i = new Intent(Activity_GoogleMaps.this, Dashboard_Activity_New.class);
            startActivity(i);
            finish();
        }else if(str_flag.equals("2")){
            Intent i = new Intent(Activity_GoogleMaps.this, Activity_HelpLineCenter.class);
            i.putExtra("flag","2");
            i.putExtra("loggedinflag",loggedinflag);
            startActivity(i);
            finish();

        }
    }




   /*  cancel_bt.setOnClickListener(new View.OnClickListener()
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

                finish();
                        *//*dialog.dismiss();
                        finish();*//*
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
    });*/


}