package com.dfcovid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*{
    "Status": true,
    "Message": "success",
    "Hospital_Details": [
        {
            "HospitalId": 1,
            "HospitalName": "KIMS",
            "ContactNo": "0",
            "Mailid": "",
            "HospitalType": "",
            "HospitalAddress": "",
            "Website": null,
            "Latitude": "",
            "Longitude": "",
            "DistrictName": "DHARWAD",
            "TalukaName": "DHARWAD",
            "status": "success",
            "HospitalServices": null
        }*/
public class Class_hsptaldetalServices_listResp
{
    @SerializedName("HospitalId")
    @Expose
    private String HospitalId;

    @SerializedName("HospitalName")
    @Expose
    private String HospitalName;


    @SerializedName("ContactNo")
    @Expose
    private String ContactNo;

    @SerializedName("Mailid")
    @Expose
    private String Mailid;


    @SerializedName("HospitalType")
    @Expose
    private String HospitalType;

    @SerializedName("HospitalAddress")
    @Expose
    private String HospitalAddress;


    @SerializedName("Website")
    @Expose
    private String Website;




    @SerializedName("Latitude")
    @Expose
    private String Latitude;


    @SerializedName("Longitude")
    @Expose
    private String Longitude;

    @SerializedName("DistrictName")
    @Expose
    private String DistrictName;

    @SerializedName("TalukaName")
    @Expose
    private String TalukaName;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("HospitalServices")
    @Expose
    private String HospitalServices;





    public String getHospitalId() {
        return HospitalId;
    }

    public void setHospitalId(String hospitalId) {
        HospitalId = hospitalId;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getMailid() {
        return Mailid;
    }

    public void setMailid(String mailid) {
        Mailid = mailid;
    }

    public String getHospitalType() {
        return HospitalType;
    }

    public void setHospitalType(String hospitalType) {
        HospitalType = hospitalType;
    }

    public String getHospitalAddress() {
        return HospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        HospitalAddress = hospitalAddress;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
    }

    public String getTalukaName() {
        return TalukaName;
    }

    public void setTalukaName(String talukaName) {
        TalukaName = talukaName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHospitalServices() {
        return HospitalServices;
    }

    public void setHospitalServices(String hospitalServices) {
        HospitalServices = hospitalServices;
    }
}
