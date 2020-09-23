package com.ajeco.boom.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ajeco.boom.R;
import com.ajeco.boom.ui.JsonPlaceHolderAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private TextView textView;
    private JsonPlaceHolderAPI jsphapi;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textView = root.findViewById(R.id.text_home);

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
        return root;
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
                    textView.setText("Code:" + response.code());
                    return;
                }
                List<UsersProfiles> usersProfiles = response.body();

                for (UsersProfiles usersProfile : usersProfiles) {
                    String content = "";
                    content += "ID: " + usersProfile.getId() + "\n";
                    content += "User ID: " + usersProfile.getuId() + "\n";
                    content += "Title: " + usersProfile.getTitle() + "\n";
                    content += "Text: " + usersProfile.getText() + "\n\n";
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<UsersProfiles>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}