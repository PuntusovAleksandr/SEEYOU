package com.aleksandrp.seeyou.social_network;

/**
 * Created by AleksandrP on 11.06.2016.
 */
public interface LoginView {

    void showProgress();

    void hideProgress();

    void setUserError();

    void setResult(String mId, String mEmail, String mDisplayName, String mS);
}
