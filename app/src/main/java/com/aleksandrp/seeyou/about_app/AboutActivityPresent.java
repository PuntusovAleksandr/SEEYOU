package com.aleksandrp.seeyou.about_app;

/**
 * Created by AleksandrP on 21.06.2016.
 */
public interface AboutActivityPresent {
    void showContactsFragment(AboutActivityView mActivityView);

    void showAboutFragment(AboutActivityView mActivityView);

    void showCooperationFragment(AboutActivityView mActivityView);

    void showTermsFragment(AboutActivityView mActivityView);

    void showConfidentialityFragment(AboutActivityView mActivityView);

    void showHelpFragment(AboutActivityView mActivityView);
}
