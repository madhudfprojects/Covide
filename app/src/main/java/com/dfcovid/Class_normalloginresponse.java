package com.dfcovid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/* "Status": true,
           "Message": "Success",
           "lst": [
           {
           "Username": "madhushree.kubsad@dfmail.org",
           "Password": "21232f297a57a5a743894a0e4a801fc3",
           "User_Type": "SuperAdmin"
           }
           ]
           }*/
public class Class_normalloginresponse
  {

      @SerializedName("Status")
      @Expose
      private Boolean status;
      @SerializedName("Message")
      @Expose
      private String message;
      @SerializedName("lst")
      @Expose
      private List<Class_normallogin_resplist> lst = null;

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

      public List<Class_normallogin_resplist> getLst() {
          return lst;
      }

      public void setLst(List<Class_normallogin_resplist> lst) {
          this.lst = lst;
      }
  }
