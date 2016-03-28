package com.codepath.apps.myTwitterClient.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.myTwitterClient.R;
import com.codepath.apps.myTwitterClient.interfaces.TweetComposeListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by HUONGVU on 3/27/2016.
 */
public class TweetComposeFragment extends android.support.v4.app.DialogFragment{

    @Bind(R.id.etTweetContent) EditText mTweetContent;
    @Bind(R.id.tvCCount) TextView mTweetCharacterCount;
    @Bind(R.id.ivClientUrl) ImageView mClientUrl;
    @Bind(R.id.btnTweet) Button btnTweet;
    @Bind(R.id.tvClientName) TextView mClientName;
    @Bind(R.id.tvClientUserName) TextView mClientUserName;


    public TweetComposeFragment() {

    }

    public static TweetComposeFragment newInstance(String title) {

        TweetComposeFragment frag = new TweetComposeFragment();

        Bundle args = new Bundle();

        args.putString("title", title);

        frag.setArguments(args);

        return frag;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tweet_compose, container);
        ButterKnife.bind(this, view);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        mClientName.setText("huong vu");
        mClientUserName.setText("@vuthehuong");

        String imageUrl = "http://abs.twimg.com/sticky/default_profile_images/default_profile_3_normal.png";

        Glide.with(mClientUrl.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher) // can also be a drawable
                .error(R.drawable.ic_launcher) // will be displayed if the image cannot be loaded
                .fitCenter()
                .into(mClientUrl);
        // Get field from view
        mTweetContent.requestFocus();

        getDialog().getWindow().setSoftInputMode(

                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        mTweetContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mTweetCharacterCount.setText(String.valueOf(140-s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnTweet.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {

                                           String tweetContent = mTweetContent.getText().toString();
                                           TweetComposeListener listener = (TweetComposeListener) getActivity();
                                           listener.onClickTweet(tweetContent);
                                           dismiss();
                                       }
                                   }
        );

    }
}
