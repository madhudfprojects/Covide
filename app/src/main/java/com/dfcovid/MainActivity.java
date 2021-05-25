package com.dfcovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.covid.R;

import com.dfcovid.remote.Class_ApiUtils;
import com.dfcovid.remote.Interface_userservice;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{

    public static final String sharedpreference_usercredential = "sharedpreferencebook_usercredential";
    public static final String KeyValue_userid = "KeyValue_userid";
    public static final String KeyValue_username = "KeyValue_userid";
    public static final String KeyValue_user_mailid = "KeyValue_user_mailid";
    public static final String KeyValue_usercategory = "KeyValue_usercategory";
    public static final String KeyValue_usercellno = "KeyValue_usercellno";
    public static final String KeyValue_isuser_setpin = "KeyValue_isuser_setpin";


    SharedPreferences sharedpreference_usercredential_Obj;


    String[] permissions= new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.INTERNET
    };
    public static final int MULTIPLE_PERMISSIONS = 10; // code you want.
    Class_InternetDectector internetDectector;
    Boolean isInternetPresent = false;


    Button login_bt;
    String str_isuser_setpin;
    SharedPreferences.Editor editor_obj;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_bt=(Button)findViewById(R.id.login_bt);


        sharedpreference_usercredential_Obj=getSharedPreferences(sharedpreference_usercredential, Context.MODE_PRIVATE);
        str_isuser_setpin = sharedpreference_usercredential_Obj.getString(KeyValue_isuser_setpin, "").trim();





     /*   if (checkPermissions())
        {

        }*/


        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if (2>1)
                {
                    internetDectector = new Class_InternetDectector(getApplicationContext());
                    isInternetPresent = internetDectector.isConnectingToInternet();

                    if (isInternetPresent)
                    {

                        fetch_normallogin();

                    }else
                    {
                        Toast.makeText(MainActivity.this, "Kindly connect to internet", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(MainActivity.this, "Kindly connect to internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private  boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p:permissions) {
            result = ContextCompat.checkSelfPermission(this,p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),MULTIPLE_PERMISSIONS );
            return false;
        }
        return true;
    }











    public void fetch_normallogin()
    {

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.setTitle("Please wait fetching details....");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);



        Class_loginrequest request = new Class_loginrequest();
        request.setUsername("9845176621");
        request.setPassword("admin");


        Interface_userservice userService;
        userService = Class_ApiUtils.getUserService();
        Call<Class_normalloginresponse> call = userService.Post_ValidateNormalLogin(request);

        Log.e("TAG", "loginreq : " + new Gson().toJson(request));

        call.enqueue(new Callback<Class_normalloginresponse>() {
            @Override
            public void onResponse(Call<Class_normalloginresponse> call, Response<Class_normalloginresponse> response)
            {
                Log.e("response", response.toString());

                Log.e("TAG", "LoginResponse : " + new Gson().toJson(response));
                Log.e("tag","LoginResponse body"+ String.valueOf(response.body()));
                //   DefaultResponse error1 = ErrorUtils.parseError(response);
               /* Log.e("response new:",error1.getMsg());
                Log.e("response new status:", String.valueOf(error1.getstatus()));*/


                if (response.isSuccessful())
                {
                    progressDialog.dismiss();

                    Toast.makeText(MainActivity.this, "successfully", Toast.LENGTH_SHORT).show();

                    if (str_isuser_setpin.isEmpty())
                    {

                        //  Toast.makeText(getApplicationContext(),"empty",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, Activity_setpin.class);
                        startActivity(i);
                        finish();

                    } else {
                        Intent i = new Intent(MainActivity.this, Activity_pinlogin.class);
                        startActivity(i);
                        finish();
                    }


                } else {


                    DefaultResponse error = ErrorUtils.parseError(response);
                    // … and use it to show error information

                    // … or just log the issue like we’re doing :)
                    Log.d("responseerror", error.getMsg());

                    Toast.makeText(MainActivity.this, error.getMsg(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t)
            {

                Log.d("retrofiteerror", t.toString());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });// end of call


    }




/*9845176621
        9964331217
        9986311618
        9902740277
        9480565182
        8971474639
        7259833450
        9742392362
    password common for all
        admin*/



}// end of class