package com.movile.up.seriestracker.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.movile.up.seriestracker.database.manual.entity.FavoriteEntity;
import com.movile.up.seriestracker.database.manual.entity.FavoriteEntity$Table;
import com.movile.up.seriestracker.database.manual.helper.ProviderUriHelper;
import com.movile.up.seriestracker.model.Favorite;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

public class FavoriteDAO {

    private Context mContext;

    public FavoriteDAO(Context context) {
        mContext = context;
    }

    public void save(Favorite favorite) {
        FavoriteEntity entity = new FavoriteEntity(favorite.slug(), favorite.title());
        entity.save(); // FIXME

        /*
        Uri uri = new ProviderUriHelper(mContext).mountManyUri(FavoriteEntity.FavoriteEntityFields.TABLE_NAME);

        FavoriteEntity entity = new FavoriteEntity(favorite.slug(), favorite.title());
        mContext.getContentResolver().insert(uri, entity.toContentValues());
        */
    }

    public Cursor all() {
        Cursor cursor = new Select().from(FavoriteEntity.class).queryCursorList().getCursor();
        return cursor;

        /*
        Uri uri = new ProviderUriHelper(mContext).mountManyUri(FavoriteEntity.FavoriteEntityFields.TABLE_NAME);
        return mContext.getContentResolver().query(uri, null, null, null, FavoriteEntity.FavoriteEntityFields.COLUMN_TITLE);
        */
    }

    public Favorite query(String slug) {
        FavoriteEntity entity = new Select()
                .from(FavoriteEntity.class)
                .where(Condition.column(FavoriteEntity$Table.SLUG).eq(slug))
                .querySingle();

        if (entity != null) {
            Favorite favorite = new Favorite(entity.slug(), entity.title());
            return favorite;
        } else {
            return null;
        }

        /*
        Cursor cursor = null;
        Favorite favorite = null;

        try {
            Uri uri = new ProviderUriHelper(mContext).mountManyUri(FavoriteEntity.FavoriteEntityFields.TABLE_NAME);
            cursor = mContext.getContentResolver().query(uri, null, FavoriteEntity.FavoriteEntityFields.COLUMN_SLUG + " = ?", new String[]{slug}, null);

            if (cursor.moveToFirst()) {
                FavoriteEntity entity = new FavoriteEntity().fromCursor(cursor);
                favorite = new Favorite(entity.slug(), entity.title());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return favorite;
        */
    }

    public void delete(String slug) {
        new Delete()
                .from(FavoriteEntity.class)
                .where(Condition.column(FavoriteEntity$Table.SLUG).eq(slug))
                .queryClose();

        /*
        Uri uri = new ProviderUriHelper(mContext).mountManyUri(FavoriteEntity.FavoriteEntityFields.TABLE_NAME);
        mContext.getContentResolver().delete(uri, FavoriteEntity.FavoriteEntityFields.COLUMN_SLUG + " = ?", new String[]{slug});
        */
    }

}