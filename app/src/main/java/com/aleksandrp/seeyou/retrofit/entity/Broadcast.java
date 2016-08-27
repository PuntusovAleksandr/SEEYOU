package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 29.06.2016.
 */
public class Broadcast {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("src")
    @Expose
    private String src;

    @SerializedName("category_id")
    @Expose
    private String category_id;

    @SerializedName("club_id")
    @Expose
    private String club_id;

    @SerializedName("city_id")
    @Expose
    private String city_id;

    @SerializedName("country_id")
    @Expose
    private String country_id;

    @SerializedName("views")
    @Expose
    private String views;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("is_adult")
    @Expose
    private String is_adult;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("start_time")
    @Expose
    private String start_time;

    @SerializedName("end_time")
    @Expose
    private String end_time;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("category_slug")
    @Expose
    private String category_slug;

    @SerializedName("category_name")
    @Expose
    private String category_name;

    @SerializedName("club_name")
    @Expose
    private String club_name;

    public Broadcast() {
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

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String mCategory_id) {
        category_id = mCategory_id;
    }

    public String getClub_id() {
        return club_id;
    }

    public void setClub_id(String mClub_id) {
        club_id = mClub_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String mCity_id) {
        city_id = mCity_id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String mCountry_id) {
        country_id = mCountry_id;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String mViews) {
        views = mViews;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String mStatus) {
        status = mStatus;
    }

    public String getIs_adult() {
        return is_adult;
    }

    public void setIs_adult(String mIs_adult) {
        is_adult = mIs_adult;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        title = mTitle;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String mStart_time) {
        start_time = mStart_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String mEnd_time) {
        end_time = mEnd_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String mDescription) {
        description = mDescription;
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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String mCategory_name) {
        category_name = mCategory_name;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String mClub_name) {
        club_name = mClub_name;
    }
}
