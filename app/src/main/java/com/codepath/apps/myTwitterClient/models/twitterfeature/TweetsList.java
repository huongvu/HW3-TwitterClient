package com.codepath.apps.myTwitterClient.models.twitterfeature;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUONGVU on 3/27/2016.
 */
public class TweetsList {
    public List<Tweet> getTweetsList() {
        return tweetsList;
    }

    @SerializedName("TweetsList")
    public List<Tweet> tweetsList;

    public TweetsList() {
        tweetsList = new ArrayList<Tweet>();
    }
}
