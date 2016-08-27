package com.aleksandrp.seeyou.video_activity;

import com.aleksandrp.seeyou.retrofit.entity.Broadcast;
import com.aleksandrp.seeyou.retrofit.entity.CommentVideo;
import com.aleksandrp.seeyou.retrofit.entity.VideoSEEYOU;

import java.util.List;

/**
 * Created by AleksandrP on 04.07.2016.
 */
public interface VideoActivityView {

    void setVideo(List<VideoSEEYOU> mData);

    void setDataVideo(List<VideoSEEYOU> mData);

    void loadVideoList(List<Broadcast> mData);

    void updateData();

    void updateDataBroadcasr();

    void setFragmentVideos();

    void setFragmentPeople();

    void setFragmentAbout();

    void setFragmentGamews();

    void setListCommens(List<CommentVideo> mList);
}
