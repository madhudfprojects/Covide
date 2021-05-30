package com.dfcovid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*"Status": true,
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
         "HospitalServices": [
         {*/
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

    @SerializedName("Latitude")
    @Expose
    private String Latitude;


    @SerializedName("Longitude")
    @Expose
    private String Longitude;


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




}
