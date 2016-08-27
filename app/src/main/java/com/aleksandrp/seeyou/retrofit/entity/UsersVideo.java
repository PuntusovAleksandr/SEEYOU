package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 29.06.2016.
 */
public class UsersVideo {


    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("src")
    @Expose
    private String src;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("category_slug")
    @Expose
    private String category_slug;


    public UsersVideo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String mId) {
        id = mId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String mSrc) {
        src = mSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        title = mTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String mDescription) {
        description = mDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String mStatus) {
        status = mStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String mDate) {
        date = mDate;
    }

    public String getCategory_slug() {
        return category_slug;
    }

    public void setCategory_slug(String mCategory_slug) {
        category_slug = mCategory_slug;
    }
}
