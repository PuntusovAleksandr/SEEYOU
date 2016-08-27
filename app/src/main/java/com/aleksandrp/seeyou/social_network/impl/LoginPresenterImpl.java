package com.aleksandrp.seeyou.social_network.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.aleksandrp.seeyou.social_network.LoginPresenter;
import com.aleksandrp.seeyou.social_network.LoginView;
import com.aleksandrp.seeyou.social_network.SocialNetworkActivity;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.services.AccountService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import io.fabric.sdk.android.Fabric;


public class LoginPresenterImpl implements LoginPresenter {

    private LoginView mLoginView;
    private CallbackManager mCallbackManager;
    private Context mContext;
//    private ServiceRealm mRealm;
    private TwitterLoginButton mLoginButtonTwitter;

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "z2lLHYWT15cZuNcgiMJWnxz88";
    private static final String TWITTER_SECRET = "oWKpnViGePXfD2LTThahP1lKNcoqrMztdP4gdS6gU2ymfamt4i";



    public LoginPresenterImpl(Context mContext, LoginView mLoginView) {
        this.mContext = mContext;
        this.mLoginView = mLoginView;
    }


    @Override
    public void connectToFB(Context mContext) {
        showProgress();
        // init FB
        FacebookSdk.sdkInitialize(mContext.getApplicationContext());
        AppEventsLogger.activateApp(mContext);
        mCallbackManager = CallbackManager.Factory.create();

//        String pic_link="https://graph.facebook.com/" + id + "/picture?type=large";

        LoginManager mInstance = LoginManager.getInstance();
        mInstance
                .logInWithReadPermissions((SocialNetworkActivity) mContext,
                        Arrays.asList("public_profile", "user_friends", "email", "user_birthday"));
        mInstance
                .registerCallback(mCallbackManager,
                        new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                // App code
                                hideProgress();
                                AccessToken mAccessToken = loginResult.getAccessToken();
                                GraphRequest request = GraphRequest.newMeRequest(
                                        mAccessToken,
                                        new GraphRequest.GraphJSONObjectCallback() {
                                            @Override
                                            public void onCompleted(
                                                    JSONObject object,
                                                    GraphResponse response) {
                                                // Application code
                                                try {
                                                    String mId = object.getString("id");
                                                    register(
                                                            mId,
                                                            object.getString("email"),
                                                            object.getString("name"),
                                                            "https://graph.facebook.com/" +
                                                                    mId + "/picture?type=large"
                                                    );
                                                } catch (JSONException mE) {
                                                    mE.printStackTrace();
                                                }
                                            }
                                        });
                                Bundle parameters = new Bundle();
                                parameters.putString("fields", "birthday,email,id,name");
                                request.setParameters(parameters);
                                request.executeAsync();
                            }

                            @Override
                            public void onCancel() {
                                // App code
                                hideProgress();
                            }

                            @Override
                            public void onError(FacebookException exception) {
                                // App code
                                hideProgress();
                            }
                        });
    }

    @Override
    public void connectToTwitter() {
        showProgress();
        TwitterAuthConfig authConfig =  new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(mContext, new TwitterCore(authConfig));


        mLoginButtonTwitter = new TwitterLoginButton(mContext);
        mLoginButtonTwitter.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> resultBt) {
                hideProgress();
                TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
                AccountService statusesService = twitterApiClient.getAccountService();
                statusesService.verifyCredentials(true, true);
//                        .verifyCredentials(true, true, new Callback<com.twitter.sdk.android.core.models.User>() {
//                            @Override
//                            public void success(Result<com.twitter.sdk.android.core.models.User> result) {
//                                String id = String.valueOf(result.data.id);
//                                register(
//                                        id,
//                                        result.data.email,
//                                        result.data.name,
//                                        "https://graph.facebook.com/" +
//                                                id + "/picture?type=large"
//                                );
//                            }
//                            @Override
//                            public void failure(TwitterException exception) {
//                                hideProgress();
////                                errorUser();
//                            }
//                        });
            }

            @Override
            public void failure(TwitterException exception) {
                System.out.println("TwitterException :::::: " + exception);
                hideProgress();
//                errorUser();
            }
        });
        mLoginButtonTwitter.performClick();

    }

    @Override
    public void connectToGOOGLE() {
        showProgress();
// Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


// Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .enableAutoManage((SocialNetworkActivity) mContext, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult mConnectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        SocialNetworkActivity activity = (SocialNetworkActivity) mContext;
        if (activity != null) {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            activity.startActivityForResult(signInIntent, STATICS_PARAMS.GOOGLE_CODE);
        }

    }

    @Override
    public void onActivityResultFB(int mRequestCode, int mResultCode, Intent mData) {
        mCallbackManager.onActivityResult(mRequestCode, mResultCode, mData);
    }

    @Override
    public void onActivityResultGoogle(int mRequestCode, int mResultCode, Intent mData) {
        hideProgress();
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(mData);
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            register(
                    acct.getId(),
                    acct.getEmail(),
                    acct.getDisplayName(),
                    String.valueOf(acct.getPhotoUrl())
            );
        } else {
            errorRegister();
        }
    }


    @Override
    public void onActivityResultTwitter(int requestCode, int resultCode, Intent data) {
        mLoginButtonTwitter.onActivityResult(requestCode, resultCode, data);
    }

    private void register(String mId, String mEmail, String mDisplayName, String mS) {
        if (mLoginView != null) {
            mLoginView.setResult(mId, mEmail, mDisplayName, mS);
        }
    }


    private void errorRegister() {

    }

    private void showProgress() {
        if (mLoginView != null) {
            mLoginView.showProgress();
        }
    }

    private void hideProgress() {
        if (mLoginView != null) {
            mLoginView.hideProgress();
        }
    }
}
