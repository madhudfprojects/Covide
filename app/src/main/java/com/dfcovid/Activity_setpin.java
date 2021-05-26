package com.dfcovid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

//import com.covid.R;

public class Activity_setpin extends AppCompatActivity {


    public static final String sharedpreference_usercredential = "sharedpreferencebook_usercredential";
    public static final String KeyValue_userid = "KeyValue_userid";
    public static final String KeyValue_username = "KeyValue_username";
    public static final String KeyValue_user_mailid = "KeyValue_user_mailid";
    public static final String KeyValue_usercategory = "KeyValue_usercategory";
    public static final String KeyValue_usercellno = "KeyValue_usercellno";
    public static final String KeyValue_isuser_setpin = "KeyValue_isuser_setpin";


    SharedPreferences sharedpreference_usercredential_Obj;


    public static final String sharedpreference_setpincredential = "sharedpreference_pincredential";
    public static final String KeyValue_setpin = "KeyValue_setpin";
    SharedPreferences sharedpreference_setpin_Obj;
    SharedPreferences.Editor editor_obj;

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

        sharedpreference_setpin_Obj=getSharedPreferences(sharedpreference_setpincredential, Context.MODE_PRIVATE);

        confirm_pin_bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(validation())
                {

                    String str_setpin=otp1_et.getText().toString()+
                            otp2_et.getText().toString()+
                            otp3_et.getText().toString()+
                            otp4_et.getText().toString();


                    editor_obj = sharedpreference_setpin_Obj.edit();
                    editor_obj.putString(KeyValue_setpin,str_setpin);
                    editor_obj.commit();

                    Intent i = new Intent(Activity_setpin.this, Activity_confirmpin.class);
                    startActivity(i);
                    finish();
                }
            }
        });


    }


    public boolean validation()
    {
        boolean b_otp1, b_otp2, b_otp3, b_otp4;
        b_otp1=b_otp2=b_otp3=b_otp4=true;



        if (otp1_et.getText().toString().trim().length() == 0) {
            otp1_et.setError("Enter OTP");
            otp1_et.requestFocus();
            b_otp1 = false;
        }

        if (otp2_et.getText().toString().trim().length() == 0) {
            otp2_et.setError("Enter OTP");
            otp2_et.requestFocus();
            b_otp2 = false;
        }

        if (otp3_et.getText().toString().trim().length() == 0) {
            otp3_et.setError("Enter OTP");
            otp3_et.requestFocus();
            b_otp3 = false;
        }
        if(otp4_et.getText().toString().trim().length()==0)
        {
            otp4_et.setError("Enter OTP");
            otp4_et.requestFocus();
            b_otp4=false;
        }

        return (b_otp1 && b_otp2 && b_otp3&& b_otp4);
    }

}