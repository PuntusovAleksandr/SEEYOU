package com.aleksandrp.seeyou.upload.impl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.Toast;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.retrofit.entity.CategoryFilm;
import com.aleksandrp.seeyou.upload.UploadActivityPresent;
import com.aleksandrp.seeyou.upload.UploadActivityView;
import com.aleksandrp.seeyou.upload.entity.DataFile;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;
import com.aleksandrp.seeyou.utils_app.SettingsApp;

import java.util.List;

/**
 * Created by AleksandrP on 19.06.2016.
 */
public class UploadActivityPresentImpl implements UploadActivityPresent {
    @Override
    public void selectFileFromDevice(UploadActivityView mActivityView) {
//        final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
//        galleryIntent.setType("*/*");
//        mActivityView.startActivityGallery(galleryIntent, STATICS_PARAMS.RESULT_LOAD_IMAGE);
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityView.startActivityGallery(Intent.createChooser(intent, "Select Video"),
                STATICS_PARAMS.RESULT_LOAD_IMAGE);


    }

    @Override
    public void showDialogSelectCategorry(UploadActivityView mActivityView) {
        mActivityView.setCategory();
    }

    @Override
    public void saveInSettingsSMSNotification(UploadActivityView mActivityView) {

    }

    @Override
    public void saveInSettingsLicense(UploadActivityView mActivityView) {

    }

    @Override
    public void startUploadVideo(Context mContext, final UploadActivityView mActivityView, DataFile mDataFile) {
        if (checkData(mContext, mDataFile)) {
            mActivityView.disableAll();
            mActivityView.showProgress();

            // TODO: 19.06.2016 need send data to server
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mActivityView.hideProgress();
                    mActivityView.enableAll();
                    mActivityView.destroyActivity();
                }
            }, 1000);
        }
    }

    private boolean checkData(Context mContext, DataFile mDataFile) {
        if (mDataFile == null) {
            return false;
        }
        if (mDataFile.getPath() == null || mDataFile.getPath().length() <= 0) {
            showMessage(mContext, mContext.getResources().getString(R.string.fill_path));
            return false;
        }
        if (mDataFile.getTitle() == null || mDataFile.getTitle().length() <= 0) {
            showMessage(mContext, mContext.getResources().getString(R.string.fill_title));
            return false;
        }
        if (mDataFile.getDescription() == null || mDataFile.getDescription().length() <= 0) {
            showMessage(mContext, mContext.getResources().getString(R.string.fill_description));
            return false;
        }
        if (mDataFile.getCategory() == null || mDataFile.getCategory().length() <= 0) {
            showMessage(mContext, mContext.getResources().getString(R.string.fill_category));
            return false;
        }
        if (mDataFile.isSms() && mDataFile.getPhoneNumber() == null ||
                mDataFile.isSms() && mDataFile.getPhoneNumber().length() <= 0) {
            showMessage(mContext, mContext.getResources().getString(R.string.fill_phone));
            return false;
        }
        if (!mDataFile.isAgree()) {
            showMessage(mContext, mContext.getResources().getString(R.string.fill_agree));
            return false;
        }
        return true;
    }

    private void showMessage(Context mContext, String mString) {
        Toast.makeText(mContext, mString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProfileSetings(UploadActivityView mActivityView) {
        mActivityView.showProfileSettings();
    }

    @Override
    public void signOutFromAcc(Context mContext, UploadActivityView mActivityView) {
        SharedPreferences sharedPreferences =
                mContext.getSharedPreferences(SettingsApp.FILE_NAME, Context.MODE_PRIVATE);
        SettingsApp.setUserMail("", sharedPreferences);
        SettingsApp.setUserPass("", sharedPreferences);
        SettingsApp.setUserId("", sharedPreferences);
        SettingsApp.setUserName("", sharedPreferences);
        SettingsApp.setUserAvatar("", sharedPreferences);
        mActivityView.signOut();
    }

    @Override
    public void setVideoToBackground(String mSelectedImagePath, UploadActivityView mActivityView) {
        mActivityView.setVideoToBackground(mSelectedImagePath);
    }

    @Override
    public void goToMessageActivity(UploadActivityView mActivityView) {
        mActivityView.goToMessageActivity();
    }

    @Override
    public void goToProfileActivity(UploadActivityView mActivityView) {
        mActivityView.goToProfileActivity();
    }

    @Override
    public void goToSettingsActivity(UploadActivityView mActivityView) {
        mActivityView.goToSettingsActivity();
    }

    @Override
    public void goToAboutActivity(UploadActivityView mActivityView) {
        mActivityView.goToAboutActivity();
    }

    @Override
    public void setCategoriesName(List<CategoryFilm> mData, UploadActivityView mActivityView) {
        mActivityView.setCategoriesName(mData);
    }
}
