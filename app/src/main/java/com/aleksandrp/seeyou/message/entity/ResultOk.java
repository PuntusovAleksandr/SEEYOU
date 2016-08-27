package com.aleksandrp.seeyou.message.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 22.08.2016.
 */
public class ResultOk {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("info")
    @Expose
    private String info;

    public ResultOk() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String mStatus) {
        status = mStatus;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String mInfo) {
        info = mInfo;
    }
}

