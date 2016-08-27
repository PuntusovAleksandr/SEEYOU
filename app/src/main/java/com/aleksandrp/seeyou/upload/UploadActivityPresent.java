package com.aleksandrp.seeyou.upload;

import android.content.Context;

import com.aleksandrp.seeyou.retrofit.entity.CategoryFilm;
import com.aleksandrp.seeyou.upload.entity.DataFile;

import java.util.List;

/**
 * Created by AleksandrP on 19.06.2016.
 */
public interface UploadActivityPresent {
    void selectFileFromDevice(UploadActivityView mActivityView);

    void showDialogSelectCategorry(UploadActivityView mActivityView);

    void saveInSettingsSMSNotification(UploadActivityView mActivityView);

    void saveInSettingsLicense(UploadActivityView mActivityView);

    void startUploadVideo(Context mContext, UploadActivityView mActivityView, DataFile mDataFile);

    void showProfileSetings(UploadActivityView mActivityView);

    void signOutFromAcc(Context mContext, UploadActivityView mActivityView);

    void setVideoToBackground(String mSelectedImagePath, UploadActivityView mActivityView);

    void goToMessageActivity(UploadActivityView mActivityView);

    void goToProfileActivity(UploadActivityView mActivityView);

    void goToSettingsActivity(UploadActivityView mActivityView);

    void goToAboutActivity(UploadActivityView mActivityView);

    void setCategoriesName(List<CategoryFilm> mData, UploadActivityView mActivityView);
}
