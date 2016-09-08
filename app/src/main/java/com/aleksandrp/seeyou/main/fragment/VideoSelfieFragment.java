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
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.main.adapter.RecyclerVideoAdapterMAinActivity;
import com.aleksandrp.seeyou.retrofit.AllRequest;
import com.aleksandrp.seeyou.retrofit.entity.VideoSEEYOU;
import com.aleksandrp.seeyou.retrofit.impl.AllRequestImpl;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;
import com.aleksandrp.seeyou.utils_app.UtilsApp;
import com.aleksandrp.seeyou.video_activity.VideoActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VideoSelfieFragment extends Fragment implements View.OnClickListener,
        AllRequestImpl.GetNewPopularSeeYouVideos,
        RecyclerVideoAdapterMAinActivity.PlayVideoSelfi {


    private RecyclerView mRecyclerViewNewVideo, mRecyclerViewMostPopularo, mRecyclerViewSeeYouChoise;
    private RelativeLayout mButtonNew, mButtonPopular, mButtonChoise;
    private boolean mButtonNewPress = false, mButtonPopularPress = false, mButtonChoisePress = false;

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

        mButtonNew = (RelativeLayout) view.findViewById(R.id.bt_show_more_new_videos);
        mButtonPopular = (RelativeLayout) view.findViewById(R.id.bt_show_more_new_videos2);
        mButtonChoise = (RelativeLayout) view.findViewById(R.id.bt_show_more_new_videos3);

        LinearLayoutManager linearLayoutManagerNew = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);

        LinearLayoutManager linearLayoutManagerPopular = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);

        mRecyclerViewNewVideo.setLayoutManager(linearLayoutManagerNew);
        mRecyclerViewMostPopularo.setLayoutManager(linearLayoutManagerPopular);
        mRecyclerViewSeeYouChoise.setLayoutManager(linearLayoutManager);
        mRecyclerViewNewVideo.setHasFixedSize(true);
        mRecyclerViewMostPopularo.setHasFixedSize(true);
        mRecyclerViewSeeYouChoise.setHasFixedSize(true);

        mButtonNew.setOnClickListener(this);
        mButtonPopular.setOnClickListener(this);
        mButtonChoise.setOnClickListener(this);

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
        // TODO: 07.09.2016 надобудет сделать норм спивсок
        if (mButtonNewPress) {
            mNewVideo = mSEEYOU;
        } else {
            for (int i = 0; i < 5; i++) {
                mNewVideo.add(mSEEYOU.get(i));
            }
        }
        updateList();
    }

    /**
     * answer reqquest from MainActivity set new list and update adapter
     *
     * @param mSEEYOU
     */
    public void notifyListPopularAdapter(List<VideoSEEYOU> mSEEYOU) {
        // TODO: 07.09.2016 надобудет сделать норм спивсок
        if (mButtonPopularPress) {
            mPopular = mSEEYOU;
        } else {
            for (int i = 0; i < 5; i++) {
                mPopular.add(mSEEYOU.get(i));
            }
        }
        updateList();
    }

    /**
     * answer reqquest from MainActivity set new list and update adapter
     *
     * @param mSEEYOU
     */
    public void notifyListSeeYouAdapter(List<VideoSEEYOU> mSEEYOU) {
        // TODO: 07.09.2016 надобудет сделать норм спивсок
        if (mButtonChoisePress) {
            mSeeYouShoise = mSEEYOU;
        } else {
            for (int i = 0; i < 5; i++) {
                mSeeYouShoise.add(mSEEYOU.get(i));
            }
        }
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
        // TODO: 07.09.2016 надобудет сделать норм спивсок
        if (mButtonNewPress) {
            mNewVideo = mSEEYOU;
        } else {
            for (int i = 0; i < 5; i++) {
                mNewVideo.add(mSEEYOU.get(i));
            }
        }
        updateList();
    }

    @Override
    public void getPopularVideo(List<VideoSEEYOU> mSEEYOU) {
        if (mPopular != null) {
            mPopular.clear();
        }
        // TODO: 07.09.2016 надобудет сделать норм спивсок
        if (mButtonPopularPress) {
            mPopular = mSEEYOU;
        } else {
            for (int i = 0; i < 5; i++) {
                mPopular.add(mSEEYOU.get(i));
            }
        }
        updateList();
    }

    @Override
    public void getSeeYouVideo(boolean firstSStart, List<VideoSEEYOU> mSEEYOU) {
        if (mSeeYouShoise != null) {
            mSeeYouShoise.clear();
        }
        // TODO: 07.09.2016 надобудет сделать норм спивсок
        if (mButtonChoisePress) {
            mSeeYouShoise = mSEEYOU;
        } else {
            for (int i = 0; i < 5; i++) {
                mSeeYouShoise.add(mSEEYOU.get(i));
            }
        }
        updateList();
        if (firstSStart) {
            playRandomSeeYouChoisVideo(mSEEYOU);
        }
    }

    private void playRandomSeeYouChoisVideo(List<VideoSEEYOU> mSEEYOU) {
        int random = new Random().nextInt(mSEEYOU.size());      // create random int for play video
        String video_src = (mSEEYOU.get(random).getVideo_src());
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

    @Override
    public void onClick(View mView) {
        UtilsApp.disableDoubleClick(mView);
        switch (mView.getId()) {
            case R.id.bt_show_more_new_videos:
                if (mButtonNewPress) {
                    mButtonNewPress = false;
                } else {
                    mButtonNewPress = true;
                }
                break;

            case R.id.bt_show_more_new_videos2:
                if (mButtonPopularPress) {
                    mButtonPopularPress = false;
                } else {
                    mButtonPopularPress = true;
                }
                break;

            case R.id.bt_show_more_new_videos3:
                if (mButtonChoisePress) {
                    mButtonChoisePress = false;
                } else {
                    mButtonChoisePress = true;
                }
                break;
        }
        loadListVideos();
    }

//   ==================================================================
}
