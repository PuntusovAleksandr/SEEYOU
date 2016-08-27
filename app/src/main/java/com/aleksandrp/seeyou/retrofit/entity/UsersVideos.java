package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AleksandrP on 29.06.2016.
 */
public class UsersVideos {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("data")
    @Expose
    private List<UsersVideo> data;

    public UsersVideos() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String mSuccess) {
        success = mSuccess;
    }

    public List<UsersVideo> getData() {
        return data;
    }

    public void setData(List<UsersVideo> mData) {
        data = mData;
    }
}
