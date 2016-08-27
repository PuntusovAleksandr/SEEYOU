package com.aleksandrp.seeyou.social_network;

import android.content.Context;
import android.content.Intent;

/**
 * Created by AleksandrP on 11.06.2016.
 */
public interface LoginPresenter {

    void connectToFB(Context mContext);

    void connectToTwitter();

    void connectToGOOGLE();


    void onActivityResultFB(int mRequestCode, int mResultCode, Intent mData);

    void onActivityResultGoogle(int mRequestCode, int mResultCode, Intent mData);

    void onActivityResultTwitter(int mRequestCode, int mResultCode, Intent mData);
}
