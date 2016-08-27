package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 28.06.2016.
 */
public class CategoryFilm {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("slug")
    @Expose
    private String slug;

    @SerializedName("name")
    @Expose
    private String name;

    public CategoryFilm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String mId) {
        id = mId;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String mSlug) {
        slug = mSlug;
    }

    public String getName() {
        return name;
    }

    public void setName(String mName) {
        name = mName;
    }
}
