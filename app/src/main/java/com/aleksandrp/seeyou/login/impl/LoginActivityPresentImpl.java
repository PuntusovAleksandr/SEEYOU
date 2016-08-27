package com.aleksandrp.seeyou.login.impl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.login.LoginActivity;
import com.aleksandrp.seeyou.login.LoginActivityPresent;
import com.aleksandrp.seeyou.login.LoginActivityView;
import com.aleksandrp.seeyou.retrofit.AllRequest;
import com.aleksandrp.seeyou.retrofit.impl.AllRequestImpl;
import com.aleksandrp.seeyou.social_network.SocialNetworkActivity;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;
import com.aleksandrp.seeyou.utils_app.SettingsApp;

/**
 * Created by AleksandrP on 17.06.2016.
 */
public class LoginActivityPresentImpl implements LoginActivityPresent,
        AllRequestImpl.SignInOK{

    private Context mContext;
    private LoginActivityView mActivityView;

    private SharedPreferences mSharedPreferences;

    private AllRequest mRequest;


    public LoginActivityPresentImpl(Context mContext, LoginActivityView mActivityView) {
        this.mContext = mContext;
        this.mActivityView = mActivityView;
        mSharedPreferences =
                mContext.getSharedPreferences(SettingsApp.FILE_NAME, Context.MODE_PRIVATE);
        mRequest = new AllRequestImpl(mContext);
    }

    @Override
    public void checkLogin() {
        mActivityView.showProgress();

        String userMail = SettingsApp.getUserMail(mSharedPreferences);
        String userPass = SettingsApp.getUserPass(mSharedPreferences);
        connectSinIn(userMail, userPass);
    }

    @Override
    public void signInWithAsne(String mKeySocNet) {
        sign_registeringInWithAsne(mKeySocNet);
    }

    @Override
    public void registeringWithAsne(String mKeySocNet) {
        sign_registeringInWithAsne(mKeySocNet);
    }

    private void sign_registeringInWithAsne(String mKeySocNet) {
        Intent intent = new Intent(mContext, SocialNetworkActivity.class);
        intent.putExtra(STATICS_PARAMS.KEY_SOCIAL_NETWORK, mKeySocNet);
        LoginActivity activity = (LoginActivity) mContext;
        activity.startActivityForResult(intent, STATICS_PARAMS.REQUEST_CODE_ACTIVITY);
    }

    @Override
    public void signIn(String mail, String pass) {
        mActivityView.showProgress();
        connectSinIn(mail, pass);
    }

    @Override
    public void registering() {
        mActivityView.setRegisterFragment();
    }

    private void connectSinIn(String mUserMail, String mUserPass) {
        if (mUserMail.length() > 0 && mUserPass.length() > 5) {
            mRequest.signInUser(mUserMail, mUserPass, this);
        } else {
            mActivityView.hideProgress();
            mActivityView.setLoginFragment();
        }
    }

    @Override
    public void okSignIn() {
        mActivityView.hideProgress();
        mActivityView.goToMainActivity();
    }

    @Override
    public void errorSignIn() {
        mActivityView.hideProgress();
        mActivityView.setLoginFragment();
        Toast.makeText(mContext, R.string.account_not_found, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorRegister() {
//        no do
    }
}
