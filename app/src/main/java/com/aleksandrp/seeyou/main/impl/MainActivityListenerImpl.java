package com.aleksandrp.seeyou.main.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.aleksandrp.seeyou.main.MainActivityListener;
import com.aleksandrp.seeyou.main.MainActivityView;
import com.aleksandrp.seeyou.retrofit.AllRequest;
import com.aleksandrp.seeyou.retrofit.entity.CategoryFilm;
import com.aleksandrp.seeyou.retrofit.impl.AllRequestImpl;
import com.aleksandrp.seeyou.utils_app.SettingsApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AleksandrP on 18.06.2016.
 */
public class MainActivityListenerImpl implements MainActivityListener {


    @Override
    public void openCloseLeftBar(MainActivityView mActivityView) {
        mActivityView.showHideLeftBar();
    }

    @Override
    public void loadData(String mNameCategory, int mNumberOfTabs, MainActivityView mActivityView) {
        mActivityView.notifyAdapterInFragment(mNameCategory, mNumberOfTabs);
    }

    @Override
    public void goToLoader(MainActivityView mActivityView) {
        mActivityView.goToUploadActivity();
    }

    @Override
    public void showSettingsProfile(MainActivityView mActivityView) {
        mActivityView.showSettingsProfile();
    }

    @Override
    public void logOutFromAcc(Context mContext, MainActivityView mActivityView) {
        SharedPreferences sharedPreferences =
                mContext.getSharedPreferences(SettingsApp.FILE_NAME, Context.MODE_PRIVATE);
        SettingsApp.setUserMail("", sharedPreferences);
        SettingsApp.setUserPass("", sharedPreferences);
        SettingsApp.setUserId("", sharedPreferences);
        SettingsApp.setUserName("", sharedPreferences);
        SettingsApp.setUserAvatar("", sharedPreferences);
        mActivityView.logOut();
    }

    @Override
    public void goToMessageActivity(MainActivityView mActivityView) {
        mActivityView.goToMessageActivity();
    }

    @Override
    public void goToProfileActivity(MainActivityView mActivityView) {
        mActivityView.goToProfileActivity();
    }

    @Override
    public void goToSettingsActivity(MainActivityView mActivityView) {
        mActivityView.goToSettingsActivity();
    }

    @Override
    public void goToAboutActivity(MainActivityView mActivityView) {
        mActivityView.goToAboutActivity();
    }

    @Override
    public void loadCategory(int mPositionTab, MainActivityView mActivityView, Context mContext) {
        List<CategoryFilm> videoCategories = new ArrayList<>();
        AllRequest request = new AllRequestImpl(mContext);
        switch (mPositionTab) {
            case 0:
                videoCategories = request.getVideoCategories(mActivityView);
                break;

            case 1:
                videoCategories = request.getBroadcastCategories(mActivityView);
                break;

            case 2:
                videoCategories = new ArrayList<>();
                mActivityView.setCategoriesName(videoCategories);
                break;
        }
    }

    @Override
    public void checkAdult(String idUser, String userPass, MainActivityView mActivityView, Context mContext) {
        AllRequest request = new AllRequestImpl(mContext);
        request.checkAdult(idUser, userPass, mActivityView);
    }
}
