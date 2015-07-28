package com.movile.up.seriestracker.database.dao;

import android.content.Context;
import android.database.Cursor;

import com.movile.up.seriestracker.database.manual.entity.FavoriteEntity;
import com.movile.up.seriestracker.database.manual.entity.FavoriteEntity$Table;
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
        entity.save();
    }

    public Cursor all() {
        Cursor cursor = new Select().from(FavoriteEntity.class).queryCursorList().getCursor();
        return cursor;
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
    }

    public void delete(String slug) {
        new Delete()
                .from(FavoriteEntity.class)
                .where(Condition.column(FavoriteEntity$Table.SLUG).eq(slug))
                .queryClose();
    }

}