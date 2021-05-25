package com.dfcovid.remote;


import com.dfcovid.Class_loginrequest;
import com.dfcovid.Class_normalloginresponse;
import com.dfcovid.model.Class_DashboardHospitalData;
import com.dfcovid.model.Class_Get_LoadHospitalDataResponse;
import com.dfcovid.model.Class_Get_UserHospitalListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @GET("Authentication/Get_UserHospitalList")
    Call<Class_Get_UserHospitalListResponse>GetUserHospitalList(@Query("User_ID") String UserId);

    //https://covid.dfindia.org/Api/Authentication/Get_LoadHospitalData?Hospital_Id=42
    @GET("Authentication/Get_LoadHospitalData")
    Call<Class_Get_LoadHospitalDataResponse>LoadHospitalData(@Query("Hospital_Id") String Hospital_Id);

    @GET("Authentication/Get_LoadHospitalDashboard")
    Call<Class_DashboardHospitalData>Get_LoadHospitalDashboard(@Query("Hospital_Id") String Hospital_Id);


}
