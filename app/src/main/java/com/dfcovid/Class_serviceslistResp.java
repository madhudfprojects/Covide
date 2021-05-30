package com.dfcovid;
/*"list": [
        {
        "Service_Id": 1,
        "Services_Name": "Vaccine Centers",
        "Web_URL": "",
        "status": "success"
        },*/

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Class_serviceslistResp
{

    @SerializedName("Service_Id")
    @Expose
    private String Service_Id;

    @SerializedName("Services_Name")
    @Expose
    private String Services_Name;


    @SerializedName("Web_URL")
    @Expose
    private String Web_URL;

    @SerializedName("status")
    @Expose
    private String status;

    public String getService_Id() {
        return Service_Id;
    }

    public void setService_Id(String service_Id) {
        Service_Id = service_Id;
    }

    public String getServices_Name() {
        return Services_Name;
    }

    public void setServices_Name(String services_Name) {
        Services_Name = services_Name;
    }

    public String getWeb_URL() {
        return Web_URL;
    }

    public void setWeb_URL(String web_URL) {
        Web_URL = web_URL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return Services_Name;
    }


}
