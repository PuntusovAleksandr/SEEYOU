package com.aleksandrp.seeyou.login;

/**
 * Created by AleksandrP on 17.06.2016.
 */
public interface LoginActivityPresent {

    void checkLogin();

    void signInWithAsne(String mKeySocNet);

    void signIn(String mail, String pass);

    void registering();

    void registeringWithAsne(String mKeySocNetwork);
}
