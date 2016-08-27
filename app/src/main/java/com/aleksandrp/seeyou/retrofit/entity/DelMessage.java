package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 29.06.2016.
 */
public class DelMessage {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("info")
    @Expose
    private Like info;

    public DelMessage() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String mSuccess) {
        success = mSuccess;
    }

    public Like getInfo() {
        return info;
    }

    public void setInfo(Like mInfo) {
        info = mInfo;
    }
}
