package com.dfcovid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;


public class ContactCenter extends AppCompatActivity {
    private WebView webView;
    Activity activity ;
    String str_fetched_usermanual="";
//    private ProgressDialog progDailog;
//    ImageView backbtn_iv;
//    public static final String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36 RuxitSynthetic/1.0 v5240046564 t38550";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_center);

        webView = (WebView) findViewById(R.id.webView);
        activity = this;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            str_fetched_usermanual= extras.getString("usermanual");
            Log.e("str_fetched_usermanual", str_fetched_usermanual);
        }
        //str_fetched_usermanual: https://skilling.dfindia.org/Document/Help/SIV_User_manual_1.0.pdf
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                // Log.d("Failure Url :" , failingUrl);
//            }
//
//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                //  Log.d("Ssl Error:",handler.toString() + "error:" +  error);
//                handler.proceed();
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setLoadWithOverviewMode(true);
//        webView.getSettings().setUseWideViewPort(true);
//        webView.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setUserAgentString(String.valueOf(R.string.app_name));
//        webView.loadUrl(str_fetched_usermanual);










        /////////////////////////////////////////////
        activity = this;


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.setCancelable(false);
        WebView web_view = findViewById(R.id.webView);
        web_view.requestFocus();
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.getSettings().setUserAgentString(System.getProperty( "http.agent" ));

       // String myPdfUrl = "https://www.dfindia.org:82//app-images/ideafactoryhelpdoc.pdf";
        String url = "https://docs.google.com/gview?embedded=true&url="+str_fetched_usermanual;
        web_view.loadUrl(url);




        web_view.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e("Failure Url :" , failingUrl);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                Log.e("Ssl Error:",handler.toString() + "error:" +  error);
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        web_view.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {
                    progressDialog.show();
                }
                if (progress == 100) {
                    progressDialog.dismiss();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ContactCenter.this, ContactUs_Activity.class);
        startActivity(i);
        finish();
    }

}