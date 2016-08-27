package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 29.06.2016.
 */
public class ActionCode {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("action_code")
    @Expose
    private String action_code;

    public ActionCode() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String mSuccess) {
        success = mSuccess;
    }

    public String getAction_code() {
        return action_code;
    }

    public void setAction_code(String mAction_code) {
        action_code = mAction_code;
    }
}
