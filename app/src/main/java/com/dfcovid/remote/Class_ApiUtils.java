package com.dfcovid.remote;



public class Class_ApiUtils {

    public static final String BASE_URL = "https://covid.dfindia.org/Api/";//
   // https://covid.dfindia.org/Api/Authentication/Post_ValidateNormalLogin

    public static Interface_userservice getUserService() {
        return Class_RetrofitClient.getClient(BASE_URL).create(Interface_userservice.class);
    }


}
