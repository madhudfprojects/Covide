package com.dfcovid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
{
        "Status": true,
        "Message": "Success",
        "listVersion": [
        {
        "AppVersion": "1",
        "Response": "Success"
        }
        ]
        }
*/


public class Class_GetAppVersionList
{

    @SerializedName("AppVersion")
    @Expose
    private String appVersion;


    @SerializedName("Response")
    @Expose
    private String response;


    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
