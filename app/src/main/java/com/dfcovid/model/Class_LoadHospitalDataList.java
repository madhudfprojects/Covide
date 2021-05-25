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
    @SerializedName("Within_Distrcit_AB_ARK")
    @Expose
    private String withinDistrcitABARK;
    @SerializedName("Outside_Distrcit_AB_ARK")
    @Expose
    private String outsideDistrcitABARK;
    @SerializedName("Total_AB_ARK")
    @Expose
    private Object totalABARK;
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
    private Object userID;

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

    public Object getTotalABARK() {
        return totalABARK;
    }

    public void setTotalABARK(Object totalABARK) {
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

    public Object getUserID() {
        return userID;
    }

    public void setUserID(Object userID) {
        this.userID = userID;
    }

}
