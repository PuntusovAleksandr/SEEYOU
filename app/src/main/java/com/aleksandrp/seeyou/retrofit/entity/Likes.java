package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 29.06.2016.
 */
public class Likes {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("data")
    @Expose
    private Like data;

    public Likes() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String mSuccess) {
        success = mSuccess;
    }

    public Like getData() {
        return data;
    }

    public void setData(Like mData) {
        data = mData;
    }
}


