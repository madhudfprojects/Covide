package com.dfcovid;

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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Class_hospitaldetalServices_resp
{

    @SerializedName("Status")
    @Expose
    private String Status;

    @SerializedName("Message")
    @Expose
    private String Message;

    @SerializedName("Hospital_Details")
    @Expose
    private List<Class_hsptaldetalServices_listResp> Hospital_Details = null;


    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public List<Class_hsptaldetalServices_listResp> getHospital_Details() {
        return Hospital_Details;
    }

    public void setHospital_Details(List<Class_hsptaldetalServices_listResp> hospital_Details) {
        Hospital_Details = hospital_Details;
    }
}
