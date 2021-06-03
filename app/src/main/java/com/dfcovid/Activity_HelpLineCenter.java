package com.dfcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.dfcovid.Activity_confirmpin.KeyValue_userid;
import static com.dfcovid.Activity_confirmpin.sharedpreference_usercredential;
import static com.dfcovid.Activity_pinlogin.KeyValue_isuser_changepin;
import static com.dfcovid.Activity_pinlogin.KeyValue_isuser_setpin;

public class Activity_HelpLineCenter extends AppCompatActivity {

    TextView contactNumber_TV, weblink_TV;
    ImageView contactNumber_IV;
    LinearLayout maps_LL, dashboard_LL;
    SharedPreferences sharedpreference_usercredential_Obj;
    SharedPreferences.Editor editor_obj;
    String str_userID = "",str_flag="0",loggedinflag="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helplinecenter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Helpline");

        sharedpreference_usercredential_Obj = getSharedPreferences(sharedpreference_usercredential, Context.MODE_PRIVATE);
        str_userID = sharedpreference_usercredential_Obj.getString(KeyValue_userid, "").trim();
        Log.e("str_userID", str_userID);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            str_flag= extras.getString("flag");
            loggedinflag= extras.getString("loggedinflag");

           // i.putExtra("loggedinflag","loggegdout");
            Log.e("str_flag", str_flag);
            Log.e("loggedinflag", loggedinflag);
        }



        contactNumber_TV = (TextView) findViewById(R.id.contactNumber_TV);
        weblink_TV = (TextView) findViewById(R.id.weblink_TV);
        contactNumber_IV = (ImageView) findViewById(R.id.contactNumber_IV);

        maps_LL = (LinearLayout) findViewById(R.id.maps_LL);
        dashboard_LL = (LinearLayout) findViewById(R.id.dashboard_LL);

        contactNumber_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "08047168111"));
                startActivity(intent);
            }
        });


        contactNumber_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "08047168111"));
                startActivity(intent);
            }
        });

        weblink_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //added flag
                Intent i = new Intent(Activity_HelpLineCenter.this, Activity_WebLink.class);
                i.putExtra("flag",str_flag);
                i.putExtra("loggedinflag",loggedinflag);
                startActivity(i);
                finish();

            }
        });


        maps_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Activity_HelpLineCenter.this, Activity_GoogleMaps.class);
                i.putExtra("flag","2");
                i.putExtra("loggedinflag",loggedinflag);
                startActivity(i);
                finish();

            }
        });

        dashboard_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loggedinflag.equals("loggegdin")) {
                    Intent i = new Intent(Activity_HelpLineCenter.this, Dashboard_Activity.class);
                    startActivity(i);
                    finish();
                }else if(loggedinflag.equals("loggegdout")) {
                    Intent i = new Intent(Activity_HelpLineCenter.this, Dashboard_Activity_New.class);
                    startActivity(i);
                    finish();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home_menu, menu);
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        MenuItem action_editProfile = menu.findItem(R.id.aboutus);
        action_editProfile.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        if (id == R.id.changepin) {


            AlertDialog.Builder dialog = new AlertDialog.Builder(Activity_HelpLineCenter.this);
            dialog.setCancelable(false);
            dialog.setTitle(R.string.alert);
            dialog.setMessage("Are you sure you want to Change PIN?");

            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {

                    editor_obj = sharedpreference_usercredential_Obj.edit();
                    editor_obj.putString(KeyValue_isuser_setpin, "");
                    editor_obj.commit();

                    editor_obj = sharedpreference_usercredential_Obj.edit();
                    editor_obj.putString(KeyValue_isuser_changepin, "yes");
                    editor_obj.commit();

                    Intent i = new Intent(Activity_HelpLineCenter.this, Activity_confirmoldpin.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
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

            return true;
        }else if (id == android.R.id.home) {

            if(loggedinflag.equals("loggegdin") && str_flag.equals("1")) {
                Intent i = new Intent(Activity_HelpLineCenter.this, Dashboard_Activity.class);
                startActivity(i);
                finish();
            }else if(loggedinflag.equals("loggegdout") && str_flag.equals("0")) {
                Intent i = new Intent(Activity_HelpLineCenter.this, Dashboard_Activity_New.class);
                startActivity(i);
                finish();
            }else if(str_flag.equals("2")){
                Intent i = new Intent(Activity_HelpLineCenter.this, Activity_GoogleMaps.class);
                i.putExtra("flag","2");
                i.putExtra("loggedinflag",loggedinflag);
                startActivity(i);
                finish();

            }



//            if(str_flag.equals("1")) {
//                Intent i = new Intent(Activity_HelpLineCenter.this, Dashboard_Activity.class);
//                startActivity(i);
//                finish();
//            }else if(str_flag.equals("2")){
//                Intent i = new Intent(Activity_HelpLineCenter.this, Activity_GoogleMaps.class);
//                i.putExtra("flag","2");
//                i.putExtra("loggedinflag",loggedinflag);
//                startActivity(i);
//                finish();
//            }else{
//                Intent i = new Intent(Activity_HelpLineCenter.this, Dashboard_Activity_New.class);
//                startActivity(i);
//                finish();
//
//            }
        }

//        else if(id==R.id.aboutus){
//            Intent i = new Intent(Activity_HelpLineCenter.this, ContactUs_Activity.class);
//            startActivity(i);
//            finish();
//
//        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(loggedinflag.equals("loggegdin")&& str_flag.equals("1")) {
            Intent i = new Intent(Activity_HelpLineCenter.this, Dashboard_Activity.class);
            startActivity(i);
            finish();

        }else if(loggedinflag.equals("loggegdout") && str_flag.equals("0")) {
            Intent i = new Intent(Activity_HelpLineCenter.this, Dashboard_Activity_New.class);
            startActivity(i);
            finish();
        }else if(str_flag.equals("2")){
            Intent i = new Intent(Activity_HelpLineCenter.this, Activity_GoogleMaps.class);
            i.putExtra("flag","2");
            i.putExtra("loggedinflag",loggedinflag);
            startActivity(i);
            finish();

        }
//        if(str_flag.equals("1")) {
//            Intent i = new Intent(Activity_HelpLineCenter.this, Dashboard_Activity.class);
//            startActivity(i);
//            finish();
//        }else if(str_flag.equals("2")){
//            Intent i = new Intent(Activity_HelpLineCenter.this, Activity_GoogleMaps.class);
//            i.putExtra("flag","2");
//            i.putExtra("loggedinflag",loggedinflag);
//            startActivity(i);
//            finish();
//        }else{
//            Intent i = new Intent(Activity_HelpLineCenter.this, Dashboard_Activity_New.class);
//            startActivity(i);
//            finish();
//
//        }
    }
}



