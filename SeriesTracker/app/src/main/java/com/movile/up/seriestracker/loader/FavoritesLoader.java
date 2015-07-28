package com.movile.up.seriestracker.loader;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import com.movile.up.seriestracker.database.dao.FavoriteDAO;

public class FavoritesLoader extends CursorLoader {

    private Context mContext;

    public FavoritesLoader(Context context) {
        super(context);
        mContext = context;
    }

    public Cursor loadInBackground() {
        FavoriteDAO mDao = new FavoriteDAO(mContext);
        return mDao.all();
    }

}