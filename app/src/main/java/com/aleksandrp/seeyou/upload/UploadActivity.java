package com.aleksandrp.seeyou.upload;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.about_app.AboutActivity;
import com.aleksandrp.seeyou.context_dialog.DialogSelectCountry;
import com.aleksandrp.seeyou.login.LoginActivity;
import com.aleksandrp.seeyou.message.MessageActivity;
import com.aleksandrp.seeyou.profile.ProfileActivity;
import com.aleksandrp.seeyou.retrofit.AllRequest;
import com.aleksandrp.seeyou.retrofit.entity.CategoryFilm;
import com.aleksandrp.seeyou.retrofit.impl.AllRequestImpl;
import com.aleksandrp.seeyou.settings.SettingsActivity;
import com.aleksandrp.seeyou.upload.entity.DataFile;
import com.aleksandrp.seeyou.upload.impl.UploadActivityPresentImpl;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;
import com.aleksandrp.seeyou.utils_app.UtilsApp;

import java.util.ArrayList;
import java.util.List;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener,
        UploadActivityView, DialogSelectCountry.OnInteractionListener {

    private UploadActivityPresent mActivityView;

    private LinearLayout mLayoutProfileSettings;
    private RelativeLayout mLayoutVideo, mMainUpload;
    private ProgressBar mProgressBar;
    private VideoView mPlayer;
    private TextView mTitle, mDescription, mCategory, mPhone;
    private CheckBox cbSMS, cbIsAgree;


    private List<CategoryFilm> mData;

    private String mPPathFile = "";
    private boolean isSmsNotification, isAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        mActivityView = new UploadActivityPresentImpl();
        isSmsNotification = false;
        isAgree = false;

        mData = new ArrayList<>();

        mProgressBar = (ProgressBar) findViewById(R.id.progress_upload);
        mMainUpload = (RelativeLayout) findViewById(R.id.rl_main_upload);

        mLayoutProfileSettings = (LinearLayout) findViewById(R.id.fragment_profile_settings);
        mLayoutVideo = (RelativeLayout) findViewById(R.id.rl_upload_video);
        mLayoutVideo.setOnClickListener(this);                                  // load icon
        mPlayer = (VideoView) findViewById(R.id.player_upload);

        mTitle = (TextView) findViewById(R.id.et_type_title);
        mDescription = (TextView) findViewById(R.id.et_description_upload);
        mCategory = (TextView) findViewById(R.id.tv_select_category);
        mPhone = (TextView) findViewById(R.id.et_number);

        cbSMS = (CheckBox) findViewById(R.id.cb_sms_notification);
        cbSMS.setOnClickListener(this);                                         // check box add phone

        cbIsAgree = (CheckBox) findViewById(R.id.cb_license);
        cbIsAgree.setOnClickListener(this);                                     // check box i agree

        findViewById(R.id.ll_select_category).setOnClickListener(this);         // select category
        findViewById(R.id.bt_upload_video).setOnClickListener(this);            // bt upload
        findViewById(R.id.rl_back_upload).setOnClickListener(this);             // back
        findViewById(R.id.icon_user).setOnClickListener(this);                  // image button in profile
        findViewById(R.id.bt_load).setOnClickListener(this);                    // bt add file
        findViewById(R.id.ll_main_profile_settings).setOnClickListener(this);

        // views from settings profile
        findViewById(R.id.rl_insaider).setOnClickListener(this);
        findViewById(R.id.rl_message).setOnClickListener(this);
        findViewById(R.id.et_my_profile).setOnClickListener(this);
//        findViewById(R.id.et_settings).setOnClickListener(this);
        findViewById(R.id.et_about).setOnClickListener(this);
        findViewById(R.id.et_sign_out).setOnClickListener(this);
        {
            // TODO: 11.07.2016 Сделано временно, т.к. пока нет функционала
            View ivIcon = findViewById(R.id.layout_icon);
            ivIcon.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View mView) {
        UtilsApp.disableDoubleClick(mView);
        switch (mView.getId()) {
            case R.id.rl_upload_video:
            case R.id.bt_load:
                mActivityView.selectFileFromDevice(this);
                break;

            case R.id.ll_select_category:
                mActivityView.showDialogSelectCategorry(this);
                break;

            case R.id.cb_sms_notification:
                if (cbSMS.isChecked()) {
                    isSmsNotification = true;
                } else isSmsNotification = false;
                break;

            case R.id.cb_license:
                if (cbIsAgree.isChecked()) {
                    isAgree = true;
                } else isAgree = false;
                break;

            case R.id.bt_upload_video:
                String phoneNumber = "";
                if (isSmsNotification) {
                    phoneNumber = mPhone.getText().toString();
                }
                DataFile dataFile = new DataFile(
                        mPPathFile,
                        mTitle.getText().toString(),
                        mDescription.getText().toString(),
                        mCategory.getText().toString(),
                        phoneNumber,
                        isSmsNotification,
                        isAgree
                );
                mActivityView.startUploadVideo(this, this, dataFile);
                break;

            case R.id.rl_back_upload:
                finish();
                break;

            case R.id.icon_user:
                mActivityView.showProfileSetings(this);
                break;

            case R.id.ll_main_profile_settings:
                mActivityView.showProfileSetings(this);
                break;


            // view from settings Profile
            case R.id.rl_insaider:
                break;

            case R.id.rl_message:
                mActivityView.goToMessageActivity(this);
                break;

            case R.id.et_my_profile:
                mActivityView.goToProfileActivity(this);
                break;

//            case R.id.et_settings:
//                mActivityView.goToSettingsActivity(this);
//                break;

            case R.id.et_about:
                mActivityView.goToAboutActivity(this);
                break;

            case R.id.et_sign_out:
                mActivityView.signOutFromAcc(this, this);
                break;

        }
    }


    // from UploadActivityView
//    ========================================================
    @Override
    public void showProfileSettings() {
        if (mLayoutProfileSettings.getVisibility() == View.GONE) {
            mLayoutProfileSettings.setVisibility(View.VISIBLE);
        } else mLayoutProfileSettings.setVisibility(View.GONE);
    }

    @Override
    public void signOut() {
        Intent intent = new Intent(UploadActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
        System.exit(0);
        // TODO: 19.06.2016 make send logout
    }

    @Override
    public void startActivityGallery(Intent mGalleryIntent, int mResultLoadImage) {
        startActivityForResult(mGalleryIntent, mResultLoadImage);
    }

    @Override
    public void setVideoToBackground(String mSelectedImagePath) {
        mLayoutVideo.setEnabled(false);

        mPlayer.setVisibility(View.VISIBLE);
        mPlayer.setVideoURI(Uri.parse(mSelectedImagePath));
        mPlayer.setMediaController(new MediaController(this));

        mPlayer.requestFocus();
    }

    @Override
    public void goToMessageActivity() {
        // TODO: 19.06.2016 make message activity
        startActivity(new Intent(UploadActivity.this, MessageActivity.class));
        showProfileSettings();
    }

    @Override
    public void goToProfileActivity() {
        // TODO: 19.06.2016 make message activity
        startActivity(new Intent(UploadActivity.this, ProfileActivity.class));
        showProfileSettings();
    }

    @Override
    public void goToSettingsActivity() {
        // TODO: 19.06.2016 make message activity
        startActivity(new Intent(UploadActivity.this, SettingsActivity.class));
        showProfileSettings();
    }

    @Override
    public void goToAboutActivity() {
        // TODO: 19.06.2016 make message activity
        startActivity(new Intent(UploadActivity.this, AboutActivity.class));
        showProfileSettings();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void disableAll() {
        mMainUpload.setEnabled(false);
    }

    @Override
    public void enableAll() {
        mMainUpload.setEnabled(true);
    }

    @Override
    public void destroyActivity() {
        finish();
    }

    @Override
    public void setCategory() {
// TODO: 19.06.2016 for trenning
        // TODO: 19.06.2016 надо сделать контекстный диалог

        AllRequest request = new AllRequestImpl(this);
        request.getVideoCategories(mActivityView, this);

    }

    @Override
    public void setCategoriesName(List<CategoryFilm> mData) {
        ArrayList<String> list = new ArrayList<>();
        if (this.mData != null) {
            this.mData.clear();
        }
        this.mData = mData;

        for (CategoryFilm film : mData) {
            list.add(film.getName());
        }

        new DialogSelectCountry(UploadActivity.this,
                list,
                STATICS_PARAMS.CATEGORY)
                .show();
    }

//    ===========================================================

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == STATICS_PARAMS.RESULT_LOAD_IMAGE) {
                Uri selectedImageUri = data.getData();

                // OI FILE Manager
                String path = selectedImageUri.getPath();

                // MEDIA GALLERY
                mPPathFile = getPath(selectedImageUri);
                if (mPPathFile != null) {
                    mActivityView.setVideoToBackground(mPPathFile, this);
                }
            }
        }
    }

    // UPDATED!
    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }


    // from DialogSelectCountry.OnInteractionListener
//    ================================================================
    @Override
    public void onSelectCountry(String country) {

    }

    @Override
    public void onSelectCity(String country) {

    }

    @Override
    public void onSelectCategory(String category) {
        TextView view = (TextView) findViewById(R.id.tv_select_category);
        view.setText(category);
    }

    //    ================================================================
}
