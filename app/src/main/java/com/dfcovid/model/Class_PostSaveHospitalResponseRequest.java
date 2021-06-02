package com.dfcovid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Hospital_Id,  Expected_Beds,  Actual_Beds , Oxygen_Beds , ICU_Beds ,  Ventilators ,
// Within_Distrcit_AB_ARK ,        Outside_Distrcit_AB_ARK,
//        Total_AB_ARK ,        Within_District, Outside_District ,
//        Total_Patients ,        Remdesivir_Given_Within_District ,
//        Remdesivir_Given_Outside_District ,        Total_Remdesivir_Given ,
//        Available_Remdesivir ,        Available_Oxygen_Liters ,
//        User_Type ,        OXY_Bed_Type_Id ,        OXY_Bed_Type ,
//        OXY_Bed_Occupied_Within_District ,        OXY_Bed_Occupied_Outside_District ,
//        OXY_Patient_Discharged_Within_District ,        OXY_Patient_Discharged_Outside_District ,
//        OXY_Total_Death ,ICU_Bed_Type_Id ,ICU_Bed_Type ,ICU_Bed_Occupied_Within_District ,
//        ICU_Bed_Occupied_Outside_District ,ICU_Patient_Discharged_Within_District ,
//        ICU_Patient_Discharged_Outside_District ,ICU_Total_Death ,VEN_Bed_Type_Id ,VEN_Bed_Type ,
//        VEN_Bed_Occupied_Within_District ,VEN_Bed_Occupied_Outside_District ,
//        VEN_Patient_Discharged_Within_District ,VEN_Patient_Discharged_Outside_District ,
//        VEN_Total_Death ,Created_Date, Entry_Date,User_ID
public class Class_PostSaveHospitalResponseRequest {
    @SerializedName("Hospital_Id")
    @Expose
    private String hospitalId;

    @SerializedName("Expected_Beds")
    @Expose
    private String Expected_Beds;

    @SerializedName("Actual_Beds")
    @Expose
    private String Actual_Beds;

    @SerializedName("Oxygen_Beds")
    @Expose
    private String Oxygen_Beds;

    @SerializedName("ICU_Beds")
    @Expose
    private String ICU_Beds;

    @SerializedName("Ventilators")
    @Expose
    private String Ventilators;

    @SerializedName("Within_Distrcit_AB_ARK")
    @Expose
    private String Within_Distrcit_AB_ARK;

    @SerializedName("Outside_Distrcit_AB_ARK")
    @Expose
    private String Outside_Distrcit_AB_ARK;

    @SerializedName("Total_AB_ARK")
    @Expose
    private String Total_AB_ARK;

    @SerializedName("Within_District")
    @Expose
    private String Within_District;

    @SerializedName("Outside_District")
    @Expose
    private String Outside_District;

    @SerializedName("Total_Patients")
    @Expose
    private String Total_Patients;

    @SerializedName("Remdesivir_Given_Within_District")
    @Expose
    private String Remdesivir_Given_Within_District;

    @SerializedName("Remdesivir_Given_Outside_District")
    @Expose
    private String Remdesivir_Given_Outside_District;

    @SerializedName("Total_Remdesivir_Given")
    @Expose
    private String Total_Remdesivir_Given;

    @SerializedName("Available_Remdesivir")
    @Expose
    private String Available_Remdesivir;

    @SerializedName("Available_Oxygen_Liters")
    @Expose
    private String Available_Oxygen_Liters;


    @SerializedName("User_Type")
    @Expose
    private String User_Type;

    @SerializedName("OXY_Bed_Type_Id")
    @Expose
    private String OXY_Bed_Type_Id;

    @SerializedName("OXY_Bed_Type")
    @Expose
    private String OXY_Bed_Type;

    @SerializedName("OXY_Bed_Occupied_Within_District")
    @Expose
    private String OXY_Bed_Occupied_Within_District;

    @SerializedName("OXY_Bed_Occupied_Outside_District")
    @Expose
    private String OXY_Bed_Occupied_Outside_District;

    @SerializedName("OXY_Patient_Discharged_Within_District")
    @Expose
    private String OXY_Patient_Discharged_Within_District;

    @SerializedName("OXY_Patient_Discharged_Outside_District")
    @Expose
    private String OXY_Patient_Discharged_Outside_District;

    @SerializedName("OXY_Total_Death")
    @Expose
    private String OXY_Total_Death;

    @SerializedName("ICU_Bed_Type_Id")
    @Expose
    private String ICU_Bed_Type_Id;

    @SerializedName("ICU_Bed_Type")
    @Expose
    private String ICU_Bed_Type;

    @SerializedName("ICU_Bed_Occupied_Within_District")
    @Expose
    private String ICU_Bed_Occupied_Within_District;

    @SerializedName("ICU_Bed_Occupied_Outside_District")
    @Expose
    private String ICU_Bed_Occupied_Outside_District;

    @SerializedName("ICU_Patient_Discharged_Within_District")
    @Expose
    private String ICU_Patient_Discharged_Within_District;

    @SerializedName("ICU_Patient_Discharged_Outside_District")
    @Expose
    private String ICU_Patient_Discharged_Outside_District;

    @SerializedName("ICU_Total_Death")
    @Expose
    private String ICU_Total_Death;

    @SerializedName("VEN_Bed_Type_Id")
    @Expose
    private String VEN_Bed_Type_Id;

    @SerializedName("VEN_Bed_Type")
    @Expose
    private String VEN_Bed_Type;

    @SerializedName("VEN_Bed_Occupied_Within_District")
    @Expose
    private String VEN_Bed_Occupied_Within_District;

    @SerializedName("VEN_Bed_Occupied_Outside_District")
    @Expose
    private String VEN_Bed_Occupied_Outside_District;


    @SerializedName("VEN_Patient_Discharged_Within_District")
    @Expose
    private String VEN_Patient_Discharged_Within_District;

    @SerializedName("VEN_Patient_Discharged_Outside_District")
    @Expose
    private String VEN_Patient_Discharged_Outside_District;

    @SerializedName("VEN_Total_Death")
    @Expose
    private String VEN_Total_Death;

    @SerializedName("Created_Date")
    @Expose
    private String Created_Date;

    @SerializedName("Entry_Date")
    @Expose
    private String Entry_Date;

    @SerializedName("User_ID")
    @Expose
    private String User_ID;

/*
public string GEN_Bed_Type_Id { get; set; }
       public string GEN_Bed_Type { get; set; }
       public string GEN_Bed_Occupied_Within_District { get; set; }
       public string GEN_Bed_Occupied_Outside_District { get; set; }
       public string GEN_Patient_Discharged_Within_District { get; set; }
       public string GEN_Patient_Discharged_Outside_District { get; set; }
       public string GEN_Total_Death { get; set; }

 */

    @SerializedName("GEN_Bed_Type_Id")
    @Expose
    private String GEN_Bed_Type_Id;

    @SerializedName("GEN_Bed_Type")
    @Expose
    private String GEN_Bed_Type;

    @SerializedName("GEN_Bed_Occupied_Within_District")
    @Expose
    private String GEN_Bed_Occupied_Within_District;

    @SerializedName("GEN_Bed_Occupied_Outside_District")
    @Expose
    private String GEN_Bed_Occupied_Outside_District;


    @SerializedName("GEN_Patient_Discharged_Within_District")
    @Expose
    private String GEN_Patient_Discharged_Within_District;


    @SerializedName("GEN_Patient_Discharged_Outside_District")
    @Expose
    private String GEN_Patient_Discharged_Outside_District;

    @SerializedName("GEN_Total_Death")
    @Expose
    private String GEN_Total_Death;

    //General_Beds
    @SerializedName("General_Beds")
    @Expose
    private String General_Beds;

    public String getGeneral_Beds() {
        return General_Beds;
    }

    public void setGeneral_Beds(String general_Beds) {
        General_Beds = general_Beds;
    }

    public String getGEN_Bed_Type_Id() {
        return GEN_Bed_Type_Id;
    }

    public void setGEN_Bed_Type_Id(String GEN_Bed_Type_Id) {
        this.GEN_Bed_Type_Id = GEN_Bed_Type_Id;
    }

    public String getGEN_Bed_Type() {
        return GEN_Bed_Type;
    }

    public void setGEN_Bed_Type(String GEN_Bed_Type) {
        this.GEN_Bed_Type = GEN_Bed_Type;
    }

    public String getGEN_Bed_Occupied_Within_District() {
        return GEN_Bed_Occupied_Within_District;
    }

    public void setGEN_Bed_Occupied_Within_District(String GEN_Bed_Occupied_Within_District) {
        this.GEN_Bed_Occupied_Within_District = GEN_Bed_Occupied_Within_District;
    }

    public String getGEN_Bed_Occupied_Outside_District() {
        return GEN_Bed_Occupied_Outside_District;
    }

    public void setGEN_Bed_Occupied_Outside_District(String GEN_Bed_Occupied_Outside_District) {
        this.GEN_Bed_Occupied_Outside_District = GEN_Bed_Occupied_Outside_District;
    }

    public String getGEN_Patient_Discharged_Within_District() {
        return GEN_Patient_Discharged_Within_District;
    }

    public void setGEN_Patient_Discharged_Within_District(String GEN_Patient_Discharged_Within_District) {
        this.GEN_Patient_Discharged_Within_District = GEN_Patient_Discharged_Within_District;
    }

    public String getGEN_Patient_Discharged_Outside_District() {
        return GEN_Patient_Discharged_Outside_District;
    }

    public void setGEN_Patient_Discharged_Outside_District(String GEN_Patient_Discharged_Outside_District) {
        this.GEN_Patient_Discharged_Outside_District = GEN_Patient_Discharged_Outside_District;
    }

    public String getGEN_Total_Death() {
        return GEN_Total_Death;
    }

    public void setGEN_Total_Death(String GEN_Total_Death) {
        this.GEN_Total_Death = GEN_Total_Death;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getExpected_Beds() {
        return Expected_Beds;
    }

    public void setExpected_Beds(String expected_Beds) {
        Expected_Beds = expected_Beds;
    }

    public String getActual_Beds() {
        return Actual_Beds;
    }

    public void setActual_Beds(String actual_Beds) {
        Actual_Beds = actual_Beds;
    }

    public String getOxygen_Beds() {
        return Oxygen_Beds;
    }

    public void setOxygen_Beds(String oxygen_Beds) {
        Oxygen_Beds = oxygen_Beds;
    }

    public String getICU_Beds() {
        return ICU_Beds;
    }

    public void setICU_Beds(String ICU_Beds) {
        this.ICU_Beds = ICU_Beds;
    }

    public String getVentilators() {
        return Ventilators;
    }

    public void setVentilators(String ventilators) {
        Ventilators = ventilators;
    }

    public String getWithin_Distrcit_AB_ARK() {
        return Within_Distrcit_AB_ARK;
    }

    public void setWithin_Distrcit_AB_ARK(String within_Distrcit_AB_ARK) {
        Within_Distrcit_AB_ARK = within_Distrcit_AB_ARK;
    }

    public String getOutside_Distrcit_AB_ARK() {
        return Outside_Distrcit_AB_ARK;
    }

    public void setOutside_Distrcit_AB_ARK(String outside_Distrcit_AB_ARK) {
        Outside_Distrcit_AB_ARK = outside_Distrcit_AB_ARK;
    }

    public String getTotal_AB_ARK() {
        return Total_AB_ARK;
    }

    public void setTotal_AB_ARK(String total_AB_ARK) {
        Total_AB_ARK = total_AB_ARK;
    }

    public String getWithin_District() {
        return Within_District;
    }

    public void setWithin_District(String within_District) {
        Within_District = within_District;
    }

    public String getOutside_District() {
        return Outside_District;
    }

    public void setOutside_District(String outside_District) {
        Outside_District = outside_District;
    }

    public String getTotal_Patients() {
        return Total_Patients;
    }

    public void setTotal_Patients(String total_Patients) {
        Total_Patients = total_Patients;
    }

    public String getRemdesivir_Given_Within_District() {
        return Remdesivir_Given_Within_District;
    }

    public void setRemdesivir_Given_Within_District(String remdesivir_Given_Within_District) {
        Remdesivir_Given_Within_District = remdesivir_Given_Within_District;
    }

    public String getRemdesivir_Given_Outside_District() {
        return Remdesivir_Given_Outside_District;
    }

    public void setRemdesivir_Given_Outside_District(String remdesivir_Given_Outside_District) {
        Remdesivir_Given_Outside_District = remdesivir_Given_Outside_District;
    }

    public String getTotal_Remdesivir_Given() {
        return Total_Remdesivir_Given;
    }

    public void setTotal_Remdesivir_Given(String total_Remdesivir_Given) {
        Total_Remdesivir_Given = total_Remdesivir_Given;
    }

    public String getAvailable_Remdesivir() {
        return Available_Remdesivir;
    }

    public void setAvailable_Remdesivir(String available_Remdesivir) {
        Available_Remdesivir = available_Remdesivir;
    }

    public String getAvailable_Oxygen_Liters() {
        return Available_Oxygen_Liters;
    }

    public void setAvailable_Oxygen_Liters(String available_Oxygen_Liters) {
        Available_Oxygen_Liters = available_Oxygen_Liters;
    }

    public String getUser_Type() {
        return User_Type;
    }

    public void setUser_Type(String user_Type) {
        User_Type = user_Type;
    }

    public String getOXY_Bed_Type_Id() {
        return OXY_Bed_Type_Id;
    }

    public void setOXY_Bed_Type_Id(String OXY_Bed_Type_Id) {
        this.OXY_Bed_Type_Id = OXY_Bed_Type_Id;
    }

    public String getOXY_Bed_Type() {
        return OXY_Bed_Type;
    }

    public void setOXY_Bed_Type(String OXY_Bed_Type) {
        this.OXY_Bed_Type = OXY_Bed_Type;
    }

    public String getOXY_Bed_Occupied_Within_District() {
        return OXY_Bed_Occupied_Within_District;
    }

    public void setOXY_Bed_Occupied_Within_District(String OXY_Bed_Occupied_Within_District) {
        this.OXY_Bed_Occupied_Within_District = OXY_Bed_Occupied_Within_District;
    }

    public String getOXY_Bed_Occupied_Outside_District() {
        return OXY_Bed_Occupied_Outside_District;
    }

    public void setOXY_Bed_Occupied_Outside_District(String OXY_Bed_Occupied_Outside_District) {
        this.OXY_Bed_Occupied_Outside_District = OXY_Bed_Occupied_Outside_District;
    }

    public String getOXY_Patient_Discharged_Within_District() {
        return OXY_Patient_Discharged_Within_District;
    }

    public void setOXY_Patient_Discharged_Within_District(String OXY_Patient_Discharged_Within_District) {
        this.OXY_Patient_Discharged_Within_District = OXY_Patient_Discharged_Within_District;
    }

    public String getOXY_Patient_Discharged_Outside_District() {
        return OXY_Patient_Discharged_Outside_District;
    }

    public void setOXY_Patient_Discharged_Outside_District(String OXY_Patient_Discharged_Outside_District) {
        this.OXY_Patient_Discharged_Outside_District = OXY_Patient_Discharged_Outside_District;
    }

    public String getOXY_Total_Death() {
        return OXY_Total_Death;
    }

    public void setOXY_Total_Death(String OXY_Total_Death) {
        this.OXY_Total_Death = OXY_Total_Death;
    }

    public String getICU_Bed_Type_Id() {
        return ICU_Bed_Type_Id;
    }

    public void setICU_Bed_Type_Id(String ICU_Bed_Type_Id) {
        this.ICU_Bed_Type_Id = ICU_Bed_Type_Id;
    }

    public String getICU_Bed_Type() {
        return ICU_Bed_Type;
    }

    public void setICU_Bed_Type(String ICU_Bed_Type) {
        this.ICU_Bed_Type = ICU_Bed_Type;
    }

    public String getICU_Bed_Occupied_Within_District() {
        return ICU_Bed_Occupied_Within_District;
    }

    public void setICU_Bed_Occupied_Within_District(String ICU_Bed_Occupied_Within_District) {
        this.ICU_Bed_Occupied_Within_District = ICU_Bed_Occupied_Within_District;
    }

    public String getICU_Bed_Occupied_Outside_District() {
        return ICU_Bed_Occupied_Outside_District;
    }

    public void setICU_Bed_Occupied_Outside_District(String ICU_Bed_Occupied_Outside_District) {
        this.ICU_Bed_Occupied_Outside_District = ICU_Bed_Occupied_Outside_District;
    }

    public String getICU_Patient_Discharged_Within_District() {
        return ICU_Patient_Discharged_Within_District;
    }

    public void setICU_Patient_Discharged_Within_District(String ICU_Patient_Discharged_Within_District) {
        this.ICU_Patient_Discharged_Within_District = ICU_Patient_Discharged_Within_District;
    }

    public String getICU_Patient_Discharged_Outside_District() {
        return ICU_Patient_Discharged_Outside_District;
    }

    public void setICU_Patient_Discharged_Outside_District(String ICU_Patient_Discharged_Outside_District) {
        this.ICU_Patient_Discharged_Outside_District = ICU_Patient_Discharged_Outside_District;
    }

    public String getICU_Total_Death() {
        return ICU_Total_Death;
    }

    public void setICU_Total_Death(String ICU_Total_Death) {
        this.ICU_Total_Death = ICU_Total_Death;
    }

    public String getVEN_Bed_Type_Id() {
        return VEN_Bed_Type_Id;
    }

    public void setVEN_Bed_Type_Id(String VEN_Bed_Type_Id) {
        this.VEN_Bed_Type_Id = VEN_Bed_Type_Id;
    }

    public String getVEN_Bed_Type() {
        return VEN_Bed_Type;
    }

    public void setVEN_Bed_Type(String VEN_Bed_Type) {
        this.VEN_Bed_Type = VEN_Bed_Type;
    }

    public String getVEN_Bed_Occupied_Within_District() {
        return VEN_Bed_Occupied_Within_District;
    }

    public void setVEN_Bed_Occupied_Within_District(String VEN_Bed_Occupied_Within_District) {
        this.VEN_Bed_Occupied_Within_District = VEN_Bed_Occupied_Within_District;
    }

    public String getVEN_Bed_Occupied_Outside_District() {
        return VEN_Bed_Occupied_Outside_District;
    }

    public void setVEN_Bed_Occupied_Outside_District(String VEN_Bed_Occupied_Outside_District) {
        this.VEN_Bed_Occupied_Outside_District = VEN_Bed_Occupied_Outside_District;
    }

    public String getVEN_Patient_Discharged_Within_District() {
        return VEN_Patient_Discharged_Within_District;
    }

    public void setVEN_Patient_Discharged_Within_District(String VEN_Patient_Discharged_Within_District) {
        this.VEN_Patient_Discharged_Within_District = VEN_Patient_Discharged_Within_District;
    }

    public String getVEN_Patient_Discharged_Outside_District() {
        return VEN_Patient_Discharged_Outside_District;
    }

    public void setVEN_Patient_Discharged_Outside_District(String VEN_Patient_Discharged_Outside_District) {
        this.VEN_Patient_Discharged_Outside_District = VEN_Patient_Discharged_Outside_District;
    }

    public String getVEN_Total_Death() {
        return VEN_Total_Death;
    }

    public void setVEN_Total_Death(String VEN_Total_Death) {
        this.VEN_Total_Death = VEN_Total_Death;
    }

    public String getCreated_Date() {
        return Created_Date;
    }

    public void setCreated_Date(String created_Date) {
        Created_Date = created_Date;
    }

    public String getEntry_Date() {
        return Entry_Date;
    }

    public void setEntry_Date(String entry_Date) {
        Entry_Date = entry_Date;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }
}
