package com.codepath.apps.myTwitterClient.models.twitterdata;

/**
 * Created by HUONGVU on 3/27/2016.
 */
public class Entities {
    /*Return Image Url or Video Url of Tweet*/
    private Media media;

    public Media getMedia() {
        return media;
    }

    /*Hanlde Image and Video*/
    public class Media{
        private String imageUrl;
        private String videoUrl;
    }


}

