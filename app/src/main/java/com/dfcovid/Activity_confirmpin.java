package com.dfcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import com.covid.R;

public class Activity_confirmpin extends AppCompatActivity {


    Button submit_pin_bt;
    EditText otp1_et,otp2_et,otp3_et,otp4_et;

    public static final String sharedpreference_usercredential = "sharedpreferencebook_usercredential";
    public static final String KeyValue_userid = "KeyValue_userid";
    public static final String KeyValue_username = "KeyValue_userid";
    public static final String KeyValue_user_mailid = "KeyValue_user_mailid";
    public static final String KeyValue_usercategory = "KeyValue_usercategory";
    public static final String KeyValue_usercellno = "KeyValue_usercellno";
    public static final String KeyValue_isuser_setpin = "KeyValue_isuser_setpin";


    SharedPreferences sharedpreference_usercredential_Obj;
    SharedPreferences.Editor editor_obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmpin);

        submit_pin_bt =(Button) findViewById(R.id.submit_pin_bt);
        otp1_et=(EditText) findViewById(R.id.otp1_et);
        otp2_et=(EditText) findViewById(R.id.otp2_et);
        otp3_et=(EditText) findViewById(R.id.otp3_et);
        otp4_et=(EditText) findViewById(R.id.otp4_et);

        sharedpreference_usercredential_Obj=getSharedPreferences(sharedpreference_usercredential, Context.MODE_PRIVATE);

        submit_pin_bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                editor_obj = sharedpreference_usercredential_Obj.edit();
                editor_obj.putString(KeyValue_isuser_setpin, "yes");
                editor_obj.commit();

                Intent i = new Intent(Activity_confirmpin.this,Dashboard_Activity.class);
                startActivity(i);
                finish();

            }
        });


    }
}