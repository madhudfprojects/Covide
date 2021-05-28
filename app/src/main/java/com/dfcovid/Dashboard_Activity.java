package com.dfcovid;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dfcovid.model.Class_DashboardHospitalData;
import com.dfcovid.model.Class_DashboardHospitalData_List;
import com.dfcovid.model.Class_GetUserHospitalList;
import com.dfcovid.model.Class_Get_UserHospitalListResponse;
import com.dfcovid.model.Class_getdemo_Response;
import com.dfcovid.model.Class_getdemo_resplist;
import com.dfcovid.model.Class_gethelp_Response;
import com.dfcovid.model.Class_gethelp_resplist;
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

public class Dashboard_Activity extends AppCompatActivity {

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
    String str_edt_fromdate_display="",str_edt_fromdate_sendTOAPI="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        internetDectector = new Class_InternetDectector(getApplicationContext());
        isInternetPresent = internetDectector.isConnectingToInternet();
        userService1 = Class_ApiUtils.getUserService();
        hospital_list_SP=(Spinner)findViewById(R.id.hospital_list_SP);
        date_time_TV=(TextView)findViewById(R.id.date_time_TV);
        Add_bt=(Button)findViewById(R.id.Add_bt);
        edt_fromdate=(TextView) findViewById(R.id.edt_fromdate);
        fromdateseterror_TV=(EditText) findViewById(R.id.fromdateseterror_TV);
        lv_summary = (ListView) findViewById(R.id.lv_summary);

        sharedpreference_usercredential_Obj=getSharedPreferences(sharedpreference_usercredential, Context.MODE_PRIVATE);
        str_userID= sharedpreference_usercredential_Obj.getString(KeyValue_userid, "").trim();
        str_username= sharedpreference_usercredential_Obj.getString(KeyValue_username, "").trim();

        Log.e("tAG","str_userID="+str_userID+"str_username="+str_username);

        edt_fromdate.setEnabled(true);
        edt_fromdate.setFocusable(true);
        dashboard_list =new ArrayList<Class_DashboardHospitalData_List>();

        dashboardHospitalListViewAdapter = new DashboardHospitalListViewAdapter(Dashboard_Activity.this, dashboard_list);

        if(isInternetPresent){
            GetUserHospitalList();
        }else{
            Toast.makeText(Dashboard_Activity.this,"No Internet", Toast.LENGTH_SHORT).show();
        }

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        edt_fromdate.setText(formattedDate);

//        edt_fromdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar c = Calendar.getInstance();
//
//                mYear = c.get(Calendar.YEAR);
//                mMonth = c.get(Calendar.MONTH);
//                mDay = c.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getApplication(), R.style.DatePickerTheme,
//                        new DatePickerDialog.OnDateSetListener() {
//
//                            @Override
//                            public void onDateSet(DatePicker view, int year,
//                                                  int monthOfYear, int dayOfMonth) {
//
//                                cDay = dayOfMonth;
//                                cMonth = monthOfYear;
//                                cYear = year;
//
//                                // String date =dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
//                                //  String date =year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
//                                String date =dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
//
//
//                                SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
//                                // SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
//
//
//                                try {
//                                    Date d=dateFormat.parse(date);
//                                    System.out.println("Formated from"+dateFormat.format(d));
//                                    fromdateseterror_TV.setVisibility(View.GONE);
//                                    edt_fromdate.setText(dateFormat.format(d).toString());
//
//                                }
//                                catch(Exception e) {
//                                    //java.text.ParseException: Unparseable date: Geting error
//                                    System.out.println("Excep"+e);
//                                }
//                                //TextView txtExactDate = (TextView) findViewById(R.id.txt_exactDate);
//
//
//                                //txtDate.edita
//                            }
//                        }, mYear, mMonth, mDay);
//
//                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
//                //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-(1000 * 60 * 60 * 24 * 365 * 14));
//                // - (1000 * 60 * 60 * 24 * 365.25 * 14)
////------
//
//                datePickerDialog.show();
//                //  originalList.clear();
//                hospital_list_SP.setSelection(0);
//                //adapter.notifyDataSetChanged();
//            }
//        });

        //added by shivaleela
        edt_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                final DatePickerDialog datePickerDialog_receiveddate = new DatePickerDialog(Dashboard_Activity.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Calendar cal = new GregorianCalendar(i, i1, i2);


                        DatePickerDialog dialog = new DatePickerDialog(Dashboard_Activity.this, this, i, i1, i2);
                        dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                        fromdateseterror_TV.setVisibility(View.GONE);


                        setReceivedstartDate(cal);

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog_receiveddate.getDatePicker().setMaxDate(System.currentTimeMillis());

                datePickerDialog_receiveddate.show();
                hospital_list_SP.setSelection(0);
            }
        });
        /////////////////////////////////////////

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
                Intent i = new Intent(Dashboard_Activity.this,MainActivity2.class);
                i.putExtra("hospitalId",str_hospitelId);
                i.putExtra("hospital",str_SelectedHospitalName);
                startActivity(i);
            }
        });

        gethelp();
    }


    public void setReceivedstartDate (Calendar calendar){
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        edt_fromdate.setText(dateFormat.format(calendar.getTime()));
        str_edt_fromdate_display = dateFormat.format(calendar.getTime());
        Log.e("stredtfromdatedisplay.", dateFormat.format(calendar.getTime()));
        edt_fromdate.setText(str_edt_fromdate_display);

        SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd");
        //SimpleDateFormat mdyFormat = new SimpleDateFormat("dd-MM-yyyy");

        str_edt_fromdate_sendTOAPI = mdyFormat.format(calendar.getTime());
        Log.e("str_edate_sendTOAPI..", str_edt_fromdate_sendTOAPI);
        Calendar c = Calendar.getInstance();
        DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Log.e("outputFormat..", String.valueOf(outputFormat));
        Get_LoadHospitalDashboard();


    }
    public void GetUserHospitalList() {
        Call<Class_Get_UserHospitalListResponse> call = userService1.GetUserHospitalList(str_userID);

        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(Dashboard_Activity.this);
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
                        ArrayAdapter<Class_GetUserHospitalList> dataAdapter_edu = new ArrayAdapter<Class_GetUserHospitalList>(Dashboard_Activity.this, R.layout.support_simple_spinner_dropdown_item, arrayObj_class_studentpaymentresp);
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
                        Toast.makeText(Dashboard_Activity.this, "Kindly restart your application", Toast.LENGTH_SHORT).show();

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
        Call<Class_DashboardHospitalData> call = userService1.Get_LoadHospitalDataDate(str_hospitelId,str_edt_fromdate_sendTOAPI);

        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(Dashboard_Activity.this);
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

                        dashboardHospitalListViewAdapter = new DashboardHospitalListViewAdapter(Dashboard_Activity.this, dashboard_list);
                        lv_summary.setAdapter(dashboardHospitalListViewAdapter);

                    } else {
                        progressDoalog.dismiss();
                                           }
                } else {
                    progressDoalog.dismiss();
                    dashboard_list.clear();
                    dashboardHospitalListViewAdapter.notifyDataSetChanged();
                    Toast.makeText(Dashboard_Activity.this, "Hospital Data Not Found", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(Dashboard_Activity.this, "Kindly restart your application", Toast.LENGTH_SHORT).show();

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

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home_menu, menu);
        getMenuInflater().inflate(R.menu.logout_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id==R.id.changepin)
        {


            AlertDialog.Builder dialog = new AlertDialog.Builder(Dashboard_Activity.this);
            dialog.setCancelable(false);
            dialog.setTitle(R.string.alert);
            dialog.setMessage("Are you sure you want to Change PIN?");

            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id)
                {

                  /* editor_obj = sharedpreference_usercredential_Obj.edit();
                    editor_obj.putString(KeyValue_isuser_setpin, "");
                    editor_obj.commit();

                    editor_obj = sharedpreference_usercredential_Obj.edit();
                    editor_obj.putString(KeyValue_isuser_changepin, "yes");
                    editor_obj.commit();*/

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
        }else if(id==R.id.aboutus){
            Intent i = new Intent(getApplicationContext(), ContactUs_Activity.class);
            startActivity(i);
            finish();

        }




        return super.onOptionsItemSelected(item);
    }




//added by shivaleela\
public void gethelp() {
    internetDectector = new Class_InternetDectector(getApplicationContext());
    isInternetPresent = internetDectector.isConnectingToInternet();

    if (isInternetPresent) {
        gethelp_api();
        //getdemo();
    }
}

    private void gethelp_api() {
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(Dashboard_Activity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.setTitle("Please wait....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();

        Interface_userservice userService;
        userService = Class_ApiUtils.getUserService();

        Call<Class_gethelp_Response> call = userService.GetHelp(str_userID);


        call.enqueue(new Callback<Class_gethelp_Response>() {
            @Override
            public void onResponse(Call<Class_gethelp_Response> call, Response<Class_gethelp_Response> response) {

                // Toast.makeText(MainActivity.this, ""+response.toString(), Toast.LENGTH_SHORT).show();

                Log.e("response_gethelp", "response_gethelp: " + new Gson().toJson(response));

               /* Class_gethelp_Response gethelp_response_obj = new Class_gethelp_Response();
                gethelp_response_obj = (Class_gethelp_Response) response.body();*/


                if (response.isSuccessful()) {
                    DBCreate_Helpdetails();
                    Class_gethelp_Response gethelp_response_obj = response.body();
                    Log.e("response.body", response.body().getLst().toString());


                    if (gethelp_response_obj.getStatus().equals(true)) {

                        List<Class_gethelp_resplist> helplist = response.body().getLst();
                        Log.e("length", String.valueOf(helplist.size()));
                        int int_helpcount = helplist.size();

                        for (int i = 0; i < int_helpcount; i++) {
                            Log.e("title", helplist.get(i).getTitle().toString());

                            String str_title = helplist.get(i).getTitle().toString();
                            String str_content = helplist.get(i).getContent().toString();
                            DBCreate_HelpDetails_insert_2sqliteDB(str_title, str_content);
                            Log.e("str_content", helplist.get(i).getContent().toString());

                        }


                        // Data_from_HelpDetails_table();

                        //helplist.get(0).
                        progressDoalog.dismiss();

                        getdemo();
                    }
                    // Log.e("response.body", response.body().size);

                }


            }

            @Override
            public void onFailure(Call call, Throwable t) {
                progressDoalog.dismiss();
                Log.e("WS", "error" + t.getMessage());
                Toast.makeText(Dashboard_Activity.this, "WS:" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void getdemo() {
        internetDectector = new Class_InternetDectector(getApplicationContext());
        isInternetPresent = internetDectector.isConnectingToInternet();

        if (isInternetPresent) {
            getdemo_api();
        }
    }

    private void getdemo_api() {
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(Dashboard_Activity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.setTitle("Please wait....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();

        Interface_userservice userService;
        userService = Class_ApiUtils.getUserService();

        Call<Class_getdemo_Response> call = userService.GetDemo(str_userID);//str_userid


        call.enqueue(new Callback<Class_getdemo_Response>() {
            @Override
            public void onResponse(Call<Class_getdemo_Response> call, Response<Class_getdemo_Response> response) {
                Log.e("response_gethelp", "response_gethelp: " + new Gson().toJson(response));

               /* Class_gethelp_Response gethelp_response_obj = new Class_gethelp_Response();
                gethelp_response_obj = (Class_gethelp_Response) response.body();*/


                if (response.isSuccessful()) {
                    DBCreate_Demodetails();
                    Class_getdemo_Response getdemo_response_obj = response.body();
                    Log.e("response.body", response.body().getLst().toString());


                    if (getdemo_response_obj.getStatus().equals(true)) {

                        List<Class_getdemo_resplist> demolist = response.body().getLst();
                        Log.e("length", String.valueOf(demolist.size()));
                        int int_helpcount = demolist.size();

                        for (int i = 0; i < int_helpcount; i++) {
                            Log.e("language", demolist.get(i).getLanguage_Name().toString());

                            String str_languagename = demolist.get(i).getLanguage_Name().toString();
                            String str_languagelink = demolist.get(i).getLanguage_Link().toString();
                            DBCreate_DemoDetails_insert_2sqliteDB(str_languagename, str_languagelink);
                        }

                        //Data_from_HelpDetails_table();

                        //helplist.get(0).
                        progressDoalog.dismiss();
                    }
                    // Log.e("response.body", response.body().size);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                progressDoalog.dismiss();
                Log.e("WS", "error" + t.getMessage());
                Toast.makeText(Dashboard_Activity.this, "WS:" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void DBCreate_Helpdetails() {

        SQLiteDatabase db2 = this.openOrCreateDatabase("DFCOVID_DB", Context.MODE_PRIVATE, null);
        db2.execSQL("CREATE TABLE IF NOT EXISTS HelpDetails_table(SlNo INTEGER PRIMARY KEY AUTOINCREMENT,TitleDB VARCHAR,ContentDB VARCHAR);");
        Cursor cursor = db2.rawQuery("SELECT * FROM HelpDetails_table", null);
        int x = cursor.getCount();
        if (x > 0) {
            db2.delete("HelpDetails_table", null, null);
        }
        db2.close();
    }

    public void DBCreate_HelpDetails_insert_2sqliteDB(String title, String content) {
        SQLiteDatabase db2 = this.openOrCreateDatabase("DFCOVID_DB", Context.MODE_PRIVATE, null);
        db2.execSQL("CREATE TABLE IF NOT EXISTS HelpDetails_table(SlNo INTEGER PRIMARY KEY AUTOINCREMENT,TitleDB VARCHAR,ContentDB VARCHAR);");

        ContentValues cv = new ContentValues();
        cv.put("TitleDB", title);
        cv.put("ContentDB", content);
        db2.insert("HelpDetails_table", null, cv);
        db2.close();

        Log.e("insert", "DBCreate_HelpDetails_insert_2sqliteDB");

    }

    public void DBCreate_Demodetails() {

        SQLiteDatabase db2 = this.openOrCreateDatabase("DFCOVID_DB", Context.MODE_PRIVATE, null);
        db2.execSQL("CREATE TABLE IF NOT EXISTS DemoDetails_table(SlNo INTEGER PRIMARY KEY AUTOINCREMENT,LanguageDB VARCHAR,LinkDB VARCHAR);");
        Cursor cursor = db2.rawQuery("SELECT * FROM DemoDetails_table", null);
        int x = cursor.getCount();
        if (x > 0) {
            db2.delete("DemoDetails_table", null, null);
        }
        db2.close();
    }

    public void DBCreate_DemoDetails_insert_2sqliteDB(String str_languagename, String str_languagelink) {
        SQLiteDatabase db2 = this.openOrCreateDatabase("DFCOVID_DB", Context.MODE_PRIVATE, null);
        db2.execSQL("CREATE TABLE IF NOT EXISTS DemoDetails_table(SlNo INTEGER PRIMARY KEY AUTOINCREMENT,LanguageDB VARCHAR,LinkDB VARCHAR);");

        ContentValues cv = new ContentValues();
        cv.put("LanguageDB", str_languagename);
        cv.put("LinkDB", str_languagelink);
        db2.insert("DemoDetails_table", null, cv);
        db2.close();

        Log.e("insert", "DBCreate_DemoDetails_insert_2sqliteDB");

    }








}