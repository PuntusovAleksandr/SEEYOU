package com.aleksandrp.seeyou.main.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.main.adapter.RecyclerVideoAdapter;
import com.aleksandrp.seeyou.main.entity.VideoEntity;
import com.aleksandrp.seeyou.utils_app.SettingsApp;

import java.util.ArrayList;


public class AdultOnlyFragment extends Fragment {


    private static AdultOnlyFragment fragment;
    private RecyclerView mRecyclerView;

    private boolean adult;

    public static AdultOnlyFragment gewInstance() {
        if (fragment == null) {
            fragment = new AdultOnlyFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adult_only, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_adult);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().
                getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        updateList();
         adult = SettingsApp.getAdult(getActivity().
                getSharedPreferences(SettingsApp.FILE_NAME, Context.MODE_PRIVATE));
        if (!adult) {
            TextView textReg = (TextView) view.findViewById(R.id.tv_need_register);
            textReg.setVisibility(View.VISIBLE);
        }

        return view;
    }


    public void updateList() {
        RecyclerVideoAdapter sRecyclerViewAdapter =
                new RecyclerVideoAdapter(getListItemsByBooks(), getActivity(), adult);
        mRecyclerView.setAdapter(sRecyclerViewAdapter);
    }

    private ArrayList<VideoEntity> getListItemsByBooks() {
        ArrayList<VideoEntity> videoEntities = new ArrayList<>();

        for (int i = 0; i < 150; i++) {
            videoEntities.add(new VideoEntity(
                    i,
                    "Title ADULT " + i,
                    i + " Author",
                    i * i + 1,
                    i * i * i - i
            ));
        }

        return videoEntities;
    }


    // notify adapter from main activity
    public void notifyAdapterByCategory(String mNameCategory) {
        // TODO: 18.06.2016 make update adapter by category
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }
}
