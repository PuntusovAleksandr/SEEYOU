package com.aleksandrp.seeyou.main;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.about_app.AboutActivity;
import com.aleksandrp.seeyou.login.LoginActivity;
import com.aleksandrp.seeyou.main.adapter.TabAdapter;
import com.aleksandrp.seeyou.main.fragment.AdultOnlyFragment;
import com.aleksandrp.seeyou.main.fragment.BroadcastsFragment;
import com.aleksandrp.seeyou.main.fragment.VideoSelfieFragment;
import com.aleksandrp.seeyou.main.impl.MainActivityListenerImpl;
import com.aleksandrp.seeyou.message.MessageActivity;
import com.aleksandrp.seeyou.profile.ProfileActivity;
import com.aleksandrp.seeyou.retrofit.AllRequest;
import com.aleksandrp.seeyou.retrofit.entity.Broadcast;
import com.aleksandrp.seeyou.retrofit.entity.CategoryFilm;
import com.aleksandrp.seeyou.retrofit.entity.VideoSEEYOU;
import com.aleksandrp.seeyou.retrofit.impl.AllRequestImpl;
import com.aleksandrp.seeyou.settings.SettingsActivity;
import com.aleksandrp.seeyou.upload.UploadActivity;
import com.aleksandrp.seeyou.utils_app.SettingsApp;
import com.aleksandrp.seeyou.utils_app.UtilsApp;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        MainActivityView,
        AllRequestImpl.GetNewPopularSeeYouVideos,
        AllRequestImpl.GetBroadcastVideos {


    private MainActivityListener mActivityListener;

    private LinearLayout mLeftLayout;

    private TabAdapter mTabAdapter;

    private LinearLayout mProfileSettings;

    private ListView mCategories;
    private ArrayList<String> mCategoriesName;
    private ArrayAdapter<String> adapter;
    private int positionTab;
    private List<CategoryFilm> mVideoCategories;

    private SharedPreferences sharedPreferences;

    private RelativeLayout showAll, noShowAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkSave();
        setContentView(R.layout.activity_main);

        showAll = (RelativeLayout) findViewById(R.id.rl_show_all);
        noShowAll = (RelativeLayout) findViewById(R.id.rl_no_show_all);

        findViewById(R.id.bt_go).setOnClickListener(this);

        if (SettingsApp.getGreeting(sharedPreferences)) {

        } else {
            showAllViews();
        }
    }

    private void showAllViews() {

        showAll.setVisibility(View.GONE);
        noShowAll.setVisibility(View.VISIBLE);

        mActivityListener = new MainActivityListenerImpl();

        mLeftLayout = (LinearLayout) findViewById(R.id.ll_main_left_bar);
        mProfileSettings = (LinearLayout) findViewById(R.id.fragment_profile_settings);

        positionTab = 0;
        initViewPager();

        findViewById(R.id.icon_search).setOnClickListener(this);
        findViewById(R.id.icon_load).setOnClickListener(this);
        findViewById(R.id.icon_user).setOnClickListener(this);
        findViewById(R.id.iv_list_blank).setOnClickListener(this);
        findViewById(R.id.view_left_baar).setOnClickListener(this);
        findViewById(R.id.ll_main_profile_settings).setOnClickListener(this);


        findViewById(R.id.tv_live_chanel).setOnClickListener(this);
        findViewById(R.id.tv_your_liked_videos).setOnClickListener(this);

        // views from settings profile
        findViewById(R.id.rl_insaider).setOnClickListener(this);
        findViewById(R.id.rl_message).setOnClickListener(this);
        findViewById(R.id.et_my_profile).setOnClickListener(this);
//        findViewById(R.id.et_settings).setOnClickListener(this);
        findViewById(R.id.et_about).setOnClickListener(this);
        findViewById(R.id.et_sign_out).setOnClickListener(this);

        initListView();

        checkAfoult();

    }

    private void checkAfoult() {
        mActivityListener.checkAdult(
                SettingsApp.getUserId(sharedPreferences),
                SettingsApp.getUserPass(sharedPreferences),
                this, this);
    }

    private void initListView() {
        mCategories = (ListView) findViewById(R.id.list_category);
        mCategoriesName = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mCategoriesName);
        mCategories.setAdapter(adapter);
        mCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                UtilsApp.disableDoubleClick(itemClicked);
                loadDataAndNotifyAdapter(mCategoriesName.get(position));
                openClouseClickListView();
            }
        });
        mActivityListener.loadCategory(positionTab, this, this);
    }

    private void openClouseClickListView() {
        mActivityListener.openCloseLeftBar(this);
    }

    private void checkSave() {
        sharedPreferences =
                getSharedPreferences(SettingsApp.FILE_NAME, Context.MODE_PRIVATE);
        if (SettingsApp.getUserMail(sharedPreferences).length() <= 0 &&
                SettingsApp.getUserPass(sharedPreferences).length() <= 0) {
            finish();
        }
    }


    @Override
    public void onClick(View mView) {
        UtilsApp.disableDoubleClick(mView);
        switch (mView.getId()) {
            case R.id.bt_go:
                SettingsApp.setGreeting(false, sharedPreferences);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showAllViews();
                    }
                });
                break;

            case R.id.icon_search:
                break;

            case R.id.icon_load:
                mActivityListener.goToLoader(this);
                break;

            case R.id.icon_user:
                mActivityListener.showSettingsProfile(this);
                break;

            case R.id.ll_main_profile_settings:
                mActivityListener.showSettingsProfile(this);
                break;

            case R.id.iv_list_blank:
                mActivityListener.openCloseLeftBar(this);
                mActivityListener.loadCategory(positionTab, this, this);
                break;

            case R.id.view_left_baar:
                mActivityListener.openCloseLeftBar(this);
                break;


            // text view on left bar
            case R.id.tv_live_chanel:
                loadDataAndNotifyAdapter(getResources().getString(R.string.live_chanels));
                mActivityListener.openCloseLeftBar(this);
                break;

            case R.id.tv_your_liked_videos:
                loadDataAndNotifyAdapter(getResources().getString(R.string.your_liked_videos));
                mActivityListener.openCloseLeftBar(this);
                break;


            // view from settings Profile
            case R.id.rl_insaider:
                break;

            case R.id.rl_message:
                mActivityListener.goToMessageActivity(this);
                break;

            case R.id.et_my_profile:
                mActivityListener.goToProfileActivity(this);
                break;

//            case R.id.et_settings:
//                mActivityListener.goToSettingsActivity(this);
//                break;

            case R.id.et_about:
                mActivityListener.goToAboutActivity(this);
                break;

            case R.id.et_sign_out:
                mActivityListener.logOutFromAcc(this, this);
                break;


        }
    }

    private void loadDataAndNotifyAdapter(String mNameCategory) {
        mActivityListener.loadData(mNameCategory, mViewPager.getCurrentItem(), this);
    }

    private ViewPager mViewPager;

    private void initViewPager() {
        FragmentManager mFragmentManager = getFragmentManager();
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.video_selfie));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.broadcasts));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.adoult_only));

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mTabAdapter = new TabAdapter(mFragmentManager, 3);

        mViewPager.setAdapter(mTabAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                positionTab = tab.getPosition();
                mViewPager.setCurrentItem(positionTab);
                updateAdapter();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void updateAdapter() {
        mActivityListener.loadCategory(positionTab, this, this);
    }

    // from MainActivityView
//    ===========================================
    @Override
    public void showHideLeftBar() {
        if (mLeftLayout.getVisibility() == View.GONE) {
            mLeftLayout.setVisibility(View.VISIBLE);
        } else mLeftLayout.setVisibility(View.GONE);
    }

    @Override
    public void notifyAdapterInFragment(String mNameCategory, int mNumberOfTabs) {
        AllRequest request = new AllRequestImpl(MainActivity.this);
        switch (mNumberOfTabs) {
            case 0:
                request.getLastVideoList(mNameCategory, "", this);
                request.getPopularVideoList(mNameCategory, "", this);
                request.getVideoList(false, mNameCategory, "", this);
                break;

            case 1:
                request.getBroadcasts(mNameCategory, this);
                break;

            case 2:
                AdultOnlyFragment adultOnlyFragment = AdultOnlyFragment.gewInstance();
                adultOnlyFragment.notifyAdapterByCategory(mNameCategory);
                // TODO: 11.07.2016  надо сделать получкение списка
                break;
        }
    }

    @Override
    public void goToUploadActivity() {
        startActivity(new Intent(MainActivity.this, UploadActivity.class));
    }

    @Override
    public void showSettingsProfile() {
        if (mProfileSettings.getVisibility() == View.GONE) {
            mProfileSettings.setVisibility(View.VISIBLE);
        } else mProfileSettings.setVisibility(View.GONE);
    }

    @Override
    public void logOut() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
        // TODO: 19.06.2016 make send logout
    }

    @Override
    public void goToMessageActivity() {
        startActivity(new Intent(MainActivity.this, MessageActivity.class));
        showSettingsProfile();
    }

    @Override
    public void goToProfileActivity() {
        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        showSettingsProfile();
    }

    @Override
    public void goToSettingsActivity() {
        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        showSettingsProfile();
    }

    @Override
    public void goToAboutActivity() {
        startActivity(new Intent(MainActivity.this, AboutActivity.class));
        showSettingsProfile();
    }

    @Override
    public void setCategoriesName(List<CategoryFilm> mVideoCategories) {
        if (this.mVideoCategories != null) {
            this.mVideoCategories.clear();
        }
        if (mCategoriesName != null) {
            mCategoriesName.clear();
        }
        this.mVideoCategories = mVideoCategories;
        for (CategoryFilm film : mVideoCategories) {
            mCategoriesName.add(film.getName());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setAdult(boolean adult) {
        SettingsApp.setAdult(adult, sharedPreferences);
    }

//    ============================================
//        for update in fragments AllRequestImpl.GetNewPopularSeeYouVideos
//    ============================================

    @Override
    public void getNewVideo(List<VideoSEEYOU> mSEEYOU) {
        VideoSelfieFragment videoSelfieFragment = VideoSelfieFragment.gewInstance();
        videoSelfieFragment.notifyListNewAdapter(mSEEYOU);
    }

    @Override
    public void getPopularVideo(List<VideoSEEYOU> mSEEYOU) {
        VideoSelfieFragment videoSelfieFragment = VideoSelfieFragment.gewInstance();
        videoSelfieFragment.notifyListPopularAdapter(mSEEYOU);
    }

    @Override
    public void getSeeYouVideo(boolean firstSStart, List<VideoSEEYOU> mSEEYOU) {
        VideoSelfieFragment videoSelfieFragment = VideoSelfieFragment.gewInstance();
        videoSelfieFragment.notifyListSeeYouAdapter(mSEEYOU);
    }


    //    ============================================
//        for update in fragments  AllRequestImpl.GetBroadcastVideos
//    ============================================
    @Override
    public void loadVideo(List<Broadcast> mBroadcastses) {
        BroadcastsFragment broadcastsFragment = BroadcastsFragment.gewInstance();
        broadcastsFragment.notifyListAdapterByCategory(mBroadcastses);
    }

    //    ============================================
//        for update in fragments  AllRequestImpl.GetBroadcastVideos
//    ============================================


//    ============================================
}
