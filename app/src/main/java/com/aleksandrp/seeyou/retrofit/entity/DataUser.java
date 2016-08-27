package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 28.06.2016.
 */
public class DataUser {

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("user_name")
    @Expose
    private String user_name;

    @SerializedName("user_avatar")
    @Expose
    private String user_avatar;

    public DataUser() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String mUser_id) {
        user_id = mUser_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String mUser_name) {
        user_name = mUser_name;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String mUser_avatar) {
        user_avatar = mUser_avatar;
    }
}
