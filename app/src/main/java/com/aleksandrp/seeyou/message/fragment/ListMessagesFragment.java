package com.aleksandrp.seeyou.message.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.message.adapter.RecyclerAdapterMessages;
import com.aleksandrp.seeyou.message.entity.MessageSeeYou;
import com.aleksandrp.seeyou.message.entity.UserMessage;
import com.aleksandrp.seeyou.retrofit.AllRequest;
import com.aleksandrp.seeyou.retrofit.entity.UsersMessageOnLine;
import com.aleksandrp.seeyou.retrofit.impl.AllRequestImpl;
import com.aleksandrp.seeyou.utils_app.SettingsApp;
import com.aleksandrp.seeyou.utils_app.UtilsApp;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListMessagesFragment extends Fragment implements
        RecyclerAdapterMessages.ListenerCardViewMess,
        AllRequestImpl.GetAllUsersActivity {


    private static ListMessagesFragment sInstance;
    private ArrayList<UsersMessageOnLine> userWithMess;
    private RecyclerAdapterMessages mStickerAdapter;
    private ListenerListFragment mListenerListFragment;
    private RecyclerView mStickerList;

    private SharedPreferences mSharedPreferences;
    private String userId;

    public ListMessagesFragment(ListenerListFragment mListenerListFragment) {
        // Required empty public constructor
        this.mListenerListFragment = mListenerListFragment;
    }

    private static ListMessagesFragment fragment;

    public static ListMessagesFragment getInstance(ListenerListFragment mListenerListFragment) {
        if (fragment == null) {
            fragment = new ListMessagesFragment(mListenerListFragment);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mSharedPreferences =
                getActivity().getSharedPreferences(SettingsApp.FILE_NAME, Context.MODE_PRIVATE);
         userId = SettingsApp.getUserId(mSharedPreferences);
        userWithMess = new ArrayList<>();
        initList(view);

        getUsers();

        return view;
    }

    private void getUsers() {
        AllRequest request = new AllRequestImpl(getActivity());
        request.getAllUsersToMessagesActivity(this);
    }


    private void initList(View view) {

        mStickerList = (RecyclerView) view.findViewById(R.id.sticks_type_list_new);
        // stickerList.setHasFixedSize(true);


        final GridLayoutManager mStickersManager = new GridLayoutManager(getActivity(), 3);

        initAdapter();

        mStickerList.setLayoutManager(mStickersManager);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        mStickerList.setItemAnimator(itemAnimator);

        view.findViewById(R.id.rl_back_list_mess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                UtilsApp.disableDoubleClick(mView);
                mListenerListFragment.backFromListFragment();
            }
        });
    }

    private void initAdapter() {
        if (mStickerAdapter != null) {
            mStickerAdapter = null;
        }
        mStickerAdapter = new RecyclerAdapterMessages(userWithMess, userId, getActivity(), this);
        mStickerList.setAdapter(mStickerAdapter);
    }

    private ArrayList<UserMessage> getUserWithMess() {
        // TODO: 22.06.2016 здесь должен быть запрос к серверу
        ArrayList<UserMessage> messages = new ArrayList<>();
        for (int i = 0; i < 125; i++) {
            ArrayList<MessageSeeYou> messageSeeYous = new ArrayList<>();
            for (int y = 0; y < (i + 5); y++) {

                messageSeeYous.add(new MessageSeeYou(
                        "Content \nContent \nContent \nContent " + i,
                        "Date Content \nContent \nContent \n " + (i + 1),
                        y % 3 == 0 ? false : true
                ));

            }
            messages.add(new UserMessage(
                    "name " + i,
                    "",
                    i,
                    i % 4 == 0 ? false : true,
                    messageSeeYous
            ));
        }

        return messages;

    }

    @Override
    public void onClickCardView(String mIdUserTo, String mUserId) {
//    public void onClickCardView(UserMessage mUserMessage) {
        mListenerListFragment.listenerListFragment(mIdUserTo, mUserId);
    }

    @Override
    public void getUsersToMessagesActivity(ArrayList<UsersMessageOnLine> mUserMessages) {
        if (userWithMess != null) {
            userWithMess.clear();
        }
        this.userWithMess = mUserMessages;
        initAdapter();
    }

    public interface ListenerListFragment {
        void listenerListFragment(String mIdUserTo, String mUserId);
//        void listenerListFragment(UserMessage mUserMessage);

        void backFromListFragment();
    }

}
