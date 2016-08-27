package com.aleksandrp.seeyou.main.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.main.adapter.RecyclerVideoAdapterMAinActivity;
import com.aleksandrp.seeyou.retrofit.AllRequest;
import com.aleksandrp.seeyou.retrofit.entity.VideoSEEYOU;
import com.aleksandrp.seeyou.retrofit.impl.AllRequestImpl;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;
import com.aleksandrp.seeyou.video_activity.VideoActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VideoSelfieFragment extends Fragment implements
        AllRequestImpl.GetNewPopularSeeYouVideos,
        RecyclerVideoAdapterMAinActivity.PlayVideoSelfi {


    private RecyclerView mRecyclerViewNewVideo, mRecyclerViewMostPopularo, mRecyclerViewSeeYouChoise;

    private static VideoSelfieFragment fragment;
    private VideoView player;
    private List<VideoSEEYOU> mNewVideo;
    private List<VideoSEEYOU> mPopular;
    private List<VideoSEEYOU> mSeeYouShoise;


    private RecyclerVideoAdapterMAinActivity adapterNew;
    private RecyclerVideoAdapterMAinActivity adapterPopular;
    private RecyclerVideoAdapterMAinActivity adapterSeeYou;

    public static VideoSelfieFragment gewInstance() {
        if (fragment == null) {
            fragment = new VideoSelfieFragment();
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_selfie, container, false);

        player = (VideoView) view.findViewById(R.id.video_view);
        player.setMediaController(new MediaController(getActivity()));
        player.setOnCompletionListener(myVideoViewCompletionListener);
        mRecyclerViewNewVideo = (RecyclerView) view.findViewById(R.id.recycler_view_selfi_new_video);
        mRecyclerViewMostPopularo = (RecyclerView) view.findViewById(R.id.recycler_view_selfi_most_popular);
        mRecyclerViewSeeYouChoise = (RecyclerView) view.findViewById(R.id.recycler_view_selfi_seeyou_choise);

        LinearLayoutManager linearLayoutManagerNew = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager linearLayoutManagerPopular = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        mRecyclerViewNewVideo.setLayoutManager(linearLayoutManagerNew);
        mRecyclerViewMostPopularo.setLayoutManager(linearLayoutManagerPopular);
        mRecyclerViewSeeYouChoise.setLayoutManager(linearLayoutManager);
        mRecyclerViewNewVideo.setHasFixedSize(true);
        mRecyclerViewMostPopularo.setHasFixedSize(true);
        mRecyclerViewSeeYouChoise.setHasFixedSize(true);

        mNewVideo = new ArrayList<>();
        mPopular = new ArrayList<>();
        mSeeYouShoise = new ArrayList<>();
        updateList();

        loadListVideos();

        return view;
    }

    /**
     * listener end play video
     */
    MediaPlayer.OnCompletionListener myVideoViewCompletionListener
            = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer arg0) {
            playRandomSeeYouChoisVideo(mSeeYouShoise);
        }
    };


    private void loadListVideos() {
        AllRequest request = new AllRequestImpl(getActivity());

        request.getLastVideoList("", "", this);
        request.getPopularVideoList("", "", this);
        request.getVideoList(true, "", "", this);

    }

    public void updateList() {

        if (adapterNew != null) adapterNew = null;
        if (adapterPopular != null) adapterPopular = null;
        if (adapterSeeYou != null) adapterSeeYou = null;

        adapterNew = new RecyclerVideoAdapterMAinActivity(mNewVideo, getActivity(), this);
        adapterPopular = new RecyclerVideoAdapterMAinActivity(mPopular, getActivity(), this);
        adapterSeeYou = new RecyclerVideoAdapterMAinActivity(mSeeYouShoise, getActivity(), this);

        mRecyclerViewNewVideo.setAdapter(adapterNew);
        mRecyclerViewMostPopularo.setAdapter(adapterPopular);
        mRecyclerViewSeeYouChoise.setAdapter(adapterSeeYou);
    }

    /**
     * answer reqquest from MainActivity set new list and update adapter
     *
     * @param mSEEYOU
     */
    public void notifyListNewAdapter(List<VideoSEEYOU> mSEEYOU) {
        mNewVideo = mSEEYOU;
        updateList();
    }

    /**
     * answer reqquest from MainActivity set new list and update adapter
     *
     * @param mSEEYOU
     */
    public void notifyListPopularAdapter(List<VideoSEEYOU> mSEEYOU) {
        mPopular = mSEEYOU;
        updateList();
    }

    /**
     * answer reqquest from MainActivity set new list and update adapter
     *
     * @param mSEEYOU
     */
    public void notifyListSeeYouAdapter(List<VideoSEEYOU> mSEEYOU) {
        mSeeYouShoise = mSEEYOU;
        updateList();
    }

    // notify adapter from main activity
    public static void notifyAdapterByCategory(String category) {
        // TODO: 18.06.2016 make update adapter by category
    }

    //    GetNewPopularSeeYouVideosfrom
//   ==================================================================
    @Override
    public void getNewVideo(List<VideoSEEYOU> mSEEYOU) {
        if (mNewVideo != null) {
            mNewVideo.clear();
        }
        this.mNewVideo = mSEEYOU;
        updateList();
    }

    @Override
    public void getPopularVideo(List<VideoSEEYOU> mSEEYOU) {
        if (mPopular != null) {
            mPopular.clear();
        }
        this.mPopular = mSEEYOU;
        updateList();
    }

    @Override
    public void getSeeYouVideo(boolean firstSStart, List<VideoSEEYOU> mSEEYOU) {
        if (mNewVideo != null) {
            mSeeYouShoise.clear();
        }
        this.mSeeYouShoise = mSEEYOU;
        updateList();
        if (firstSStart) {
            playRandomSeeYouChoisVideo(mSEEYOU);
        }
    }

    private void playRandomSeeYouChoisVideo(List<VideoSEEYOU> mSEEYOU) {
        int random = new Random().nextInt(mSEEYOU.size());      // create random int for play video
        String video_src =(mSEEYOU.get(random).getVideo_src());
        player.setVideoURI(Uri.parse("http://seeyou.media/" + video_src));
        player.start();
    }

    //   ==================================================================
    //    RecyclerVideoAdapterMAinActivity.PlayVideoSelfi
//   ==================================================================
    @Override
    public void playVideo(String id) {
        Intent intent = new Intent(getActivity(), VideoActivity.class);
        intent.putExtra(STATICS_PARAMS.KEY_ID_VIDEO, id);
        intent.putExtra(STATICS_PARAMS.KEY_FROM_VIDEO, STATICS_PARAMS.TAG_SELFI_FRAGMENT);
        getActivity().startActivity(intent);
    }

//   ==================================================================
}
