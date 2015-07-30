package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.up.seriestracker.R;

public class InfoRecyclerAdapter extends RecyclerView.Adapter<InfoRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private String[] mGenres;

    public InfoRecyclerAdapter(Context context) {
        mContext =  context;
    }

    public void updateContents(String[] genres) {
        mGenres = genres;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_details_info_fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String genre = mGenres[position];
        holder.genre().setText(genre);
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.length : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View mRoot;
        private TextView mGenre;

        public ViewHolder(View view) {
            super(view);
            mRoot = view;
            mGenre = (TextView) mRoot.findViewById(R.id.show_details_genre_label);
        }

        public View root() {
            return mRoot;
        }

        public TextView genre() {
            return mGenre;
        }

    }

}
