package com.dfcovid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Class_postuserdevicedetailsRequest {

 /*   {
        "ModelNo":"Redmi Note 7 Pro",
            "DeviceHeight":2131,
            "AppVersion":8,
            "DeviceSrlNo":"26cc2a89a5f127d0",
            "SIMSrlNo":"26cc2a89a5f127d0",
            "DeviceId":"f6DqdbBsmfM:APA91bH4tkRm_PnPQJcvVjySZOUgu8EO0U9jtFloFRCQ6Vb5mp_At5S2xn_cSAgBCVfL3Mz0W6hcy4eO6tP8Q1MtYSNE_2DgIoYD5x_XzCCeI-rE41WNQpW1ikjr2NERk1lW_Eb3IDcZ",
            "ServiceProvider":"airtel",
            "Manufacturer":"xiaomi",
            "OSVersion":10,
            "User_ID":12,
            "SDKVersion":29,
            "DeviceWidth":1080
    }*/
    @SerializedName("EmailId")
    @Expose
    private String emailId;
    @SerializedName("DeviceID")
    @Expose
    private String deviceID;
    @SerializedName("OSVersion")
    @Expose
    private String oSVersion;
    @SerializedName("Manufacturer")
    @Expose
    private String manufacturer;
    @SerializedName("ModelNo")
    @Expose
    private String modelNo;
    @SerializedName("SDKVersion")
    @Expose
    private String sDKVersion;
    @SerializedName("DeviceSrlNo")
    @Expose
    private String deviceSrlNo;
    @SerializedName("ServiceProvider")
    @Expose
    private String serviceProvider;
    @SerializedName("SIMSrlNo")
    @Expose
    private String sIMSrlNo;
    @SerializedName("DeviceWidth")
    @Expose
    private String deviceWidth;
    @SerializedName("DeviceHeight")
    @Expose
    private String deviceHeight;
    @SerializedName("AppVersion")
    @Expose
    private String appVersion;
    @SerializedName("User_ID")
    @Expose
    private Integer userId;
    @SerializedName("EmployeeId")
    @Expose
    private Integer employeeId;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getOSVersion() {
        return oSVersion;
    }

    public void setOSVersion(String oSVersion) {
        this.oSVersion = oSVersion;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getSDKVersion() {
        return sDKVersion;
    }

    public void setSDKVersion(String sDKVersion) {
        this.sDKVersion = sDKVersion;
    }

    public String getDeviceSrlNo() {
        return deviceSrlNo;
    }

    public void setDeviceSrlNo(String deviceSrlNo) {
        this.deviceSrlNo = deviceSrlNo;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String getSIMSrlNo() {
        return sIMSrlNo;
    }

    public void setSIMSrlNo(String sIMSrlNo) {
        this.sIMSrlNo = sIMSrlNo;
    }

    public String getDeviceWidth() {
        return deviceWidth;
    }

    public void setDeviceWidth(String deviceWidth) {
        this.deviceWidth = deviceWidth;
    }

    public String getDeviceHeight() {
        return deviceHeight;
    }

    public void setDeviceHeight(String deviceHeight) {
        this.deviceHeight = deviceHeight;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

}
