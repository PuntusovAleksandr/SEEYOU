package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 28.06.2016.
 */
public class VideoSEEYOU {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("video_src")
    @Expose
    private String video_src;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("views")
    @Expose
    private String views;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("author_name")
    @Expose
    private String author_name;

    @SerializedName("author_avatar")
    @Expose
    private String author_avatar;

    @SerializedName("category_slug")
    @Expose
    private String category_slug;

    @SerializedName("category_name")
    @Expose
    private String category_name;

    @SerializedName("likes")
    @Expose
    private String likes;


    @SerializedName("dislikes")
    @Expose
    private String dislikes;

    public VideoSEEYOU() {
    }

    public String getId() {
        return id;
    }

    public void setId(String mId) {
        id = mId;
    }

    public String getVideo_src() {
        return video_src;
    }

    public void setVideo_src(String mVideo_src) {
        video_src = mVideo_src;
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

    public String getViews() {
        return views;
    }

    public void setViews(String mViews) {
        views = mViews;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String mCreated_at) {
        created_at = mCreated_at;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String mAuthor_name) {
        author_name = mAuthor_name;
    }

    public String getAuthor_avatar() {
        return author_avatar;
    }

    public void setAuthor_avatar(String mAuthor_avatar) {
        author_avatar = mAuthor_avatar;
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

    public String getLikes() {
        return likes;
    }

    public void setLikes(String mLikes) {
        likes = mLikes;
    }

    public String getDislikes() {
        return dislikes;
    }

    public void setDislikes(String mDislikes) {
        dislikes = mDislikes;
    }
}
