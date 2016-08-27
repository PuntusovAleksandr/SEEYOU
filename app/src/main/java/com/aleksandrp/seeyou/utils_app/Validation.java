package com.aleksandrp.seeyou.utils_app;

/**
 * Created by zloj on 15.03.16.
 */
public class Validation {

    public static final String REGULAR_PASS =     "(?=(.*[0-9]))(?=.*[a-z])(?=(.*)).{6,30}";
//    public static final String REGULAR_PASS =     "(?=(.*[0-9]))(?=.*[a-z])(?=(.*[A-Z]))(?=(.*)).{6,30}";
    public static final String REGULAR_MAIL =     "^[a-z0-9](\\.?[a-z0-9_-]){0,}@[a-z0-9-]+\\.([a-z]{1,6}\\.)?[a-z]{2,15}$";

}
