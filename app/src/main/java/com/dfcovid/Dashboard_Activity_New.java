package com.dfcovid;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dfcovid.model.Class_DashboardHospitalData;
import com.dfcovid.model.Class_DashboardHospitalData_List;
import com.dfcovid.model.Class_GetUserHospitalList;
import com.dfcovid.model.Class_Get_UserHospitalListResponse;
import com.dfcovid.remote.Class_ApiUtils;
import com.dfcovid.remote.Interface_userservice;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard_Activity_New extends AppCompatActivity {

    Boolean isInternetPresent = false;
    Class_InternetDectector internetDectector;
    Interface_userservice userService1;

    Class_GetUserHospitalList[] arrayObj_class_studentpaymentresp;
    Spinner hospital_list_SP;
    TextView date_time_TV;
    Class_GetUserHospitalList class_getUserHospitalList;
    String Date_time;
    Button Add_bt;
    EditText fromdateseterror_TV;
    TextView edt_fromdate;
    String FromDate,ToDate;
    private int mYear, mMonth, mDay;
    private int cYear, cMonth, cDay;
    Class_DashboardHospitalData_List[] array_class_dashboardHospitalData_lists;



    DashboardHospitalListViewAdapter dashboardHospitalListViewAdapter;
    private ArrayList<Class_DashboardHospitalData_List> dashboard_list;
    private ArrayList<Class_DashboardHospitalData_List> dashboarddata_list = null;

    public static final String sharedpreference_usercredential = "sharedpreferencebook_usercredential";
    public static final String KeyValue_userid = "KeyValue_userid";
    public static final String KeyValue_username = "KeyValue_username";

    SharedPreferences sharedpreference_usercredential_Obj;
    SharedPreferences.Editor editor_obj;
    String str_userID,str_username,str_loginpin,str_hospitelId="",str_SelectedHospitalName="";

    ListView lv_summary;
    String str_versioncode;
    LinearLayout helplinecenter_LL,googlemaps_LL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_new);

        internetDectector = new Class_InternetDectector(getApplicationContext());
        isInternetPresent = internetDectector.isConnectingToInternet();
        userService1 = Class_ApiUtils.getUserService();
        hospital_list_SP=(Spinner)findViewById(R.id.hospital_list_SP);
        date_time_TV=(TextView)findViewById(R.id.date_time_TV);
        Add_bt=(Button)findViewById(R.id.Add_bt);
        edt_fromdate=(TextView) findViewById(R.id.edt_fromdate);
        fromdateseterror_TV=(EditText) findViewById(R.id.fromdateseterror_TV);
        lv_summary = (ListView) findViewById(R.id.lv_summary);

        helplinecenter_LL=(LinearLayout)findViewById(R.id.helplinecenter_LL);
        googlemaps_LL=(LinearLayout)findViewById(R.id.googlemaps_LL);

        Add_bt.setVisibility(View.GONE);
        sharedpreference_usercredential_Obj=getSharedPreferences(sharedpreference_usercredential, Context.MODE_PRIVATE);
        str_userID= sharedpreference_usercredential_Obj.getString(KeyValue_userid, "").trim();
        str_username= sharedpreference_usercredential_Obj.getString(KeyValue_username, "").trim();

        Log.e("tAG","str_userID="+str_userID+"str_username="+str_username);

        edt_fromdate.setEnabled(false);
        edt_fromdate.setFocusable(false);
        dashboard_list =new ArrayList<Class_DashboardHospitalData_List>();


        try {
            str_versioncode = String.valueOf(getPackageManager().getPackageInfo(getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        dashboardHospitalListViewAdapter = new DashboardHospitalListViewAdapter(Dashboard_Activity_New.this, dashboard_list);

        if(isInternetPresent)
        {
            Get_App_Version();
            GetUserHospitalList();
        }else{
            Toast.makeText(Dashboard_Activity_New.this,"No Internet", Toast.LENGTH_SHORT).show();
        }

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        edt_fromdate.setText(formattedDate);

        edt_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getApplication(), R.style.DatePickerTheme,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                cDay = dayOfMonth;
                                cMonth = monthOfYear;
                                cYear = year;

                                // String date =dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                //  String date =year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                String date =dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
//                                 str_edt_fromdate_sendTOAPI =year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
//                                 Log.e("str_edate_sendTOAPI..", str_edt_fromdate_sendTOAPI);
                                SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
                                // SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");


                                try {
                                    Date d=dateFormat.parse(date);
                                    System.out.println("Formated from"+dateFormat.format(d));
                                    fromdateseterror_TV.setVisibility(View.GONE);
                                    edt_fromdate.setText(dateFormat.format(d).toString());

                                }
                                catch(Exception e) {
                                    //java.text.ParseException: Unparseable date: Geting error
                                    System.out.println("Excep"+e);
                                }
                                //TextView txtExactDate = (TextView) findViewById(R.id.txt_exactDate);


                                //txtDate.edita
                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-(1000 * 60 * 60 * 24 * 365 * 14));
                // - (1000 * 60 * 60 * 24 * 365.25 * 14)
//------

                datePickerDialog.show();
                //  originalList.clear();
                hospital_list_SP.setSelection(0);
                //adapter.notifyDataSetChanged();
            }
        });
        hospital_list_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected (AdapterView< ? > parent, View view,
                                        int position, long id){
                // TODO Auto-generated method stub
                //   obj_Class_Project_FundMain = (Class_Project_FundMain) spin_ticketStatus.getSelectedItem();

                class_getUserHospitalList = (Class_GetUserHospitalList) hospital_list_SP.getSelectedItem();

                if(class_getUserHospitalList.getEntryDate()==null||class_getUserHospitalList.getEntryDate().equalsIgnoreCase("")) {
                    date_time_TV.setText("");
                }else{
                    Date_time = class_getUserHospitalList.getEntryDate().toString();
                    Log.e("tag","Date_time="+Date_time);
                    date_time_TV.setText(Date_time);
                }
                str_hospitelId = class_getUserHospitalList.getHospitalId().toString();
                str_SelectedHospitalName = class_getUserHospitalList.getHospitalName().toString();

                Get_LoadHospitalDashboard();
                // Toast.makeText(getApplicationContext(),"str_Programsid: "+str_programid,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected (AdapterView< ? > parent){
                // TODO Auto-generated method stub
            }
        });

        Add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard_Activity_New.this, MainActivity2.class);
                i.putExtra("hospitalId",str_hospitelId);
                i.putExtra("hospital",str_SelectedHospitalName);
                startActivity(i);
                finish();
            }
        });




        //added by shivaleela
        helplinecenter_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Dashboard_Activity_New.this, Activity_HelpLineCenter.class);
                i.putExtra("flag","0");
                startActivity(i);
                finish();
            }
        });

        googlemaps_LL.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Dashboard_Activity_New.this, Activity_GoogleMaps.class);
                i.putExtra("flag","0");
                startActivity(i);
                finish();

            }
        });
    }



    public void GetUserHospitalList() {
        Call<Class_Get_UserHospitalListResponse> call = userService1.GetUserHospitalList("0");

        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(Dashboard_Activity_New.this);
        //  progressDoalog.setMax(100);
        //  progressDoalog.setMessage("Loading....");
        progressDoalog.setTitle("Please wait....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();

        call.enqueue(new Callback<Class_Get_UserHospitalListResponse>() {
            @Override
            public void onResponse(Call<Class_Get_UserHospitalListResponse> call, Response<Class_Get_UserHospitalListResponse> response) {
                Log.e("Entered resp", "Class_Get_UserHospitalListResponse");

                if (response.isSuccessful()) {
                    progressDoalog.dismiss();
                    Class_Get_UserHospitalListResponse class_loginresponse = response.body();
                    if (class_loginresponse.getStatus()) {

                        List<Class_GetUserHospitalList> monthContents_list = response.body().getLst();
                        Class_GetUserHospitalList[] arrayObj_Class_monthcontents = new Class_GetUserHospitalList[monthContents_list.size()];
                        arrayObj_class_studentpaymentresp = new Class_GetUserHospitalList[arrayObj_Class_monthcontents.length];
                        for (int i = 0; i < arrayObj_Class_monthcontents.length; i++) {
                            Log.e("GetUserHospitalList", String.valueOf(class_loginresponse.getLst().get(i).getHospitalId()));

                            Class_GetUserHospitalList innerObj_Class_academic = new Class_GetUserHospitalList();
                            innerObj_Class_academic.setHospitalId(class_loginresponse.getLst().get(i).getHospitalId());
                            innerObj_Class_academic.setHospitalName(class_loginresponse.getLst().get(i).getHospitalName());
                            innerObj_Class_academic.setEntryDate(class_loginresponse.getLst().get(i).getEntryDate());

                            arrayObj_class_studentpaymentresp[i] = innerObj_Class_academic;

                        }//for loop end
                        ArrayAdapter<Class_GetUserHospitalList> dataAdapter_edu = new ArrayAdapter<Class_GetUserHospitalList>(Dashboard_Activity_New.this, R.layout.support_simple_spinner_dropdown_item, arrayObj_class_studentpaymentresp);
//                        dataAdapter_edu.setDropDownViewResource(R.layout.spinnercenterstyle);
                        hospital_list_SP.setAdapter(dataAdapter_edu);
                        //LoadHospitalData();

                    } else {
                        progressDoalog.dismiss();
                    }
                } else {
                    progressDoalog.dismiss();
                    Log.e("Entered resp else", "");
                    DefaultResponse error = ErrorUtils.parseError(response);
                    // … and use it to show error information

                    // … or just log the issue like we’re doing :)
                    //   Log.e("error message", error.getMsg());

                    if (error.getMsg() != null) {

                        Log.e("error message", error.getMsg());
//                        str_getmonthsummary_errormsg = error.getMsg();
//                        alerts_dialog_getexlistviewError();

                        //Toast.makeText(getActivity(), error.getMsg(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Dashboard_Activity_New.this, "Kindly restart your application", Toast.LENGTH_SHORT).show();

                    }

                }
            }



            @Override
            public void onFailure(Call call, Throwable t) {
                progressDoalog.dismiss();
//                str_getmonthsummary_errormsg = t.getMessage();
//                alerts_dialog_getexlistviewError();

                // Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });// end of call

    }

    public void Get_LoadHospitalDashboard() {
        Call<Class_DashboardHospitalData> call = userService1.Get_LoadHospitalDashboard(str_hospitelId);

        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(Dashboard_Activity_New.this);
        //  progressDoalog.setMax(100);
        //  progressDoalog.setMessage("Loading....");
        progressDoalog.setTitle("Please wait....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();

        call.enqueue(new Callback<Class_DashboardHospitalData>() {
            @Override
            public void onResponse(Call<Class_DashboardHospitalData> call, Response<Class_DashboardHospitalData> response) {
                Log.e("Entered resp", "Class_DashboardHospitalData");

                if (response.isSuccessful()) {
                    progressDoalog.dismiss();
                    Class_DashboardHospitalData class_loginresponse = response.body();
                    if (class_loginresponse.getStatus()) {

                        List<Class_DashboardHospitalData_List> monthContents_list = response.body().getLst();
                        Class_GetUserHospitalList[] arrayObj_Class_monthcontents = new Class_GetUserHospitalList[monthContents_list.size()];
                        array_class_dashboardHospitalData_lists = new Class_DashboardHospitalData_List[arrayObj_Class_monthcontents.length];
                        dashboard_list.clear();
                        for (int i = 0; i < arrayObj_Class_monthcontents.length; i++) {
                            Log.e("tag","Class_DashboardHospitalData=="+ String.valueOf(class_loginresponse.getLst().get(i).getBedType()));

                            Class_DashboardHospitalData_List innerObj_Class_academic = new Class_DashboardHospitalData_List();
                            innerObj_Class_academic.setAvailable(class_loginresponse.getLst().get(i).getAvailable());
                            innerObj_Class_academic.setBedType(class_loginresponse.getLst().get(i).getBedType());
                            innerObj_Class_academic.setBedTypeId(class_loginresponse.getLst().get(i).getBedTypeId());
                            innerObj_Class_academic.setOccupied(class_loginresponse.getLst().get(i).getOccupied());
                            innerObj_Class_academic.setTotal(class_loginresponse.getLst().get(i).getTotal());

                            array_class_dashboardHospitalData_lists[i] = innerObj_Class_academic;

                            Class_DashboardHospitalData_List item1 = null;
                            item1 = new Class_DashboardHospitalData_List( array_class_dashboardHospitalData_lists[i].getBedTypeId(),  array_class_dashboardHospitalData_lists[i].getBedType(),  array_class_dashboardHospitalData_lists[i].getTotal(), array_class_dashboardHospitalData_lists[i].getOccupied(),  array_class_dashboardHospitalData_lists[i].getAvailable());

                            dashboard_list.add(item1);
                        }//for loop end

                        dashboardHospitalListViewAdapter = new DashboardHospitalListViewAdapter(Dashboard_Activity_New.this, dashboard_list);
                        lv_summary.setAdapter(dashboardHospitalListViewAdapter);

                    } else {
                        progressDoalog.dismiss();
                                           }
                } else {
                    progressDoalog.dismiss();
                    dashboard_list.clear();
                    dashboardHospitalListViewAdapter.notifyDataSetChanged();
                    Toast.makeText(Dashboard_Activity_New.this, "Hospital Data Not Found", Toast.LENGTH_SHORT).show();

                    Log.e("Entered resp else", "");
                    DefaultResponse error = ErrorUtils.parseError(response);
                    // … and use it to show error information

                    // … or just log the issue like we’re doing :)
                    //   Log.e("error message", error.getMsg());

                    if (error.getMsg() != null) {

                        Log.e("error message", error.getMsg());
//                        str_getmonthsummary_errormsg = error.getMsg();
//                        alerts_dialog_getexlistviewError();

                        //Toast.makeText(getActivity(), error.getMsg(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Dashboard_Activity_New.this, "Kindly restart your application", Toast.LENGTH_SHORT).show();

                    }

                }
            }



            @Override
            public void onFailure(Call call, Throwable t) {
                progressDoalog.dismiss();
//                str_getmonthsummary_errormsg = t.getMessage();
//                alerts_dialog_getexlistviewError();

                // Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });// end of call

    }




    private void Get_App_Version()
    {



        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(Dashboard_Activity_New.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.setTitle("Please wait....");
        progressDoalog.setCancelable(false);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        retrofit2.Call call = userService1.Get_App_Version();
        // show it
        progressDoalog.show();
        call.enqueue(new Callback<Class_GetAppVersion>()
        {
            @Override
            public void onResponse(Call<Class_GetAppVersion> call, Response<Class_GetAppVersion> response)
            {

                Log.e("VerResponse", new Gson().toJson(response) );


                if(response.isSuccessful())
                {

                    Class_GetAppVersion  class_getappversion = response.body();
                    Log.e("Response",class_getappversion.toString());

                    if(class_getappversion.getStatus())
                    {

                        List<Class_GetAppVersionList> getAppVersionList=response.body().getlistVersion();
                        String str_releaseVer=getAppVersionList.get(0).getAppVersion();

                        int int_versioncode= Integer.parseInt(str_versioncode);
                        int int_releaseVer= Integer.parseInt(str_releaseVer);

                        Log.e("str_releaseVer",str_releaseVer);
                        progressDoalog.dismiss();

                        if(int_releaseVer>int_versioncode)
                        {
                            alerts_dialog_playstoreupdate();
                        }
                        else{

                        }

                    }else{
                        progressDoalog.dismiss();
                        Toast.makeText(Dashboard_Activity_New.this, class_getappversion.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    progressDoalog.dismiss();

                    DefaultResponse error = ErrorUtils.parseError(response);
                    // … and use it to show error information
                    // … or just log the issue like we’re doing :)
                    Log.d("error message", error.getMsg());

                    Toast.makeText(Dashboard_Activity_New.this, error.getMsg(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call call, Throwable t)
            {
                progressDoalog.dismiss();
                Log.e("TAG", "onFailure: "+t.toString() );

                Log.e("tag","Error:"+t.getMessage());
                Toast.makeText(Dashboard_Activity_New.this, "WS:ErrorVercheck:"+t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });


    }






    public  void alerts_dialog_playstoreupdate()
    {

        AlertDialog.Builder dialog = new AlertDialog.Builder(Dashboard_Activity_New.this);
        dialog.setCancelable(false);
        dialog.setTitle(R.string.alert);
        dialog.setMessage("Kindly update from playstore");

        dialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
               /* Intent	intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.dfcovid"));
                startActivity(intent);*/
            }
        });


        final AlertDialog alert = dialog.create();
        alert.setOnShowListener( new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#004D40"));
            }
        });
        alert.show();

    }




















    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        /*MenuItem action_editProfile = menu.findItem(R.id.action_editProfile);
        action_editProfile.setVisible(false);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_feesCollect){
            Intent ittFeesPaidToHome = new Intent(Dashboard_Activity_New.this , MainActivity.class);
            startActivity(ittFeesPaidToHome);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}