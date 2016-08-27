package com.aleksandrp.seeyou.main;

import android.content.Context;

/**
 * Created by AleksandrP on 18.06.2016.
 */
public interface MainActivityListener {

    void openCloseLeftBar(MainActivityView mActivityView);

    void loadData(String mNameCategory, int mNumberOfTabs, MainActivityView mActivityView);

    void goToLoader(MainActivityView mActivityView);

    void showSettingsProfile(MainActivityView mActivityView);

    void logOutFromAcc(Context mContext, MainActivityView mActivityView);

    void goToMessageActivity(MainActivityView mActivityView);

    void goToProfileActivity(MainActivityView mActivityView);

    void goToSettingsActivity(MainActivityView mActivityView);

    void goToAboutActivity(MainActivityView mActivityView);

    void loadCategory(int mPositionTab, MainActivityView mActivityView, Context mContext);

    void checkAdult(String idUser, String userPass, MainActivityView mActivityView, Context mContext);
}
