package com.aleksandrp.seeyou.retrofit.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AleksandrP on 18.07.2016.
 */
public class UsersMessageOnLine {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;

    @SerializedName("birthday")
    @Expose
    private String birthday;

    @SerializedName("avatar")
    @Expose
    private String avatar;

    private String countMessage;

    public UsersMessageOnLine() {
    }

    public UsersMessageOnLine(String mId, String mName, String mSurname, String mBirthday,
                              String mAvatar, String mCountMessage) {
        id = mId;
        name = mName;
        surname = mSurname;
        birthday = mBirthday;
        avatar = mAvatar;
        countMessage = mCountMessage;
    }

    public String getCountMessage() {
        return countMessage;
    }

    public void setCountMessage(String mCountMessage) {
        countMessage = mCountMessage;
    }

    public String getId() {
        return id;
    }

    public void setId(String mId) {
        id = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String mName) {
        name = mName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String mSurname) {
        surname = mSurname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String mBirthday) {
        birthday = mBirthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String mAvatar) {
        avatar = mAvatar;
    }
}
