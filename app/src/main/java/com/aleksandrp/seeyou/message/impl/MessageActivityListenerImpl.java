package com.aleksandrp.seeyou.message.impl;

import com.aleksandrp.seeyou.message.MessageActivityListener;
import com.aleksandrp.seeyou.message.MessageActivityView;

/**
 * Created by AleksandrP on 22.06.2016.
 */
public class MessageActivityListenerImpl implements MessageActivityListener {
    @Override
    public void setListFragment(MessageActivityView mListFragment) {
        mListFragment.setListFragmentMessage();
    }

    @Override
    public void setMessagesFragment(MessageActivityView mListFragment, String mIdUserTo, String mUserId) {
        mListFragment.setMessagesFragment(mIdUserTo, mUserId);
    }
}
