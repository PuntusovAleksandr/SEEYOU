package com.aleksandrp.seeyou.utils_app;

import android.content.SharedPreferences;

/**
 * Created by AleksandrP on 17.06.2016.
 */
public class SettingsApp {


    /**
     * The constant FILE_NAME.
     */
// Settings xml file name
    public static final String FILE_NAME = "settings";

    private static final String DEF_STRING = "";
    private static final boolean DEF_BOOL = false;

    private static final String KEY_USER_MAIL = "KEY_USER_MAIL";
    private static final String KEY_USER_PASS = "KEY_USER_PASS";
    private static final String KEY_USER_ID = "KEY_USER_ID";
    private static final String KEY_USER_NAME = "KEY_USER_NAME";
    private static final String KEY_USER_AVA = "KEY_USER_AVA";
    private static final String KEY_USER_ADULT = "KEY_USER_ADULT";
    private static final String KEY_GREETING = "show_first_title";


    /**
     * set user email for check registered
     *
     * @param mail
     * @param preferences
     */
    public static void setUserMail(String mail, SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER_MAIL, mail);
        editor.commit();
    }

    /**
     * get user email for check registered
     *
     * @param preferences
     * @return
     */
    public static String getUserMail(SharedPreferences preferences) {
        return preferences.getString(KEY_USER_MAIL, DEF_STRING);
    }


    /**
     * set user password for check registered
     *
     * @param pass
     * @param preferences
     */
    public static void setUserPass(String pass, SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER_PASS, pass);
        editor.commit();
    }

    /**
     * get user password for check registered
     *
     * @param preferences
     * @return
     */
    public static String getUserPass(SharedPreferences preferences) {
        return preferences.getString(KEY_USER_PASS, DEF_STRING);
    }


    /**
     * set user id for check registered
     *
     * @param pass
     * @param preferences
     */
    public static void setUserId(String pass, SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER_ID, pass);
        editor.commit();
    }

    /**
     * get user id for check registered
     *
     * @param preferences
     * @return
     */
    public static String getUserId(SharedPreferences preferences) {
        return preferences.getString(KEY_USER_ID, DEF_STRING);
    }


    /**
     * set user name for check registered
     *
     * @param pass
     * @param preferences
     */
    public static void setUserName(String pass, SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER_NAME, pass);
        editor.commit();
    }

    /**
     * get user name for check registered
     *
     * @param preferences
     * @return
     */
    public static String getUserName(SharedPreferences preferences) {
        return preferences.getString(KEY_USER_NAME, DEF_STRING);
    }


    /**
     * set user avatar for check registered
     *
     * @param pass
     * @param preferences
     */
    public static void setUserAvatar(String pass, SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER_AVA, pass);
        editor.commit();
    }

    /**
     * get user avatar for check registered
     *
     * @param preferences
     * @return
     */
    public static String getUserAvatar(SharedPreferences preferences) {
        return preferences.getString(KEY_USER_AVA, DEF_STRING);
    }


    /**
     * set user adult for check registered
     *
     * @param adult
     * @param preferences
     */
    public static void setAdult(boolean adult, SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_USER_ADULT, adult);
        editor.commit();
    }

    /**
     * get user adult for check registered
     *
     * @param preferences
     * @return
     */
    public static boolean getAdult(SharedPreferences preferences) {
        return preferences.getBoolean(KEY_USER_ADULT, DEF_BOOL);
    }


    /**
     * SET GREETING
     *
     * @param adult
     * @param preferences
     */
    public static void setGreeting(boolean adult, SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_GREETING, adult);
        editor.commit();
    }

    /**
     * GET GREETING
     *
     * @param preferences
     * @return
     */
    public static boolean getGreeting(SharedPreferences preferences) {
        return preferences.getBoolean(KEY_GREETING, true);
    }


}
