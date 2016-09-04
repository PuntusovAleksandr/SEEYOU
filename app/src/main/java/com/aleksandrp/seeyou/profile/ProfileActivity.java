package com.aleksandrp.seeyou.profile;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.context_dialog.DialogDate;
import com.aleksandrp.seeyou.context_dialog.DialogSelectCountry;
import com.aleksandrp.seeyou.profile.entity.UserProfile;
import com.aleksandrp.seeyou.profile.impl.ProfileActivityListenerImpl;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;
import com.aleksandrp.seeyou.utils_app.UtilsApp;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener,
        ProfileActivityView, DialogSelectCountry.OnInteractionListener,
        DialogDate.DialogDateListener {

    private ProfileActivityListener mListener;

    private ProgressBar mProgressBar;
    private Button btEdit, btCancel;
    private ImageView ivIconUser, ivSelectDate, ivSelectCountry, ivSelectSity;
    private TextView tvDate, tvCounry, tvCity, tvMail;
    private CheckBox cbMan, cbWoman;
    private EditText etName, etSurname;
    private RelativeLayout rlCountry, rlCity;


    private String pathToImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        pathToImage = "";
        mListener = new ProfileActivityListenerImpl();

        mProgressBar = (ProgressBar) findViewById(R.id.progress_profile);
        btEdit = (Button) findViewById(R.id.bt_edit_profile);
        btEdit.setOnClickListener(this);        // bt edit

        ivSelectDate = (ImageView) findViewById(R.id.iv_select_data);
        ivSelectCountry = (ImageView) findViewById(R.id.iv_select_country);
        ivSelectSity = (ImageView) findViewById(R.id.iv_select_sity);

        ivIconUser = (ImageView) findViewById(R.id.iv_user_Profile);
        ivIconUser.setOnClickListener(this);        // select icon

        tvMail = (TextView) findViewById(R.id.tv_email_profile);
        tvDate = (TextView) findViewById(R.id.tv_date_profile);
        tvDate.setOnClickListener(this);        // select date

        tvCounry = (TextView) findViewById(R.id.tv_select_country);
        tvCity = (TextView) findViewById(R.id.tv_select_city);

        etName = (EditText) findViewById(R.id.et_name_profile);
        etSurname = (EditText) findViewById(R.id.et_surname_profile);

        cbMan = (CheckBox) findViewById(R.id.cb_man);
        findViewById(R.id.cb_man).setOnClickListener(this);                 // check man

        cbWoman = (CheckBox) findViewById(R.id.cb_women);
        findViewById(R.id.cb_women).setOnClickListener(this);               // check woman

        rlCity = (RelativeLayout) findViewById(R.id.ll_select_city);
        rlCity.setOnClickListener(this);      // select country

        rlCountry = (RelativeLayout) findViewById(R.id.ll_select_country);
        rlCountry.setOnClickListener(this);         // select city

        findViewById(R.id.rl_back_profile).setOnClickListener(this);        // back
        btCancel = (Button) findViewById(R.id.bt_cancel);                   // bt cancel
        btCancel.setOnClickListener(this);

        disableAllViews();

        mListener.loadData(this);
    }

    @Override
    public void onClick(View mView) {
        UtilsApp.disableDoubleClick(mView);
        switch (mView.getId()) {
            // back
            case R.id.rl_back_profile:
                onBackPressed();
                finish();
                break;

            // bt edit
            case R.id.bt_edit_profile:
                mListener.enableAll(this);
                break;

            // select icon
            case R.id.iv_user_Profile:
                mListener.setImageUser(this);
                break;

            // select date
            case R.id.tv_date_profile:
                new DialogDate(ProfileActivity.this).show();
                break;

            // select country
            case R.id.ll_select_country:
                // TODO: 20.06.2016 for example
                ArrayList<String> countriesList = new ArrayList<>();
                countriesList.add("Ui");
                countriesList.add("RU");
                countriesList.add("America");
                new DialogSelectCountry(ProfileActivity.this,
                        countriesList,
                        STATICS_PARAMS.COUNTRY)
                        .show();
                break;

            // select city
            case R.id.ll_select_city:
                // TODO: 20.06.2016 for example
                ArrayList<String> citysList = new ArrayList<>();
                citysList.add("Dnepr");
                citysList.add("Kiev");
                citysList.add("London");
                new DialogSelectCountry(ProfileActivity.this,
                        citysList,
                        STATICS_PARAMS.CITY)
                        .show();
                break;

            // bt cancel
            case R.id.bt_cancel:
                if (btEdit.getText().toString().equals(getResources().getText(R.string.ok))) {
                    disableAllViews();
                } else {
                    onBackPressed();
                    finish();
                }
                break;

            // check man
            case R.id.cb_man:
                cbMan.setChecked(true);
                cbWoman.setChecked(false);
                break;

            // check woman
            case R.id.cb_women:
                cbWoman.setChecked(true);
                cbMan.setChecked(false);
                break;


        }
    }


    // fro ProfileActivityView
//    =============================================================
    @Override
    public void showProgress() {
        if (mProgressBar.getVisibility() == View.GONE) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void setParams(UserProfile mUserProfile) {
        setDataInUi(mUserProfile);
    }

    @Override
    public void setEnableAll() {
        if (btEdit.getText().toString().equals(getResources().getText(R.string.ok))) {
            btCancel.setVisibility(View.GONE);
            disableAllViews();
            String sex = STATICS_PARAMS.SEX_MAN;
            if (cbWoman.isChecked()) {
                sex = STATICS_PARAMS.SEX_WOMAN;
            }

            mListener.sendDataToServer(new UserProfile(
                    pathToImage,
                    etName.getText().toString(),
                    etSurname.getText().toString(),
                    tvMail.getText().toString(),
                    tvDate.getText().toString(),
                    tvCounry.getText().toString(),
                    tvCity.getText().toString(),
                    sex
            ));
        } else enableAllViews();
    }

    @Override
    public void setImageUser(Intent mChooser, int mResultLoadImageProfile) {
        startActivityForResult(mChooser, mResultLoadImageProfile);
    }

    private void enableAllViews() {
        btCancel.setVisibility(View.VISIBLE);

        btEdit.setText(getResources().getText(R.string.ok));

        ivIconUser.setEnabled(true);
        tvDate.setEnabled(true);
        tvCounry.setEnabled(true);
        tvCity.setEnabled(true);
        cbMan.setEnabled(true);
        cbWoman.setEnabled(true);
        etName.setEnabled(true);
        etSurname.setEnabled(true);
        rlCountry.setEnabled(true);
        rlCity.setEnabled(true);

        ivSelectDate.setVisibility(View.VISIBLE);
        ivSelectCountry.setVisibility(View.VISIBLE);
        ivSelectSity.setVisibility(View.VISIBLE);
    }

    private void disableAllViews() {

        btCancel.setVisibility(View.GONE);

        btEdit.setText(getResources().getText(R.string.edit));

        ivIconUser.setEnabled(false);
        tvDate.setEnabled(false);
        tvCounry.setEnabled(false);
        tvCity.setEnabled(false);
        cbMan.setEnabled(false);
        cbWoman.setEnabled(false);
        etName.setEnabled(false);
        etSurname.setEnabled(false);
        rlCountry.setEnabled(false);
        rlCity.setEnabled(false);

        ivSelectDate.setVisibility(View.INVISIBLE);
        ivSelectCountry.setVisibility(View.INVISIBLE);
        ivSelectSity.setVisibility(View.INVISIBLE);
    }


    //    ============================================================


    private void setDataInUi(UserProfile mUserProfile) {
        pathToImage = mUserProfile.getPathIcon();
//        if (pathToImage != null && !pathToImage.isEmpty()) {
//            Picasso.with(ProfileActivity.this)
//                    .load(pathToImage)
//                    .into(ivIconUser);
//        } else {
        ivIconUser.setImageResource(R.drawable.user);
//        }
        etName.setText(mUserProfile.getName());
        etSurname.setText(mUserProfile.getSurName());
        tvMail.setText(mUserProfile.getE_mail());
        tvCounry.setText(mUserProfile.getCountry());
        tvCity.setText(mUserProfile.getCity());
        if (mUserProfile.getSex().equals(STATICS_PARAMS.SEX_MAN)) {
            cbMan.setChecked(true);
            cbWoman.setChecked(false);
        } else {
            cbMan.setChecked(false);
            cbWoman.setChecked(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == STATICS_PARAMS.RESULT_LOAD_IMAGE_PROFILE) {
                Uri selectedImageUri = data.getData();
                // MEDIA GALLERY
                pathToImage = getPath(selectedImageUri);
                if (pathToImage != null) {
                    File file = new File(pathToImage);
                    Picasso.with(ProfileActivity.this)
                            .load(file)
                            .fit()
                            .into(ivIconUser);
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
//    ===========================================================================

    @Override
    public void onSelectCountry(String country) {
        tvCounry.setText(country);
    }

    @Override
    public void onSelectCity(String country) {
        tvCity.setText(country);
    }

    @Override
    public void onSelectCategory(String category) {

    }
//    =========================================================================


    //  DialogDate.DialogDateListener
//    =========================================================================
    @Override
    public void setDataInTextView(String mData) {
        tvDate.setText(mData);
    }
//    =========================================================================
}
