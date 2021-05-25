package com.dfcovid.model;


import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Class_GetUserHospitalList {

    @SerializedName("Hospital_Id")
    @Expose
    private String hospitalId;
    @SerializedName("Hospital_Name")
    @Expose
    private String hospitalName;
    @SerializedName("Entry_Date")
    @Expose
    private String entryDate;

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }


    @NonNull
    @Override
    public String toString() {
        return hospitalName;
    }
}
