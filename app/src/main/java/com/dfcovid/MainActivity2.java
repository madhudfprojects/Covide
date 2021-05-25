package com.dfcovid;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dfcovid.model.Class_GetUserHospitalList;
import com.dfcovid.model.Class_Get_LoadHospitalDataResponse;
import com.dfcovid.model.Class_Get_UserHospitalListResponse;
import com.dfcovid.model.Class_LoadHospitalDataList;
import com.dfcovid.remote.Class_ApiUtils;
import com.dfcovid.remote.Interface_userservice;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    Boolean isInternetPresent = false;
    Class_InternetDectector internetDectector;
    Interface_userservice userService1;
    Class_GetUserHospitalList[] arrayObj_class_studentpaymentresp;
    Class_LoadHospitalDataList[] arrayObj_class_loadhospitaldata;
    String str_ox="",str_icu="",str_ventilator="",str_ox_ID="",str_icu_ID="",str_ventilator_ID="",str_ox_BedOccupiedWithinDistrict="",str_icu_BedOccupiedWithinDistrict="",str_ventilator_BedOccupiedWithinDistrict="",str_ox_BedOccupiedOutsideDistrict="",str_icu_BedOccupiedOutsideDistrict="",str_ventilator_BedOccupiedOutsideDistrict="",
            str_ox_PatientDischargedWithinDistrict="",str_icu_PatientDischargedWithinDistrict="",str_ventilator_PatientDischargedWithinDistrict="",str_ox_PatientDischargedOutsideDistrict="",str_icu_PatientDischargedOutsideDistrict="",str_ventilator_PatientDischargedOutsideDistrict="",str_ox_TotalDeath="",str_icu_TotalDeath="",str_ventilator_TotalDeath="";
    Spinner hospital_list_SP;
    Button edit_BT,disable_bt;
    EditText exp_beds_ET,actual_beds_ET,ox_beds_ET,icu_beds_ET,ventilator_ET,ABARKDWD_ET,ABARK_Outside_ET,ABARK_ET,Dharwad_ET,Outside_DWD_ET,Patient_ET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        internetDectector = new Class_InternetDectector(getApplicationContext());
        isInternetPresent = internetDectector.isConnectingToInternet();
        userService1 = Class_ApiUtils.getUserService();
        hospital_list_SP=(Spinner)findViewById(R.id.hospital_list_SP);


        if(isInternetPresent){
            GetUserHospitalList();
        }else{
            Toast.makeText(MainActivity2.this,"No Internet", Toast.LENGTH_SHORT).show();
        }



    }


    public void GetUserHospitalList() {
        Call<Class_Get_UserHospitalListResponse> call = userService1.GetUserHospitalList("4");

        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(MainActivity2.this);
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
                            arrayObj_class_studentpaymentresp[i] = innerObj_Class_academic;

                        }//for loop end
                        ArrayAdapter<Class_GetUserHospitalList> dataAdapter_edu = new ArrayAdapter<Class_GetUserHospitalList>(MainActivity2.this, R.layout.support_simple_spinner_dropdown_item, arrayObj_class_studentpaymentresp);
//                        dataAdapter_edu.setDropDownViewResource(R.layout.spinnercenterstyle);
                        hospital_list_SP.setAdapter(dataAdapter_edu);
                        LoadHospitalData();

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
                        Toast.makeText(MainActivity2.this, "Kindly restart your application", Toast.LENGTH_SHORT).show();

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

    public void LoadHospitalData() {
        Call<Class_Get_LoadHospitalDataResponse> call = userService1.LoadHospitalData("42");

        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(MainActivity2.this);
        //  progressDoalog.setMax(100);
        //  progressDoalog.setMessage("Loading....");
        progressDoalog.setTitle("Please wait....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();

        call.enqueue(new Callback<Class_Get_LoadHospitalDataResponse>() {
            @Override
            public void onResponse(Call<Class_Get_LoadHospitalDataResponse> call, Response<Class_Get_LoadHospitalDataResponse> response) {
                Log.e("Entered resp", "Class_Get_LoadHospitalDataResponse");

                if (response.isSuccessful()) {
                    progressDoalog.dismiss();
                    Class_Get_LoadHospitalDataResponse class_loginresponse = response.body();
                    if (class_loginresponse.getStatus()) {

                        List<Class_LoadHospitalDataList> monthContents_list = response.body().getLst();
                        Class_LoadHospitalDataList[] arrayObj_Class_monthcontents = new Class_LoadHospitalDataList[monthContents_list.size()];
                        arrayObj_class_loadhospitaldata = new Class_LoadHospitalDataList[arrayObj_Class_monthcontents.length];
                        for (int i = 0; i < arrayObj_Class_monthcontents.length; i++) {
                            Log.e("LoadHospitalData", String.valueOf(class_loginresponse.getLst().get(i).getHospitalId()));

                            Class_LoadHospitalDataList innerObj_Class_academic = new Class_LoadHospitalDataList();
                            //bed details
                            innerObj_Class_academic.setHospitalId(class_loginresponse.getLst().get(i).getHospitalId());
                            innerObj_Class_academic.setExpectedBeds(class_loginresponse.getLst().get(i).getExpectedBeds());
                            innerObj_Class_academic.setActualBeds(class_loginresponse.getLst().get(i).getActualBeds());
                            innerObj_Class_academic.setOxygenBeds(class_loginresponse.getLst().get(i).getOxygenBeds());
                            innerObj_Class_academic.setICUBeds(class_loginresponse.getLst().get(i).getICUBeds());
                            innerObj_Class_academic.setVentilators(class_loginresponse.getLst().get(i).getVentilators());

                            //total beds occupied
                            innerObj_Class_academic.setWithinDistrcitABARK(class_loginresponse.getLst().get(i).getWithinDistrcitABARK());
                            innerObj_Class_academic.setOutsideDistrcitABARK(class_loginresponse.getLst().get(i).getOutsideDistrcitABARK());
                            innerObj_Class_academic.setTotalABARK(class_loginresponse.getLst().get(i).getTotalABARK());
                            innerObj_Class_academic.setWithinDistrict(class_loginresponse.getLst().get(i).getWithinDistrict());
                            innerObj_Class_academic.setOutsideDistrict(class_loginresponse.getLst().get(i).getOutsideDistrict());
                            innerObj_Class_academic.setTotalPatients(class_loginresponse.getLst().get(i).getTotalPatients());



                            //beds details
                            innerObj_Class_academic.setBedType(class_loginresponse.getLst().get(i).getBedType());
                            innerObj_Class_academic.setBedTypeId(class_loginresponse.getLst().get(i).getBedTypeId());
                            innerObj_Class_academic.setBedOccupiedWithinDistrict(class_loginresponse.getLst().get(i).getBedOccupiedWithinDistrict());
                            innerObj_Class_academic.setBedOccupiedOutsideDistrict(class_loginresponse.getLst().get(i).getBedOccupiedOutsideDistrict());
                            innerObj_Class_academic.setPatientDischargedWithinDistrict(class_loginresponse.getLst().get(i).getPatientDischargedWithinDistrict());
                            innerObj_Class_academic.setPatientDischargedOutsideDistrict(class_loginresponse.getLst().get(i).getPatientDischargedOutsideDistrict());
                            innerObj_Class_academic.setTotalDeath(class_loginresponse.getLst().get(i).getTotalDeath());

                             str_ox=class_loginresponse.getLst().get(0).getBedType();
                             str_icu=class_loginresponse.getLst().get(1).getBedType();
                             str_ventilator=class_loginresponse.getLst().get(2).getBedType();


                             str_ox_ID=class_loginresponse.getLst().get(0).getBedTypeId();
                             str_icu_ID=class_loginresponse.getLst().get(1).getBedTypeId();
                             str_ventilator_ID=class_loginresponse.getLst().get(2).getBedTypeId();

                             str_ox_BedOccupiedWithinDistrict=class_loginresponse.getLst().get(0).getBedOccupiedWithinDistrict();
                             str_icu_BedOccupiedWithinDistrict=class_loginresponse.getLst().get(1).getBedOccupiedWithinDistrict();
                             str_ventilator_BedOccupiedWithinDistrict=class_loginresponse.getLst().get(2).getBedOccupiedWithinDistrict();


                             str_ox_BedOccupiedOutsideDistrict=class_loginresponse.getLst().get(0).getBedOccupiedOutsideDistrict();
                             str_icu_BedOccupiedOutsideDistrict=class_loginresponse.getLst().get(1).getBedOccupiedOutsideDistrict();
                             str_ventilator_BedOccupiedOutsideDistrict=class_loginresponse.getLst().get(2).getBedOccupiedOutsideDistrict();

                             str_ox_PatientDischargedWithinDistrict=class_loginresponse.getLst().get(0).getPatientDischargedWithinDistrict();
                             str_icu_PatientDischargedWithinDistrict=class_loginresponse.getLst().get(1).getPatientDischargedWithinDistrict();
                             str_ventilator_PatientDischargedWithinDistrict=class_loginresponse.getLst().get(2).getPatientDischargedWithinDistrict();


                             str_ox_PatientDischargedOutsideDistrict=class_loginresponse.getLst().get(0).getPatientDischargedOutsideDistrict();
                             str_icu_PatientDischargedOutsideDistrict=class_loginresponse.getLst().get(1).getPatientDischargedOutsideDistrict();
                             str_ventilator_PatientDischargedOutsideDistrict=class_loginresponse.getLst().get(2).getPatientDischargedOutsideDistrict();

                             str_ox_TotalDeath=class_loginresponse.getLst().get(0).getTotalDeath();
                             str_icu_TotalDeath=class_loginresponse.getLst().get(1).getTotalDeath();
                             str_ventilator_TotalDeath=class_loginresponse.getLst().get(2).getTotalDeath();

                            //no.of patients rem given
                            innerObj_Class_academic.setRemdesivirGivenWithinDistrict(class_loginresponse.getLst().get(i).getRemdesivirGivenWithinDistrict());
                            innerObj_Class_academic.setRemdesivirGivenOutsideDistrict(class_loginresponse.getLst().get(i).getRemdesivirGivenOutsideDistrict());
                            innerObj_Class_academic.setTotalRemdesivirGiven(class_loginresponse.getLst().get(i).getTotalRemdesivirGiven());

                            //avail of
                            innerObj_Class_academic.setAvailableRemdesivir(class_loginresponse.getLst().get(i).getAvailableRemdesivir());
                            innerObj_Class_academic.setAvailableOxygenLiters(class_loginresponse.getLst().get(i).getAvailableOxygenLiters());

//                            String str_ox=class_loginresponse.getLst().get(0).getBedType();
//                            String str_icu=class_loginresponse.getLst().get(1).getBedType();
//                            String str_ventilator=class_loginresponse.getLst().get(2).getBedType();

                            Log.e("Entered s1", str_ox);
                            Log.e("Entered s2", str_icu);
                            Log.e("Entered s3", str_ventilator);

                            arrayObj_class_loadhospitaldata[i] = innerObj_Class_academic;

                        }//for loop end
                        setvalues();

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
                        Toast.makeText(MainActivity2.this, "Kindly restart your application", Toast.LENGTH_SHORT).show();

                    }

                }
            }

            private void setvalues() {

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