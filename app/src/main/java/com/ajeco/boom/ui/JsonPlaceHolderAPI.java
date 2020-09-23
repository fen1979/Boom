package com.ajeco.boom.ui;

import com.ajeco.boom.login.User;
import com.ajeco.boom.ui.home.UsersProfiles;
import com.ajeco.boom.ui.slideshow.Comments;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderAPI {
    /* login method */
    @FormUrlEncoded
    @POST("post-api.php")
    Call<User> sendUserData(@Field("email") String email, @Field("password") String pass);

    /* view all users profiles to gallery */
    @FormUrlEncoded
    @POST("post-api.php")
    Call<User> sendUserData(@Field("get-gallery") String gallery);

    /* view searched users profiles in gallery*/
    @FormUrlEncoded
    @POST("post-api.php")
    Call<User> sendUserData(@FieldMap Map<String, String> searchParameters);


    /* test methods for test if all work  for delete soon */
    @GET("posts")
    Call<List<UsersProfiles>> getPost(@Query("userId") int userId,
                                      @Query("_sort") String sort,
                                      @Query("_order") String order
    );

    @GET("posts")
    Call<List<UsersProfiles>> getPost(@QueryMap Map<String, String> parameters);

    @GET("posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int postId);

    @GET
    Call<List<Comments>> getComments(@Url String url);
}
