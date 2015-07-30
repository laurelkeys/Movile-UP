package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.listener.OnSeasonClickListener;
import com.movile.up.seriestracker.model.Season;

import java.util.List;

public class SeasonRecyclerAdapter extends RecyclerView.Adapter<SeasonRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private List<Season> mSeasons;
    private OnSeasonClickListener mClickListener;

    public SeasonRecyclerAdapter(Context context, OnSeasonClickListener clickListener) {
        mContext =  context;
        mClickListener = clickListener;
    }

    public void updateContents(List<Season> seasons) {
        mSeasons = seasons;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_details_season_fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Season season = mSeasons.get(position);

        Glide
                .with(mContext)
                .load(season.images().poster().get("full"))
                .placeholder(R.drawable.season_item_placeholder)
                .centerCrop()
                .into(holder.screenshot());

        holder.seasonNumber().setText("Season " + season.number().toString());
        holder.episodesCount().setText(season.episodeCount().toString() + " episodes");

        holder.mRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onSeasonClick(mSeasons.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSeasons != null ? mSeasons.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View mRoot;
        private ImageView mScreenshot;
        private TextView mSeasonNumber;
        private TextView mEpisodesCount;

        public ViewHolder(View view) {
            super(view);
            mRoot = view;
            mScreenshot = (ImageView) mRoot.findViewById(R.id.show_details_season_screenshot);
            mSeasonNumber = (TextView) mRoot.findViewById(R.id.show_details_season_label);
            mEpisodesCount = (TextView) mRoot.findViewById(R.id.show_details_episodes_label);
        }

        public View root() {
            return mRoot;
        }

        public ImageView screenshot() {
            return mScreenshot;
        }

        public TextView seasonNumber() {
            return mSeasonNumber;
        }

        public TextView episodesCount() {
            return mEpisodesCount;
        }

    }

}
