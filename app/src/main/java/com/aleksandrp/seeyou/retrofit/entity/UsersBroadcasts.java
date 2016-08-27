package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AleksandrP on 29.06.2016.
 */
public class UsersBroadcasts {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("data")
    @Expose
    private List<UsersBroadcast> data;

    public UsersBroadcasts() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String mSuccess) {
        success = mSuccess;
    }

    public List<UsersBroadcast> getData() {
        return data;
    }

    public void setData(List<UsersBroadcast> mData) {
        data = mData;
    }
}
