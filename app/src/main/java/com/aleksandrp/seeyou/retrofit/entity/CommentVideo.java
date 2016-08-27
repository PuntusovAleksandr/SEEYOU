package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 28.06.2016.
 */
public class CommentVideo {

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("relation")
    @Expose
    private String relation;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("author_name")
    @Expose
    private String author_name;

    @SerializedName("author_avatar")
    @Expose
    private String author_avatar;

    public CommentVideo() {
    }

    public String getText() {
        return text;
    }

    public void setText(String mText) {
        text = mText;
    }

    public String getType() {
        return type;
    }

    public void setType(String mType) {
        type = mType;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String mRelation) {
        relation = mRelation;
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
}
