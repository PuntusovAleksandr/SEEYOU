package com.aleksandrp.seeyou.login;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.login.fragment.EmptyFragment;
import com.aleksandrp.seeyou.login.fragment.LoginFragment;
import com.aleksandrp.seeyou.login.fragment.RegisteringFragment;
import com.aleksandrp.seeyou.login.impl.LoginActivityPresentImpl;
import com.aleksandrp.seeyou.main.MainActivity;
import com.aleksandrp.seeyou.retrofit.AllRequest;
import com.aleksandrp.seeyou.retrofit.impl.AllRequestImpl;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity implements LoginActivityView,
        View.OnClickListener, LoginFragment.ClickLoginListener,
        RegisteringFragment.RegisteringFragmentListener,
        AllRequestImpl.SignInSocialOK {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "z2lLHYWT15cZuNcgiMJWnxz88";
    private static final String TWITTER_SECRET = "oWKpnViGePXfD2LTThahP1lKNcoqrMztdP4gdS6gU2ymfamt4i";


    private LoginActivityPresent mPresent;


    private FragmentManager mFragmentManager;
    private LoginFragment mLoginFragment;
    private RegisteringFragment mRegisteringFragment;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_login);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_main);

        mPresent = new LoginActivityPresentImpl(this, this);
        mFragmentManager = getFragmentManager();
        mPresent.checkLogin();
    }

    // from LoginActivityView
// ==========================================================================
    @Override
    public void setLoginFragment() {
        mLoginFragment = (LoginFragment) getFragmentManager()
                .findFragmentByTag(STATICS_PARAMS.TAG_LOGIN_FRAGMENT);
        if (mLoginFragment == null) {
            mLoginFragment = LoginFragment.getInstance();
        }
        setFragment(mLoginFragment, STATICS_PARAMS.TAG_LOGIN_FRAGMENT);
    }

    @Override
    public void setRegisterFragment() {
        mRegisteringFragment = (RegisteringFragment) getFragmentManager()
                .findFragmentByTag(STATICS_PARAMS.TAG_REGISTERING_FRAGMENT);
        if (mRegisteringFragment == null) {
            mRegisteringFragment = RegisteringFragment.getInstance(this);
        }
        setFragment(mRegisteringFragment, STATICS_PARAMS.TAG_REGISTERING_FRAGMENT);
    }

    @Override
    public void goToMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void setFragment(Fragment fragment, String tag) {
        mFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment, tag)
                .commit();
    }
// ==========================================================================


    // from OnClickListener
// ==========================================================================
    @Override
    public void onClick(View mView) {
    }
// ==========================================================================


    // from LoginFragment.ClickLoginListener
// ==========================================================================
    @Override
    public void signInWithSocialNetworks(String keySocNet) {
        mPresent.signInWithAsne(keySocNet);
    }

    @Override
    public void signIn(String mail, String pass) {
        mPresent.signIn(mail, pass);
    }

    @Override
    public void registering() {
        mPresent.registering();
    }

    // ==========================================================================


    // from RegisteringFragment.RegisteringFragmentListener
// ==========================================================================
    @Override
    public void onBackPress() {
        setLoginFragment();
    }

    @Override
    public void registerInWithSocialNetworks(String keySocNetwork) {
        mPresent.registeringWithAsne(keySocNetwork);
    }

    @Override
    public void shoeProgress() {
        showProgress();
    }

    @Override
    public void hideProgressAndGoToMainActivity() {
        hideProgress();
        goToMainActivity();
    }

// ==========================================================================


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == STATICS_PARAMS.REQUEST_CODE_ACTIVITY) {

                if (data != null) {

                    setFragment(new EmptyFragment(),STATICS_PARAMS.TAG_EMPTY_FRAGMENT );
                    showProgress();

                    String id = data.getStringExtra(STATICS_PARAMS.RESULT_ID);
                    String mail = data.getStringExtra(STATICS_PARAMS.RESULT_MAIL);
                    String name = data.getStringExtra(STATICS_PARAMS.RESULT_NAME);
                    String photo = data.getStringExtra(STATICS_PARAMS.RESULT_PHOTO);
                    AllRequest request = new AllRequestImpl(this);
                    request.registerFromSocial(
                           name,
                            mail,
                            id,
                            this
                    );
                }
//                goToMainActivity();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    // from AllRequestImpl.SignInSocialOK
//    ===================================================
    @Override
    public void okSignIn() {
        hideProgress();
        goToMainActivity();
    }

    @Override
    public void errorSignIn() {
        hideProgress();
        setLoginFragment();
        Toast.makeText(this, R.string.account_not_found, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorRegister() {
        Toast.makeText(this, R.string.database_insertion_error, Toast.LENGTH_SHORT).show();
    }
//    ===================================================
}
