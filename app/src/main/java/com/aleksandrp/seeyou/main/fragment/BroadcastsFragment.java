package com.aleksandrp.seeyou.main.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.main.adapter.RecyclerVideoAdapterBroadcast;
import com.aleksandrp.seeyou.retrofit.AllRequest;
import com.aleksandrp.seeyou.retrofit.entity.Broadcast;
import com.aleksandrp.seeyou.retrofit.impl.AllRequestImpl;

import java.util.ArrayList;
import java.util.List;

public class BroadcastsFragment extends Fragment implements AllRequestImpl.GetBroadcastVideos {


    private static BroadcastsFragment sFragment;
    private RecyclerView mRecyclerView;
    private List<Broadcast> mBroadcasts;
    private RecyclerVideoAdapterBroadcast sRecyclerViewAdapter;


    public static BroadcastsFragment gewInstance() {
        if (sFragment == null) {
            sFragment = new BroadcastsFragment();
        }
        return sFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_broadcasts, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_broadcasts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().
                getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mBroadcasts = new ArrayList<>();
        updateList();
        loadBroadcastVideos();
        return view;
    }

    private void loadBroadcastVideos() {
        AllRequest request = new AllRequestImpl(getActivity());
        request.getBroadcasts("", this);
    }


    public void updateList() {
        sRecyclerViewAdapter =
                new RecyclerVideoAdapterBroadcast(mBroadcasts, getActivity());
        mRecyclerView.setAdapter(sRecyclerViewAdapter);
    }

    // notify adapter from main activity
    public void notifyAdapterByCategory(String mNameCategory) {
        // TODO: 18.06.2016 make update adapter by category
    }

    // answer from loader
    @Override
    public void loadVideo(List<Broadcast> mBroadcastses) {
        if (mBroadcasts != null) {
            mBroadcasts.clear();
        }
        this.mBroadcasts = mBroadcastses;

        if (sRecyclerViewAdapter != null) {
            sRecyclerViewAdapter = null;
        }
        updateList();
    }

    /**
     * nswer reqquest from MainActivity set new list and update adapter
     *
     * @param mBroadcastses
     */
    public void notifyListAdapterByCategory(List<Broadcast> mBroadcastses) {
        loadVideo(mBroadcastses);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }
}
