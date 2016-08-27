package com.aleksandrp.seeyou.about_app.impl;

import com.aleksandrp.seeyou.about_app.AboutActivityPresent;
import com.aleksandrp.seeyou.about_app.AboutActivityView;

/**
 * Created by AleksandrP on 21.06.2016.
 */
public class AboutActivityPresentImpl implements AboutActivityPresent {
    @Override
    public void showContactsFragment(AboutActivityView mActivityView) {
        mActivityView.setContactsFragment();
    }

    @Override
    public void showAboutFragment(AboutActivityView mActivityView) {
        mActivityView.setAboutFragment();
    }

    @Override
    public void showCooperationFragment(AboutActivityView mActivityView) {
        mActivityView.setCooperationFragment();
    }

    @Override
    public void showTermsFragment(AboutActivityView mActivityView) {
        mActivityView.setCTermsFragment();
    }

    @Override
    public void showConfidentialityFragment(AboutActivityView mActivityView) {
        mActivityView.setConfidentialityFragment();
    }

    @Override
    public void showHelpFragment(AboutActivityView mActivityView) {
        mActivityView.setHelpFragment();
    }
}
