package com.aleksandrp.seeyou.utils_app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.view.View;

/**
 * Created by AleksandrP on 17.06.2016.
 */
public class UtilsApp {

    /**
     * for disable double click in button
     */
    public static void disableDoubleClick(final View mView) {
        mView.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.setEnabled(true);
            }
        }, 250);
    }

    /**
     * Check internet connection
     * @param mContext
     * @return
     */
    public static boolean isNetworkConnected(Context mContext) {
        ConnectivityManager cm =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


    /**
     * Check the email validity
     *
     * @return true or false
     */
    public static boolean isValidEmail(String email) {
        if (!email.isEmpty()) {
            email = email.toLowerCase();
        }
        return email.matches(Validation.REGULAR_MAIL);
    }

    /**
     * Check the password validity
     *
     * @return true or false
     */
    public static boolean isValidPassword(String pass) {
        return pass.matches(Validation.REGULAR_PASS);
    }

}
