package com.dfcovid.remote;



public class Class_ApiUtils {

    //public static final String BASE_URL = "https://covid.dfindia.org/Api/";
   // https://covid.dfindia.org/Api/Authentication/Post_ValidateNormalLogin

    //http://ec2-3-1-63-28.ap-southeast-1.compute.amazonaws.com:8080/Api/
    public static final String BASE_URL = "http://ec2-3-1-63-28.ap-southeast-1.compute.amazonaws.com:8080/Api/";
    public static Interface_userservice getUserService() {
        return Class_RetrofitClient.getClient(BASE_URL).create(Interface_userservice.class);
    }


}
