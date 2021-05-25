package com.dfcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

//import com.covid.R;

public class Activity_pinlogin extends AppCompatActivity {

    TextView forgotpin_tv;
    EditText otp1_et,otp2_et,otp3_et,otp4_et;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        forgotpin_tv=(TextView)findViewById(R.id.forgotpin_tv);
        otp1_et=(EditText) findViewById(R.id.otp1_et);
        otp2_et=(EditText) findViewById(R.id.otp2_et);
        otp3_et=(EditText) findViewById(R.id.otp3_et);
        otp4_et=(EditText) findViewById(R.id.otp4_et);





        //otp4_et



        otp4_et.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

                if(s.toString().trim().isEmpty())
                {


                }
                else
                {

                    if(validation())
                    {

                        Intent i = new Intent(Activity_pinlogin.this,HomeActivity.class);
                        startActivity(i);
                        finish();
                    }


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        forgotpin_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                AlertDialog.Builder dialog = new AlertDialog.Builder(Activity_pinlogin.this);
                dialog.setCancelable(false);
                dialog.setTitle(R.string.forgotpin);
                dialog.setMessage("You need to relogin to the application for set new PIN.\n" +
                        "Do you want to proceed");

                dialog.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Intent i = new Intent(Activity_pinlogin.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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


        return (b_otp1 && b_otp2 && b_otp3 );
    }

}