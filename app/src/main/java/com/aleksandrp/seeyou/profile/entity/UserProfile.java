package com.aleksandrp.seeyou.profile.entity;

/**
 * Created by AleksandrP on 20.06.2016.
 */
public class UserProfile {

    private String pathIcon;
    private String name;
    private String surName;
    private String e_mail;
    private String date;
    private String country;
    private String city;
    private String sex;

    public UserProfile() {
    }

    public UserProfile(String mPathIcon, String mName, String mSurName, String mE_mail,
                       String mDate, String mCountry, String mCity, String mSex) {
        pathIcon = mPathIcon;
        name = mName;
        surName = mSurName;
        e_mail = mE_mail;
        date = mDate;
        country = mCountry;
        city = mCity;
        sex = mSex;
    }

    public String getPathIcon() {
        return pathIcon;
    }

    public void setPathIcon(String mPathIcon) {
        pathIcon = mPathIcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String mName) {
        name = mName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String mSurName) {
        surName = mSurName;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String mE_mail) {
        e_mail = mE_mail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String mDate) {
        date = mDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String mCountry) {
        country = mCountry;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String mCity) {
        city = mCity;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String mSex) {
        sex = mSex;
    }
}
