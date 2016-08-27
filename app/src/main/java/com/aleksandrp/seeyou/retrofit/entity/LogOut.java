package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 28.06.2016.
 */
public class LogOut {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("info")
    @Expose
    private String info;

    public LogOut() {
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
