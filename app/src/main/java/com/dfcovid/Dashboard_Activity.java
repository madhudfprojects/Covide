package com.dfcovid;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
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
import com.dfcovid.remote.Class_ApiUtils;
import com.dfcovid.remote.Interface_userservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

        edt_fromdate.setEnabled(false);
        edt_fromdate.setFocusable(false);
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
                Intent i = new Intent(Dashboard_Activity.this,MainActivity2.class);
                i.putExtra("hospitalId",str_hospitelId);
                i.putExtra("hospital",str_SelectedHospitalName);
                startActivity(i);
            }
        });
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
        Call<Class_DashboardHospitalData> call = userService1.Get_LoadHospitalDashboard(str_hospitelId);

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

}