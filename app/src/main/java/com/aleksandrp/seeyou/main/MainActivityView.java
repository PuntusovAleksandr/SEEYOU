package com.aleksandrp.seeyou.main;

import com.aleksandrp.seeyou.retrofit.entity.CategoryFilm;

import java.util.List;

/**
 * Created by AleksandrP on 18.06.2016.
 */
public interface MainActivityView {

    void showHideLeftBar();

    void notifyAdapterInFragment(String mNameCategory, int mNumberOfTabs);

    void goToUploadActivity();

    void showSettingsProfile();

    void logOut();

    void goToMessageActivity();

    void goToProfileActivity();

    void goToSettingsActivity();

    void goToAboutActivity();

    void setCategoriesName(List<CategoryFilm> mData);

    void setAdult(boolean adult);
}

