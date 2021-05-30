package com.dfcovid;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.MenuItemCompat;

import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dfcovid.Constants.Database;
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
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.dfcovid.NotificationList_Activity.count_ofnotifications;

public class Dashboard_Activity extends AppCompatActivity {

    Boolean isInternetPresent = false;
    Class_InternetDectector internetDectector;
    Interface_userservice userService1;

    private final int REQUEST_READ_PHONE_STATE = 1;

    Class_GetUserHospitalList[] arrayObj_class_studentpaymentresp;
    Spinner hospital_list_SP;
    TextView date_time_TV;
    Class_GetUserHospitalList class_getUserHospitalList;
    String Date_time;
    Button Add_bt;
    EditText fromdateseterror_TV;
    TextView edt_fromdate;
    String FromDate, ToDate;
    private int mYear, mMonth, mDay;
    private int cYear, cMonth, cDay;
    Class_DashboardHospitalData_List[] array_class_dashboardHospitalData_lists;

    TextView textCartItemCount;
    int mCartItemCount = 0;
   // public static int count_ofnotifications;
    MenuItem menuItem;

    DashboardHospitalListViewAdapter dashboardHospitalListViewAdapter;
    private ArrayList<Class_DashboardHospitalData_List> dashboard_list;
    private ArrayList<Class_DashboardHospitalData_List> dashboarddata_list = null;

    public static final String sharedpreference_usercredential = "sharedpreferencebook_usercredential";
    public static final String KeyValue_userid = "KeyValue_userid";
    public static final String KeyValue_username = "KeyValue_username";

    SharedPreferences sharedpreference_usercredential_Obj;
    SharedPreferences.Editor editor_obj;
    String str_userID, str_username, str_loginpin, str_hospitelId = "", str_SelectedHospitalName = "", str_versioncode = "";

    ListView lv_summary;
    String str_edt_fromdate_display = "", str_edt_fromdate_sendTOAPI = "";

    TelephonyManager tm1 = null;
   /* String myVersion, deviceBRAND, deviceHARDWARE, devicePRODUCT, deviceUSER, deviceModelName, deviceId, tmDevice, tmSerial, androidId, simOperatorName, sdkver, mobileNumber;
    int sdkVersion, Measuredwidth = 0, Measuredheight = 0, update_flage = 0;
    AsyncTask<Void, Void, Void> mRegisterTask;
    String regId = "dfagriXZ", str_userid;
    private String versioncode;*/

    String str_login_username = "", str_AUTH_token = "", str_login_empid = "", simOperatorName = "", tmDevice = "", mobileNumber = "", tmSerial = "", androidId = "",
            deviceId = "", deviceModelName = "", deviceUSER = "", devicePRODUCT = "",
            deviceHARDWARE = "", deviceBRAND = "", myVersion = "", sdkver = "", regId = "", str_loginEmailID = "";
    int sdkVersion, Measuredwidth, Measuredheight;
    int versionCodes;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        internetDectector = new Class_InternetDectector(getApplicationContext());
        isInternetPresent = internetDectector.isConnectingToInternet();
        userService1 = Class_ApiUtils.getUserService();
        hospital_list_SP = (Spinner) findViewById(R.id.hospital_list_SP);
        date_time_TV = (TextView) findViewById(R.id.date_time_TV);
        Add_bt = (Button) findViewById(R.id.Add_bt);
        edt_fromdate = (TextView) findViewById(R.id.edt_fromdate);
        fromdateseterror_TV = (EditText) findViewById(R.id.fromdateseterror_TV);
        lv_summary = (ListView) findViewById(R.id.lv_summary);

        sharedpreference_usercredential_Obj = getSharedPreferences(sharedpreference_usercredential, Context.MODE_PRIVATE);
        str_userID = sharedpreference_usercredential_Obj.getString(KeyValue_userid, "").trim();
        str_username = sharedpreference_usercredential_Obj.getString(KeyValue_username, "").trim();

        Log.e("tAG", "str_userID=" + str_userID + "str_username=" + str_username);

        database = new Database(getApplicationContext());
        count_ofnotifications=database.getCursorSize();
        Log.e("tag","count="+count_ofnotifications);

        try {
            versionCodes = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        str_versioncode = Integer.toString(versionCodes);
        Log.e("tag", "str_versioncode=" + str_versioncode);

        internetDectector = new Class_InternetDectector(getApplicationContext());
        isInternetPresent = internetDectector.isConnectingToInternet();

        if (isInternetPresent) {
            InsertDeviceDetails();
        }

        edt_fromdate.setEnabled(true);
        edt_fromdate.setFocusable(true);
        dashboard_list = new ArrayList<Class_DashboardHospitalData_List>();

        dashboardHospitalListViewAdapter = new DashboardHospitalListViewAdapter(Dashboard_Activity.this, dashboard_list);

        if (isInternetPresent) {
            GetUserHospitalList();
        } else {
            Toast.makeText(Dashboard_Activity.this, "No Internet", Toast.LENGTH_SHORT).show();
        }

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        edt_fromdate.setText(formattedDate);
        SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        str_edt_fromdate_sendTOAPI = mdyFormat.format(c);
        //SimpleDateFormat mdyFormat = new SimpleDateFormat("dd-MM-yyyy");


        Log.e("tag", "str_edate_sendTOAPI 1st.." + str_edt_fromdate_sendTOAPI);

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
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                //   obj_Class_Project_FundMain = (Class_Project_FundMain) spin_ticketStatus.getSelectedItem();

                class_getUserHospitalList = (Class_GetUserHospitalList) hospital_list_SP.getSelectedItem();

                if (class_getUserHospitalList.getEntryDate() == null || class_getUserHospitalList.getEntryDate().equalsIgnoreCase("")) {
                    date_time_TV.setText("");
                } else {
                    Date_time = class_getUserHospitalList.getEntryDate().toString();
                    Log.e("tag", "Date_time=" + Date_time);
                    date_time_TV.setText(Date_time);
                }
                str_hospitelId = class_getUserHospitalList.getHospitalId().toString();
                str_SelectedHospitalName = class_getUserHospitalList.getHospitalName().toString();
                if (isInternetPresent) {
                    Get_LoadHospitalDashboard();
                }
                // Toast.makeText(getApplicationContext(),"str_Programsid: "+str_programid,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        Add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard_Activity.this, MainActivity2.class);
                i.putExtra("hospitalId", str_hospitelId);
                i.putExtra("hospital", str_SelectedHospitalName);
                startActivity(i);
            }
        });

        gethelp();
    }


    public void setReceivedstartDate(Calendar calendar) {
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
        Log.e("tag", "str_hospitelId=" + str_hospitelId + "selected date=" + str_edt_fromdate_sendTOAPI);
        Call<Class_DashboardHospitalData> call = userService1.Get_LoadHospitalDataDate(str_hospitelId, str_edt_fromdate_sendTOAPI);

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
                            Log.e("tag", "Class_DashboardHospitalData==" + String.valueOf(class_loginresponse.getLst().get(i).getBedType()));

                            Class_DashboardHospitalData_List innerObj_Class_academic = new Class_DashboardHospitalData_List();
                            innerObj_Class_academic.setAvailable(class_loginresponse.getLst().get(i).getAvailable());
                            innerObj_Class_academic.setBedType(class_loginresponse.getLst().get(i).getBedType());
                            innerObj_Class_academic.setBedTypeId(class_loginresponse.getLst().get(i).getBedTypeId());
                            innerObj_Class_academic.setOccupied(class_loginresponse.getLst().get(i).getOccupied());
                            innerObj_Class_academic.setTotal(class_loginresponse.getLst().get(i).getTotal());

                            array_class_dashboardHospitalData_lists[i] = innerObj_Class_academic;

                            Class_DashboardHospitalData_List item1 = null;
                            item1 = new Class_DashboardHospitalData_List(array_class_dashboardHospitalData_lists[i].getBedTypeId(), array_class_dashboardHospitalData_lists[i].getBedType(), array_class_dashboardHospitalData_lists[i].getTotal(), array_class_dashboardHospitalData_lists[i].getOccupied(), array_class_dashboardHospitalData_lists[i].getAvailable());

                            dashboard_list.add(item1);
                        }//for loop end

                        dashboardHospitalListViewAdapter = new DashboardHospitalListViewAdapter(Dashboard_Activity.this, dashboard_list);
                        lv_summary.setAdapter(dashboardHospitalListViewAdapter);

                    } else {
                        progressDoalog.dismiss();
                        dashboard_list.clear();
                        dashboardHospitalListViewAdapter.notifyDataSetChanged();
                        Toast.makeText(Dashboard_Activity.this, "Hospital Data Not Found", Toast.LENGTH_SHORT).show();

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
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        menuItem = menu.findItem(R.id.action_notification);
        setupBadge();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

       if(id == R.id.action_notification){
           Intent i = new Intent(Dashboard_Activity.this, NotificationList_Activity.class);
           startActivity(i);
           finish();

        return true;
    }

        if (id == R.id.changepin) {


            AlertDialog.Builder dialog = new AlertDialog.Builder(Dashboard_Activity.this);
            dialog.setCancelable(false);
            dialog.setTitle(R.string.alert);
            dialog.setMessage("Are you sure you want to Change PIN?");

            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {

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
        } else if (id == R.id.aboutus) {
            Intent i = new Intent(getApplicationContext(), ContactUs_Activity.class);
            startActivity(i);
            finish();

        }


        return super.onOptionsItemSelected(item);
    }

    private void setupBadge() {

        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = actionView.findViewById(R.id.cart_badge);
        mCartItemCount=count_ofnotifications;

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, count_ofnotifications)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
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


//-------------------------------------------------
    //  --------------------Notification--------------------------

    /*private void Add_setGCM1() {
        Interface_userservice userService;
        userService = Class_ApiUtils.getUserService();

        tm1 = (TelephonyManager) getBaseContext()
                .getSystemService(Context.TELEPHONY_SERVICE);

        String NetworkType;
        simOperatorName = tm1.getSimOperatorName();
        Log.e("Operator", "" + simOperatorName);
        NetworkType = "GPRS";


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        int simSpeed = tm1.getNetworkType();
        if (simSpeed == 1)
            NetworkType = "Gprs";
        else if (simSpeed == 4)
            NetworkType = "Edge";
        else if (simSpeed == 8)
            NetworkType = "HSDPA";
        else if (simSpeed == 13)
            NetworkType = "LTE";
        else if (simSpeed == 3)
            NetworkType = "UMTS";
        else
            NetworkType = "Unknown";

        Log.e("SIM_INTERNET_SPEED", "" + NetworkType);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //  tmDevice = "" + tm1.getDeviceId();
        String tmDevice = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.e("DeviceIMEI", "" + tmDevice);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mobileNumber = "" + tm1.getLine1Number();
        Log.e("getLine1Number value", "" + mobileNumber);

        String mobileNumber1 = "" + tm1.getPhoneType();
        Log.e("getPhoneType value", "" + mobileNumber1);
        //tmSerial = "" + tm1.getSimSerialNumber();

        TelephonyManager tMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            tmSerial = "" + tMgr.getSimSerialNumber();
        }catch(Exception ex)
        {
            tmSerial="inaccessible";
        }

        //  Log.v("GSM devices Serial Number[simcard] ", "" + tmSerial);
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
        Log.e("androidId CDMA devices", "" + androidId);
        UUID deviceUuid = new UUID(androidId.hashCode(),
                ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        deviceId = deviceUuid.toString();
        //  Log.v("deviceIdUUID universally unique identifier", "" + deviceId);


        deviceModelName = Build.MODEL;
        Log.v("Model Name", "" + deviceModelName);
        deviceUSER = Build.USER;
        Log.v("Name USER", "" + deviceUSER);
        devicePRODUCT = Build.PRODUCT;
        Log.v("PRODUCT", "" + devicePRODUCT);
        deviceHARDWARE = Build.HARDWARE;
        Log.v("HARDWARE", "" + deviceHARDWARE);
        deviceBRAND = Build.BRAND;
        Log.v("BRAND", "" + deviceBRAND);
        myVersion = Build.VERSION.RELEASE;
        Log.v("VERSION.RELEASE", "" + myVersion);
        sdkVersion = Build.VERSION.SDK_INT;
        Log.v("VERSION.SDK_INT", "" + sdkVersion);
        sdkver = Integer.toString(sdkVersion);
        // Get display details

        Measuredwidth = 0;
        Measuredheight = 0;
        Point size = new Point();
        WindowManager w = getWindowManager();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            //   w.getDefaultDisplay().getSize(size);
            Measuredwidth = w.getDefaultDisplay().getWidth();//size.x;
            Measuredheight = w.getDefaultDisplay().getHeight();//size.y;
        } else {
            Display d = w.getDefaultDisplay();
            Measuredwidth = d.getWidth();
            Measuredheight = d.getHeight();
        }

        Log.e("SCREEN_Width", "" + Measuredwidth);
        Log.e("SCREEN_Height", "" + Measuredheight);


        regId = FirebaseInstanceId.getInstance().getToken();



        Log.e("regId_DeviceID", "" + regId);

        Class_devicedetails request = new Class_devicedetails();
        request.setUser_ID(str_userID);
        request.setDeviceId(regId);
        request.setOSVersion(myVersion);
        request.setManufacturer(deviceBRAND);
        request.setModelNo(deviceModelName);
        request.setSDKVersion(sdkver);
        request.setDeviceSrlNo(tmDevice);
        request.setServiceProvider(simOperatorName);
        request.setSIMSrlNo(tmSerial);
        request.setDeviceWidth(String.valueOf(Measuredwidth));
        request.setDeviceHeight(String.valueOf(Measuredheight));
        request.setAppVersion(versioncode);



        {
            retrofit2.Call call = userService.Post_ActionDeviceDetails(request);

            call.enqueue(new Callback<Class_devicedetails>()
            {
                @Override
                public void onResponse(retrofit2.Call<Class_devicedetails> call, Response<Class_devicedetails> response) {


                    Log.e("response device", response.toString());
                    Log.e("response_body", String.valueOf(response.body()));

                    if (response.isSuccessful())
                    {
                        //  progressDoalog.dismiss();


                        Class_devicedetails class_addfarmponddetailsresponse = response.body();

                        if (class_addfarmponddetailsresponse.getStatus().equals("true"))
                        {
                            Log.e("devicedetails", "devicedetails_Added");

                            gethelp();

                        } else if (class_addfarmponddetailsresponse.getStatus().equals("false")) {
                            //     progressDoalog.dismiss();
                            Toast.makeText(Dashboard_Activity.this, class_addfarmponddetailsresponse.getMessage(), Toast.LENGTH_SHORT).show();
                            gethelp();
                        }
                    } else {
                        //   progressDoalog.dismiss();
                        DefaultResponse error = ErrorUtils.parseError(response);
                        Log.e("devicedetailserror", error.getMsg());
                        Toast.makeText(Dashboard_Activity.this, "devicedetails"+error.getMsg(), Toast.LENGTH_SHORT).show();
                        gethelp();


                    }

                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    //Toast.makeText(Activity_HomeScreen.this, "error" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Dashboard_Activity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
                    Log.e("response_error", t.getMessage().toString());
                }
            });

        }
    }*/


    private void InsertDeviceDetails() {
        Log.e("ENtered", "InsertDeviceDetails");
        String str_token = "Bearer " + str_AUTH_token;
        Log.e("str_tokendevicedetails", str_token);
        setGCM1();
        // setInputParametersForInsertDeviceDetails();
       /* Class_postuserdevicedetailsRequest request = new Class_postuserdevicedetailsRequest();
        request.setDeviceID(regId);
        Log.e("regId", regId);
        request.setOSVersion(myVersion);
        Log.e("myVersion", myVersion);
        request.setManufacturer(deviceBRAND);
        Log.e("deviceBRAND", deviceBRAND);
        request.setModelNo(deviceModelName);
        Log.e("deviceModelName", deviceModelName);
        request.setSDKVersion(sdkver);
        Log.e("sdkver", sdkver);
        request.setDeviceSrlNo(tmDevice);
        Log.e("tmDevice", tmDevice);
        request.setServiceProvider(simOperatorName);
        Log.e("simOperatorName", simOperatorName);
        request.setSIMSrlNo(tmSerial);
        Log.e("tmSerial", tmSerial);
        request.setDeviceWidth(String.valueOf(Measuredwidth));
        Log.e("Measuredwidth", String.valueOf(Measuredwidth));
        request.setDeviceHeight(String.valueOf(Measuredheight));
        Log.e("Measuredheight", String.valueOf(Measuredheight));
        request.setAppVersion(str_versioncode);
        Log.e("str_versioncode", str_versioncode);
        request.setUserId(Integer.valueOf(str_userID));
        Log.e("str_login_userid", str_userID);
        request.setEmployeeId(Integer.valueOf(str_userID));
        Log.e("str_login_empid", str_userID);
        request.setEmailId(str_loginEmailID);
        Log.e("str_loginEmailID", str_loginEmailID);*/

        Class_devicedetails request = new Class_devicedetails();
        request.setUser_ID(str_userID);
        request.setDeviceId(regId);
        request.setOSVersion(myVersion);
        request.setManufacturer(deviceBRAND);
        request.setModelNo(deviceModelName);
        request.setSDKVersion(sdkver);
        request.setDeviceSrlNo(tmDevice);
        request.setServiceProvider(simOperatorName);
        request.setSIMSrlNo(tmSerial);
        request.setDeviceWidth(String.valueOf(Measuredwidth));
        request.setDeviceHeight(String.valueOf(Measuredheight));
        request.setAppVersion(str_versioncode);
        Log.e("tag", "save device request=" + new Gson().toJson(request));

        Call<Class_postuserdevicedetailsResponse> call = userService1.postuserdevicedetails(request);

        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(Dashboard_Activity.this);
        // progressDoalog.setMax(100);
        // progressDoalog.setMessage("Loading....");
        progressDoalog.setTitle("Please wait....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();

        call.enqueue(new Callback<Class_postuserdevicedetailsResponse>() {
            @Override
            public void onResponse(Call<Class_postuserdevicedetailsResponse> call, Response<Class_postuserdevicedetailsResponse> response) {
                if (response.isSuccessful()) {
                    progressDoalog.dismiss();
                    Class_postuserdevicedetailsResponse class_postuserdevicedetailsResponse = response.body();
                    if (class_postuserdevicedetailsResponse.getStatus()) {
//                        if(!str_login_username.equals("")) {
//                            Class_SaveSharedPreference.setUserName(Login.this, str_login_username);
//                        }

//                        Toast.makeText(Login.this, class_postuserdevicedetailsResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        progressDoalog.dismiss();
                        Log.e("tag", "Error:" + class_postuserdevicedetailsResponse.getMessage());
                        Toast.makeText(Dashboard_Activity.this, class_postuserdevicedetailsResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    }


                } else {
                    progressDoalog.dismiss();
                    DefaultResponse error = ErrorUtils.parseError(response);
                    // … and use it to show error information

                    // … or just log the issue like we’re doing :)
                    Log.d("error message", error.getMsg());

                    Toast.makeText(Dashboard_Activity.this, error.getMsg(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(Dashboard_Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });// end of call


    }

   /* @SuppressLint("HardwareIds")
    private void setInputParametersForInsertDeviceDetails() {
        final TelephonyManager tm1 = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

//        tm1 = (TelephonyManager) getBaseContext()
//                .getSystemService(Context.TELEPHONY_SERVICE);

        //   final String tmDevice, tmSerial, androidId;
        String NetworkType;
        //TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        simOperatorName = tm1.getSimOperatorName();
        Log.v("Operator", "" + simOperatorName);
        NetworkType = "GPRS";


        int simSpeed = tm1.getNetworkType();
        if (simSpeed == 1)
            NetworkType = "Gprs";
        else if (simSpeed == 4)
            NetworkType = "Edge";
        else if (simSpeed == 8)
            NetworkType = "HSDPA";
        else if (simSpeed == 13)
            NetworkType = "LTE";
        else if (simSpeed == 3)
            NetworkType = "UMTS";
        else
            NetworkType = "Unknown";

        Log.v("SIM_INTERNET_SPEED", "" + NetworkType);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

//        //added by shivaleela on nov 25th 2020
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//            if (ContextCompat.checkSelfPermission(Login.this,
//                    Manifest.permission.READ_PHONE_STATE)
//                    != PackageManager.PERMISSION_GRANTED) {
//
//                // Should we show an explanation?
//                if (ActivityCompat.shouldShowRequestPermissionRationale(Login.this,
//                        Manifest.permission.READ_PHONE_STATE)) {
//
//                    // Show an explanation to the user *asynchronously* -- don't block
//                    // this thread waiting for the user's response! After the user
//                    // sees the explanation, try again to request the permission.
//
//                    showPermissionMessage();
//
//                } else {
//
//                    // No explanation needed, we can request the permission.
//                    ActivityCompat.requestPermissions(Login.this,
//                            new String[]{Manifest.permission.READ_PHONE_STATE},
//                            REQUEST_PHONE_STATE);
//                }
//            } else {
//                getDeviceUuId(Login.this);
//
//            }
//        } else {
//            getDeviceUuId(Login.this);
//
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            tmDevice = "" + tm1.getImei();
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//            tmDevice = "" + tm1.getDeviceId();
//            Log.e("DeviceIMEI", "" + tmDevice);
//        }


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//            tmDevice = tm1.getImei();
//        } else {
//            tmDevice = tm1.getDeviceId();
//        }



        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            tmDevice = Settings.Secure.getString(
                    getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        } else {
            if (tm1.getDeviceId() != null) {
                tmDevice = tm1.getDeviceId();
            } else {
                tmDevice = Settings.Secure.getString(
                        getContentResolver(),
                        Settings.Secure.ANDROID_ID);
            }
        }

        Log.e("DeviceIMEI", "" + tmDevice);

        mobileNumber = "" + tm1.getLine1Number();
        Log.v("getLine1Number value", "" + mobileNumber);

        String mobileNumber1 = "" + tm1.getPhoneType();
        Log.v("getPhoneType value", "" + mobileNumber1);

//        tmSerial = "" + tm1.getSimSerialNumber();
        //  Log.v("GSM devices Serial Number[simcard] ", "" + tmSerial);

        *//*added by shivaleela *//*
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            tmSerial = Settings.Secure.getString(
                    getContentResolver(),
                    Settings.Secure.ANDROID_ID);

            // tmSerial = "" + tm1.getSimSerialNumber();

        } else {
            if (tm1.getSimSerialNumber() != null) {
                tmSerial = tm1.getSimSerialNumber();
            } else {
                tmSerial = Settings.Secure.getString(
                        getContentResolver(),
                        Settings.Secure.ANDROID_ID);
            }
        }











        androidId = "" + Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.v("androidId CDMA devices", "" + androidId);
        UUID deviceUuid = new UUID(androidId.hashCode(),
                ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        deviceId = deviceUuid.toString();
        //  Log.v("deviceIdUUID universally unique identifier", "" + deviceId);

        deviceModelName = Build.MODEL;
        Log.v("Name USER", "" + deviceUSER);
        devicePRODUCT = Build.PRODUCT;
        Log.v("PRODUCT", "" + devicePRODUCT);
        deviceHARDWARE = Build.HARDWARE;
        Log.v("HARDWARE", "" + deviceHARDWARE);
        deviceBRAND = Build.BRAND;
        Log.v("BRAND", "" + deviceBRAND);
        myVersion = Build.VERSION.RELEASE;
        Log.v("VERSION.RELEASE", "" + myVersion);

        sdkVersion = Build.VERSION.SDK_INT;
        Log.v("VERSION.SDK_INT", "" + sdkVersion);
        sdkver = Integer.toString(sdkVersion);
        // Get display details

        Measuredwidth = 0;
        Measuredheight = 0;
        Point size = new Point();
        WindowManager w = getWindowManager();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getWindowManager().getDefaultDisplay().getSize(size);
            Measuredwidth = size.x;
            Measuredheight = size.y;

        } else {
            Display d = w.getDefaultDisplay();
//                Measuredwidth = d.getWidth();
//                Measuredheight = d.getHeight();

            getWindowManager().getDefaultDisplay().getSize(size);
            Measuredwidth = size.x;
            Measuredheight = size.y;

        }

        Log.v("SCREEN_Width", "" + Measuredwidth);
        Log.v("SCREEN_Height", "" + Measuredheight);
        regId = FirebaseInstanceId.getInstance().getToken();
        Log.e("regId_DeviceID", "" + regId);

    }*/

    @SuppressLint("HardwareIds")
    public void setGCM1() {


//

        // Fetch Device info

       /* final TelephonyManager tm = (TelephonyManager) getBaseContext()
                .getSystemService(Context.TELEPHONY_SERVICE);*/

        tm1 = (TelephonyManager) getBaseContext()
                .getSystemService(Context.TELEPHONY_SERVICE);

        //   final String tmDevice, tmSerial, androidId;
        String NetworkType;
        //TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        simOperatorName = tm1.getSimOperatorName();
        Log.v("Operator", "" + simOperatorName);
        NetworkType = "GPRS";

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        int simSpeed = tm1.getNetworkType();
        if (simSpeed == 1)
            NetworkType = "Gprs";
        else if (simSpeed == 4)
            NetworkType = "Edge";
        else if (simSpeed == 8)
            NetworkType = "HSDPA";
        else if (simSpeed == 13)
            NetworkType = "LTE";
        else if (simSpeed == 3)
            NetworkType = "UMTS";
        else
            NetworkType = "Unknown";

        Log.v("SIM_INTERNET_SPEED", "" + NetworkType);

        //tmDevice = "" + tm1.getDeviceId();
        String tmDevice = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.v("DeviceIMEI", "" + tmDevice);
        mobileNumber = "" + tm1.getLine1Number();
        Log.v("getLine1Number value", "" + mobileNumber);

        String mobileNumber1 = "" + tm1.getPhoneType();
        Log.v("getPhoneType value", "" + mobileNumber1);
            return;
        }
        // tmSerial = "" + tm1.getSimSerialNumber();
        TelephonyManager tMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            tmSerial = "" + tMgr.getSimSerialNumber();
        }catch(Exception ex)
        {
            tmSerial="inaccessible";
        }



        //  Log.v("GSM devices Serial Number[simcard] ", "" + tmSerial);
        androidId = "" + Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.v("androidId CDMA devices", "" + androidId);
        UUID deviceUuid = new UUID(androidId.hashCode(),
                ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        deviceId = deviceUuid.toString();
        Log.v("deviceIdUUID", "" + deviceId);


        deviceModelName = Build.MODEL;
        Log.v("Model Name", "" + deviceModelName);
        deviceUSER = Build.USER;
        Log.v("Name USER", "" + deviceUSER);
        devicePRODUCT = Build.PRODUCT;
        Log.v("PRODUCT", "" + devicePRODUCT);
        deviceHARDWARE = Build.HARDWARE;
        Log.v("HARDWARE", "" + deviceHARDWARE);
        deviceBRAND = Build.BRAND;
        Log.v("BRAND", "" + deviceBRAND);
        myVersion = Build.VERSION.RELEASE;
        Log.v("VERSION.RELEASE", "" + myVersion);
        sdkVersion = Build.VERSION.SDK_INT;
        Log.v("VERSION.SDK_INT", "" + sdkVersion);
        sdkver = Integer.toString(sdkVersion);
        // Get display details

        Measuredwidth = 0;
        Measuredheight = 0;
        Point size = new Point();
        // WindowManager w = getWindowManager();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        /*int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            //   w.getDefaultDisplay().getSize(size);
           /* Measuredwidth = w.getDefaultDisplay().getWidth();//size.x;
            Measuredheight = w.getDefaultDisplay().getHeight();//size.y;*/

            Measuredwidth = displaymetrics.widthPixels;//size.x;
            Measuredheight = displaymetrics.heightPixels;//size.y;
        } else {
            // Display d = w.getDefaultDisplay();
            /*Measuredwidth = d.getWidth();
            Measuredheight = d.getHeight();*/
            Measuredwidth = displaymetrics.widthPixels;//size.x;
            Measuredheight = displaymetrics.heightPixels;//size.y;
        }

        Log.v("SCREEN_Width", "" + Measuredwidth);
        Log.v("SCREEN_Height", "" + Measuredheight);


        regId = FirebaseInstanceId.getInstance().getToken();



        Log.e("regId_DeviceID", "" + regId);

/*<username>string</username>
      <DeviceId>string</DeviceId>
      <OSVersion>string</OSVersion>
      <Manufacturer>string</Manufacturer>
      <ModelNo>string</ModelNo>
      <SDKVersion>string</SDKVersion>
      <DeviceSrlNo>string</DeviceSrlNo>
      <ServiceProvider>string</ServiceProvider>
      <SIMSrlNo>string</SIMSrlNo>
      <DeviceWidth>string</DeviceWidth>
      <DeviceHeight>string</DeviceHeight>
      <AppVersion>string</AppVersion>*/

        //if (!regId.equals("")){
        /*if (2>1){
            // String WEBSERVICE_NAME = "http://dfhrms.cloudapp.net/PMSservice.asmx?WSDL";
            String SOAP_ACTION1 = "http://mis.leadcampus.org/SaveDeviceDetails";
            String METHOD_NAME1 = "SaveDeviceDetails";
            String MAIN_NAMESPACE = "http://mis.leadcampus.org/";
            String URI = Class_URL.URL_Login.toString().trim();


            SoapObject request = new SoapObject(MAIN_NAMESPACE, METHOD_NAME1);

            //	request.addProperty("LeadId", Password1);
            request.addProperty("username",Str_FCMName );

            request.addProperty("DeviceId", regId);
            request.addProperty("OSVersion", myVersion);
            request.addProperty("Manufacturer", deviceBRAND);
            request.addProperty("ModelNo", deviceModelName);
            request.addProperty("SDKVersion", sdkver);
            request.addProperty("DeviceSrlNo", tmDevice);
            request.addProperty("ServiceProvider", simOperatorName);
            request.addProperty("SIMSrlNo", tmSerial);
            request.addProperty("DeviceWidth", Measuredwidth);
            request.addProperty("DeviceHeight", Measuredheight);
            request.addProperty("AppVersion", versioncode);
            //request.addProperty("AppVersion","4.0");


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            // Set output SOAP object
            envelope.setOutputSoapObject(request);
            Log.e("deviceDetails Request","deviceDetail"+request.toString());
            // Create HTTP call object
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URI);

            try {
                androidHttpTransport.call(SOAP_ACTION1, envelope);
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

                System.out.println("Device Res"+response);

                Log.i("sending device detail", response.toString());

            } catch (Exception e) {
                e.printStackTrace();
                Log.i("err",e.toString());
            }
        }*/






    }//end of GCM()

}