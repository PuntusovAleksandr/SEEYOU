package com.aleksandrp.seeyou.profile;

import android.content.Intent;

import com.aleksandrp.seeyou.profile.entity.UserProfile;

/**
 * Created by AleksandrP on 20.06.2016.
 */
public interface ProfileActivityView {
    void showProgress();

    void setParams(UserProfile mUserProfile);

    void setEnableAll();

    void setImageUser(Intent mChooser, int mResultLoadImageProfile);
}
