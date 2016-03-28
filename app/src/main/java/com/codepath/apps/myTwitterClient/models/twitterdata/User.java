package com.codepath.apps.myTwitterClient.models.twitterdata;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HUONGVU on 3/27/2016.
 */
public class User {
    @SerializedName("name")
    private String name;
    @SerializedName("screen_name")
    private String user_name;
    @SerializedName("profile_image_url")
    private String profileUrl;


    public String getName() {
        return name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

}
