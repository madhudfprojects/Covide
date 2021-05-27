package com.dfcovid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

 /*{
         "Status": true,
         "Message": "Success",
         "lst": [
         {
         "User_Id": "76",
         "Username": "9742392362",
         "Password": "21232f297a57a5a743894a0e4a801fc3",
         "User_Type": "7",
         "PIN": "4321"
         }
         ]
         }*/

public class Class_googleloginRequest
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
