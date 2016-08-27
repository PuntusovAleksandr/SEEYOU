package com.aleksandrp.seeyou.video_activity.impl;

import android.content.Context;

import com.aleksandrp.seeyou.retrofit.AllRequest;
import com.aleksandrp.seeyou.retrofit.impl.AllRequestImpl;
import com.aleksandrp.seeyou.video_activity.ShowVideoService;
import com.aleksandrp.seeyou.video_activity.VideoActivityView;

/**
 * Created by AleksandrP on 04.07.2016.
 */
public class ShowVideoServiceImpl implements ShowVideoService {

    private Context mContext;

    public ShowVideoServiceImpl(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public void getAndLoadVideo(String idVideo, VideoActivityView mActivityView) {
        AllRequest request = new AllRequestImpl(mContext);
        request.getVideoFileById(idVideo, mActivityView);
    }

    @Override
    public void getAndLoadBroadcast(String idVideo, VideoActivityView mActivityView) {
        // TODO: 07.07.2016  сделать загрузку трансляции
    }

    @Override
    public void getAndLoadAdoult(String idVideo, VideoActivityView mActivityView) {
        // TODO: 07.07.2016  сделать загрузку трансляции
    }

    @Override
    public void getDataByVideo(String mId, VideoActivityView mActivityView) {
        AllRequest request = new AllRequestImpl(mContext);
        request.getDataVideoFileById(mId, mActivityView);
    }

    @Override
    public void sendDisLike(String mUserId, String mUserPass, int type, String mId,
                            VideoActivityView mActivityView) {
        AllRequest request = new AllRequestImpl(mContext);
        request.addDislike(mUserId, mUserPass, type, mId, mActivityView);
    }

    @Override
    public void sendLike(String mUserId, String mUserPass, int type, String mId,
                         VideoActivityView mActivityView) {
        AllRequest request = new AllRequestImpl(mContext);
        request.addLike(mUserId, mUserPass, type, mId, mActivityView);
    }

    @Override
    public void setFragmentVideos(VideoActivityView mActivityView) {
        mActivityView.setFragmentVideos();
    }

    @Override
    public void setFragmentPeople(VideoActivityView mActivityView) {
        mActivityView.setFragmentPeople();
    }

    @Override
    public void setFragmentAbout(VideoActivityView mActivityView) {
        mActivityView.setFragmentAbout();
    }

    @Override
    public void setFragmentGames(VideoActivityView mActivityView) {
        mActivityView.setFragmentGamews();
    }

    @Override
    public void getListComments(String mId, VideoActivityView mActivityView) {
        AllRequest request = new AllRequestImpl(mContext);
        request.getBroadcastComments(mId, mActivityView);
    }

    @Override
    public void getVideoListComments(String mId, VideoActivityView mActivityView) {
        AllRequest request = new AllRequestImpl(mContext);
        request.getVideoComments(mId, mActivityView);
    }

    @Override
    public void sendComments(String mUserId, String mUserPass, int mTypeVideo, String mId,
                             String mText, int mTypeTextComment,String mRelation,VideoActivityView mActivityView) {
        AllRequest request = new AllRequestImpl(mContext);
        request.sendComments(mUserId, mUserPass, mTypeVideo, mId, mText, mTypeTextComment,
                mRelation, mActivityView);
    }

}
