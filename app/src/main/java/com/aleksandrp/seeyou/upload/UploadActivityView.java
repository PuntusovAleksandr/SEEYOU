package com.aleksandrp.seeyou.upload;

import android.content.Intent;

import com.aleksandrp.seeyou.retrofit.entity.CategoryFilm;

import java.util.List;

/**
 * Created by AleksandrP on 19.06.2016.
 */
public interface UploadActivityView {
    void showProfileSettings();

    void signOut();


    void startActivityGallery(Intent mGalleryIntent, int mResultLoadImage);

    void setVideoToBackground(String mSelectedImagePath);

    void goToMessageActivity();

    void goToProfileActivity();

    void goToSettingsActivity();

    void goToAboutActivity();

    void showProgress();

    void hideProgress();

    void disableAll();

    void enableAll();

    void destroyActivity();

    void setCategory();

    void setCategoriesName(List<CategoryFilm> mData);
}
