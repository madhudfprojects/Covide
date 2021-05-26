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
            "User_Type": "NodalOfficer",
            "PIN": null
        }
    ]
}*/
public class Class_normallogin_resplist
{


    @SerializedName("User_Id")
    @Expose
    private String User_Id;

    @SerializedName("Username")
    @Expose
    private String Username;

    @SerializedName("Password")
    @Expose
    private String Password;

    @SerializedName("User_Type")
    @Expose
    private String User_Type;


    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

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

    public String getUser_Type() {
        return User_Type;
    }

    public void setUser_Type(String user_Type) {
        User_Type = user_Type;
    }
}
