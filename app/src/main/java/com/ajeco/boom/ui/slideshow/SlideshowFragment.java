package com.ajeco.boom.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ajeco.boom.R;
import com.ajeco.boom.ui.JsonPlaceHolderAPI;
import com.ajeco.boom.ui.home.UsersProfiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SlideshowFragment extends Fragment {

    private JsonPlaceHolderAPI jsphapi;
    private TextView textv;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        textv = root.findViewById(R.id.view_result);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsphapi = retrofit.create(JsonPlaceHolderAPI.class);

        Map<String, String> params = new HashMap<>();
        params.put("userId", "2");
        params.put("_sort", "id");
        params.put("_order", "desc");
        getPosts(params);

        //getPosts(4, "id", "desc");

        //getComments(5);

        //getComments("posts/3/comments");

        //mText = new MutableLiveData<>();
        //mText.setValue("This is slideshow fragment");
        //mText.setValue(textv.getText().toString());


        return root;
    }


    /* get all comments from links */
    //private void getComments(int postId) {
    //Call<List<Comments>> call = jsphapi.getComments(postId);

    private void getComments(String url) {
        Call<List<Comments>> call = jsphapi.getComments(url);

        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if (!response.isSuccessful()) {
                    textv.setText("Code:" + response.code());
                    return;
                }
                List<Comments> comments = response.body();

                for (Comments post : comments) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "Post ID: " + post.getPostId() + "\n";
                    content += "Name: " + post.getName() + "\n";
                    content += "Email: " + post.getEmail() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    textv.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                textv.setText(t.getMessage());
            }
        });
    }

    /* get all posts from link */
    //private void getPosts(int uId, String sort, String order) {
    //Call<List<Post>> call = jsphapi.getPost(uId, sort, order);

    private void getPosts(Map<String, String> parameters) {
        Call<List<UsersProfiles>> call = jsphapi.getPost(parameters);

        call.enqueue(new Callback<List<UsersProfiles>>() {
            @Override
            public void onResponse(Call<List<UsersProfiles>> call, Response<List<UsersProfiles>> response) {
                if (!response.isSuccessful()) {
                    textv.setText("Code:" + response.code());
                    return;
                }
                List<UsersProfiles> usersProfiles = response.body();

                for (UsersProfiles usersProfile : usersProfiles) {
                    String content = "";
                    content += "ID: " + usersProfile.getId() + "\n";
                    content += "User ID: " + usersProfile.getuId() + "\n";
                    content += "Title: " + usersProfile.getTitle() + "\n";
                    content += "Text: " + usersProfile.getText() + "\n\n";
                    textv.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<UsersProfiles>> call, Throwable t) {
                textv.setText(t.getMessage());
            }
        });
    }
}