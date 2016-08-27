package com.aleksandrp.seeyou.message;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.message.entity.UserMessage;
import com.aleksandrp.seeyou.message.fragment.ListMessagesFragment;
import com.aleksandrp.seeyou.message.fragment.MessagesFragment;
import com.aleksandrp.seeyou.message.impl.MessageActivityListenerImpl;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;

public class MessageActivity extends AppCompatActivity implements MessageActivityView,
        ListMessagesFragment.ListenerListFragment,
        MessagesFragment.LiastenerMessageFragment {

    private MessageActivityListener mListener;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    private ListMessagesFragment mListMessagesFragment;
    private MessagesFragment mMessagesFragment;


    private UserMessage mUserMessage;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mFragmentManager = getFragmentManager();

        mListener = new MessageActivityListenerImpl();
        mListener.setListFragment(this);

    }


//    from MessageActivityView
//    =========================================================================================
    @Override
    public void setListFragmentMessage() {
        mListMessagesFragment = (ListMessagesFragment) getFragmentManager()
                .findFragmentByTag(STATICS_PARAMS.LIST_MESSAGES_FRAGMENT);
        if (mListMessagesFragment == null) {
            mListMessagesFragment = new ListMessagesFragment(this);
        }
        setFragment(mListMessagesFragment, STATICS_PARAMS.LIST_MESSAGES_FRAGMENT);
    }

    @Override
    public void setMessagesFragment(String mIdUserTo, String mUserId) {
        mMessagesFragment = (MessagesFragment) getFragmentManager()
                .findFragmentByTag(STATICS_PARAMS.MESSAGES_FRAGMENT);
        if (mMessagesFragment == null) {
            mMessagesFragment = new MessagesFragment(mIdUserTo, mUserId,  this);
        }
        setFragment(mMessagesFragment, STATICS_PARAMS.MESSAGES_FRAGMENT);
    }


//    =========================================================================================



    private void setFragment(Fragment mFragment, String tag) {
        mFragmentManager.beginTransaction()
                .replace(R.id.main_container_mess, mFragment, tag)
                .commit();
    }

//    from ListMessagesFragment.ListenerListFragment
    //    =========================================================================================
    @Override
    public void listenerListFragment(String mIdUserTo, String mUserId) {
//    public void listenerListFragment(UserMessage mUserMessage) {
        this.mUserMessage = mUserMessage;
        mListener.setMessagesFragment(this, mIdUserTo, mUserId);
    }

    @Override
    public void backFromListFragment() {

        mListMessagesFragment = null;
        mMessagesFragment = null;
        finish();
    }
//    =========================================================================================


    //    from  MessagesFragment.LiastenerMessageFragment
    //    =========================================================================================
    @Override
    public void onClickBack() {
        mListener.setListFragment(this);
    }

    //    =========================================================================================

}
