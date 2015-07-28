package com.movile.up.seriestracker.loader.callback;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.movile.up.seriestracker.interfaces.listener.OnFavoritesListener;
import com.movile.up.seriestracker.loader.FavoritesLoader;

public class FavoritesLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context mContext;
    private OnFavoritesListener mListener;

    public FavoritesLoaderCallback(Context context, OnFavoritesListener listener) {
        mContext = context;
        mListener = listener;
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        return new FavoritesLoader(mContext);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mListener.OnFavoritesSuccess(data);
    }

    public void onLoaderReset(Loader<Cursor> loader) {

    }

}