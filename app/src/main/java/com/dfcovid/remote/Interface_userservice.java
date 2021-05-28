package com.dfcovid.remote;


import com.dfcovid.Class_GetAppVersion;
import com.dfcovid.Class_forgotrequest;
import com.dfcovid.Class_googleloginRequest;
import com.dfcovid.Class_loginPinrequest;
import com.dfcovid.Class_loginrequest;
import com.dfcovid.Class_normalloginresponse;
import com.dfcovid.Class_pinrequest;
import com.dfcovid.model.Class_DashboardHospitalData;
import com.dfcovid.model.Class_Get_LoadHospitalDataResponse;
import com.dfcovid.model.Class_Get_UserHospitalListResponse;
import com.dfcovid.model.Class_PostSaveHospitalResponse;
import com.dfcovid.model.Class_PostSaveHospitalResponseRequest;

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

    //https://covid.dfindia.org/Api/Authentication/Post_ValidateLogin
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("Authentication/Post_ValidateLogin")
    Call<Class_normalloginresponse>Post_ValidateGoogleLogin(@Body Class_googleloginRequest request);


    //https://covid.dfindia.org/api/Authentication/Post_UpdateUserPIN
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("Authentication/Post_UpdateUserPIN")
    Call<Class_normalloginresponse>Post_UpdateUserPIN(@Body Class_pinrequest request);


    //https://covid.dfindia.org/api/Authentication/Post_ValidateUserPIN
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("Authentication/Post_ValidateUserPIN")
    Call<Class_normalloginresponse>Post_ValidateUserPIN(@Body Class_loginPinrequest request);


    //https://covid.dfindia.org/Api/Authentication/Post_ForgetPassword
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("Authentication/Post_ForgetPassword")
    Call<Class_normalloginresponse>Post_ForgetPassword(@Body Class_forgotrequest request);


    //https://covid.dfindia.org/api/Authentication/Get_App_Version
    @GET("Authentication/Get_App_Version")
    Call<Class_GetAppVersion> Get_App_Version();


    @GET("Authentication/Get_UserHospitalList")
    Call<Class_Get_UserHospitalListResponse>GetUserHospitalList(@Query("User_ID") String UserId);

    //https://covid.dfindia.org/Api/Authentication/Get_LoadHospitalData?Hospital_Id=42
    @GET("Authentication/Get_LoadHospitalData")
    Call<Class_Get_LoadHospitalDataResponse>LoadHospitalData(@Query("Hospital_Id") String Hospital_Id);

    @GET("Authentication/Get_LoadHospitalDashboard")
    Call<Class_DashboardHospitalData>Get_LoadHospitalDashboard(@Query("Hospital_Id") String Hospital_Id);

//changed by shivaleela
   // https://covid.dfindia.org//Api/Authentication/Get_LoadHospitalDataDate?Hospital_Id=42&dates=2021-05-27
    //dates
    @GET("Authentication/Get_LoadHospitalDashboardDate")
    Call<Class_DashboardHospitalData>Get_LoadHospitalDataDate(@Query("Hospital_Id") String Hospital_Id,@Query("Entry_Date") String dates);

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("Authentication/Post_SaveHospital")
    Call<Class_PostSaveHospitalResponse>PostSaveHospital(@Body Class_PostSaveHospitalResponseRequest request);


}
