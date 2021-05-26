package com.dfcovid;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dfcovid.remote.Class_ApiUtils;
import com.dfcovid.remote.Interface_userservice;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.covid.R;

public class MainActivity extends AppCompatActivity
{

    public static final String sharedpreference_usercredential = "sharedpreferencebook_usercredential";
    public static final String KeyValue_userid = "KeyValue_userid";
    public static final String KeyValue_username = "KeyValue_username";
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

    private static final int RC_SIGN_IN = 234;////a constant for detecting the login intent result
    private static final String TAG = "dfcovid";
    FirebaseAuth firebaseauth_obj;
    SignInButton google_signin_bt;
    GoogleSignInClient googlesigninclient_obj;
    GoogleSignInAccount account;
    String str_gmailid;


    public final static String COLOR = "#1565C0";


    Button login_bt;
    String str_isuser_setpin,str_forgotusername;
    SharedPreferences.Editor editor_obj;

    EditText username_et,password_et, dialogusername_et;
    TextView forgotpassword_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username_et=(EditText)findViewById(R.id.username_et);
                password_et=(EditText)findViewById(R.id.password_et);

        login_bt=(Button)findViewById(R.id.login_bt);
        forgotpassword_tv=(TextView)findViewById(R.id.forgotpassword_tv);


        google_signin_bt =(SignInButton)findViewById(R.id.google_signin_bt);
        google_signin_bt.setColorScheme(SignInButton.COLOR_DARK);
        setGooglePlusButtonText(google_signin_bt,"  Sign in with DF mail  ");


        sharedpreference_usercredential_Obj=getSharedPreferences(sharedpreference_usercredential, Context.MODE_PRIVATE);
        str_isuser_setpin = sharedpreference_usercredential_Obj.getString(KeyValue_isuser_setpin, "").trim();





     /*   if (checkPermissions())
        {

        }*/

        if (str_isuser_setpin.isEmpty())
        {

        }else{
            if (str_isuser_setpin.equalsIgnoreCase("yes"))
            {
                Intent i = new Intent(MainActivity.this, Activity_pinlogin.class);
                startActivity(i);
                finish();
            }

        }



        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (2 > 1)
                {
                    internetDectector = new Class_InternetDectector(getApplicationContext());
                    isInternetPresent = internetDectector.isConnectingToInternet();


                    if(validation())
                    {

                    if (isInternetPresent)
                    {


                        fetch_normallogin();

                        /*Intent i = new Intent(MainActivity.this, Activity_setpin.class);
                        startActivity(i);
                        finish();*/

                    } else {
                        Toast.makeText(MainActivity.this, "Kindly connect to internet", Toast.LENGTH_SHORT).show();
                    }

                }


            }



            }
        });



        //Google Sign initializing
        firebaseauth_obj = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_ID_type3))
                .requestEmail()
                .build();
        googlesigninclient_obj = GoogleSignIn.getClient(this, gso);
        //Google Sign initializing




        google_signin_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


                internetDectector = new Class_InternetDectector(getApplicationContext());
                isInternetPresent = internetDectector.isConnectingToInternet();

                if (2>1)
                {
                    if (isInternetPresent)
                    {
                        google_sign();
                    }else{
                        Toast.makeText(MainActivity.this, "Kindly connect to internet", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });






        forgotpassword_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final Dialog dialog = new Dialog(MainActivity.this);


                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.forgetdialog);
                dialog.setCancelable(false);


                Button forgotpasswordcancel_bt = (Button) dialog.findViewById(R.id.forgotpasswordcancel_bt);
                Button forgotpasswordsubmit_bt = (Button) dialog.findViewById(R.id.forgotpasswordsubmit_bt);
                dialogusername_et=(EditText)dialog.findViewById(R.id.dialogusername_et);

                forgotpasswordcancel_bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.dismiss();

                    }
                });

                forgotpasswordsubmit_bt.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0)
                    {

                        internetDectector = new Class_InternetDectector(getApplicationContext());
                        isInternetPresent = internetDectector.isConnectingToInternet();

                        if(forgotpassword_validation())
                        {
                            if (isInternetPresent)
                            {

                                str_forgotusername=dialogusername_et.getText().toString();
                                dialog.dismiss();
                                 AsyncTask_Post_ForgetPassword();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Kindly connect to internet", Toast.LENGTH_SHORT).show();
                            }
                        }



                    }
                });


                dialog.show();



            }
        });




    }//end of Oncreate





    public boolean forgotpassword_validation()
    {
        boolean b_username, b_password;
        b_username=true;


        if (dialogusername_et.getText().toString().trim().length() == 0||
                dialogusername_et.getText().toString().trim().length()<=4) {
            dialogusername_et.setError("Enter Valid Username");
            username_et.requestFocus();
            b_username = false;
        }



        return (b_username);

    }



    public boolean validation()
    {
        boolean b_username, b_password;
        b_username=b_password=true;


        if (username_et.getText().toString().trim().length() == 0) {
            username_et.setError("Enter Username");
            username_et.requestFocus();
            b_username = false;
        }

        if (password_et.getText().toString().trim().length() == 0) {
            password_et.setError("Enter password");
            password_et.requestFocus();
            b_password = false;
        }

        return (b_username && b_password);
    }



//Google Login





    private void google_sign() {
        //getting the google signin intent
        Intent signInIntent = googlesigninclient_obj.getSignInIntent();
        //starting the activity for result
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //if the requestCode is the Google Sign In code that we defined at starting
        if (requestCode == RC_SIGN_IN) {
            //Getting the GoogleSignIn Task
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //Google Sign In was successful, authenticate with Firebase
                account = task.getResult(ApiException.class);
                //authenticating with firebase
                firebaseAuthWithGoogle(account);

            } catch (ApiException e) {
                Toast.makeText(MainActivity.this, "Error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }




    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct)
    {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.getId());

        //getting the auth credential
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        str_gmailid=acct.getEmail().toString();

        //Now using firebase we are signing in the user here
        firebaseauth_obj.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = firebaseauth_obj.getCurrentUser();


                            Toast.makeText(MainActivity.this, "User Signed In:"+str_gmailid, Toast.LENGTH_SHORT).show();

                           // AsyncTask_Googleloginverify();


                            /*try {
                                postRequest();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }*/

                        } else
                        {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }






    protected void setGooglePlusButtonText(SignInButton signInButton, String buttonText)
    {
        // Find the TextView that is inside of the SignInButton and set its text
        for (int i = 0; i < signInButton.getChildCount(); i++) {
            View v = signInButton.getChildAt(i);

            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setText(buttonText);
                // tv.setBackgroundColor(Color.CYAN);
                tv.setBackgroundDrawable(
                        new ColorDrawable(Color.parseColor(COLOR)));
                tv.setTextColor(Color.WHITE);
                tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/laouibold.ttf"));
                return;
            }
        }
    }

    private void signOut_InvalidUser()
    {
        googlesigninclient_obj.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Toast.makeText(MainActivity.this,"Sigined Out: InValid User", Toast.LENGTH_SHORT).show();
                    }
                });
    }





    private void signOut() {
        googlesigninclient_obj.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Toast.makeText(MainActivity.this,"Sigined Out Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
    }


//Google Login


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
        progressDialog.show();


        Class_loginrequest request = new Class_loginrequest();
        request.setUsername(username_et.getText().toString());
        request.setPassword(password_et.getText().toString());


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

                Class_normalloginresponse user_object;
                user_object = (Class_normalloginresponse) response.body();

                if (response.isSuccessful())
                {
                    // "Message": "Success",
                    if (user_object.getMessage().equalsIgnoreCase("Success"))
                    {

                        Toast.makeText(MainActivity.this, user_object.getLst().get(0).getUser_Id().toString(), Toast.LENGTH_SHORT).show();

                    /*"User_Id": "76",
                        "Username": "9742392362",
                        "Password": "21232f297a57a5a743894a0e4a801fc3",
                        "User_Type": "7",*/

                    String str_userid = user_object.getLst().get(0).getUser_Id().toString();
                    String str_username = user_object.getLst().get(0).getUsername().toString();
                    String str_userpassword = user_object.getLst().get(0).getPassword().toString();
                    String str_usertype = user_object.getLst().get(0).getUser_Type().toString();

                    Log.e("userID", str_userid);
                    Log.e("username", str_username);

                    editor_obj = sharedpreference_usercredential_Obj.edit();
                    editor_obj.putString(KeyValue_userid, str_userid);
                    editor_obj.putString(KeyValue_username, str_username);
                    editor_obj.commit();


                    progressDialog.dismiss();

                    Toast.makeText(MainActivity.this, "successfully", Toast.LENGTH_SHORT).show();

                    if (str_isuser_setpin.isEmpty()) {

                        //  Toast.makeText(getApplicationContext(),"empty",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, Activity_setpin.class);
                        startActivity(i);
                        finish();

                    } else {
                        Intent i = new Intent(MainActivity.this, Activity_pinlogin.class);
                        startActivity(i);
                        finish();
                    }

                }
                    else{




                        Toast.makeText(MainActivity.this, "Invaild Crendential", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();


                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setCancelable(false);
                        dialog.setTitle("Alert");
                        dialog.setMessage(" Invaild Crendential \n Try once again");

                        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id)
                            {
                               dialog.dismiss();
                            }
                        });


                        final AlertDialog alert = dialog.create();
                        alert.setOnShowListener(new DialogInterface.OnShowListener() {
                            @Override
                            public void onShow(DialogInterface arg0) {
                                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#004D40"));
                            }
                        });
                        alert.show();




                    }

                } else {


                    DefaultResponse error = ErrorUtils.parseError(response);
                    // … and use it to show error information

                    // … or just log the issue like we’re doing :)
                    Log.d("responseerror", error.getMsg());

                    Toast.makeText(MainActivity.this, "Invaild Crendential", Toast.LENGTH_SHORT).show();
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







    public void AsyncTask_Post_ForgetPassword()
    {

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.setTitle("Please wait....");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();



        Class_forgotrequest request = new Class_forgotrequest();
        request.setUsername(str_forgotusername);



        Interface_userservice userService;
        userService = Class_ApiUtils.getUserService();
        Call<Class_normalloginresponse> call = userService.Post_ForgetPassword(request);

        Log.e("TAG", "req : " + new Gson().toJson(request));

        call.enqueue(new Callback<Class_normalloginresponse>() {
            @Override
            public void onResponse(Call<Class_normalloginresponse> call, Response<Class_normalloginresponse> response)
            {
                Log.e("response", response.toString());

                Log.e("TAG", "Res: " + new Gson().toJson(response));
                Log.e("tag","Response body"+ String.valueOf(response.body()));
                //   DefaultResponse error1 = ErrorUtils.parseError(response);
               /* Log.e("response new:",error1.getMsg());
                Log.e("response new status:", String.valueOf(error1.getstatus()));*/

                Class_normalloginresponse user_object;
                user_object = (Class_normalloginresponse) response.body();

                if (response.isSuccessful())
                {



                    progressDialog.dismiss();

                    Toast.makeText(MainActivity.this, "Password is sent to your Mail", Toast.LENGTH_SHORT).show();

                } else {


                    DefaultResponse error = ErrorUtils.parseError(response);
                    // … and use it to show error information

                    // … or just log the issue like we’re doing :)
                    Log.d("responseerror", error.getMsg());

                    Toast.makeText(MainActivity.this, "unauthorized Email ID", Toast.LENGTH_SHORT).show();
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