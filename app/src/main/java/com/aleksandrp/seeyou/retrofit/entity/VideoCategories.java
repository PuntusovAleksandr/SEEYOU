package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AleksandrP on 28.06.2016.
 */
public class VideoCategories {


    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("data")
    @Expose
    private List<CategoryFilm> data;

    public VideoCategories() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String mSuccess) {
        success = mSuccess;
    }

    public List<CategoryFilm> getData() {
        return data;
    }

    public void setData(List<CategoryFilm> mData) {
        data = mData;
    }
}
