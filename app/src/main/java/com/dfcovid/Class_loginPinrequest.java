package com.dfcovid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//{"Username":"9742392362","PIN":"4321"}
public class Class_loginPinrequest
{

    @SerializedName("Username")
    @Expose
    private String Username;

    @SerializedName("PIN")
    @Expose
    private String PIN;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }
}
