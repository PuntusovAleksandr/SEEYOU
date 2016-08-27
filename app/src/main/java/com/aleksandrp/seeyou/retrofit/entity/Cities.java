package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AleksandrP on 28.06.2016.
 */
public class Cities {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("info")
    @Expose
    private String info;

    @SerializedName("data")
    @Expose
    private List<City> data;

    public Cities() {
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

    public List<City> getData() {
        return data;
    }

    public void setData(List<City> mData) {
        data = mData;
    }
}
