package com.dfcovid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//"Username":""
public class Class_forgotrequest
{

    @SerializedName("Username")
    @Expose
    private String Username;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
