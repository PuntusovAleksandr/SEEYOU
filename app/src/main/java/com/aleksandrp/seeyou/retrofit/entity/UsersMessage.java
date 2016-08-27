package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 29.06.2016.
 */
public class UsersMessage {


    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("subject")
    @Expose
    private String subject;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("sender_id")
    @Expose
    private String sender_id;

    @SerializedName("sender_name")
    @Expose
    private String sender_name;

    @SerializedName("date")
    @Expose
    private String date;

    public UsersMessage() {
    }

    public String getId() {
        return id;
    }

    public void setId(String mId) {
        id = mId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String mSubject) {
        subject = mSubject;
    }

    public String getText() {
        return text;
    }

    public void setText(String mText) {
        text = mText;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String mStatus) {
        status = mStatus;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String mSender_id) {
        sender_id = mSender_id;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String mSender_name) {
        sender_name = mSender_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String mDate) {
        date = mDate;
    }
}
