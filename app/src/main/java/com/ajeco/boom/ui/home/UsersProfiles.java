package com.ajeco.boom.ui.home;

import com.google.gson.annotations.SerializedName;

public class UsersProfiles {
    private int userId;
    private int id;
    private String title;
    @SerializedName("body")
    private String text;

    public int getuId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
