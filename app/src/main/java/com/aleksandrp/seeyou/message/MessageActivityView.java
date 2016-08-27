package com.aleksandrp.seeyou.message;

/**
 * Created by AleksandrP on 22.06.2016.
 */
public interface MessageActivityView {
    void setListFragmentMessage();

    void setMessagesFragment(String mIdUserTo, String mUserId);
}
