package com.dfcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.dfcovid.Activity_confirmpin.KeyValue_userid;
import static com.dfcovid.Activity_confirmpin.sharedpreference_usercredential;
import static com.dfcovid.Activity_pinlogin.KeyValue_isuser_changepin;
import static com.dfcovid.Activity_pinlogin.KeyValue_isuser_setpin;

public class Activity_WebLink extends AppCompatActivity {
    private WebView webView;

    SharedPreferences sharedpreference_usercredential_Obj;
    SharedPreferences.Editor editor_obj;
    String str_userID = "",str_flag="",loggedinflag="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_link);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("HD Mitra");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            str_flag= extras.getString("flag");
            loggedinflag= extras.getString("loggedinflag");

            // i.putExtra("loggedinflag","loggegdout");
            Log.e("str_flag", str_flag);
            Log.e("loggedinflag", loggedinflag);
        }

        sharedpreference_usercredential_Obj = getSharedPreferences(sharedpreference_usercredential, Context.MODE_PRIVATE);
        str_userID = sharedpreference_usercredential_Obj.getString(KeyValue_userid, "").trim();
        Log.e("str_userID", str_userID);

        webView = (WebView) findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                // Log.d("Failure Url :" , failingUrl);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //  Log.d("Ssl Error:",handler.toString() + "error:" +  error);
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setUserAgentString(String.valueOf(R.string.app_name));
        webView.loadUrl("https://hdmitra.org/");


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


            AlertDialog.Builder dialog = new AlertDialog.Builder(Activity_WebLink.this);
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

                    Intent i = new Intent(getApplicationContext(), Activity_confirmoldpin.class);
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
        } else if (id == android.R.id.home) {
            Intent i = new Intent(Activity_WebLink.this, Activity_HelpLineCenter.class);
            i.putExtra("flag",str_flag);
            i.putExtra("loggedinflag",loggedinflag);
            startActivity(i);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Activity_WebLink.this, Activity_HelpLineCenter.class);
        i.putExtra("flag",str_flag);
        i.putExtra("loggedinflag",loggedinflag);
        startActivity(i);
        finish();

    }
}