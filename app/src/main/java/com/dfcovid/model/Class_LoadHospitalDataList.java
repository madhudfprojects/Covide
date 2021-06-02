package com.dfcovid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Class_LoadHospitalDataList {
    @SerializedName("Hospital_Id")
    @Expose
    private String hospitalId;
    @SerializedName("Expected_Beds")
    @Expose
    private String expectedBeds;
    @SerializedName("Actual_Beds")
    @Expose
    private String actualBeds;
    @SerializedName("Oxygen_Beds")
    @Expose
    private String oxygenBeds;
    @SerializedName("ICU_Beds")
    @Expose
    private String iCUBeds;


    @SerializedName("Ventilators")
    @Expose
    private String ventilators;


    @SerializedName("General_Beds")
    @Expose
    private String General_Beds;

    @SerializedName("Within_Distrcit_AB_ARK")
    @Expose
    private String withinDistrcitABARK;
    @SerializedName("Outside_Distrcit_AB_ARK")
    @Expose
    private String outsideDistrcitABARK;
    @SerializedName("Total_AB_ARK")
    @Expose
    private String totalABARK;
    @SerializedName("Within_District")
    @Expose
    private String withinDistrict;
    @SerializedName("Outside_District")
    @Expose
    private String outsideDistrict;
    @SerializedName("Total_Patients")
    @Expose
    private String totalPatients;
    @SerializedName("Remdesivir_Given_Within_District")
    @Expose
    private String remdesivirGivenWithinDistrict;
    @SerializedName("Remdesivir_Given_Outside_District")
    @Expose
    private String remdesivirGivenOutsideDistrict;
    @SerializedName("Total_Remdesivir_Given")
    @Expose
    private String totalRemdesivirGiven;
    @SerializedName("Available_Remdesivir")
    @Expose
    private String availableRemdesivir;
    @SerializedName("Available_Oxygen_Liters")
    @Expose
    private String availableOxygenLiters;
    @SerializedName("User_Type")
    @Expose
    private Object userType;
    @SerializedName("Bed_Type_Id")
    @Expose
    private String bedTypeId;
    @SerializedName("Bed_Type")
    @Expose
    private String bedType;
    @SerializedName("Bed_Occupied_Within_District")
    @Expose
    private String bedOccupiedWithinDistrict;
    @SerializedName("Bed_Occupied_Outside_District")
    @Expose
    private String bedOccupiedOutsideDistrict;
    @SerializedName("Patient_Discharged_Within_District")
    @Expose
    private String patientDischargedWithinDistrict;
    @SerializedName("Patient_Discharged_Outside_District")
    @Expose
    private String patientDischargedOutsideDistrict;
    @SerializedName("Total_Death")
    @Expose
    private String totalDeath;
    @SerializedName("Created_Date")
    @Expose
    private String createdDate;
    @SerializedName("Entry_Date")
    @Expose
    private String entryDate;
    @SerializedName("User_ID")
    @Expose
    private String userID;



//    @SerializedName("Total_General_Beds")
//    @Expose
//    private String Total_General_Beds;


    @SerializedName("GEN_Bed_Type_Id")
    @Expose
    private String GEN_Bed_Type_Id;

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

    public String getGeneral_Beds() {
        return General_Beds;
    }

    public void setGeneral_Beds(String general_Beds) {
        General_Beds = general_Beds;
    }

//    public String getTotal_General_Beds() {
//        return Total_General_Beds;
//    }
//
//    public void setTotal_General_Beds(String total_General_Beds) {
//        Total_General_Beds = total_General_Beds;
//    }

    public String getGEN_Bed_Type_Id() {
        return GEN_Bed_Type_Id;
    }

    public void setGEN_Bed_Type_Id(String GEN_Bed_Type_Id) {
        this.GEN_Bed_Type_Id = GEN_Bed_Type_Id;
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

    public String getExpectedBeds() {
        return expectedBeds;
    }

    public void setExpectedBeds(String expectedBeds) {
        this.expectedBeds = expectedBeds;
    }

    public String getActualBeds() {
        return actualBeds;
    }

    public void setActualBeds(String actualBeds) {
        this.actualBeds = actualBeds;
    }

    public String getOxygenBeds() {
        return oxygenBeds;
    }

    public void setOxygenBeds(String oxygenBeds) {
        this.oxygenBeds = oxygenBeds;
    }

    public String getICUBeds() {
        return iCUBeds;
    }

    public void setICUBeds(String iCUBeds) {
        this.iCUBeds = iCUBeds;
    }

    public String getVentilators() {
        return ventilators;
    }

    public void setVentilators(String ventilators) {
        this.ventilators = ventilators;
    }

    public String getWithinDistrcitABARK() {
        return withinDistrcitABARK;
    }

    public void setWithinDistrcitABARK(String withinDistrcitABARK) {
        this.withinDistrcitABARK = withinDistrcitABARK;
    }

    public String getOutsideDistrcitABARK() {
        return outsideDistrcitABARK;
    }

    public void setOutsideDistrcitABARK(String outsideDistrcitABARK) {
        this.outsideDistrcitABARK = outsideDistrcitABARK;
    }

    public String getTotalABARK() {
        return totalABARK;
    }

    public void setTotalABARK(String totalABARK) {
        this.totalABARK = totalABARK;
    }

    public String getWithinDistrict() {
        return withinDistrict;
    }

    public void setWithinDistrict(String withinDistrict) {
        this.withinDistrict = withinDistrict;
    }

    public String getOutsideDistrict() {
        return outsideDistrict;
    }

    public void setOutsideDistrict(String outsideDistrict) {
        this.outsideDistrict = outsideDistrict;
    }

    public String getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(String totalPatients) {
        this.totalPatients = totalPatients;
    }

    public String getRemdesivirGivenWithinDistrict() {
        return remdesivirGivenWithinDistrict;
    }

    public void setRemdesivirGivenWithinDistrict(String remdesivirGivenWithinDistrict) {
        this.remdesivirGivenWithinDistrict = remdesivirGivenWithinDistrict;
    }

    public String getRemdesivirGivenOutsideDistrict() {
        return remdesivirGivenOutsideDistrict;
    }

    public void setRemdesivirGivenOutsideDistrict(String remdesivirGivenOutsideDistrict) {
        this.remdesivirGivenOutsideDistrict = remdesivirGivenOutsideDistrict;
    }

    public String getTotalRemdesivirGiven() {
        return totalRemdesivirGiven;
    }

    public void setTotalRemdesivirGiven(String totalRemdesivirGiven) {
        this.totalRemdesivirGiven = totalRemdesivirGiven;
    }

    public String getAvailableRemdesivir() {
        return availableRemdesivir;
    }

    public void setAvailableRemdesivir(String availableRemdesivir) {
        this.availableRemdesivir = availableRemdesivir;
    }

    public String getAvailableOxygenLiters() {
        return availableOxygenLiters;
    }

    public void setAvailableOxygenLiters(String availableOxygenLiters) {
        this.availableOxygenLiters = availableOxygenLiters;
    }

    public Object getUserType() {
        return userType;
    }

    public void setUserType(Object userType) {
        this.userType = userType;
    }

    public String getBedTypeId() {
        return bedTypeId;
    }

    public void setBedTypeId(String bedTypeId) {
        this.bedTypeId = bedTypeId;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getBedOccupiedWithinDistrict() {
        return bedOccupiedWithinDistrict;
    }

    public void setBedOccupiedWithinDistrict(String bedOccupiedWithinDistrict) {
        this.bedOccupiedWithinDistrict = bedOccupiedWithinDistrict;
    }

    public String getBedOccupiedOutsideDistrict() {
        return bedOccupiedOutsideDistrict;
    }

    public void setBedOccupiedOutsideDistrict(String bedOccupiedOutsideDistrict) {
        this.bedOccupiedOutsideDistrict = bedOccupiedOutsideDistrict;
    }

    public String getPatientDischargedWithinDistrict() {
        return patientDischargedWithinDistrict;
    }

    public void setPatientDischargedWithinDistrict(String patientDischargedWithinDistrict) {
        this.patientDischargedWithinDistrict = patientDischargedWithinDistrict;
    }

    public String getPatientDischargedOutsideDistrict() {
        return patientDischargedOutsideDistrict;
    }

    public void setPatientDischargedOutsideDistrict(String patientDischargedOutsideDistrict) {
        this.patientDischargedOutsideDistrict = patientDischargedOutsideDistrict;
    }

    public String getTotalDeath() {
        return totalDeath;
    }

    public void setTotalDeath(String totalDeath) {
        this.totalDeath = totalDeath;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
