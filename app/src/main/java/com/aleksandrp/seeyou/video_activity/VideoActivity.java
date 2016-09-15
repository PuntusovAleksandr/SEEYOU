package com.aleksandrp.seeyou.video_activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.retrofit.entity.Broadcast;
import com.aleksandrp.seeyou.retrofit.entity.CommentVideo;
import com.aleksandrp.seeyou.retrofit.entity.VideoSEEYOU;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;
import com.aleksandrp.seeyou.utils_app.SettingsApp;
import com.aleksandrp.seeyou.utils_app.UtilsApp;
import com.aleksandrp.seeyou.video_activity.adapter.RecyclerVideoAdapterBroadcastVideoActivity;
import com.aleksandrp.seeyou.video_activity.dialog.DialogMakeInOutCall;
import com.aleksandrp.seeyou.video_activity.fragments.FragmentAbout;
import com.aleksandrp.seeyou.video_activity.fragments.FragmentGames;
import com.aleksandrp.seeyou.video_activity.fragments.FragmentPeorle;
import com.aleksandrp.seeyou.video_activity.fragments.FragmentVideos;
import com.aleksandrp.seeyou.video_activity.impl.ShowVideoServiceImpl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener,
        VideoActivityView, RecyclerVideoAdapterBroadcastVideoActivity.ClickVideo,
        FragmentVideos.ShowVideo,
        DialogMakeInOutCall.DialogSendMessage {

    private VideoView mPlayer;
    private ShowVideoService mVideoService;
    private VideoSEEYOU mVideoSEEYOU;

    private FragmentManager mFragmentManager;

    private CircleImageView ivSmallIcon;
    private ImageView ivBigIcon;
    private ImageButton ibOpenComments;
    private TextView tvCategory, tvDataPublish, tvViews, tvLiks, tvDisLiks, tvName, tvAuthor;
    private View vVideos, vPeople, vAbout, vGames;
    private RecyclerView mRecyclerView;
    private RelativeLayout rlVideo, rlPeopl, rlAbout, rlGames;
    private EditText textComment;

    private RelativeLayout rlDetailsVideo;
    private LinearLayout llComments, llLike, llDisLike, ibOpenDetails;

    private FragmentVideos mVideos;
    private FragmentPeorle mPeorle;
    private FragmentAbout mAbout;
    private FragmentGames mGames;

    private List<CommentVideo> mListComments;

    private String MARKER_FROM_VIDEO = "";

    private SharedPreferences mSharedPreferences;
    private int typeVideo = 2,      // 1 - video, 2-broadcast
            typeTextComment = 1;      // 1 - out, 2 - inner

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mSharedPreferences =
                getSharedPreferences(SettingsApp.FILE_NAME, Context.MODE_PRIVATE);

        mListComments = new ArrayList<>();
        String id = getIntent().getStringExtra(STATICS_PARAMS.KEY_ID_VIDEO);
        MARKER_FROM_VIDEO = getIntent().getStringExtra(STATICS_PARAMS.KEY_FROM_VIDEO);

        mVideoService = new ShowVideoServiceImpl(this);
        initUi();

        if (MARKER_FROM_VIDEO.equals(STATICS_PARAMS.TAG_SELFI_FRAGMENT)) {
            typeVideo = 1;
            rlPeopl.setVisibility(View.GONE);
            rlAbout.setVisibility(View.GONE);
            rlGames.setVisibility(View.GONE);
            mVideoService.getAndLoadVideo(id, this);
        } else if (MARKER_FROM_VIDEO.equals(STATICS_PARAMS.TAG_BRODCAST_FRAGMENT)) {
            mVideoService.getAndLoadBroadcast(id, this);
        } else if (MARKER_FROM_VIDEO.equals(STATICS_PARAMS.TAG_ADOULT_FRAGMENT)) {
            rlVideo.setVisibility(View.GONE);
            rlGames.setVisibility(View.GONE);
            mVideoService.getAndLoadAdoult(id, this);
        } else {
            Toast.makeText(VideoActivity.this, "Error loading", Toast.LENGTH_SHORT).show();
        }

    }

    private void initUi() {
        mFragmentManager = getFragmentManager();

        vVideos = (View) findViewById(R.id.view_videos);
        vPeople = (View) findViewById(R.id.view_people);
        vAbout = (View) findViewById(R.id.view_about);
        vGames = (View) findViewById(R.id.view_games);

        rlDetailsVideo = (RelativeLayout) findViewById(R.id.rl_description_club);
        llComments = (LinearLayout) findViewById(R.id.ll_comments);
        llLike = (LinearLayout) findViewById(R.id.ll_like);
        llDisLike = (LinearLayout) findViewById(R.id.ll_dislike);

        textComment = (EditText) findViewById(R.id.et_message_send);

        mPlayer = (VideoView) findViewById(R.id.video_video_activity);
        mPlayer.setMediaController(new MediaController(VideoActivity.this));

        ivSmallIcon = (CircleImageView) findViewById(R.id.iv_icon_author);
        ivBigIcon = (ImageView) findViewById(R.id.icon_club_full);

        tvName = (TextView) findViewById(R.id.tv_name_video);
        tvAuthor = (TextView) findViewById(R.id.tv_name_video_author);
        tvCategory = (TextView) findViewById(R.id.tv_category_video);
        tvDataPublish = (TextView) findViewById(R.id.tv_published_video);
        tvViews = (TextView) findViewById(R.id.tv_views_video);
        tvLiks = (TextView) findViewById(R.id.tv_show_like);
        tvDisLiks = (TextView) findViewById(R.id.tv_show_dislike);

        ibOpenDetails = (LinearLayout) findViewById(R.id.ib_show_details_video);
        ibOpenComments = (ImageButton) findViewById(R.id.ib_show_comments);

        ibOpenDetails.setOnClickListener(this);
        ibOpenComments.setOnClickListener(this);
        llLike.setOnClickListener(this);
        llDisLike.setOnClickListener(this);

        rlVideo = (RelativeLayout) findViewById(R.id.rl_main_videos);
        rlPeopl = (RelativeLayout) findViewById(R.id.rl_main_people);
        rlAbout = (RelativeLayout) findViewById(R.id.rl_main_about);
        rlGames = (RelativeLayout) findViewById(R.id.rl_main_games);

        rlVideo.setOnClickListener(this);
        rlPeopl.setOnClickListener(this);
        rlAbout.setOnClickListener(this);
        rlGames.setOnClickListener(this);

        findViewById(R.id.rl_back_video_activity).setOnClickListener(this);
        findViewById(R.id.bt_send_comment).setOnClickListener(this);

        initRecycler();

        mVideoService.setFragmentVideos(this);
    }

    private void initRecycler() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_all_comments_by_video);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        updateListComments();
    }

    private void updateListComments() {
        RecyclerVideoAdapterBroadcastVideoActivity sRecyclerViewAdapter =
                new RecyclerVideoAdapterBroadcastVideoActivity(mListComments, this, this);
        mRecyclerView.setAdapter(sRecyclerViewAdapter);
    }


    @Override
    public void onClick(View mView) {
        UtilsApp.disableDoubleClick(mView);
        switch (mView.getId()) {
            case R.id.rl_back_video_activity:
                onBackPressed();
                break;

            case R.id.ib_show_details_video:
                if (rlDetailsVideo.getVisibility() == View.GONE) {
                    rlDetailsVideo.setVisibility(View.VISIBLE);
                } else rlDetailsVideo.setVisibility(View.GONE);
                break;

            case R.id.ib_show_comments:
                if (llComments.getVisibility() == View.GONE) {
                    llComments.setVisibility(View.VISIBLE);
                } else llComments.setVisibility(View.GONE);
                break;

            case R.id.ll_dislike:
                mVideoService.sendDisLike(
                        SettingsApp.getUserId(mSharedPreferences),
                        SettingsApp.getUserPass(mSharedPreferences),
                        typeVideo, mVideoSEEYOU.getId(), this);
                break;

            case R.id.ll_like:
                mVideoService.sendLike(SettingsApp.getUserId(mSharedPreferences),
                        SettingsApp.getUserPass(mSharedPreferences),
                        typeVideo, mVideoSEEYOU.getId(), this);
                break;

            case R.id.rl_main_videos:
                mVideoService.setFragmentVideos(this);
                break;

            case R.id.rl_main_people:
                mVideoService.setFragmentPeople(this);
                break;

            case R.id.rl_main_about:
                mVideoService.setFragmentAbout(this);
                break;

            case R.id.rl_main_games:
                mVideoService.setFragmentGames(this);
                break;

            case R.id.bt_send_comment:
                String text = textComment.getText().toString();
                if (text.length() > 0) {
                    mVideoService.sendComments(
                            SettingsApp.getUserId(mSharedPreferences),
                            SettingsApp.getUserPass(mSharedPreferences),
                            typeVideo, mVideoSEEYOU.getId(), text,
                            typeTextComment, "", this);
                    textComment.setText("");
                }
                break;

        }
    }

    // VideoActivityView
//    =====================================================================
    @Override
    public void setVideo(List<VideoSEEYOU> mData) {
        this.mVideoSEEYOU = mData.get(0);
        mPlayer.setVideoURI(Uri.parse("http://seeyou.media/" + mVideoSEEYOU.getVideo_src()));
        mPlayer.start();
        mVideoService.getDataByVideo(mVideoSEEYOU.getId(), this);

        mVideos = (FragmentVideos) getFragmentManager()
                .findFragmentByTag(STATICS_PARAMS.TAG_VIDEOS_FRAGMENT);
        if (mVideos != null) {
            mVideos.loadVideosByCategory(mVideoSEEYOU.getCategory_slug());
        }
    }

    @Override
    public void setDataVideo(List<VideoSEEYOU> mData) {
        this.mVideoSEEYOU = mData.get(0);
        String likes = mVideoSEEYOU.getLikes();
        String dislikes = mVideoSEEYOU.getDislikes();
        tvName.setText(mVideoSEEYOU.getTitle());
        tvAuthor.setText(mVideoSEEYOU.getAuthor_name());
        tvCategory.setText(mVideoSEEYOU.getCategory_name());
        tvDataPublish.setText(mVideoSEEYOU.getCreated_at());
        tvViews.setText(mVideoSEEYOU.getViews());
        tvLiks.setText(likes);
        tvDisLiks.setText(dislikes);

        String path = "http://seeyou.media/" + mVideoSEEYOU.getAuthor_avatar();
        Picasso.with(this)
                .load(path)
                .placeholder(R.drawable.progress_animation)
                .into(ivSmallIcon);
        Picasso.with(this)
                .load(path)
                .placeholder(R.drawable.progress_animation)
                .into(ivBigIcon);
        if (typeVideo == 1) {
            mVideoService.getVideoListComments(mVideoSEEYOU.getId(), this);
        } else {
            mVideoService.getListComments(mVideoSEEYOU.getId(), this);
        }
    }

    @Override
    public void loadVideoList(List<Broadcast> mData) {

    }

    @Override
    public void updateData() {
        mVideoService.getDataByVideo(mVideoSEEYOU.getId(), this);

    }

    @Override
    public void updateDataBroadcasr() {
        // TODO: 08.07.2016 for broadcast
    }

    @Override
    public void setFragmentVideos() {
        mVideos = (FragmentVideos) getFragmentManager()
                .findFragmentByTag(STATICS_PARAMS.TAG_VIDEOS_FRAGMENT);
        if (mVideos == null) {
            mVideos = FragmentVideos.getInstance();
        }
        setFragment(mVideos, STATICS_PARAMS.TAG_VIDEOS_FRAGMENT, 1);
    }

    @Override
    public void setFragmentPeople() {
        mPeorle = (FragmentPeorle) getFragmentManager()
                .findFragmentByTag(STATICS_PARAMS.TAG_PEOPLE_FRAGMENT);
        if (mPeorle == null) {
            mPeorle = FragmentPeorle.getInstance();
        }
        setFragment(mPeorle, STATICS_PARAMS.TAG_PEOPLE_FRAGMENT, 2);
    }

    @Override
    public void setFragmentAbout() {
        mAbout = (FragmentAbout) getFragmentManager()
                .findFragmentByTag(STATICS_PARAMS.TAG_ABOUT_FRAGMENT);
        if (mAbout == null) {
            mAbout = FragmentAbout.getInstance();
        }
        setFragment(mAbout, STATICS_PARAMS.TAG_ABOUT_FRAGMENT, 3);
    }

    @Override
    public void setFragmentGamews() {
        mGames = (FragmentGames) getFragmentManager()
                .findFragmentByTag(STATICS_PARAMS.TAG_GAMES_FRAGMENT);
        if (mGames == null) {
            mGames = FragmentGames.getInstance();
        }
        setFragment(mGames, STATICS_PARAMS.TAG_GAMES_FRAGMENT, 4);
    }

    @Override
    public void setListCommens(List<CommentVideo> mList) {
        if (mListComments != null) {
            mListComments.clear();
        }
        mListComments = mList;
        updateListComments();
    }


    private void setFragment(Fragment fragment, String tag, int fragmenIntt) {
        mFragmentManager.beginTransaction()
                .replace(R.id.container_show_video_activity, fragment, tag)
                .commit();
        vVideos.setBackgroundResource(R.color.light_green);
        vPeople.setBackgroundResource(R.color.light_green);
        vAbout.setBackgroundResource(R.color.light_green);
        vGames.setBackgroundResource(R.color.light_green);
        switch (fragmenIntt) {
            case 1:
                vVideos.setBackgroundResource(R.color.color_green);
                break;

            case 2:
                vPeople.setBackgroundResource(R.color.color_green);
                break;

            case 3:
                vAbout.setBackgroundResource(R.color.color_green);
                break;

            case 4:
                vGames.setBackgroundResource(R.color.color_green);
                break;

        }
    }

    //    =====================================================================
    // RecyclerVideoAdapterBroadcastVideoActivity.ClickVideo
//    =====================================================================
    @Override
    public void clickToConnect(CommentVideo mCommentVideo) {
        new DialogMakeInOutCall(mCommentVideo, this, this).show();
    }

    //    =====================================================================
    // ShowVideo
//    =====================================================================
    @Override
    public void clickShowVideo(VideoSEEYOU mVideoSEEYOU) {
//        mPlayer.setVideoURI(Uri.parse("http://seeyou.media/" + mVideoSEEYOU.getVideo_src()));
//        mPlayer.start();
        mVideoService.getAndLoadVideo(mVideoSEEYOU.getId(), this);
    }


    //    =====================================================================
    // DialogMakeInOutCall
//    =====================================================================
    @Override
    public void sendMess(String text, String mRelation) {
        mVideoService.sendComments(
                SettingsApp.getUserId(mSharedPreferences),
                SettingsApp.getUserPass(mSharedPreferences),
                typeVideo, mVideoSEEYOU.getId(), text,
                2, mRelation, this);
        textComment.setText("");
    }

//    =====================================================================
}
