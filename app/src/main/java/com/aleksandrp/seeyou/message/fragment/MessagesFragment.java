package com.aleksandrp.seeyou.message.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.message.adapter.RecyclerMessagesAdapter;
import com.aleksandrp.seeyou.message.entity.MessageSeeYou;
import com.aleksandrp.seeyou.message.entity.UserMessage;
import com.aleksandrp.seeyou.retrofit.AllRequest;
import com.aleksandrp.seeyou.retrofit.impl.AllRequestImpl;
import com.aleksandrp.seeyou.utils_app.UtilsApp;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessagesFragment extends Fragment implements View.OnClickListener,
        RecyclerMessagesAdapter.ListenerCardViewMessSend,
        AllRequestImpl.GetListMessagesUserId {

    private EditText textMessage;

    private String mIdUserTo, mUserId, mDialogId;
    private LiastenerMessageFragment mLiastenerMessageFragment;
    private ArrayList<MessageSeeYou.MessageSY> mMessageSeeYous;

    private RecyclerView mStickerList;
    private RecyclerMessagesAdapter adapter;

    private AllRequest request;

    public MessagesFragment(String mIdUserTo, String mUserId, LiastenerMessageFragment mLiastenerMessageFragment) {
        // Required empty public constructor
        this.mIdUserTo = mIdUserTo;
        this.mUserId = mUserId;
        this.mLiastenerMessageFragment = mLiastenerMessageFragment;
    }

    private static MessagesFragment fragment;

    public static MessagesFragment getInstance(String mIdUserTo, String mUserId,
                                               LiastenerMessageFragment mLiastenerMessageFragment) {
        if (fragment == null) {
            fragment = new MessagesFragment(mIdUserTo, mUserId, mLiastenerMessageFragment);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        mMessageSeeYous = new ArrayList<>();
        request = new AllRequestImpl(getActivity());

        view.findViewById(R.id.rl_back_mess).setOnClickListener(this);
        view.findViewById(R.id.bt_send_mess).setOnClickListener(this);

        textMessage = (EditText) view.findViewById(R.id.et_send_mess);

        mStickerList = (RecyclerView) view.findViewById(R.id.recycler_mess);
        // stickerList.setHasFixedSize(true);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().
                getApplicationContext());

        initAdapter();
        mStickerList.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        mStickerList.setItemAnimator(itemAnimator);

        getListMessages();

        return view;
    }

    private void initAdapter() {
        if (adapter != null) {
            adapter = null;
        }
        adapter = new RecyclerMessagesAdapter(mMessageSeeYous, mUserId,
               getActivity(), this);
        mStickerList.setAdapter(adapter);
    }

    private void getListMessages() {
        request.getListMessagesUserId(mIdUserTo, mUserId, this);
    }

    @Override
    public void onClick(View mView) {
        UtilsApp.disableDoubleClick(mView);
        switch (mView.getId()) {
            case R.id.rl_back_mess:
                mLiastenerMessageFragment.onClickBack();
                break;

            case R.id.bt_send_mess:
                String text = textMessage.getText().toString();
                sendMessageToUser(text);
                textMessage.setText("");
                break;
        }
    }

    private void sendMessageToUser(String mText) {
        request.sendMessagesToUserId(mUserId, mText, mDialogId, this);
    }

    // from RecyclerMessagesAdapter.ListenerCardViewMessSen
//    ==============================================================
    @Override
    public void onClickCardView(UserMessage mUserMessage) {

    }

    @Override
    public void setListMessagesUserId() {
        getListMessages();
    }

    @Override
    public void setListMessagesUserId(MessageSeeYou mBody) {
        if (mMessageSeeYous != null) {
            mMessageSeeYous.clear();
        }
        mDialogId = mBody.getDialog_id();
        mMessageSeeYous = mBody.getMessageSie();
        initAdapter();
    }

//    ==============================================================

    public interface LiastenerMessageFragment {
        void onClickBack();
    }

}
