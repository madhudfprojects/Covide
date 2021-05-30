package com;
/*{
"Status": true,
        "Message": "success",
        "list": [
        {
        "Service_Id": 1,
        "Services_Name": "Vaccine Centers",
        "Web_URL": "",
        "status": "success"
        },
        {
        "Service_Id": 2,
        "Services_Name": "Covid Testing Centers",
        "Web_URL": "",
        "status": "success"*/

import com.dfcovid.Class_normallogin_resplist;
import com.dfcovid.Class_serviceslistResp;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Class_servicesdetailsResp
{

    @SerializedName("Status")
    @Expose
    private Boolean status;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("lst")
    @Expose
    private List<Class_serviceslistResp> lst = null;


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Class_serviceslistResp> getLst() {
        return lst;
    }

    public void setLst(List<Class_serviceslistResp> lst) {
        this.lst = lst;
    }
}
