package com.aleksandrp.seeyou.video_activity.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.retrofit.AllRequest;
import com.aleksandrp.seeyou.retrofit.entity.Broadcast;
import com.aleksandrp.seeyou.retrofit.entity.VideoSEEYOU;
import com.aleksandrp.seeyou.retrofit.impl.AllRequestImpl;
import com.aleksandrp.seeyou.video_activity.adapter.RecyclerVideoAdapterBroadcastShowActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AleksandrP on 04.07.2016.
 */
public class FragmentVideos extends Fragment implements
        AllRequestImpl.GetBroadcastVideosToShowActivity,
        RecyclerVideoAdapterBroadcastShowActivity.ClickShowVideo {

    private RecyclerView mRecyclerView;
    private List<Broadcast> mBroadcasts;
    private List<VideoSEEYOU> mVideoSEEYOUs;
    private RecyclerVideoAdapterBroadcastShowActivity sRecyclerViewAdapter;

    private ShowVideo mListenerVideo;

    private static FragmentVideos sVideost;

    public static FragmentVideos getInstance() {
        if (sVideost == null) {
            sVideost = new FragmentVideos();
        }
        return sVideost;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_videos, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list_video_show_video_fragment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().
                getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mBroadcasts = new ArrayList<>();
        mVideoSEEYOUs = new ArrayList<>();
        updateList();
        if (getActivity() instanceof ShowVideo) {
            mListenerVideo = (ShowVideo) getActivity();
        }
        return view;
    }

    public void loadVideosByCategory(String mCategory_slug) {
        AllRequest request = new AllRequestImpl(getActivity());
        request.getVideoList(mCategory_slug, "", this);
    }


    public void updateList() {
        sRecyclerViewAdapter =
                new RecyclerVideoAdapterBroadcastShowActivity(mBroadcasts, mVideoSEEYOUs,
                        getActivity(), this);
        mRecyclerView.setAdapter(sRecyclerViewAdapter);
    }

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

    @Override
    public void getSeeYouVideo(List<VideoSEEYOU> mData) {
        if (mVideoSEEYOUs != null) {
            mVideoSEEYOUs.clear();
        }
        mVideoSEEYOUs = mData;
        if (sRecyclerViewAdapter != null) {
            sRecyclerViewAdapter = null;
        }
        updateList();
    }

    // from ClickShowVideo
//    ======================================================
    @Override
    public void clickShowVideo(VideoSEEYOU mVideoSEEYOU) {
        mListenerVideo.clickShowVideo(mVideoSEEYOU);
    }

    @Override
    public void clickShowBroadcast(String mSrc) {

    }
//    ======================================================

    public interface ShowVideo {
        void clickShowVideo(VideoSEEYOU mVideoSEEYOU);
    }

}
