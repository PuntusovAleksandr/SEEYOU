package com.aleksandrp.seeyou.profile;

import com.aleksandrp.seeyou.profile.entity.UserProfile;

/**
 * Created by AleksandrP on 20.06.2016.
 */
public interface ProfileActivityListener {
    void loadData(ProfileActivityView mActivityView);

    void enableAll(ProfileActivityView mActivityView);

    void sendDataToServer(UserProfile mUserProfile);

    void setImageUser(ProfileActivityView mActivityView);
}
