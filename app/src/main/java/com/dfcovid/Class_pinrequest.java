package com.dfcovid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//"User_Id":"76","PIN":"4321"
public class Class_pinrequest
{


    @SerializedName("User_Id")
    @Expose
    private String User_Id;

    @SerializedName("PIN")
    @Expose
    private String PIN;

    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }
}
