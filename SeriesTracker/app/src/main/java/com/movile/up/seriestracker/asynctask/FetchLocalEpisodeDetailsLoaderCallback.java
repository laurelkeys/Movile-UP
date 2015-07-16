package com.movile.up.seriestracker.asynctask;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.movile.up.seriestracker.interfaces.OnEpisodeDetailsListener;
import com.movile.up.seriestracker.model.Episode;

public class FetchLocalEpisodeDetailsLoaderCallback implements LoaderManager.LoaderCallbacks<Episode> {
    private Context mContext;
    private String mUrl;
    private OnEpisodeDetailsListener<Episode> mListener;

    public FetchLocalEpisodeDetailsLoaderCallback(Context context, OnEpisodeDetailsListener<Episode> listener, String url) {
        mContext = context;
        mUrl = url;
        mListener = listener;
    }

    public Loader<Episode> onCreateLoader(int id, Bundle bundle) {
        return new FetchLocalEpisodeDetailsLoader(mContext, mUrl);
    }

    public void onLoadFinished(Loader<Episode> loader, Episode episode) {
        mListener.onEpisodeDetailsSuccess(episode);
    }

    @Override
    public void onLoaderReset(Loader<Episode> loader) {

    }
}

