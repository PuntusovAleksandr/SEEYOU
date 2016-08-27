package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 29.06.2016.
 */
public class UpdateUsersProfile {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("info")
    @Expose
    private String info;

    public UpdateUsersProfile() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String mSuccess) {
        success = mSuccess;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String mInfo) {
        info = mInfo;
    }
}
