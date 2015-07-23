package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.listener.OnShowClickListener;
import com.movile.up.seriestracker.model.Show;

import java.util.List;

public class ShowsGridAdapter extends ArrayAdapter<Show> {
    private Context mContext;
    private List<Show> mShows;
    private OnShowClickListener mClickListener;

    public ShowsGridAdapter(Context context, OnShowClickListener clickListener) {
        super(context, R.layout.shows_grid_item);
        mContext = context;
        mClickListener = clickListener;
    }

    public int getCount() {
        return mShows == null ? 0 : mShows.size();
    }

    public Show getItem(int position) {
        return mShows.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            int resource = R.layout.shows_grid_item;
            view = LayoutInflater.from(parent.getContext())
                    .inflate(resource, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        populateViewFromHolder(holder, position);

        return view;
    }

    private void populateViewFromHolder(ViewHolder holder, final int position) {
        Glide
                .with(mContext)
                .load(mShows.get(position).images().poster().get("full"))
                .placeholder(R.drawable.show_item_placeholder)
                .centerCrop()
                .into(holder.show());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onShowClick(mShows.get(position));
            }
        });
    }

    public void updateShows(List<Show> shows) {
        mShows = shows;
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        private View mView;
        private ImageView show;

        public ViewHolder(View root) {
            mView = root;
            show = (ImageView) root.findViewById(R.id.show_poster);
        }

        public View view() {
            return mView;
        }

        public ImageView show() {
            return show;
        }
    }
}
