package com.dfcovid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*{
        "Status": true,
        "Message": "Success",
        "listVersion": [
        {
        "AppVersion": "1",
        "Response": "Success"
        }
        ]
        }*/

import java.util.List;

public class Class_GetAppVersion
{
    @SerializedName("Status")
    @Expose
    private Boolean status;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("listVersion")
    @Expose
    private List<Class_GetAppVersionList> listVersion = null;

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

    public List<Class_GetAppVersionList> getlistVersion() {
        return listVersion;
    }

    public void setlistVersion(List<Class_GetAppVersionList> lstFarmer) {
        this.listVersion = lstFarmer;
    }

}
