package com.dfcovid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Class_DashboardHospitalData_List {

    @SerializedName("Bed_Type_Id")
    @Expose
    private String bedTypeId;
    @SerializedName("Bed_Type")
    @Expose
    private String bedType;
    @SerializedName("Total")
    @Expose
    private String total;
    @SerializedName("Occupied")
    @Expose
    private String occupied;
    @SerializedName("Available")
    @Expose
    private String available;

    public Class_DashboardHospitalData_List() {

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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOccupied() {
        return occupied;
    }

    public void setOccupied(String occupied) {
        this.occupied = occupied;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public Class_DashboardHospitalData_List(String bedTypeId, String bedType, String total, String occupied, String available) {
        this.bedTypeId = bedTypeId;
        this.bedType = bedType;
        this.total = total;
        this.occupied = occupied;
        this.available = available;
    }


}
