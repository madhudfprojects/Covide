package com.dfcovid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Class_loginrequest
{


    @SerializedName("Username")
    @Expose
    private String Username;

    @SerializedName("Password")
    @Expose
    private String Password;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
