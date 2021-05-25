package com.dfcovid.remote;


import com.dfcovid.Class_loginrequest;
import com.dfcovid.Class_normalloginresponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Interface_userservice {

  /*  @POST("api/Authentication/ValidateLogin")
    @FormUrlEncoded*/


   // @Headers("Content-Type: application/json")



   /* @POST("Authentication/Post_ValidateNormalLogin")
    @FormUrlEncoded
    Call<> getValidateLoginPostNew(@Field("User_Email") String userEmail, @Field("User_Version") String app_version);*/


    // https://covid.dfindia.org/Api/Authentication/Post_ValidateNormalLogin
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("Authentication/Post_ValidateNormalLogin")
    Call<Class_normalloginresponse>Post_ValidateNormalLogin(@Body Class_loginrequest request);




}
