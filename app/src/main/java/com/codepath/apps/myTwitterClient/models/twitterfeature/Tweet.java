package com.codepath.apps.myTwitterClient.models.twitterfeature;

import android.text.format.DateUtils;

import com.codepath.apps.myTwitterClient.models.twitterdata.User;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by HUONGVU on 3/27/2016.
 */
public class Tweet {

    @SerializedName("user")
    private User tweetUser;

    public User getTweetUser() {
        return tweetUser;
    }

    @SerializedName("text")
    private String body;

    @SerializedName("created_at")
    private String createAt;

    @SerializedName("retweet_count")
    private int reTweetCount;

    @SerializedName("favorited")
    private boolean favStatus;

    @SerializedName("retweeted")
    private boolean reTweetStatus;

    @SerializedName("in_reply_to_status_id")
    private String replyStatusId;

    @SerializedName("in_reply_to_screen_name")
    private String replyScreenName;


    @SerializedName("id_str")
    private String tweetId;


    public String getTweetId() {
        return tweetId;
    }

    private String timeStamp;

    public String getTimeStamp() {
        timeStamp = getRelativeTimeAgo(createAt);
        return timeStamp;
    }


    public String getBody() {
        return body;
    }

    public String getCreateAt() {
        return createAt;
    }

    public int getReTweetCount() {
        return reTweetCount;
    }

    public boolean isFavStatus() {
        return favStatus;
    }

    public boolean isReTweetStatus() {
        return reTweetStatus;
    }

    public String getReplyStatusId() {
        return replyStatusId;
    }

    public String getReplyScreenName() {
        return replyScreenName;
    }


    private String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";

        long dateMillis = 0;
        try {
            dateMillis = sf.parse(rawJsonDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();


        return relativeDate;
    }
}
