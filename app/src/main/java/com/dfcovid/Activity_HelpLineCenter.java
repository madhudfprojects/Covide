package com.dfcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_HelpLineCenter extends AppCompatActivity {

    TextView contactNumber_TV,weblink_TV;
    ImageView contactNumber_IV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helplinecenter);

        contactNumber_TV=(TextView)findViewById(R.id.contactNumber_TV);
        weblink_TV=(TextView)findViewById(R.id.weblink_TV);
        contactNumber_IV=(ImageView)findViewById(R.id.contactNumber_IV);

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
                Intent i = new Intent(Activity_HelpLineCenter.this, Activity_WebLink.class);
                startActivity(i);
                finish();

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Activity_HelpLineCenter.this, Dashboard_Activity_New.class);
        startActivity(i);
        finish();

    }

}