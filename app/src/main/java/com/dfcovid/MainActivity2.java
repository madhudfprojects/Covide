package com.dfcovid;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.dfcovid.model.Class_GetUserHospitalList;
import com.dfcovid.model.Class_Get_LoadHospitalDataResponse;
import com.dfcovid.model.Class_Get_UserHospitalListResponse;
import com.dfcovid.model.Class_LoadHospitalDataList;
import com.dfcovid.model.Class_PostSaveHospitalResponse;
import com.dfcovid.model.Class_PostSaveHospitalResponseRequest;
import com.dfcovid.model.Class_getdemo_Response;
import com.dfcovid.model.Class_getdemo_resplist;
import com.dfcovid.model.Class_gethelp_Response;
import com.dfcovid.model.Class_gethelp_resplist;
import com.dfcovid.remote.Class_ApiUtils;
import com.dfcovid.remote.Interface_userservice;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static com.dfcovid.Activity_confirmpin.KeyValue_userid;
import static com.dfcovid.Activity_confirmpin.sharedpreference_usercredential;

public class MainActivity2 extends AppCompatActivity {
    Boolean isInternetPresent = false;
    Class_InternetDectector internetDectector;
    Interface_userservice userService1;
    Class_GetUserHospitalList[] arrayObj_class_studentpaymentresp;
    Class_LoadHospitalDataList[] arrayObj_class_loadhospitaldata;
    Class_GetUserHospitalList obj_Class_GetUserHospitalList;
    String str_ox = "", str_icu = "", str_ventilator = "", str_ox_ID = "", str_icu_ID = "", str_ventilator_ID = "", str_ox_BedOccupiedWithinDistrict = "", str_icu_BedOccupiedWithinDistrict = "", str_ventilator_BedOccupiedWithinDistrict = "", str_ox_BedOccupiedOutsideDistrict = "", str_icu_BedOccupiedOutsideDistrict = "", str_ventilator_BedOccupiedOutsideDistrict = "",
            str_ox_PatientDischargedWithinDistrict = "", str_icu_PatientDischargedWithinDistrict = "",
            str_ventilator_PatientDischargedWithinDistrict = "", str_ox_PatientDischargedOutsideDistrict = "",
            str_icu_PatientDischargedOutsideDistrict = "", str_ventilator_PatientDischargedOutsideDistrict = "",
            str_ox_TotalDeath = "", str_icu_TotalDeath = "", str_ventilator_TotalDeath = "", str_ExpectedBeds = "",
            str_actualBeds = "", str_oxBeds = "", str_ICUBeds = "", str_Ventilators = "", str_WithinDistrcitABARK = "",
            str_OutsideDistrcitABARK = "", str_TotalABARK = "0", str_WithinDistrict = "", str_OutsideDistrict = "",
            str_TotalPatients = "", str_getRemdesivirGivenWithinDistrict = "",
            str_getRemdesivirGivenOutsideDistrict = "", str_getTotalRemdesivirGiven = "", str_getAvailableRemdesivir = "",
            str_getAvailableOxygenLiters = "", sp_strHospital_ID = "", str_currentdate = "",
            str_userID = "", str_fetched_hospital = "", str_fetched_hospitalID = "",
            str_Totl_Gen_Bed="",str_general="",str_gen_ID="",str_general_BedOccupiedWithinDistrict="",
            str_general_BedOccupiedOutsideDistrict="",str_general_PatientDischargedWithinDistrict="",
            str_general_PatientDischargedOutsideDistrict="",str_general_TotalDeath="";
    Spinner hospital_list_SP;
    ImageButton edit_BT, disable_bt,close_bt;
    Button save_BTN,close_BTN;
    EditText exp_beds_ET, actual_beds_ET, ox_beds_ET, icu_beds_ET, ventilator_ET, ABARKDWD_ET, ABARK_Outside_ET, ABARK_ET, Dharwad_ET, Outside_DWD_ET, Patient_ET, beds_occupied_within_dwd_ICU_ET, beds_occupied_within_dwd_oxygen_ET, beds_occupied_within_dwd_ventilator_ET, beds_occupied_out_dwd_ICU_ET, beds_occupied_out_dwd_oxygen_ET, beds_occupied_out_dwd_vent_ET, patientsdischrged_within_dwd_ICU_ET, patientsdischrged_within_dwd_oxygen_ET, patientsdischrged_within_dwd_vent_ET, patientsdischrged_outside_dwd_ICU_ET, patientsdischrged_out_dwd_oxy_ET, patientsdischrged_out_dwd_vent_ET, deaths_ICU_ET, deaths_oxy_ET, deaths_vent_ET, Rem_withinDWD_ET, Rem_Outside_DWD_ET, Remdesivir_Given_ET, Remdesivir_Available_ET, Oxygen_in_Litres_ET;
    RelativeLayout rel2, rel3, rel4, rel5, rel6,rel_genbed;

    SharedPreferences sharedpreference_usercredential_Obj;
    SharedPreferences.Editor editor_obj;
    int total_ABARK=0,total_patients=0;
    EditText general_beds_ET,beds_occupied_within_dwd_Gen_ET,beds_occupied_out_dwd_Gen_ET,patientsdischrged_within_dwd_Gen_ET,patientsdischrged_outside_dwd_Gen_ET,deaths_Gen_ET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bed Details");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        internetDectector = new Class_InternetDectector(getApplicationContext());
        isInternetPresent = internetDectector.isConnectingToInternet();
        userService1 = Class_ApiUtils.getUserService();

        sharedpreference_usercredential_Obj = getSharedPreferences(sharedpreference_usercredential, Context.MODE_PRIVATE);
        str_userID = sharedpreference_usercredential_Obj.getString(KeyValue_userid, "").trim();
        Log.e("str_userID", str_userID);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            str_fetched_hospital = extras.getString("hospital");
            str_fetched_hospitalID = extras.getString("hospitalId");
            Log.e("str_fetched_hospital", str_fetched_hospital);
            Log.e("str_fetched_hospitalID", str_fetched_hospitalID);
            LoadHospitalData(str_fetched_hospitalID);
        }
        //button
        edit_BT = (ImageButton) findViewById(R.id.edit_BT);
        disable_bt = (ImageButton) findViewById(R.id.disable_bt);
        close_bt= (ImageButton) findViewById(R.id.close_bt);
        save_BTN = (Button) findViewById(R.id.save_BTN);
        close_BTN= (Button) findViewById(R.id.close_BTN);
        //spinner
        hospital_list_SP = (Spinner) findViewById(R.id.hospital_list_SP);
        //Edittext
        exp_beds_ET = (EditText) findViewById(R.id.exp_beds_ET);
        actual_beds_ET = (EditText) findViewById(R.id.actual_beds_ET);
        ox_beds_ET = (EditText) findViewById(R.id.ox_beds_ET);
        icu_beds_ET = (EditText) findViewById(R.id.icu_beds_ET);
        ventilator_ET = (EditText) findViewById(R.id.ventilator_ET);
        ABARKDWD_ET = (EditText) findViewById(R.id.ABARKDWD_ET);
        ABARK_Outside_ET = (EditText) findViewById(R.id.ABARK_Outside_ET);
        ABARK_ET = (EditText) findViewById(R.id.ABARK_ET);
        Dharwad_ET = (EditText) findViewById(R.id.Dharwad_ET);
        Outside_DWD_ET = (EditText) findViewById(R.id.Outside_DWD_ET);
        Patient_ET = (EditText) findViewById(R.id.Patient_ET);
        beds_occupied_within_dwd_ICU_ET = (EditText) findViewById(R.id.beds_occupied_within_dwd_ICU_ET);
        beds_occupied_within_dwd_oxygen_ET = (EditText) findViewById(R.id.beds_occupied_within_dwd_oxygen_ET);
        beds_occupied_within_dwd_ventilator_ET = (EditText) findViewById(R.id.beds_occupied_within_dwd_ventilator_ET);
        beds_occupied_out_dwd_ICU_ET = (EditText) findViewById(R.id.beds_occupied_out_dwd_ICU_ET);
        beds_occupied_out_dwd_oxygen_ET = (EditText) findViewById(R.id.beds_occupied_out_dwd_oxygen_ET);
        beds_occupied_out_dwd_vent_ET = (EditText) findViewById(R.id.beds_occupied_out_dwd_vent_ET);
        patientsdischrged_within_dwd_ICU_ET = (EditText) findViewById(R.id.patientsdischrged_within_dwd_ICU_ET);
        patientsdischrged_within_dwd_oxygen_ET = (EditText) findViewById(R.id.patientsdischrged_within_dwd_oxygen_ET);
        patientsdischrged_within_dwd_vent_ET = (EditText) findViewById(R.id.patientsdischrged_within_dwd_vent_ET);
        patientsdischrged_outside_dwd_ICU_ET = (EditText) findViewById(R.id.patientsdischrged_outside_dwd_ICU_ET);
        patientsdischrged_out_dwd_oxy_ET = (EditText) findViewById(R.id.patientsdischrged_out_dwd_oxy_ET);
        patientsdischrged_out_dwd_vent_ET = (EditText) findViewById(R.id.patientsdischrged_out_dwd_vent_ET);
        deaths_ICU_ET = (EditText) findViewById(R.id.deaths_ICU_ET);
        deaths_oxy_ET = (EditText) findViewById(R.id.deaths_oxy_ET);
        deaths_vent_ET = (EditText) findViewById(R.id.deaths_vent_ET);
        Rem_withinDWD_ET = (EditText) findViewById(R.id.Rem_withinDWD_ET);
        Rem_Outside_DWD_ET = (EditText) findViewById(R.id.Rem_Outside_DWD_ET);
        Remdesivir_Given_ET = (EditText) findViewById(R.id.Remdesivir_Given_ET);
        Remdesivir_Available_ET = (EditText) findViewById(R.id.Remdesivir_Available_ET);
        Oxygen_in_Litres_ET = (EditText) findViewById(R.id.Oxygen_in_Litres_ET);

//gen bed
        general_beds_ET= (EditText) findViewById(R.id.general_beds_ET);
        beds_occupied_within_dwd_Gen_ET= (EditText) findViewById(R.id.beds_occupied_within_dwd_Gen_ET);
        beds_occupied_out_dwd_Gen_ET= (EditText) findViewById(R.id.beds_occupied_out_dwd_Gen_ET);
        patientsdischrged_within_dwd_Gen_ET= (EditText) findViewById(R.id.patientsdischrged_within_dwd_Gen_ET);
        patientsdischrged_outside_dwd_Gen_ET= (EditText) findViewById(R.id.patientsdischrged_outside_dwd_Gen_ET);
        deaths_Gen_ET= (EditText) findViewById(R.id.deaths_Gen_ET);

        //rel2
        rel2 = (RelativeLayout) findViewById(R.id.rel2);
        rel3 = (RelativeLayout) findViewById(R.id.rel3);
        rel4 = (RelativeLayout) findViewById(R.id.rel4);
        rel5 = (RelativeLayout) findViewById(R.id.rel5);
        rel6 = (RelativeLayout) findViewById(R.id.rel6);
        rel_genbed=(RelativeLayout) findViewById(R.id.rel_genbed);

        ABARKDWD_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int ABARK_WithinDWD = 0, ABARK_OutsideDWD = 0;

                if (ABARKDWD_ET.getText().toString().equals("")) {
                    ABARK_WithinDWD = 0;
                    ABARK_OutsideDWD = 0;
                } else {
                    try {
                        ABARK_WithinDWD = Integer.parseInt(ABARKDWD_ET.getText().toString());
                        ABARK_OutsideDWD = Integer.parseInt(ABARK_Outside_ET.getText().toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                total_ABARK = ABARK_WithinDWD + ABARK_OutsideDWD;
                Log.e("total_ABARK", String.valueOf(total_ABARK));
                ABARK_ET.setText(String.valueOf(total_ABARK));
            }
        });


        ABARK_Outside_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int ABARK_WithinDWD = 0, ABARK_OutsideDWD = 0;

                if (ABARKDWD_ET.getText().toString().equals("")) {
                    ABARK_WithinDWD = 0;
                    ABARK_OutsideDWD = 0;
                } else {
                    try {
                        ABARK_WithinDWD = Integer.parseInt(ABARKDWD_ET.getText().toString());
                        ABARK_OutsideDWD = Integer.parseInt(ABARK_Outside_ET.getText().toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                total_ABARK = ABARK_WithinDWD + ABARK_OutsideDWD;
                Log.e("total_ABARK", String.valueOf(total_ABARK));
                ABARK_ET.setText(String.valueOf(total_ABARK));
            }
        });


        Rem_withinDWD_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //int ABARK_WithinDWD= 0,ABARK_OutsideDWD=0;
                int Rem_withinDWD = 0;
                int Rem_Outside_DWD = 0;

                if (ABARKDWD_ET.getText().toString().equals("")) {
                    Rem_withinDWD = 0;
                    Rem_Outside_DWD = 0;
                } else {
                    try {
                        Rem_withinDWD = Integer.parseInt(Rem_withinDWD_ET.getText().toString());
                        Rem_Outside_DWD = Integer.parseInt(Rem_Outside_DWD_ET.getText().toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                int total_REM_GIVEN = Rem_withinDWD + Rem_Outside_DWD;
                Log.e("total_REM_GIVEN", String.valueOf(total_REM_GIVEN));
                Remdesivir_Given_ET.setText(String.valueOf(total_REM_GIVEN));
            }
        });


        Rem_Outside_DWD_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int Rem_withinDWD = 0;
                int Rem_Outside_DWD = 0;

                if (ABARKDWD_ET.getText().toString().equals("")) {
                    Rem_withinDWD = 0;
                    Rem_Outside_DWD = 0;
                } else {
                    try {
                        Rem_withinDWD = Integer.parseInt(Rem_withinDWD_ET.getText().toString());
                        Rem_Outside_DWD = Integer.parseInt(Rem_Outside_DWD_ET.getText().toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                int total_REM_GIVEN = Rem_withinDWD + Rem_Outside_DWD;
                Log.e("total_REM_GIVEN", String.valueOf(total_REM_GIVEN));
                Remdesivir_Given_ET.setText(String.valueOf(total_REM_GIVEN));
            }
        });

//patients
        Dharwad_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int patients_withinDWD = 0;
                int patients_Outside_DWD = 0;

                if (ABARKDWD_ET.getText().toString().equals("")) {
                    patients_withinDWD = 0;
                    patients_Outside_DWD = 0;
                } else {
                    try {
                        patients_withinDWD = Integer.parseInt(Dharwad_ET.getText().toString());
                        patients_Outside_DWD = Integer.parseInt(Outside_DWD_ET.getText().toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                 total_patients = patients_withinDWD + patients_Outside_DWD;
                Log.e("total_Patients", String.valueOf(total_patients));
                Patient_ET.setText(String.valueOf(total_patients));
            }
        });
        Outside_DWD_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int patients_withinDWD = 0;
                int patients_Outside_DWD = 0;

                if (ABARKDWD_ET.getText().toString().equals("")) {
                    patients_withinDWD = 0;
                    patients_Outside_DWD = 0;
                } else {
                    try {
                        patients_withinDWD = Integer.parseInt(Dharwad_ET.getText().toString());
                        patients_Outside_DWD = Integer.parseInt(Outside_DWD_ET.getText().toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                 total_patients= patients_withinDWD + patients_Outside_DWD;
                Log.e("total_Patients", String.valueOf(total_patients));
                Patient_ET.setText(String.valueOf(total_patients));
            }
        });

        edit_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disable_bt.setVisibility(View.VISIBLE);
                edit_BT.setVisibility(View.GONE);
                exp_beds_ET.setEnabled(false);
                actual_beds_ET.setEnabled(false);
                ox_beds_ET.setEnabled(true);
                icu_beds_ET.setEnabled(true);
                ventilator_ET.setEnabled(true);
                general_beds_ET.setEnabled(true);
                rel2.setBackgroundColor(Color.WHITE);
                rel3.setBackgroundColor(Color.WHITE);
                rel4.setBackgroundColor(Color.WHITE);
                rel5.setBackgroundColor(Color.WHITE);
                rel6.setBackgroundColor(Color.WHITE);
                rel_genbed.setBackgroundColor(Color.WHITE);


            }
        });

        disable_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_BT.setVisibility(View.VISIBLE);
                disable_bt.setVisibility(View.GONE);
                exp_beds_ET.setEnabled(false);
                actual_beds_ET.setEnabled(false);
                ox_beds_ET.setEnabled(false);
                icu_beds_ET.setEnabled(false);
                ventilator_ET.setEnabled(false);
                general_beds_ET.setEnabled(false);
                rel2.setBackgroundColor(Color.parseColor("#EDE9E9"));
                rel3.setBackgroundColor(Color.parseColor("#EDE9E9"));
                rel4.setBackgroundColor(Color.parseColor("#EDE9E9"));
                rel5.setBackgroundColor(Color.parseColor("#EDE9E9"));
                rel6.setBackgroundColor(Color.parseColor("#EDE9E9"));
                rel_genbed.setBackgroundColor(Color.parseColor("#EDE9E9"));

            }
        });

        hospital_list_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                obj_Class_GetUserHospitalList = (Class_GetUserHospitalList) hospital_list_SP.getSelectedItem();
                sp_strHospital_ID = obj_Class_GetUserHospitalList.getHospitalId();
                String selected_hospitalName = hospital_list_SP.getSelectedItem().toString();
                Log.e("sp_strHospital_ID", " : " + sp_strHospital_ID);
                Log.e("selected_hospitalName", " : " + selected_hospitalName);
                LoadHospitalData(sp_strHospital_ID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        save_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int actual_beds= 0;int ox_beds_intval= 0;int icu_beds_intval=0;int ventilator_intval=0,gen_beds_intval=0,total_AB=0,gentotal=0;
                int beds_occupied_within_dwd_ICU_intval= 0,beds_occupied_within_dwd_oxygen_intval=-0,beds_occupied_within_dwd_ventilator_intval=0,beds_occupied_out_dwd_oxygen_intval=0,beds_occupied_out_dwd_ICU_intval=0,beds_occupied_out_dwd_vent_intval=0,icutotal=0,oxtotal=0,venttotal=0,beds_occupied_within_dwd_gen_intval=0,beds_occupied_out_dwd_gen_intval=0;
                if(actual_beds_ET.getText().toString().equals("") || ox_beds_ET.getText().toString().equals("") || icu_beds_ET.getText().toString().equals("") || ventilator_ET.getText().toString().equals("") || general_beds_ET.getText().toString().equals("")  ){
                    ox_beds_intval=0;
                    icu_beds_intval=0;
                    ventilator_intval=0;
                    gen_beds_intval=0;

                    beds_occupied_within_dwd_ICU_intval=0;
                    beds_occupied_within_dwd_oxygen_intval=0;
                    beds_occupied_within_dwd_ventilator_intval=0;
                    beds_occupied_within_dwd_gen_intval=0;
                    beds_occupied_out_dwd_ICU_intval=0;
                    beds_occupied_out_dwd_oxygen_intval=0;
                    beds_occupied_out_dwd_vent_intval=0;
                    beds_occupied_out_dwd_gen_intval=0;
                    oxtotal=0;
                    icutotal=0;
                    venttotal=0;
                    gentotal=0;

                }else {
                    try {
                        actual_beds = Integer.parseInt(actual_beds_ET.getText().toString());
                        ox_beds_intval= Integer.parseInt(ox_beds_ET.getText().toString());
                        icu_beds_intval= Integer.parseInt(icu_beds_ET.getText().toString());
                        ventilator_intval= Integer.parseInt(ventilator_ET.getText().toString());
                        gen_beds_intval= Integer.parseInt(general_beds_ET.getText().toString());

                        beds_occupied_within_dwd_ICU_intval = Integer.parseInt(beds_occupied_within_dwd_ICU_ET.getText().toString());
                        beds_occupied_within_dwd_oxygen_intval = Integer.parseInt(beds_occupied_within_dwd_oxygen_ET.getText().toString());
                        beds_occupied_within_dwd_ventilator_intval = Integer.parseInt(beds_occupied_within_dwd_ventilator_ET.getText().toString());
                        beds_occupied_within_dwd_gen_intval = Integer.parseInt(beds_occupied_within_dwd_Gen_ET.getText().toString());

                        beds_occupied_out_dwd_ICU_intval= Integer.parseInt(beds_occupied_out_dwd_ICU_ET.getText().toString());
                        beds_occupied_out_dwd_oxygen_intval = Integer.parseInt(beds_occupied_out_dwd_oxygen_ET.getText().toString());
                        beds_occupied_out_dwd_vent_intval = Integer.parseInt(beds_occupied_out_dwd_vent_ET.getText().toString());
                        beds_occupied_out_dwd_gen_intval = Integer.parseInt(beds_occupied_out_dwd_Gen_ET.getText().toString());

                    }catch (Exception exception){
                        exception.printStackTrace();
                    }

                }
              //  total_AB = ox_beds_intval + icu_beds_intval + ventilator_intval+gen_beds_intval;
                icutotal=beds_occupied_within_dwd_ICU_intval+beds_occupied_out_dwd_ICU_intval;
                oxtotal=beds_occupied_within_dwd_oxygen_intval+beds_occupied_out_dwd_oxygen_intval;
                venttotal=beds_occupied_within_dwd_ventilator_intval+beds_occupied_out_dwd_vent_intval;
                gentotal=beds_occupied_within_dwd_gen_intval+beds_occupied_out_dwd_gen_intval;

                    if(exp_beds_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter Expected Beds", Toast.LENGTH_SHORT).show();

                    }else if(actual_beds_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter Actual Beds", Toast.LENGTH_SHORT).show();

                    }else if(ox_beds_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter Oxygen Beds", Toast.LENGTH_SHORT).show();

                    }else if(icu_beds_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter ICU Beds", Toast.LENGTH_SHORT).show();

                    }else if(ventilator_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter Ventilator Beds", Toast.LENGTH_SHORT).show();

                    }else if(ABARKDWD_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter Total Beds occupied in AB-ARK within Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(ABARK_Outside_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter Total Beds occupied in AB-ARK outside Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(Dharwad_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter Total Beds occupied in Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(Outside_DWD_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter Total Beds occupied in outside Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(beds_occupied_within_dwd_ICU_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter ICU Beds occupied within Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(beds_occupied_within_dwd_oxygen_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter Oxygen Beds occupied within Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(beds_occupied_within_dwd_ventilator_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter Ventilator Beds occupied within Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(beds_occupied_out_dwd_oxygen_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter Oxygen Beds occupied outside Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(beds_occupied_out_dwd_ICU_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter ICU Beds occupied outside Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(beds_occupied_out_dwd_vent_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter Ventilator Beds occupied outside Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(exp_beds_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter patients discharged of oxygen beds within Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(exp_beds_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter patients discharged of ICU beds within Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(patientsdischrged_within_dwd_vent_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter patients discharged of ventilator beds within Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(patientsdischrged_within_dwd_oxygen_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter patients discharged of oxygen beds outside Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(patientsdischrged_within_dwd_ICU_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter patients discharged of ICU beds outside Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(patientsdischrged_out_dwd_vent_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter patients discharged of ventilator beds outside Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(deaths_oxy_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter deaths in oxygen beds", Toast.LENGTH_SHORT).show();

                    }else if(deaths_ICU_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter deaths in ICU beds", Toast.LENGTH_SHORT).show();

                    }else if(deaths_vent_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter deaths in ventilator beds", Toast.LENGTH_SHORT).show();

                    } else if(Rem_withinDWD_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter Number of Patients given Remdesivir avials within Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(Rem_Outside_DWD_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter Number of Patients given Remdesivir avials outside Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(Remdesivir_Available_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter Remdesivir Availabel", Toast.LENGTH_SHORT).show();

                    }else if(Oxygen_in_Litres_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter Availability of Oxygen in Litres", Toast.LENGTH_SHORT).show();

                    }else if(beds_occupied_within_dwd_Gen_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter General Beds occupied within Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(beds_occupied_out_dwd_Gen_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter General Beds occupied outside Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(patientsdischrged_within_dwd_Gen_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter patients discharged of General beds within Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(patientsdischrged_outside_dwd_Gen_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter patients discharged of General beds outside Dharwad", Toast.LENGTH_SHORT).show();

                    }else if(deaths_Gen_ET.getText().toString().equals("")){
                        Toast.makeText(MainActivity2.this, "Please enter deaths in General beds", Toast.LENGTH_SHORT).show();

                    } /*else if(actual_beds!=total_AB){
                        Toast.makeText(MainActivity2.this, "Actual Bed count is not matching with detailed entries", Toast.LENGTH_SHORT).show();
                    }*/ else if(total_ABARK>actual_beds){
                        Toast.makeText(MainActivity2.this, "Total AB-ARK count is more than the Actual Beds count", Toast.LENGTH_SHORT).show();
                    }else if(total_patients>actual_beds){
                        Toast.makeText(MainActivity2.this, "Total Patients count is more than the Actual Beds count", Toast.LENGTH_SHORT).show();
                    }else if(icutotal>icu_beds_intval){
                        Toast.makeText(MainActivity2.this, "ICU Bed count is more than the ICU bed details entered", Toast.LENGTH_SHORT).show();
                    }else if(oxtotal>ox_beds_intval){
                        Toast.makeText(MainActivity2.this, "Oxygen Bed count is more than the Oxygen bed details entered", Toast.LENGTH_SHORT).show();
                    }else if(venttotal>ventilator_intval){
                        Toast.makeText(MainActivity2.this, "Ventilator Bed count is more than the Ventilator bed details entered", Toast.LENGTH_SHORT).show();
                    }else if(gentotal>gen_beds_intval){
                        Toast.makeText(MainActivity2.this, "general Bed count is more than the general bed details entered", Toast.LENGTH_SHORT).show();
                    }else{
                        if (isInternetPresent) {
                            SaveHospitalDetails();
                        } else {
                            Toast.makeText(MainActivity2.this, "No Internet", Toast.LENGTH_SHORT).show();
                        }
                    }

            }
        });

        close_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, Dashboard_Activity.class);
                startActivity(i);
                finish();

            }
        });

        close_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, Dashboard_Activity.class);
                startActivity(i);
                finish();

            }
        });

        if (isInternetPresent) {
            GetUserHospitalList();
        } else {
            Toast.makeText(MainActivity2.this, "No Internet", Toast.LENGTH_SHORT).show();
        }

        setcurrentdate();
//        gethelp();
    }

    public void setcurrentdate() {
        //// Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        Date todayDate = Calendar.getInstance().getTime();
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        str_currentdate = formatter.format(todayDate);
        Log.e("str_currentdate..", str_currentdate);
    }

    public void GetUserHospitalList() {
        Log.e("API..", str_userID);
        Call<Class_Get_UserHospitalListResponse> call = userService1.GetUserHospitalList(str_userID);

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
                        hospital_list_SP.setSelection(getIndex(hospital_list_SP, str_fetched_hospital));
                        //  LoadHospitalData("42");
//                        if(str_fetched_hospital.equals("")) {
//                            LoadHospitalData(sp_strHospital_ID);
//                        }else{
//                            Log.e("LoadHospitalData","no call");
//                        }

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

    public int getIndex(Spinner spinner, String myString) {
        int index = 0;
        String item = null;
        for (int i = 0; i < spinner.getCount(); i++) {
            //   Log.e("entered getIndex", "entered getIndex");

            item = spinner.getItemAtPosition(i).toString();
            if (item.equals(myString)) {
                index = i;
                //  Log.e("entered myString", "entered myString");

            }
        }
        return index;
    }

    public void LoadHospitalData(String strID) {
        Log.e("2Hospital_ID", strID);
        Call<Class_Get_LoadHospitalDataResponse> call = userService1.LoadHospitalData(strID);

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

                            //genbed
                            innerObj_Class_academic.setGeneral_Beds(class_loginresponse.getLst().get(i).getVentilators());

                            str_ExpectedBeds = class_loginresponse.getLst().get(i).getExpectedBeds();
                            str_actualBeds = class_loginresponse.getLst().get(i).getActualBeds();
                            str_oxBeds = class_loginresponse.getLst().get(i).getOxygenBeds();
                            str_ICUBeds = class_loginresponse.getLst().get(i).getICUBeds();
                            str_Ventilators = class_loginresponse.getLst().get(i).getVentilators();
                            str_Totl_Gen_Bed = class_loginresponse.getLst().get(i).getGeneral_Beds();

                            //total beds occupied
                            innerObj_Class_academic.setWithinDistrcitABARK(class_loginresponse.getLst().get(i).getWithinDistrcitABARK());
                            innerObj_Class_academic.setOutsideDistrcitABARK(class_loginresponse.getLst().get(i).getOutsideDistrcitABARK());
                            innerObj_Class_academic.setTotalABARK(class_loginresponse.getLst().get(i).getTotalABARK());
                            innerObj_Class_academic.setWithinDistrict(class_loginresponse.getLst().get(i).getWithinDistrict());
                            innerObj_Class_academic.setOutsideDistrict(class_loginresponse.getLst().get(i).getOutsideDistrict());
                            innerObj_Class_academic.setTotalPatients(class_loginresponse.getLst().get(i).getTotalPatients());

                            str_WithinDistrcitABARK = class_loginresponse.getLst().get(i).getWithinDistrcitABARK();
                            str_OutsideDistrcitABARK = class_loginresponse.getLst().get(i).getOutsideDistrcitABARK();
                            str_TotalABARK = class_loginresponse.getLst().get(i).getTotalABARK();
                            str_WithinDistrict = class_loginresponse.getLst().get(i).getWithinDistrict();
                            str_OutsideDistrict = class_loginresponse.getLst().get(i).getOutsideDistrict();
                            str_TotalPatients = class_loginresponse.getLst().get(i).getTotalPatients();


                            //beds details
                            innerObj_Class_academic.setBedType(class_loginresponse.getLst().get(i).getBedType());
                            innerObj_Class_academic.setBedTypeId(class_loginresponse.getLst().get(i).getBedTypeId());
                            innerObj_Class_academic.setBedOccupiedWithinDistrict(class_loginresponse.getLst().get(i).getBedOccupiedWithinDistrict());
                            innerObj_Class_academic.setBedOccupiedOutsideDistrict(class_loginresponse.getLst().get(i).getBedOccupiedOutsideDistrict());
                            innerObj_Class_academic.setPatientDischargedWithinDistrict(class_loginresponse.getLst().get(i).getPatientDischargedWithinDistrict());
                            innerObj_Class_academic.setPatientDischargedOutsideDistrict(class_loginresponse.getLst().get(i).getPatientDischargedOutsideDistrict());
                            innerObj_Class_academic.setTotalDeath(class_loginresponse.getLst().get(i).getTotalDeath());



                            //gen bed
                            innerObj_Class_academic.setGEN_Bed_Occupied_Within_District(class_loginresponse.getLst().get(i).getGEN_Bed_Occupied_Within_District());
                            innerObj_Class_academic.setGEN_Bed_Occupied_Outside_District(class_loginresponse.getLst().get(i).getGEN_Bed_Occupied_Outside_District());
                            innerObj_Class_academic.setPatientDischargedWithinDistrict(class_loginresponse.getLst().get(i).getPatientDischargedWithinDistrict());
                            innerObj_Class_academic.setPatientDischargedOutsideDistrict(class_loginresponse.getLst().get(i).getPatientDischargedOutsideDistrict());
                            innerObj_Class_academic.setGEN_Total_Death(class_loginresponse.getLst().get(i).getGEN_Total_Death());



                            str_general= class_loginresponse.getLst().get(0).getBedType();
                            str_ox = class_loginresponse.getLst().get(1).getBedType();
                            str_icu = class_loginresponse.getLst().get(2).getBedType();
                            str_ventilator = class_loginresponse.getLst().get(3).getBedType();


                            str_gen_ID = class_loginresponse.getLst().get(0).getBedTypeId();
                            str_ox_ID = class_loginresponse.getLst().get(1).getBedTypeId();
                            str_icu_ID = class_loginresponse.getLst().get(2).getBedTypeId();
                            str_ventilator_ID = class_loginresponse.getLst().get(3).getBedTypeId();



                            str_general_BedOccupiedWithinDistrict = class_loginresponse.getLst().get(0).getBedOccupiedWithinDistrict();
                            str_ox_BedOccupiedWithinDistrict = class_loginresponse.getLst().get(1).getBedOccupiedWithinDistrict();
                            str_icu_BedOccupiedWithinDistrict = class_loginresponse.getLst().get(2).getBedOccupiedWithinDistrict();
                            str_ventilator_BedOccupiedWithinDistrict = class_loginresponse.getLst().get(3).getBedOccupiedWithinDistrict();

                            str_general_BedOccupiedOutsideDistrict = class_loginresponse.getLst().get(0).getBedOccupiedOutsideDistrict();
                            str_ox_BedOccupiedOutsideDistrict = class_loginresponse.getLst().get(1).getBedOccupiedOutsideDistrict();
                            str_icu_BedOccupiedOutsideDistrict = class_loginresponse.getLst().get(2).getBedOccupiedOutsideDistrict();
                            str_ventilator_BedOccupiedOutsideDistrict = class_loginresponse.getLst().get(3).getBedOccupiedOutsideDistrict();



                            str_general_PatientDischargedWithinDistrict = class_loginresponse.getLst().get(0).getPatientDischargedWithinDistrict();
                            str_ox_PatientDischargedWithinDistrict = class_loginresponse.getLst().get(1).getPatientDischargedWithinDistrict();
                            str_icu_PatientDischargedWithinDistrict = class_loginresponse.getLst().get(2).getPatientDischargedWithinDistrict();
                            str_ventilator_PatientDischargedWithinDistrict = class_loginresponse.getLst().get(3).getPatientDischargedWithinDistrict();


                            str_general_PatientDischargedOutsideDistrict = class_loginresponse.getLst().get(0).getPatientDischargedOutsideDistrict();
                            str_ox_PatientDischargedOutsideDistrict = class_loginresponse.getLst().get(1).getPatientDischargedOutsideDistrict();
                            str_icu_PatientDischargedOutsideDistrict = class_loginresponse.getLst().get(2).getPatientDischargedOutsideDistrict();
                            str_ventilator_PatientDischargedOutsideDistrict = class_loginresponse.getLst().get(3).getPatientDischargedOutsideDistrict();

                            str_general_TotalDeath = class_loginresponse.getLst().get(0).getTotalDeath();
                            str_ox_TotalDeath = class_loginresponse.getLst().get(1).getTotalDeath();
                            str_icu_TotalDeath = class_loginresponse.getLst().get(2).getTotalDeath();
                            str_ventilator_TotalDeath = class_loginresponse.getLst().get(3).getTotalDeath();

                            //no.of patients rem given
                            innerObj_Class_academic.setRemdesivirGivenWithinDistrict(class_loginresponse.getLst().get(i).getRemdesivirGivenWithinDistrict());
                            innerObj_Class_academic.setRemdesivirGivenOutsideDistrict(class_loginresponse.getLst().get(i).getRemdesivirGivenOutsideDistrict());
                            innerObj_Class_academic.setTotalRemdesivirGiven(class_loginresponse.getLst().get(i).getTotalRemdesivirGiven());

                            str_getRemdesivirGivenWithinDistrict = class_loginresponse.getLst().get(i).getRemdesivirGivenWithinDistrict();
                            str_getRemdesivirGivenOutsideDistrict = class_loginresponse.getLst().get(i).getRemdesivirGivenOutsideDistrict();
                            str_getTotalRemdesivirGiven = class_loginresponse.getLst().get(i).getTotalRemdesivirGiven();

                            //avail of
                            innerObj_Class_academic.setAvailableRemdesivir(class_loginresponse.getLst().get(i).getAvailableRemdesivir());
                            innerObj_Class_academic.setAvailableOxygenLiters(class_loginresponse.getLst().get(i).getAvailableOxygenLiters());

                            str_getAvailableRemdesivir = class_loginresponse.getLst().get(i).getAvailableRemdesivir();
                            str_getAvailableOxygenLiters = class_loginresponse.getLst().get(i).getAvailableOxygenLiters();


                            Log.e("Entered s1", str_ox_ID);
                            Log.e("Entered s2", str_icu_ID);
                            Log.e("Entered s3", str_ventilator_ID);

                            arrayObj_class_loadhospitaldata[i] = innerObj_Class_academic;

                        }//for loop end
                        setvalues();

                    } else {
                        Log.e("Entered else", "no data");
                        ClearEditText();
                        Toast.makeText(MainActivity2.this, class_loginresponse.getMessage(), Toast.LENGTH_SHORT).show();

                        //No User Hospital Data Found
                        progressDoalog.dismiss();
                    }
                } else {
                    progressDoalog.dismiss();
                    ClearEditText();
                    Toast.makeText(MainActivity2.this, "Hospital Data Not Found", Toast.LENGTH_SHORT).show();

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

            private void ClearEditText() {
                exp_beds_ET.setText("");
                actual_beds_ET.setText("");
                ox_beds_ET.setText("");
                icu_beds_ET.setText("");
                ventilator_ET.setText("");
                //gen bed
                general_beds_ET.setText("");

                ABARKDWD_ET.setText("");
                ABARK_Outside_ET.setText("");
                ABARK_ET.setText("");

                Dharwad_ET.setText("");
                Outside_DWD_ET.setText("");
                Patient_ET.setText("");

                beds_occupied_within_dwd_ICU_ET.setText("");
                beds_occupied_within_dwd_oxygen_ET.setText("");
                beds_occupied_within_dwd_ventilator_ET.setText("");
                beds_occupied_out_dwd_ICU_ET.setText("");
                beds_occupied_out_dwd_oxygen_ET.setText("");
                beds_occupied_out_dwd_vent_ET.setText("");
                patientsdischrged_within_dwd_ICU_ET.setText("");
                patientsdischrged_within_dwd_oxygen_ET.setText("");
                patientsdischrged_within_dwd_vent_ET.setText("");
                patientsdischrged_outside_dwd_ICU_ET.setText("");
                patientsdischrged_out_dwd_oxy_ET.setText("");
                patientsdischrged_out_dwd_vent_ET.setText("");
                deaths_ICU_ET.setText("");
                deaths_oxy_ET.setText("");
                deaths_vent_ET.setText("");

                //gen bed
                beds_occupied_within_dwd_Gen_ET.setText("");
                beds_occupied_out_dwd_Gen_ET.setText("");
                patientsdischrged_within_dwd_Gen_ET.setText("");
                patientsdischrged_outside_dwd_Gen_ET.setText("");
                deaths_Gen_ET.setText("");


                Rem_withinDWD_ET.setText("");
                Rem_Outside_DWD_ET.setText("");
                Remdesivir_Given_ET.setText("");
                Remdesivir_Available_ET.setText("");
                Oxygen_in_Litres_ET.setText("");

            }

            private void setvalues() {
                exp_beds_ET.setText(str_ExpectedBeds);
                actual_beds_ET.setText(str_actualBeds);
                ox_beds_ET.setText(str_oxBeds);
                icu_beds_ET.setText(str_ICUBeds);
                ventilator_ET.setText(str_Ventilators);
                general_beds_ET.setText(str_Totl_Gen_Bed);

                ABARKDWD_ET.setText(str_WithinDistrcitABARK);
                ABARK_Outside_ET.setText(str_OutsideDistrcitABARK);

//                int abark_dwd = Integer.parseInt(ABARKDWD_ET.getText().toString());
//                int abark_outdwd = Integer.parseInt(ABARK_Outside_ET.getText().toString());
//                int total_ABARK= abark_dwd + abark_outdwd;
//                ABARK_ET.setText(total_ABARK);

               ABARK_ET.setText(str_TotalABARK);

//                if(str_TotalABARK.equals("") || str_TotalABARK.equals("null") || str_TotalABARK == null){
//                    ABARK_ET.setText("0");
//                }else {
//                    ABARK_ET.setText(str_TotalABARK);
//                }
                Dharwad_ET.setText(str_WithinDistrict);
                Outside_DWD_ET.setText(str_OutsideDistrict);
                Patient_ET.setText(str_TotalPatients);

                beds_occupied_within_dwd_ICU_ET.setText(str_icu_BedOccupiedWithinDistrict);
                beds_occupied_within_dwd_oxygen_ET.setText(str_ox_BedOccupiedWithinDistrict);
                beds_occupied_within_dwd_ventilator_ET.setText(str_ventilator_BedOccupiedWithinDistrict);
                beds_occupied_out_dwd_ICU_ET.setText(str_icu_BedOccupiedOutsideDistrict);
                beds_occupied_out_dwd_oxygen_ET.setText(str_ox_BedOccupiedOutsideDistrict);
                beds_occupied_out_dwd_vent_ET.setText(str_ventilator_BedOccupiedOutsideDistrict);
                patientsdischrged_within_dwd_ICU_ET.setText(str_icu_PatientDischargedWithinDistrict);
                patientsdischrged_within_dwd_oxygen_ET.setText(str_ox_PatientDischargedWithinDistrict);
                patientsdischrged_within_dwd_vent_ET.setText(str_ventilator_PatientDischargedWithinDistrict);
                patientsdischrged_outside_dwd_ICU_ET.setText(str_icu_PatientDischargedOutsideDistrict);
                patientsdischrged_out_dwd_oxy_ET.setText(str_ox_PatientDischargedOutsideDistrict);
                patientsdischrged_out_dwd_vent_ET.setText(str_ventilator_PatientDischargedOutsideDistrict);
                deaths_ICU_ET.setText(str_icu_TotalDeath);
                deaths_oxy_ET.setText(str_ox_TotalDeath);
                deaths_vent_ET.setText(str_ventilator_TotalDeath);



                beds_occupied_within_dwd_Gen_ET.setText(str_general_BedOccupiedWithinDistrict);
                beds_occupied_out_dwd_Gen_ET.setText(str_general_BedOccupiedOutsideDistrict);
                patientsdischrged_within_dwd_Gen_ET.setText(str_general_PatientDischargedWithinDistrict);
                patientsdischrged_outside_dwd_Gen_ET.setText(str_general_PatientDischargedOutsideDistrict);
                deaths_Gen_ET.setText(str_general_TotalDeath);

                Rem_withinDWD_ET.setText(str_getRemdesivirGivenWithinDistrict);
                Rem_Outside_DWD_ET.setText(str_getRemdesivirGivenOutsideDistrict);
                Remdesivir_Given_ET.setText(str_getTotalRemdesivirGiven);
                Remdesivir_Available_ET.setText(str_getAvailableRemdesivir);
                Oxygen_in_Litres_ET.setText(str_getAvailableOxygenLiters);
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

    public void SaveHospitalDetails() {
        /*
        {"Actual_Beds":"500","Available_Oxygen_Liters":"0","Available_Remdesivir":"0","Created_Date":"","Entry_Date":"2021-06-02","Expected_Beds":"600","GEN_Bed_Occupied_Outside_District":"0","GEN_Bed_Occupied_Within_District":"0","GEN_Bed_Type":"General Bed","GEN_Bed_Type_Id":"1","GEN_Patient_Discharged_Outside_District":"0","GEN_Patient_Discharged_Within_District":"0","GEN_Total_Death":"0","General_Beds":"100","ICU_Bed_Occupied_Outside_District":"5","ICU_Bed_Occupied_Within_District":"5","ICU_Bed_Type":"ICU Bed","ICU_Bed_Type_Id":"3","ICU_Beds":"200","ICU_Patient_Discharged_Outside_District":"0","ICU_Patient_Discharged_Within_District":"0","ICU_Total_Death":"0","OXY_Bed_Occupied_Outside_District":"0","OXY_Bed_Occupied_Within_District":"0","OXY_Bed_Type":"Oxygen Bed","OXY_Bed_Type_Id":"2","OXY_Patient_Discharged_Outside_District":"0","OXY_Patient_Discharged_Within_District":"0","OXY_Total_Death":"0","Outside_Distrcit_AB_ARK":"0","Outside_District":"0","Oxygen_Beds":"100","Remdesivir_Given_Outside_District":"0","Remdesivir_Given_Within_District":"0","Total_AB_ARK":"0","Total_Patients":"32","Total_Remdesivir_Given":"0","User_ID":"91","User_Type":"","VEN_Bed_Occupied_Outside_District":"0","VEN_Bed_Occupied_Within_District":"0","VEN_Bed_Type":"Ventilator","VEN_Bed_Type_Id":"4","VEN_Patient_Discharged_Outside_District":"0","VEN_Patient_Discharged_Within_District":"0","VEN_Total_Death":"0","Ventilators":"100","Within_Distrcit_AB_ARK":"0","Within_District":"32","Hospital_Id":"3"}
         */

        Class_PostSaveHospitalResponseRequest request = new Class_PostSaveHospitalResponseRequest();
        Log.e("1Hospital_ID", sp_strHospital_ID);
        request.setHospitalId(sp_strHospital_ID);
        request.setExpected_Beds(exp_beds_ET.getText().toString());
        request.setActual_Beds(actual_beds_ET.getText().toString());
        request.setOxygen_Beds(ox_beds_ET.getText().toString());
        request.setICU_Beds(icu_beds_ET.getText().toString());
        request.setVentilators(ventilator_ET.getText().toString());

        request.setWithin_Distrcit_AB_ARK(ABARKDWD_ET.getText().toString());
        request.setOutside_Distrcit_AB_ARK(ABARK_Outside_ET.getText().toString());

        int ABARK_WithinDWD = Integer.parseInt(ABARKDWD_ET.getText().toString());
        int ABARK_OutsideDWD = Integer.parseInt(ABARK_Outside_ET.getText().toString());
        int total_ABARK = ABARK_WithinDWD + ABARK_OutsideDWD;
        Log.e("total_ABARK", String.valueOf(total_ABARK));
        // ABARK_ET.setText(String.valueOf(total_ABARK));
        request.setTotal_AB_ARK(String.valueOf(total_ABARK));//ABARK_ET.getText().toString()
        request.setWithin_District(Dharwad_ET.getText().toString());
        request.setOutside_District(Outside_DWD_ET.getText().toString());
        request.setTotal_Patients(Patient_ET.getText().toString());

        request.setICU_Bed_Occupied_Within_District(beds_occupied_within_dwd_ICU_ET.getText().toString());
        request.setICU_Bed_Occupied_Outside_District(beds_occupied_out_dwd_ICU_ET.getText().toString());

        request.setOXY_Bed_Occupied_Within_District(beds_occupied_within_dwd_oxygen_ET.getText().toString());
        request.setOXY_Bed_Occupied_Outside_District(beds_occupied_out_dwd_oxygen_ET.getText().toString());

        request.setVEN_Bed_Occupied_Within_District(beds_occupied_within_dwd_ventilator_ET.getText().toString());
        request.setVEN_Bed_Occupied_Outside_District(beds_occupied_out_dwd_vent_ET.getText().toString());


        request.setICU_Patient_Discharged_Within_District(patientsdischrged_within_dwd_ICU_ET.getText().toString());
        request.setICU_Patient_Discharged_Outside_District(patientsdischrged_outside_dwd_ICU_ET.getText().toString());

        request.setOXY_Patient_Discharged_Within_District(patientsdischrged_within_dwd_oxygen_ET.getText().toString());
        request.setOXY_Patient_Discharged_Outside_District(patientsdischrged_out_dwd_oxy_ET.getText().toString());

        request.setVEN_Patient_Discharged_Within_District(patientsdischrged_within_dwd_vent_ET.getText().toString());
        request.setVEN_Patient_Discharged_Outside_District(patientsdischrged_out_dwd_vent_ET.getText().toString());

        request.setICU_Total_Death(deaths_ICU_ET.getText().toString());
        request.setOXY_Total_Death(deaths_oxy_ET.getText().toString());
        request.setVEN_Total_Death(deaths_vent_ET.getText().toString());

        request.setRemdesivir_Given_Within_District(Rem_withinDWD_ET.getText().toString());
        request.setRemdesivir_Given_Outside_District(Rem_Outside_DWD_ET.getText().toString());

        int Rem_withinDWD = Integer.parseInt(Rem_withinDWD_ET.getText().toString());
        int Rem_Outside_DWD = Integer.parseInt(Rem_Outside_DWD_ET.getText().toString());
        int total_REM_GIVEN = Rem_withinDWD + Rem_Outside_DWD;
        Log.e("total_REM_GIVEN", String.valueOf(total_REM_GIVEN));

//        request.setTotal_Remdesivir_Given(Remdesivir_Given_ET.getText().toString());
        request.setTotal_Remdesivir_Given(String.valueOf(total_REM_GIVEN));


        request.setAvailable_Remdesivir(Remdesivir_Available_ET.getText().toString());
        request.setAvailable_Oxygen_Liters(Oxygen_in_Litres_ET.getText().toString());

        request.setEntry_Date(str_currentdate);
        request.setUser_Type("");
        Log.e("str_userID1", str_userID);
        request.setUser_ID(str_userID);//change user ID=4 later
        request.setCreated_Date("");
        request.setICU_Bed_Type("ICU Bed");
        request.setICU_Bed_Type_Id("3");

        request.setOXY_Bed_Type("Oxygen Bed");
        request.setOXY_Bed_Type_Id("2");

        request.setVEN_Bed_Type("Ventilator");
        request.setVEN_Bed_Type_Id("4");



//        public string GEN_Bed_Type_Id { get; set; }
//        public string GEN_Bed_Type { get; set; }
//        public string GEN_Bed_Occupied_Within_District { get; set; }
//        public string GEN_Bed_Occupied_Outside_District { get; set; }
//        public string GEN_Patient_Discharged_Within_District { get; set; }
//        public string GEN_Patient_Discharged_Outside_District { get; set; }
//        public string GEN_Total_Death { get; set; }


        request.setGEN_Bed_Type_Id("1");
        request.setGEN_Bed_Type("General Bed");
        request.setGEN_Bed_Occupied_Within_District(beds_occupied_within_dwd_Gen_ET.getText().toString());
        request.setGEN_Bed_Occupied_Outside_District(beds_occupied_out_dwd_Gen_ET.getText().toString());
        request.setGEN_Patient_Discharged_Within_District(patientsdischrged_within_dwd_Gen_ET.getText().toString());
        request.setGEN_Patient_Discharged_Outside_District(patientsdischrged_outside_dwd_Gen_ET.getText().toString());
        request.setGEN_Total_Death(deaths_Gen_ET.getText().toString());
        request.setGeneral_Beds(general_beds_ET.getText().toString());



        Call<Class_PostSaveHospitalResponse> call = userService1.PostSaveHospital(request);
        // Set up progress before call
        Log.e("PostSaveHospital", "Request 33: " + new Gson().toJson(request));

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(MainActivity2.this);
        //  progressDoalog.setMax(100);
        //  progressDoalog.setMessage("Loading....");
        progressDoalog.setTitle("Please wait....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();

        call.enqueue(new Callback<Class_PostSaveHospitalResponse>() {
            @Override
            public void onResponse(Call<Class_PostSaveHospitalResponse> call, Response<Class_PostSaveHospitalResponse> response) {
                Log.e("Entered resp", "PostSaveHospital");

                if (response.isSuccessful()) {
                    progressDoalog.dismiss();
                    Class_PostSaveHospitalResponse class_loginresponse = response.body();
                    Log.e("resp", new Gson().toJson(response.body()));
                    /*
                     {"Message":"Success","Status":true}
                     */
                    if (class_loginresponse.getStatus()) {

                        Toast.makeText(MainActivity2.this, "Successfully submitted", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity2.this, Dashboard_Activity.class);
                        startActivity(i);
                        finish();

                    } else {
                        Toast.makeText(MainActivity2.this, class_loginresponse.getMessage(), Toast.LENGTH_SHORT).show();

                        progressDoalog.dismiss();
                    }
                } else {
                    progressDoalog.dismiss();
                    Log.e("Entered resp else", "");
                    DefaultResponse error = ErrorUtils.parseError(response);
                    // … and use it to show error information

                    // … or just log the issue like we’re doing :)
                    Log.e("error message", error.getMsg());

                    if (error.getMsg() != null) {

                        Log.e("error message", error.getMsg());
//                        str_getmonthsummary_errormsg = error.getMsg();
//                        alerts_dialog_getexlistviewError();

                        //Toast.makeText(getActivity(), error.getMsg(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity2.this, error.getMsg(), Toast.LENGTH_SHORT).show();

                    } else {
                        //  Toast.makeText(Activity_FeesPayment.this,error.getMsg(), Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                progressDoalog.dismiss();
//                str_getmonthsummary_errormsg = t.getMessage();
//                alerts_dialog_getexlistviewError();

                Toast.makeText(MainActivity2.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });// end of call


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(MainActivity2.this, Dashboard_Activity.class);
        startActivity(i);
        finish();

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home_menu, menu);
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        MenuItem action_editProfile = menu.findItem(R.id.aboutus);
        action_editProfile.setVisible(false);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        if(id==R.id.changepin) {


            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity2.this);
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
        }else if (id == android.R.id.home) {
            Intent i = new Intent(MainActivity2.this, Dashboard_Activity.class);
            startActivity(i);
            finish();

        }
//        }else if(id==R.id.aboutus){
//            Intent i = new Intent(getApplicationContext(), ContactUs_Activity.class);
//            startActivity(i);
//            finish();
//
//        }




        return super.onOptionsItemSelected(item);
    }






    //////////change it later
//    public void gethelp() {
//        internetDectector = new Class_InternetDectector(getApplicationContext());
//        isInternetPresent = internetDectector.isConnectingToInternet();
//
//        if (isInternetPresent) {
//            gethelp_api();
//            //getdemo();
//        }
//    }
//
//    private void gethelp_api() {
//        final ProgressDialog progressDoalog;
//        progressDoalog = new ProgressDialog(MainActivity2.this);
//        progressDoalog.setMessage("Loading....");
//        progressDoalog.setTitle("Please wait....");
//        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDoalog.show();
//
//        Interface_userservice userService;
//        userService = Class_ApiUtils.getUserService();
//
//        Call<Class_gethelp_Response> call = userService.GetHelp(str_userID);
//
//
//        call.enqueue(new Callback<Class_gethelp_Response>() {
//            @Override
//            public void onResponse(Call<Class_gethelp_Response> call, Response<Class_gethelp_Response> response) {
//
//                // Toast.makeText(MainActivity.this, ""+response.toString(), Toast.LENGTH_SHORT).show();
//
//                Log.e("response_gethelp", "response_gethelp: " + new Gson().toJson(response));
//
//               /* Class_gethelp_Response gethelp_response_obj = new Class_gethelp_Response();
//                gethelp_response_obj = (Class_gethelp_Response) response.body();*/
//
//
//                if (response.isSuccessful()) {
//                    DBCreate_Helpdetails();
//                    Class_gethelp_Response gethelp_response_obj = response.body();
//                    Log.e("response.body", response.body().getLst().toString());
//
//
//                    if (gethelp_response_obj.getStatus().equals(true)) {
//
//                        List<Class_gethelp_resplist> helplist = response.body().getLst();
//                        Log.e("length", String.valueOf(helplist.size()));
//                        int int_helpcount = helplist.size();
//
//                        for (int i = 0; i < int_helpcount; i++) {
//                            Log.e("title", helplist.get(i).getTitle().toString());
//
//                            String str_title = helplist.get(i).getTitle().toString();
//                            String str_content = helplist.get(i).getContent().toString();
//                            DBCreate_HelpDetails_insert_2sqliteDB(str_title, str_content);
//                            Log.e("str_content", helplist.get(i).getContent().toString());
//
//                        }
//
//
//                        // Data_from_HelpDetails_table();
//
//                        //helplist.get(0).
//                        progressDoalog.dismiss();
//
//                        getdemo();
//                    }
//                    // Log.e("response.body", response.body().size);
//
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                progressDoalog.dismiss();
//                Log.e("WS", "error" + t.getMessage());
//                Toast.makeText(MainActivity2.this, "WS:" + t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }
//
//    public void getdemo() {
//        internetDectector = new Class_InternetDectector(getApplicationContext());
//        isInternetPresent = internetDectector.isConnectingToInternet();
//
//        if (isInternetPresent) {
//            getdemo_api();
//        }
//    }
//
//    private void getdemo_api() {
//        final ProgressDialog progressDoalog;
//        progressDoalog = new ProgressDialog(MainActivity2.this);
//        progressDoalog.setMessage("Loading....");
//        progressDoalog.setTitle("Please wait....");
//        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDoalog.show();
//
//        Interface_userservice userService;
//        userService = Class_ApiUtils.getUserService();
//
//        Call<Class_getdemo_Response> call = userService.GetDemo(str_userID);//str_userid
//
//
//        call.enqueue(new Callback<Class_getdemo_Response>() {
//            @Override
//            public void onResponse(Call<Class_getdemo_Response> call, Response<Class_getdemo_Response> response) {
//                Log.e("response_gethelp", "response_gethelp: " + new Gson().toJson(response));
//
//               /* Class_gethelp_Response gethelp_response_obj = new Class_gethelp_Response();
//                gethelp_response_obj = (Class_gethelp_Response) response.body();*/
//
//
//                if (response.isSuccessful()) {
//                    DBCreate_Demodetails();
//                    Class_getdemo_Response getdemo_response_obj = response.body();
//                    Log.e("response.body", response.body().getLst().toString());
//
//
//                    if (getdemo_response_obj.getStatus().equals(true)) {
//
//                        List<Class_getdemo_resplist> demolist = response.body().getLst();
//                        Log.e("length", String.valueOf(demolist.size()));
//                        int int_helpcount = demolist.size();
//
//                        for (int i = 0; i < int_helpcount; i++) {
//                            Log.e("language", demolist.get(i).getLanguage_Name().toString());
//
//                            String str_languagename = demolist.get(i).getLanguage_Name().toString();
//                            String str_languagelink = demolist.get(i).getLanguage_Link().toString();
//                            DBCreate_DemoDetails_insert_2sqliteDB(str_languagename, str_languagelink);
//                        }
//
//                        //Data_from_HelpDetails_table();
//
//                        //helplist.get(0).
//                        progressDoalog.dismiss();
//                    }
//                    // Log.e("response.body", response.body().size);
//                }
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                progressDoalog.dismiss();
//                Log.e("WS", "error" + t.getMessage());
//                Toast.makeText(MainActivity2.this, "WS:" + t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }
//
//    public void DBCreate_Helpdetails() {
//
//        SQLiteDatabase db2 = this.openOrCreateDatabase("DFCOVID_DB", Context.MODE_PRIVATE, null);
//        db2.execSQL("CREATE TABLE IF NOT EXISTS HelpDetails_table(SlNo INTEGER PRIMARY KEY AUTOINCREMENT,TitleDB VARCHAR,ContentDB VARCHAR);");
//        Cursor cursor = db2.rawQuery("SELECT * FROM HelpDetails_table", null);
//        int x = cursor.getCount();
//        if (x > 0) {
//            db2.delete("HelpDetails_table", null, null);
//        }
//        db2.close();
//    }
//
//    public void DBCreate_HelpDetails_insert_2sqliteDB(String title, String content) {
//        SQLiteDatabase db2 = this.openOrCreateDatabase("DFCOVID_DB", Context.MODE_PRIVATE, null);
//        db2.execSQL("CREATE TABLE IF NOT EXISTS HelpDetails_table(SlNo INTEGER PRIMARY KEY AUTOINCREMENT,TitleDB VARCHAR,ContentDB VARCHAR);");
//
//        ContentValues cv = new ContentValues();
//        cv.put("TitleDB", title);
//        cv.put("ContentDB", content);
//        db2.insert("HelpDetails_table", null, cv);
//        db2.close();
//
//        Log.e("insert", "DBCreate_HelpDetails_insert_2sqliteDB");
//
//    }
//
//    public void DBCreate_Demodetails() {
//
//        SQLiteDatabase db2 = this.openOrCreateDatabase("DFCOVID_DB", Context.MODE_PRIVATE, null);
//        db2.execSQL("CREATE TABLE IF NOT EXISTS DemoDetails_table(SlNo INTEGER PRIMARY KEY AUTOINCREMENT,LanguageDB VARCHAR,LinkDB VARCHAR);");
//        Cursor cursor = db2.rawQuery("SELECT * FROM DemoDetails_table", null);
//        int x = cursor.getCount();
//        if (x > 0) {
//            db2.delete("DemoDetails_table", null, null);
//        }
//        db2.close();
//    }
//
//    public void DBCreate_DemoDetails_insert_2sqliteDB(String str_languagename, String str_languagelink) {
//        SQLiteDatabase db2 = this.openOrCreateDatabase("DFCOVID_DB", Context.MODE_PRIVATE, null);
//        db2.execSQL("CREATE TABLE IF NOT EXISTS DemoDetails_table(SlNo INTEGER PRIMARY KEY AUTOINCREMENT,LanguageDB VARCHAR,LinkDB VARCHAR);");
//
//        ContentValues cv = new ContentValues();
//        cv.put("LanguageDB", str_languagename);
//        cv.put("LinkDB", str_languagelink);
//        db2.insert("DemoDetails_table", null, cv);
//        db2.close();
//
//        Log.e("insert", "DBCreate_DemoDetails_insert_2sqliteDB");
//
//    }






}