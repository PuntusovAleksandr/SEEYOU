package com.aleksandrp.seeyou.retrofit;

import com.aleksandrp.seeyou.main.MainActivityView;
import com.aleksandrp.seeyou.message.entity.UserMessage;
import com.aleksandrp.seeyou.retrofit.entity.Broadcast;
import com.aleksandrp.seeyou.retrofit.entity.CategoryFilm;
import com.aleksandrp.seeyou.retrofit.entity.City;
import com.aleksandrp.seeyou.retrofit.entity.CommentVideo;
import com.aleksandrp.seeyou.retrofit.entity.Country;
import com.aleksandrp.seeyou.retrofit.entity.UsersBroadcasts;
import com.aleksandrp.seeyou.retrofit.entity.UsersVideo;
import com.aleksandrp.seeyou.retrofit.entity.VideoSEEYOU;
import com.aleksandrp.seeyou.retrofit.impl.AllRequestImpl;
import com.aleksandrp.seeyou.upload.UploadActivityPresent;
import com.aleksandrp.seeyou.upload.UploadActivityView;
import com.aleksandrp.seeyou.video_activity.VideoActivityView;

import java.util.List;

/**
 * Created by AleksandrP on 28.06.2016.
 */
public interface AllRequest {

    void signInUser(String email, String password, AllRequestImpl.SignInOK mInOK);

    void logOut();

    List<Country> getAllCountries();

    List<City> getCityByCountry(String countryId);

    boolean register(String name, String email, String password,
                     String surname, String birthDate,
                     String countryId, String cityId, String sex,
                     AllRequestImpl.SignInOK mRegisterOK);


    void registerFromSocial(String mName, String mMail, String mId,
                            AllRequestImpl.SignInSocialOK mRegisterOK);

    List<CategoryFilm> getVideoCategories(MainActivityView mActivityView);

    List<CategoryFilm> getVideoCategories(UploadActivityPresent mActiPresent, UploadActivityView mActivityView);

    List<CategoryFilm> getBroadcastCategories(MainActivityView mActivityView);

    List<VideoSEEYOU> getVideoList(boolean firstSStart, String categorySlug, String limit,
                                   AllRequestImpl.GetNewPopularSeeYouVideos mVideos);

    List<VideoSEEYOU> getVideoList(String categorySlug, String limit,
                                   AllRequestImpl.GetBroadcastVideosToShowActivity mVideos);

    List<VideoSEEYOU> getVideoFileById(String video_id, VideoActivityView mActivityView);

    void getDataVideoFileById(String mId, VideoActivityView mActivityView);

    List<VideoSEEYOU> getPopularVideoList(String categorySlug, String limit,
                                          AllRequestImpl.GetNewPopularSeeYouVideos mVideos);

    List<VideoSEEYOU> getLastVideoList(String categorySlug, String limit,
                                       AllRequestImpl.GetNewPopularSeeYouVideos mVideos);

    List<VideoSEEYOU> getUsersLikedVideo();

    List<VideoSEEYOU> searchVideo(String search);

    List<CommentVideo> getVideoComments(String videoId, VideoActivityView mActivityView);

    List<CommentVideo> getBroadcastComments(String broadcastId, VideoActivityView mActivityView);

    boolean addComment(String text, String type, String entity,
                       String resource, String relation);

    int getPageLikes(String pageSlug);

    int getPageDislikes(String pageSlug);

    int addPageLike(String pageSlug);

    int addPageDislike(String pageSlug);

    int addVideoLike(String videoId, VideoActivityView mActivityView);

    int addVideoDislike(String videoId, VideoActivityView mActivityView);

    int addBroadcastLike(String broadcastId, VideoActivityView mActivityView);

    int addLike(String mUserId, String mUserPass, int type, String mId,
                VideoActivityView mActivityView);

    int addBroadcastDislike(String broadcastId, VideoActivityView mActivityView);


    int addDislike(String mUserId, String mUserPass, int type, String mId,
                   VideoActivityView mActivityView);


    List<Broadcast> getBroadcasts(String broadcastId, AllRequestImpl.GetBroadcastVideos mVideos);

    List<Broadcast> getBroadcasts(String broadcastId,
                                  AllRequestImpl.GetBroadcastVideosToShowActivity mVideos);

    List<UserMessage> getUsersMessages(String broadcastId);

    boolean deleteUsersMessage(String id);

    boolean replyUsersMessage(String subject, String text, String to);

    List<UsersVideo> getUsersVideo();

    boolean deleteUsersVideo(String id);

    boolean updateUsersProfile(String name, String surname, String birthday,
                               String country, String city, String sex);

    List<UsersBroadcasts> getUsersBroadcasts();


    void sendComments(String mUserId, String mUserPass, int mTypeVideo, String mId, String mText,
                      int mTypeTextComment, String mRelation, VideoActivityView mActivityView);

    void checkAdult(String idUser, String userPass, MainActivityView mActivityView);

    void getAllUsersToMessagesActivity(AllRequestImpl.GetAllUsersActivity mUsersActivity);

    void getListMessagesUserId(String mIdUserTo, String mUserId, AllRequestImpl.GetListMessagesUserId mGetListMessagesUserId);

    void sendMessagesToUserId(String mIdUserFrom, String mText, String mDialogId, AllRequestImpl.GetListMessagesUserId mGetListMessagesUserId);
}
