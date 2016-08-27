package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AleksandrP on 28.06.2016.
 */
public class Comments {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("data")
    @Expose
    private List<CommentVideo> data;

    public Comments() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String mSuccess) {
        success = mSuccess;
    }

    public List<CommentVideo> getData() {
        return data;
    }

    public void setData(List<CommentVideo> mData) {
        data = mData;
    }
}
