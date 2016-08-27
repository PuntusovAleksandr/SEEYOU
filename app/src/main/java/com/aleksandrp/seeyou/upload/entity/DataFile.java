package com.aleksandrp.seeyou.upload.entity;

/**
 * Created by AleksandrP on 19.06.2016.
 */
public class DataFile {

    private String path;
    private String title;
    private String description;
    private String category;
    private String phoneNumber;
    private boolean isSms;
    private boolean isAgree;

    public DataFile() {
    }

    public DataFile(String mPath, String mTitle, String mDescription, String mCategory,
                    String mPhoneNumber, boolean mIsSms, boolean mIsAgree) {
        path = mPath;
        title = mTitle;
        description = mDescription;
        category = mCategory;
        phoneNumber = mPhoneNumber;
        isSms = mIsSms;
        isAgree = mIsAgree;
    }

    public boolean isAgree() {
        return isAgree;
    }

    public void setAgree(boolean mAgree) {
        isAgree = mAgree;
    }

    public boolean isSms() {
        return isSms;
    }

    public void setSms(boolean mSms) {
        isSms = mSms;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String mPath) {
        path = mPath;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String mCategory) {
        category = mCategory;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String mPhoneNumber) {
        phoneNumber = mPhoneNumber;
    }
}
