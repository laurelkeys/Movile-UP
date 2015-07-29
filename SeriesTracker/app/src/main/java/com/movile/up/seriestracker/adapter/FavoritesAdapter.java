package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.database.manual.entity.FavoriteEntity;
import com.movile.up.seriestracker.database.manual.entity.FavoriteEntity$Adapter;
import com.movile.up.seriestracker.interfaces.listener.OnFavoritesClickListener;

public class FavoritesAdapter extends CursorAdapter {

    private OnFavoritesClickListener mClickListener;

    public FavoritesAdapter(Context context, Cursor c, int flags, OnFavoritesClickListener clickListener) {
        super(context, c, flags);
        mClickListener = clickListener;
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        int resource = R.layout.favorites_fragment_item;
        View view = LayoutInflater.from(context).inflate(resource, parent, false);

        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);

        return view;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        FavoriteEntity$Adapter entity$Adapter = new FavoriteEntity$Adapter();
        final FavoriteEntity entity = new FavoriteEntity();
        entity$Adapter.loadFromCursor(cursor, entity);

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.title().setText(entity.title());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onFavoritesClick(entity.slug()); // FIXME
            }
        });

    }

    public static class ViewHolder {
        private View mView;
        private TextView mShowTitle;

        public ViewHolder(View root) {
            mView = root;
            mShowTitle = (TextView) root.findViewById(R.id.favorites_show_title);
        }

        public View view() {
            return mView;
        }

        public TextView title() {
            return mShowTitle;
        }
    }
}
