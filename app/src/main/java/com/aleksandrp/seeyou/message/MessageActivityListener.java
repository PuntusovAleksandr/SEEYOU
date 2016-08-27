package com.aleksandrp.seeyou.message;

/**
 * Created by AleksandrP on 22.06.2016.
 */
public interface MessageActivityListener {
    void setListFragment(MessageActivityView mListFragment);

    void setMessagesFragment(MessageActivityView mListFragment, String mIdUserTo, String mUserId);
}
