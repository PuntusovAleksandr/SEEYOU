package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 28.06.2016.
 */
public class AddComment {


    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("info")
    @Expose
    private String info;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("html")
    @Expose
    private String html;

    public AddComment() {
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

    public String getType() {
        return type;
    }

    public void setType(String mType) {
        type = mType;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String mHtml) {
        html = mHtml;
    }
}
