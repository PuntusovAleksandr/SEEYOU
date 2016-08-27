package com.aleksandrp.seeyou.login;

/**
 * Created by AleksandrP on 17.06.2016.
 */
public interface LoginActivityView {

    void setLoginFragment();

    void setRegisterFragment();

    void goToMainActivity();

    void showProgress();

    void hideProgress();

}
