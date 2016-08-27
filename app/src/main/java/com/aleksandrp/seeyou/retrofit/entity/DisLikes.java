package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 28.06.2016.
 */
public class DisLikes {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("data")
    @Expose
    private DisLike data;

    public DisLikes() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String mSuccess) {
        success = mSuccess;
    }

    public DisLike getData() {
        return data;
    }

    public void setData(DisLike mData) {
        data = mData;
    }
}
