package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.OnContentClickListener;
import com.movile.up.seriestracker.model.Episode;

import java.util.List;

public class SeasonDetailsAdapter extends ArrayAdapter<Episode> {
    private List<Episode> mEpisodes;

    public SeasonDetailsAdapter(Context context, OnContentClickListener clickListener) {
        super(context, R.layout.season_details_episode);
    }

    public int getCount() {
        return mEpisodes == null ? 0 : mEpisodes.size();
    }

    public Episode getItem(int position) {
        return mEpisodes.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            int resource = R.layout.season_details_episode;
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

    private void populateViewFromHolder(ViewHolder holder, int position) {
        holder.getNumber().setText("E".concat(mEpisodes.get(position).number().toString()));
        holder.getTitle().setText(mEpisodes.get(position).title());
    }

    public void updateEpisodes(List<Episode> episodes) {
        mEpisodes = episodes;
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        private View mView;
        private TextView number;
        private TextView title;

        public ViewHolder(View root) {
            mView = root.findViewById(R.id.episode_view);
            number = (TextView) root.findViewById(R.id.episode_number);
            title = (TextView) root.findViewById(R.id.episode_title);
        }

        public View view() {
            return mView;
        }

        public TextView getNumber() {
            return number;
        }

        public TextView getTitle() {
            return title;
        }
    }
}
