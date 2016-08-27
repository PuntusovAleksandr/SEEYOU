package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 28.06.2016.
 */
public class DisLike {

    @SerializedName("dislikes")
    @Expose
    private String dislikes;

    public DisLike() {
    }

    public String getDislikes() {
        return dislikes;
    }

    public void setDislikes(String mDislikes) {
        dislikes = mDislikes;
    }
}
