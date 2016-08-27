package com.aleksandrp.seeyou.video_activity;

/**
 * Created by AleksandrP on 04.07.2016.
 */
public interface ShowVideoService {

    void getAndLoadVideo(String idVideo, VideoActivityView mActivityView);

    void getAndLoadBroadcast(String idVideo, VideoActivityView mActivityView);

    void getAndLoadAdoult(String idVideo, VideoActivityView mActivityView);

    void getDataByVideo(String mId, VideoActivityView mActivityView);

    void sendDisLike(String mUserId, String mUserPass, int type, String mId,
                     VideoActivityView mActivityView);

    void sendLike(String mUserId, String mUserPass, int type, String mId,
                  VideoActivityView mActivityView);

    void setFragmentVideos(VideoActivityView mActivityView);

    void setFragmentPeople(VideoActivityView mActivityView);

    void setFragmentAbout(VideoActivityView mActivityView);

    void setFragmentGames(VideoActivityView mActivityView);

    void getListComments(String mId, VideoActivityView mActivityView);

    void getVideoListComments(String mId, VideoActivityView mVideoActivity);

    void sendComments(String mUserId, String mUserPass, int mTypeVideo, String mId, String mText,
                      int mTypeTextComment, String mRelation, VideoActivityView mActivityView);
}
