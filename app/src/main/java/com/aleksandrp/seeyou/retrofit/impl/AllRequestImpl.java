package com.aleksandrp.seeyou.retrofit.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.main.MainActivityView;
import com.aleksandrp.seeyou.message.entity.MessageSeeYou;
import com.aleksandrp.seeyou.message.entity.ResultOk;
import com.aleksandrp.seeyou.message.entity.UserMessage;
import com.aleksandrp.seeyou.retrofit.AllRequest;
import com.aleksandrp.seeyou.retrofit.ServiceQuery;
import com.aleksandrp.seeyou.retrofit.entity.ActionCode;
import com.aleksandrp.seeyou.retrofit.entity.Adult;
import com.aleksandrp.seeyou.retrofit.entity.Broadcast;
import com.aleksandrp.seeyou.retrofit.entity.Broadcasts;
import com.aleksandrp.seeyou.retrofit.entity.CategoryFilm;
import com.aleksandrp.seeyou.retrofit.entity.City;
import com.aleksandrp.seeyou.retrofit.entity.CommentVideo;
import com.aleksandrp.seeyou.retrofit.entity.Comments;
import com.aleksandrp.seeyou.retrofit.entity.Country;
import com.aleksandrp.seeyou.retrofit.entity.DataUser;
import com.aleksandrp.seeyou.retrofit.entity.Register;
import com.aleksandrp.seeyou.retrofit.entity.SignInUser;
import com.aleksandrp.seeyou.retrofit.entity.UsersBroadcasts;
import com.aleksandrp.seeyou.retrofit.entity.UsersMessageOnLine;
import com.aleksandrp.seeyou.retrofit.entity.UsersVideo;
import com.aleksandrp.seeyou.retrofit.entity.VideoCategories;
import com.aleksandrp.seeyou.retrofit.entity.VideoSEEYOU;
import com.aleksandrp.seeyou.retrofit.entity.Videos;
import com.aleksandrp.seeyou.upload.UploadActivityPresent;
import com.aleksandrp.seeyou.upload.UploadActivityView;
import com.aleksandrp.seeyou.utils_app.SettingsApp;
import com.aleksandrp.seeyou.video_activity.VideoActivityView;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AleksandrP on 28.06.2016.
 */
public class AllRequestImpl implements AllRequest {

    private static final String DOMAIN_COM = "http://seeyou.media/";
    private static final String DOMAIN_COM_MESSAGE = "https://messager-api-eskrano.c9users.io/";

    private Context mContext;

    private SharedPreferences mSharedPreferences;
    private SignInOK mInOK;


    public AllRequestImpl(Context mContext) {
        this.mContext = mContext;
        mSharedPreferences =
                mContext.getSharedPreferences(SettingsApp.FILE_NAME, Context.MODE_PRIVATE);

    }

    @Override
    public void signInUser(final String email, final String password, final SignInOK mInOK) {
        this.mInOK = mInOK;
        ServiceQuery service = getRetrofitServiceQuery();
        Call<SignInUser> signInUserCall = service.signIn(email, password);
        signInUserCall.enqueue(new Callback<SignInUser>() {

            @Override
            public void onResponse(Call<SignInUser> call, Response<SignInUser> response) {
                hideProgressBar();
                SignInUser user = response.body();
                if (user == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (user.getSuccess().equals("1")) {
                        DataUser userData = user.getData();
                        SettingsApp.setUserMail(email, mSharedPreferences);
                        SettingsApp.setUserPass(password, mSharedPreferences);
                        SettingsApp.setUserId(userData.getUser_id(), mSharedPreferences);
                        SettingsApp.setUserName(userData.getUser_name(), mSharedPreferences);
                        SettingsApp.setUserAvatar(userData.getUser_avatar(), mSharedPreferences);
                        mInOK.okSignIn();
                    } else mInOK.errorSignIn();
                }
            }

            @Override
            public void onFailure(Call<SignInUser> call, Throwable t) {

                hideProgresWithError(t);
            }
        });
    }


    private void signInUserSocial(final String mMail, final String mId, final SignInSocialOK mSignInSocialOK) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<SignInUser> signInUserCall = service.signIn(mMail, mId);
        signInUserCall.enqueue(new Callback<SignInUser>() {
            @Override
        public void onResponse(Call<SignInUser> call, Response<SignInUser> response) {
            hideProgressBar();
                SignInUser user = response.body();
                if (user == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (user.getSuccess().equals("1")) {
                        DataUser userData = user.getData();
                        SettingsApp.setUserMail(mMail, mSharedPreferences);
                        SettingsApp.setUserPass(mId, mSharedPreferences);
                        SettingsApp.setUserId(userData.getUser_id(), mSharedPreferences);
                        SettingsApp.setUserName(userData.getUser_name(), mSharedPreferences);
                        SettingsApp.setUserAvatar(userData.getUser_avatar(), mSharedPreferences);
                        mSignInSocialOK.okSignIn();
                    } else mSignInSocialOK.errorSignIn();
                }
            }

            @Override
            public void onFailure(Call<SignInUser> call, Throwable t) {
               hideProgresWithError(t);
            }
        });
    }


    @Override
    public void logOut() {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
    }

    @Override
    public List<Country> getAllCountries() {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
        return null;
    }

    @Override
    public List<City> getCityByCountry(String countryId) {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
        return null;
    }

    @Override
    public boolean register(String name, final String email, final String password, String surname,
                            String birthDate, String countryId, String cityId, String sex,
                            final SignInOK mRegisterOK) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<Register> call =
                service.registerUser(name, email, password, surname, birthDate, countryId, cityId, sex);
        call.enqueue(new Callback<Register>() {

            @Override
        public void onResponse(Call<Register> call, Response<Register> response) {
            hideProgressBar();
                Register body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        signInUser(email, password, mRegisterOK);
                    } else mRegisterOK.errorRegister();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
        return false;
    }

    @Override
    public void registerFromSocial(String mName, final String mMail, final String mId,
                                   final SignInSocialOK mSignInSocialOK) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<Register> call =
                service.registerUser(mName, mMail, mId, "", "", "", "", "");
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {

                        hideProgressBar();
                Register body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1") || body.getSuccess().equals("0")) {
//                        mRegisterOK.okRegister();
                        signInUserSocial(mMail, mId, mSignInSocialOK);
                    } else mSignInSocialOK.errorRegister();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
    }

    @Override
    public List<CategoryFilm> getVideoCategories(final MainActivityView mActivityView) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<VideoCategories> call = service.getVideoCategories();
        call.enqueue(new Callback<VideoCategories>() {
            @Override
            public void onResponse(Call<VideoCategories> call, Response<VideoCategories> response) {

                hideProgressBar();
                VideoCategories body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mActivityView.setCategoriesName(body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoCategories> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
        return null;
    }


    @Override
    public List<CategoryFilm> getVideoCategories(final UploadActivityPresent mActivityPresent, final UploadActivityView mActivityView) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<VideoCategories> call = service.getVideoCategories();
        call.enqueue(new Callback<VideoCategories>() {
            @Override
            public void onResponse(Call<VideoCategories> call, Response<VideoCategories> response) {
                hideProgressBar();
                VideoCategories body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mActivityPresent.setCategoriesName(body.getData(), mActivityView);
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoCategories> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
        return null;
    }


    @Override
    public List<CategoryFilm> getBroadcastCategories(final MainActivityView mActivityView) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<VideoCategories> call = service.getBroadcastCategories();
        call.enqueue(new Callback<VideoCategories>() {
            @Override
            public void onResponse(Call<VideoCategories> call, Response<VideoCategories> response) {
                hideProgressBar();
                VideoCategories body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mActivityView.setCategoriesName(body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoCategories> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
        return null;
    }

    @Override
    public List<VideoSEEYOU> getVideoList(final boolean firstSStart, String categorySlug, String limit,
                                          final AllRequestImpl.GetNewPopularSeeYouVideos mVideos) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<Videos> call = service.getVideoList(categorySlug, limit);
        call.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                hideProgressBar();
                Videos body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mVideos.getSeeYouVideo(firstSStart, body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {
                hideProgresWithError(t);
            }
        });

        return null;
    }

    @Override
    public List<VideoSEEYOU> getVideoList(String categorySlug, String limit, final GetBroadcastVideosToShowActivity mVideos) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<Videos> call = service.getVideoList(categorySlug, limit);
        call.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                hideProgressBar();
                Videos body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mVideos.getSeeYouVideo(body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
        return null;
    }

    @Override
    public List<VideoSEEYOU> getVideoFileById(String video_id, final VideoActivityView mActivityView) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<Videos> call = service.getVideoFileById(video_id);
        call.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                hideProgressBar();
                Videos body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mActivityView.setVideo(body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
        return null;
    }

    @Override
    public void getDataVideoFileById(String mId, final VideoActivityView mActivityView) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<Videos> call = service.getVideoFileById(mId);
        call.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                hideProgressBar();
                Videos body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mActivityView.setDataVideo(body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
    }

    @Override
    public List<VideoSEEYOU> getPopularVideoList(String categorySlug, String limit,
                                                 final AllRequestImpl.GetNewPopularSeeYouVideos mVideos) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<Videos> call = service.getPopularVideoList(categorySlug, limit);
        call.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                hideProgressBar();
                Videos body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mVideos.getPopularVideo(body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
        return null;
    }

    @Override
    public List<VideoSEEYOU> getLastVideoList(String categorySlug, String limit,
                                              final AllRequestImpl.GetNewPopularSeeYouVideos mVideos) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<Videos> call = service.getLastVideoList(categorySlug, limit);
        call.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                hideProgressBar();
                Videos body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mVideos.getNewVideo(body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
        return null;
    }

    @Override
    public List<VideoSEEYOU> getUsersLikedVideo() {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
        return null;
    }

    @Override
    public List<VideoSEEYOU> searchVideo(String search) {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
        return null;
    }

    @Override
    public List<CommentVideo> getVideoComments(String videoId, final VideoActivityView mActivityView) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<Comments> call = service.getVideoComments(videoId);
        call.enqueue(new Callback<Comments>() {
            @Override
            public void onResponse(Call<Comments> call, Response<Comments> response) {
                hideProgressBar();
                Comments body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mActivityView.setListCommens(body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<Comments> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
        return null;
    }

    @Override
    public List<CommentVideo> getBroadcastComments(String broadcastId, final VideoActivityView mActivityView) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<Comments> call = service.getBroadcastComments(broadcastId);
        call.enqueue(new Callback<Comments>() {
            @Override
            public void onResponse(Call<Comments> call, Response<Comments> response) {
                hideProgressBar();
                Comments body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mActivityView.setListCommens(body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<Comments> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
        return null;
    }

    @Override
    public boolean addComment(String text, String type, String entity, String resource, String relation) {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
        return false;
    }

    @Override
    public int getPageLikes(String pageSlug) {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
        return 0;
    }

    @Override
    public int getPageDislikes(String pageSlug) {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
        return 0;
    }

    @Override
    public int addPageLike(String pageSlug) {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
        return 0;
    }

    @Override
    public int addPageDislike(String pageSlug) {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
        return 0;
    }

    @Override
    public int addVideoLike(String videoId, final VideoActivityView mActivityView) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<ActionCode> call = service.addVideoLike(videoId);
        call.enqueue(new Callback<ActionCode>() {
            @Override
            public void onResponse(Call<ActionCode> call, Response<ActionCode> response) {
                hideProgressBar();
                ActionCode body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mActivityView.updateData();
                    }
                }
            }

            @Override
            public void onFailure(Call<ActionCode> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
        return 0;
    }

    @Override
    public int addVideoDislike(String videoId, final VideoActivityView mActivityView) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<ActionCode> call = service.addVideoDislike(videoId);
        call.enqueue(new Callback<ActionCode>() {
            @Override
            public void onResponse(Call<ActionCode> call, Response<ActionCode> response) {
                hideProgressBar();
                ActionCode body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mActivityView.updateData();
                    }
                }
            }

            @Override
            public void onFailure(Call<ActionCode> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
        return 0;
    }

    @Override
    public int addBroadcastLike(String broadcastId, final VideoActivityView mActivityView) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<ActionCode> call = service.addVideoLike(broadcastId);
        call.enqueue(new Callback<ActionCode>() {
            @Override
            public void onResponse(Call<ActionCode> call, Response<ActionCode> response) {
                hideProgressBar();
                ActionCode body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mActivityView.updateData();
                    }
                }
            }

            @Override
            public void onFailure(Call<ActionCode> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
        return 0;
    }

    @Override
    public int addLike(String mUserId, String mUserPass, final int type, String mId, final VideoActivityView mActivityView) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<Object> call = service.addLike(mUserId, mUserPass, type, mId);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                hideProgressBar();
                Object body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (type == 1) {
                        mActivityView.updateData();
                    } else {
                        mActivityView.updateDataBroadcasr();
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                hideProgresWithError(t);
            }

        });

        return 0;
    }

    @Override
    public int addBroadcastDislike(String broadcastId, final VideoActivityView mActivityView) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<ActionCode> call = service.addVideoDislike(broadcastId);
        call.enqueue(new Callback<ActionCode>() {
            @Override
            public void onResponse(Call<ActionCode> call, Response<ActionCode> response) {
                hideProgressBar();
                ActionCode body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mActivityView.updateData();
                    }
                }
            }

            @Override
            public void onFailure(Call<ActionCode> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
        return 0;
    }

    @Override
    public int addDislike(String mUserId, String mUserPass, final int type, String mId, final VideoActivityView mActivityView) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<Object> call = service.addDislike(mUserId, mUserPass, type, mId);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                hideProgressBar();
                Object body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (type == 1) {
                        mActivityView.updateData();
                    } else {
                        mActivityView.updateDataBroadcasr();
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                hideProgresWithError(t);
            }

        });
        return 0;
    }

    @Override
    public List<Broadcast> getBroadcasts(String broadcastId,
                                         final AllRequestImpl.GetBroadcastVideos mVideos) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<Broadcasts> call = service.getBroadcasts(broadcastId);
        call.enqueue(new Callback<Broadcasts>() {
            @Override
            public void onResponse(Call<Broadcasts> call, Response<Broadcasts> response) {
                hideProgressBar();
                Broadcasts body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mVideos.loadVideo(body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<Broadcasts> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
        return null;
    }

    @Override
    public List<Broadcast> getBroadcasts(String broadcastId, final GetBroadcastVideosToShowActivity mVideos) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<Broadcasts> call = service.getBroadcasts(broadcastId);
        call.enqueue(new Callback<Broadcasts>() {
            @Override
            public void onResponse(Call<Broadcasts> call, Response<Broadcasts> response) {
                hideProgressBar();
                Broadcasts body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().equals("1")) {
//                        mRegisterOK.okRegister();
                        mVideos.loadVideo(body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<Broadcasts> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
        return null;
    }

    @Override
    public List<UserMessage> getUsersMessages(String broadcastId) {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
        return null;
    }

    @Override
    public boolean deleteUsersMessage(String id) {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
        return false;
    }

    @Override
    public boolean replyUsersMessage(String subject, String text, String to) {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
        return false;
    }

    @Override
    public List<UsersVideo> getUsersVideo() {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
        return null;
    }

    @Override
    public boolean deleteUsersVideo(String id) {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
        return false;
    }

    @Override
    public boolean updateUsersProfile(String name, String surname, String birthday, String country, String city, String sex) {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
        return false;
    }

    @Override
    public List<UsersBroadcasts> getUsersBroadcasts() {
        ServiceQuery service = getRetrofitServiceQuery();
// TODO: 28.06.2016 сделать авторизацию
        return null;
    }

    @Override
    public void sendComments(String mUserId, String mUserPass, final int mTypeVideo, String mId,
                             String mText, int mTypeTextComment, String mRelation, final VideoActivityView mActivityView) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<Object> call = service.sendComments(mUserId, mUserPass, mTypeVideo, mId, mText,
                mTypeTextComment, mRelation);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                hideProgressBar();
                Object body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (mTypeVideo == 1) {
                        mActivityView.updateData();
                    } else {
                        mActivityView.updateDataBroadcasr();
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
    }

    @Override
    public void checkAdult(String idUser, String userPass, final MainActivityView mActivityView) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<Adult> call = service.checkAdult(idUser, userPass);
        call.enqueue(new Callback<Adult>() {
            @Override
            public void onResponse(Call<Adult> call, Response<Adult> response) {
                hideProgressBar();
                Adult body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getSuccess().contains("1")) {
                        mActivityView.setAdult(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<Adult> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
    }

    @Override
    public void getAllUsersToMessagesActivity(final GetAllUsersActivity mActivity) {
        ServiceQuery service = getRetrofitServiceQuery();
        Call<ArrayList<UsersMessageOnLine>> call = service.allUsersOnline();
        call.enqueue(new Callback<ArrayList<UsersMessageOnLine>>() {
            @Override
            public void onResponse(Call<ArrayList<UsersMessageOnLine>> call, Response<ArrayList<UsersMessageOnLine>> response) {
                hideProgressBar();
                ArrayList<UsersMessageOnLine> body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                   mActivity.getUsersToMessagesActivity(body);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UsersMessageOnLine>> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
    }

    @Override
    public void getListMessagesUserId(String mIdUserTo, String mUserId, final GetListMessagesUserId mGetListMessagesUserId) {
        ServiceQuery service = getRetrofitServiceQuery(true);
        Call<MessageSeeYou> call = service.getListMessagesToUserId(mUserId, mIdUserTo);
        call.enqueue(new Callback<MessageSeeYou>() {
            @Override
            public void onResponse(Call<MessageSeeYou> call, Response<MessageSeeYou> response) {
                hideProgressBar();
                MessageSeeYou  body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    // TODO: 22.08.2016 должен получить диалог id и сообщения
                    mGetListMessagesUserId.setListMessagesUserId(body);
                }
            }

            @Override
            public void onFailure(Call<MessageSeeYou> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
    }

    @Override
    public void sendMessagesToUserId(String mIdUserFrom, String mText, String mDialogId, final GetListMessagesUserId mGetListMessagesUserId) {
        ServiceQuery service = getRetrofitServiceQuery(true);
        Call<ResultOk> call = service.sendMessagesToUserId(mIdUserFrom, mDialogId, mText);
        call.enqueue(new Callback<ResultOk>() {
            @Override
            public void onResponse(Call<ResultOk> call, Response<ResultOk> response) {
                hideProgressBar();
                ResultOk body = response.body();
                if (body == null) {         //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        Log.e("error", responseBody.toString());
                    }
                } else {                    //200 - Ok
                    if (body.getStatus().equals("200")) {
                        mGetListMessagesUserId.setListMessagesUserId();
                    } else {
                        Log.i("ResultOk", "ResultOk info " + body.getInfo());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultOk> call, Throwable t) {
                hideProgresWithError(t);
            }
        });
    }

    private ServiceQuery getRetrofitServiceQuery(boolean mB) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DOMAIN_COM_MESSAGE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ServiceQuery.class);
    }


    private ServiceQuery getRetrofitServiceQuery() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DOMAIN_COM)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ServiceQuery.class);
    }

    private void hideProgressBar() {
        // TODO: 29.06.2016 сделать прогресс бар
    }

    private void hideProgresWithError(Throwable t) {
        hideProgressBar();
        if (t instanceof UnknownHostException) {
            Toast.makeText(mContext, R.string.check_internet, Toast.LENGTH_SHORT).show();
        }
    }

    public interface SignInOK {
        void okSignIn();

        void errorSignIn();

        void errorRegister();
    }

    public interface SignInSocialOK {
        void okSignIn();

        void errorSignIn();

        void errorRegister();
    }

    public interface GetNewPopularSeeYouVideos {

        void getNewVideo(List<VideoSEEYOU> mSEEYOU);

        void getPopularVideo(List<VideoSEEYOU> mSEEYOU);

        void getSeeYouVideo(boolean firstSStart, List<VideoSEEYOU> mSEEYOU);

    }

    public interface GetBroadcastVideos {
        void loadVideo(List<Broadcast> mBroadcastses);
    }

    public interface GetBroadcastVideosToShowActivity {
        void loadVideo(List<Broadcast> mBroadcastses);

        void getSeeYouVideo(List<VideoSEEYOU> mData);
    }

    public interface GetAllUsersActivity {
        void getUsersToMessagesActivity(ArrayList<UsersMessageOnLine> mUserMessages);
    }

    public interface GetListMessagesUserId {
        void setListMessagesUserId();

        void setListMessagesUserId(MessageSeeYou mBody);
    }

}
