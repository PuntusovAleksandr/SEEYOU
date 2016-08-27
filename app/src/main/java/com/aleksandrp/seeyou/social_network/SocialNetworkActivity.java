package com.aleksandrp.seeyou.social_network;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.social_network.impl.LoginPresenterImpl;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class SocialNetworkActivity extends AppCompatActivity
        implements LoginView {

    private ProgressBar mProgressBar;
//    private TextView validation;

    private LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_conteiner);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar_soc);

        presenter = new LoginPresenterImpl(this, this);

        String keySoc = getIntent().getStringExtra(STATICS_PARAMS.KEY_SOCIAL_NETWORK);
        if (keySoc.equals(STATICS_PARAMS.FB)) {
            presenter.connectToFB(this);
        } else if (keySoc.equals(STATICS_PARAMS.GOOGLE)) {
            presenter.connectToGOOGLE();
        } else if (keySoc.equals(STATICS_PARAMS.TWITTER)) {
            presenter.connectToTwitter();
        } else {
            Toast.makeText(SocialNetworkActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 3000);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == STATICS_PARAMS.GOOGLE_CODE) {
                presenter.onActivityResultGoogle(requestCode, resultCode, data);
            }
            if (requestCode == STATICS_PARAMS.FB_CODE) {
                presenter.onActivityResultFB(requestCode, resultCode, data);
            }
            if (requestCode == STATICS_PARAMS.TWITTER_CODE) {
                presenter.onActivityResultTwitter(requestCode, resultCode, data);
            }

        } else if (requestCode == STATICS_PARAMS.GOOGLE_CODE_ERROR) {
            hideProgress();
            finish();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    //from LoginView
//======================================================
    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUserError() {

    }

    @Override
    public void setResult(String mId, String mEmail, String mDisplayName, String mS) {
        Intent intent = new Intent();
        intent.putExtra(STATICS_PARAMS.RESULT_ID, mId);
        intent.putExtra(STATICS_PARAMS.RESULT_MAIL, mEmail);
        intent.putExtra(STATICS_PARAMS.RESULT_NAME, mDisplayName);
        intent.putExtra(STATICS_PARAMS.RESULT_PHOTO, mS);
        setResult(RESULT_OK, intent);
        finish();
    }
//======================================================


}
