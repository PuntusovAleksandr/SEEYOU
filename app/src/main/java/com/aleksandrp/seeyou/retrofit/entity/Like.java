package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 29.06.2016.
 */
public class Like {

    @SerializedName("likes")
    @Expose
    private String likes;

    public Like() {
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String mLikes) {
        likes = mLikes;
    }
}
