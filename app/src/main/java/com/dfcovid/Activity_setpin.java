package com.dfcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import com.covid.R;

public class Activity_setpin extends AppCompatActivity {

    Button confirm_pin_bt;
    EditText otp1_et,otp2_et,otp3_et,otp4_et;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpin);

        confirm_pin_bt =(Button) findViewById(R.id.confirm_pin_bt);
        otp1_et=(EditText) findViewById(R.id.otp1_et);
        otp2_et=(EditText) findViewById(R.id.otp2_et);
        otp3_et=(EditText) findViewById(R.id.otp3_et);
        otp4_et=(EditText) findViewById(R.id.otp4_et);



        confirm_pin_bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                Intent i = new Intent(Activity_setpin.this,Activity_confirmpin.class);
                startActivity(i);
                finish();

            }
        });


    }
}