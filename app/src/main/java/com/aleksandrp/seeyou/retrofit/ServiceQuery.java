package com.aleksandrp.seeyou.retrofit;

import com.aleksandrp.seeyou.message.entity.MessageSeeYou;
import com.aleksandrp.seeyou.message.entity.ResultOk;
import com.aleksandrp.seeyou.retrofit.entity.ActionCode;
import com.aleksandrp.seeyou.retrofit.entity.AddComment;
import com.aleksandrp.seeyou.retrofit.entity.Adult;
import com.aleksandrp.seeyou.retrofit.entity.Broadcasts;
import com.aleksandrp.seeyou.retrofit.entity.Cities;
import com.aleksandrp.seeyou.retrofit.entity.Comments;
import com.aleksandrp.seeyou.retrofit.entity.Countries;
import com.aleksandrp.seeyou.retrofit.entity.DelMessage;
import com.aleksandrp.seeyou.retrofit.entity.DisLikes;
import com.aleksandrp.seeyou.retrofit.entity.Likes;
import com.aleksandrp.seeyou.retrofit.entity.LogOut;
import com.aleksandrp.seeyou.retrofit.entity.Register;
import com.aleksandrp.seeyou.retrofit.entity.SignInUser;
import com.aleksandrp.seeyou.retrofit.entity.UpdateUsersProfile;
import com.aleksandrp.seeyou.retrofit.entity.UsersBroadcasts;
import com.aleksandrp.seeyou.retrofit.entity.UsersMessageOnLine;
import com.aleksandrp.seeyou.retrofit.entity.UsersMessages;
import com.aleksandrp.seeyou.retrofit.entity.UsersVideos;
import com.aleksandrp.seeyou.retrofit.entity.VideoCategories;
import com.aleksandrp.seeyou.retrofit.entity.Videos;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by AleksandrP on 28.06.2016.
 */
public interface ServiceQuery {

    /**
     * login in server
     *
     * @param email
     * @param password
     * @return
     */
    @GET("api/login")
    Call<SignInUser> signIn(
            @Query("email") String email,
            @Query("password") String password);

    /**
     * logOut from server
     *
     * @return
     */
    @GET("api/logout")
    Call<LogOut> logOut();


    /**
     * get all list countries
     *
     * @return
     */
    @GET("api/getCountries")
    Call<Countries> getAllCountries();

    /**
     * get all list cities
     *
     * @param countryId
     * @return
     */
    @GET("api/getCities")
    Call<Cities> getAllCities(
            @Query("countryId") String countryId);

    /**
     * register user in server
     *
     * @param name
     * @param email
     * @param password
     * @param surname
     * @param birthDate
     * @param countryId
     * @param cityId
     * @param sex
     * @return
     */
    @GET("api/register")
    Call<Register> registerUser(
            @Query("name") String name,
            @Query("email") String email,
            @Query("password") String password,
            @Query("surname") String surname,
            @Query("birthDate") String birthDate,
            @Query("countryId") String countryId,
            @Query("cityId") String cityId,
            @Query("sex") String sex);

    /**
     * getVideoCategories
     *
     * @return
     */
    @GET("api/getVideoCategories")
    Call<VideoCategories> getVideoCategories();

    /**
     * getBroadcastCategories
     *
     * @return
     */
    @GET("api/getBroadcastCategories")
    Call<VideoCategories> getBroadcastCategories();

    /**
     * getVideoList
     *
     * @param category
     * @param limit
     * @return
     */
    @GET("api/getVideoList")
    Call<Videos> getVideoList(
            @Query("slug") String category,
            @Query("limit") String limit);

    /**
     * getVideoFileById
     *
     * @param video_id
     * @return
     */
    @GET("api/getVideoFileById")
    Call<Videos> getVideoFileById(
            @Query("video_id") String video_id);

    /**
     * getPopularVideoList
     *
     * @param category
     * @param limit
     * @return
     */
    @GET("api/getPopularVideoList")
    Call<Videos> getPopularVideoList(
            @Query("category") String category,
            @Query("limit") String limit);

    /**
     * getLastVideoList
     *
     * @param category
     * @param limit
     * @return
     */
    @GET("api/getLastVideoList")
    Call<Videos> getLastVideoList(
            @Query("category") String category,
            @Query("limit") String limit);

    /**
     * getUsersLikedVideo
     *
     * @return
     */
    @GET("api/getUsersLikedVideo")
    Call<Videos> getUsersLikedVideo();

    /**
     * searchVideo
     *
     * @param search
     * @return
     */
    @GET("api/searchVideo")
    Call<Videos> searchVideo(
            @Query("search") String search);

    /**
     * getVideoComments
     *
     * @param videoId
     * @return
     */
    @GET("api/getVideoComments")
    Call<Comments> getVideoComments(
            @Query("videoId") String videoId);

    /**
     * getBroadcastComments
     *
     * @param broadcastId
     * @return
     */
    @GET("api/getBroadcastComments")
    Call<Comments> getBroadcastComments(
            @Query("broadcastId") String broadcastId);

    /**
     * addComment
     *
     * @param text
     * @param type
     * @param entity
     * @param resource
     * @param relation
     * @return
     */
    @GET("api/addComment")
    Call<AddComment> addComment(
            @Query("text") String text,
            @Query("type") String type,
            @Query("entity") String entity,
            @Query("resource") String resource,
            @Query("relation") String relation
    );

    /**
     * getPageLikes
     *
     * @param pageSlug
     * @return
     */
    @GET("api/getPageLikes")
    Call<DisLikes> getPageLikes(
            @Query("pageSlug") String pageSlug);

    /**
     * getPageDislikes
     *
     * @param pageSlug
     * @return
     */
    @GET("api/getPageDislikes")
    Call<Likes> getPageDislikes(
            @Query("pageSlug") String pageSlug);

    /**
     * addPageLike
     *
     * @param pageSlug
     * @return
     */
    @GET("api/addPageLike")
    Call<ActionCode> addPageLike(
            @Query("pageSlug") String pageSlug);

    /**
     * addPageDislike
     *
     * @param pageSlug
     * @return
     */
    @GET("api/addPageDislike")
    Call<ActionCode> addPageDislike(
            @Query("pageSlug") String pageSlug);

    /**
     * addVideoLike
     *
     * @param videoId
     * @return
     */
    @GET("api/addVideoLike")
    Call<ActionCode> addVideoLike(
            @Query("videoId") String videoId);

    /**
     * add like by video
     *
     * @param id       id_user from server
     * @param password - pass
     * @param entity   if 1 - video, else broadcast
     * @param item     - id video or broadcast
     */
    @GET("api/user/{id}/{password}/addLike/{entity}/{item}")
    Call<Object> addLike(
            @Path("id") String id,
            @Path("password") String password,
            @Path("entity") int entity,
            @Path("item") String item);

    /**
     * addVideoDislike
     *
     * @param videoId
     * @return
     */
    @GET("api/addVideoDislike")
    Call<ActionCode> addVideoDislike(
            @Query("videoId") String videoId);

    /**
     * add disLike by video
     *
     * @param id       id_user from server
     * @param password - pass
     * @param entity   if 1 - video, else broadcast
     * @param item     - id video or broadcast
     */
    @GET("api/user/{id}/{password}/addDislike/{entity}/{item}")
    Call<Object> addDislike(
            @Path("id") String id,
            @Path("password") String password,
            @Path("entity") int entity,
            @Path("item") String item);

    /**
     * addBroadcastLike
     *
     * @param broadcastId
     * @return
     */
    @GET("api/addBroadcastLike")
    Call<ActionCode> addBroadcastLike(
            @Query("broadcastId") String broadcastId);

    /**
     * addBroadcastDislike
     *
     * @param broadcastId
     * @return
     */
    @GET("api/addBroadcastDislike")
    Call<ActionCode> addBroadcastDislike(
            @Query("broadcastId") String broadcastId);

    /**
     * getBroadcasts
     *
     * @param slug
     * @return
     */
    @GET("api/getBroadcasts")
    Call<Broadcasts> getBroadcasts(
            @Query("slug") String slug);

    /**
     * getUsersMessages
     *
     * @param broadcastId
     * @return
     */
    @GET("api/getUsersMessages")
    Call<UsersMessages> getUsersMessages(
            @Query("broadcastId") String broadcastId);

    /**
     * deleteUsersMessage
     *
     * @param id
     * @return
     */
    @GET("api/deleteUsersMessage")
    Call<DelMessage> deleteUsersMessage(
            @Query("id") String id);

    /**
     * replyUsersMessage
     *
     * @param subject
     * @param text
     * @param to
     * @return
     */
    @GET("api/replyUsersMessage")
    Call<DelMessage> replyUsersMessage(
            @Query("subject") String subject,
            @Query("text") String text,
            @Query("to") String to);        // - id получателя, см getUsersMessages sender_id

    /**
     * getUsersVideo
     *
     * @return
     */
    @GET("api/getUsersVideo")
    Call<UsersVideos> getUsersVideo();

    /**
     * deleteUsersVideo
     *
     * @param id
     * @return
     */
    @GET("api/deleteUsersVideo")
    Call<DelMessage> deleteUsersVideo(
            @Query("id") String id);

    /**
     * updateUsersProfile
     *
     * @param name
     * @param surname
     * @param birthday
     * @param country
     * @param city
     * @param sex
     * @return
     */
    @GET("api/updateUsersProfile")
    Call<UpdateUsersProfile> updateUsersProfile(
            @Query("name ") String name,
            @Query("surname") String surname,
            @Query("birthday") String birthday,
            @Query("country") String country,
            @Query("city") String city,
            @Query("sex") String sex);

    /**
     * getUsersBroadcasts
     *
     * @return
     */
    @GET("api/getUsersBroadcasts")
    Call<UsersBroadcasts> getUsersBroadcasts();

    /**
     * send comment by video or broadcast
     *
     * @param mUserId          - user)id
     * @param mUserPass        - user password
     * @param mTypeVideo       - 1 video, 2-broadcast
     * @param mId              - id video
     * @param mText            - text comments
     * @param mTypeTextComment - 1-out, 2-inner comment
     * @param mRelation
     * @return
     */
    @GET("api/user/{id}/{password}/addComment/{entity}/{item}/{text}/{type}/{relation}")
    Call<Object> sendComments(
            @Path("id") String mUserId,
            @Path("password") String mUserPass,
            @Path("entity") int mTypeVideo,
            @Path("item") String mId,
            @Path("text") String mText,
            @Path("type") int mTypeTextComment,
            @Path("relation") String mRelation);

    /**
     * check user adult condition
     *
     * @param mUserId
     * @param mUserPass
     * @return
     */
    @GET("api/user/{id}/{password}/isPayForAdult")
    Call<Adult> checkAdult(
            @Path("id") String mUserId,
            @Path("password") String mUserPass);

    /**
     * get all userMessages
     *
     * @return
     */
    @GET("api/allUsersOnline")
    Call<ArrayList<UsersMessageOnLine>> allUsersOnline();

    /**
     * for chat getAll message from user
     *
     * @param mIdUserTo
     * @return
     */
//    @GET("dialog/create/{mIdUserTo}/:reciver")
    @GET("dialog/show/{user_first}/{user_second}")
    Call<MessageSeeYou> getListMessagesToUserId(
            @Path("user_first") String mIdUserFromo,
            @Path("user_second") String mIdUserTo);

    /**
     * for chat send message from user
     */
//    @POST("dialog/show/{mMFromId}/{mIdUserTo}")
    @FormUrlEncoded
    @POST("dialog/sendMessage/{mUserFrom}/{dialog_id}")
    Call<ResultOk> sendMessagesToUserId(
            @Path("mUserFrom") String mUserFrom,
            @Path("dialog_id") String mDialogId,
            @Field("message") String mText
            );
}
