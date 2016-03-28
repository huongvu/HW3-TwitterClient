package com.codepath.apps.myTwitterClient.interfaces;

/**
 * Created by HUONGVU on 3/27/2016.
 */

public interface EndlessScrollListener {
    /**
     * Loads more data.
     * @param position
     * @return true loads data actually, false otherwise.
     */
    public boolean onLoadMore(int position);
}

