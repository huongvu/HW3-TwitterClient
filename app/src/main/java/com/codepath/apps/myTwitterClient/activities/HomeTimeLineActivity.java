package com.codepath.apps.myTwitterClient.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.apps.myTwitterClient.R;
import com.codepath.apps.myTwitterClient.adapters.TweetsAdapter;
import com.codepath.apps.myTwitterClient.fragments.TweetComposeFragment;
import com.codepath.apps.myTwitterClient.interfaces.EndlessScrollListener;
import com.codepath.apps.myTwitterClient.interfaces.TweetComposeListener;
import com.codepath.apps.myTwitterClient.models.twitterfeature.Tweet;
import com.codepath.apps.myTwitterClient.models.twitterfeature.TweetsList;
import com.codepath.apps.myTwitterClient.network.TwitterApplication;
import com.codepath.apps.myTwitterClient.network.TwitterClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeTimeLineActivity extends AppCompatActivity implements TweetComposeListener {
    private TwitterClient client;
    private TweetsAdapter adapter;
    private List<Tweet> tweetsResponse;
    private long currentId = 1;
    final private int numberTweets =  20;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void onComposeAction(MenuItem mi) {
        // handle click here
        Toast.makeText(this, "success tweet", Toast.LENGTH_SHORT).show();
        FragmentManager fm = getSupportFragmentManager();
        TweetComposeFragment editNameDiaglog = TweetComposeFragment.newInstance("");
        editNameDiaglog.show(fm, "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hometimeline);

        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.twitter3128);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //actionBar.hide(); // or even hide the actionbar

        RecyclerView rvTweets = (RecyclerView) findViewById(R.id.rvTweets);

        rvTweets.setHasFixedSize(true);

        tweetsResponse = new ArrayList<>();

        adapter = new TweetsAdapter(tweetsResponse);
        // Attach the adapter to the recyclerview to populate items
        rvTweets.setAdapter(adapter);

        rvTweets.setLayoutManager(new LinearLayoutManager(this));

        client = TwitterApplication.getRestClient();
        populateTimeLine();

        adapter.setEndlessScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int position) {
                if (position >= (tweetsResponse.size() - 5)) {
                    currentId = Long.valueOf(tweetsResponse.get(tweetsResponse.size() - 1).getTweetId());
                    populateTimeLine();
                }
                return true;
            }
        });

    }


    private void populateTimeLine() {

        client.getHomeTimeline(currentId, numberTweets, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                String myCustom_JSONResponse;

                myCustom_JSONResponse = "{\"TweetsList\":" + response.toString() + "}";

                GsonBuilder gsonBuilder = new GsonBuilder();

                Gson gson = gsonBuilder.create();

                TweetsList tweetsData = gson.fromJson(myCustom_JSONResponse, TweetsList.class);

                List<Tweet> tweetRespone = tweetsData.tweetsList;

                for (Tweet tweet : tweetRespone) {

                    tweetsResponse.add(tweet);
                }

                int curSize = adapter.getItemCount();
                adapter.notifyItemRangeInserted(curSize, tweetsResponse.size());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("Debug", "onFailure: " + errorResponse.toString());
            }
        });



    }

    private void postTimeline(String status){
        client.postHomeTimeline(status, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("Debug", "onFailure: " + errorResponse.toString());
            }
        });
    }

    @Override
    public void onClickTweet(String tweetContent) {

        if(tweetContent.trim().length() != 0) {
            postTimeline(tweetContent);
        }
        else{
            Toast.makeText(this, "Input Tweet Content", Toast.LENGTH_SHORT).show();
        }
    }
}
