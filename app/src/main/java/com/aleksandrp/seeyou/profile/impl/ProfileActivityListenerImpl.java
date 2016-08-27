package com.aleksandrp.seeyou.profile.impl;

import android.content.Intent;

import com.aleksandrp.seeyou.profile.ProfileActivityListener;
import com.aleksandrp.seeyou.profile.ProfileActivityView;
import com.aleksandrp.seeyou.profile.entity.UserProfile;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;

/**
 * Created by AleksandrP on 20.06.2016.
 */
public class ProfileActivityListenerImpl implements ProfileActivityListener {
    @Override
    public void loadData(ProfileActivityView mActivityView) {
        mActivityView.showProgress();
        // TODO: 20.06.2016 make load data from server
        UserProfile userProfile = new UserProfile(
                "http://cdn.1001freedownloads.com/icon/thumb/341521/UserMessage-icon.png",
                "Igor",
                "Pupcov",
                "Zl@zz.zz",
                "12-07-12",
                "Ukraine",
                "Dnepr",
                STATICS_PARAMS.SEX_MAN
        );
        mActivityView.setParams(userProfile);
        mActivityView.showProgress();
    }

    @Override
    public void enableAll(ProfileActivityView mActivityView) {
        mActivityView.setEnableAll();
    }

    @Override
    public void sendDataToServer(UserProfile mUserProfile) {
        // TODO: 20.06.2016 sendData to server
    }

    @Override
    public void setImageUser(ProfileActivityView mActivityView) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityView.setImageUser(intent,
                STATICS_PARAMS.RESULT_LOAD_IMAGE_PROFILE);
    }
}
