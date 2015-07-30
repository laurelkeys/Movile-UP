package com.movile.up.seriestracker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.SeasonDetailsActivity;
import com.movile.up.seriestracker.adapter.SeasonRecyclerAdapter;
import com.movile.up.seriestracker.interfaces.listener.OnSeasonClickListener;
import com.movile.up.seriestracker.interfaces.view.ShowDetailsSeasonView;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.presenter.ShowDetailsPresenter;

import java.util.List;

public class ShowDetailsSeasonFragment extends Fragment implements ShowDetailsSeasonView, OnSeasonClickListener {

    //private static final String TAG = EpisodeDetailsActivity.class.getSimpleName();
    private static final String EXTRA_SHOW = "show";
    private static final String EXTRA_SEASON = "season";
    private static final String EXTRA_RATING = "rating";
    private static final String EXTRA_POSTER = "poster";
    private static final String EXTRA_THUMB = "thumb";
    private String mShow;
    private View mView;
    private SeasonRecyclerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.show_details_season_fragment, container, false);
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle b = this.getArguments();
        mShow = b.getString(EXTRA_SHOW);
        new ShowDetailsPresenter(this.getActivity(), this).loadRemoteSeasonsWithRetrofit(mShow);
        configureRecyclerView();
    }

    private void configureRecyclerView() {
        RecyclerView view = (RecyclerView) mView.findViewById(R.id.season_horizontal_recycler);
        view.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));

        mAdapter = new SeasonRecyclerAdapter(this.getActivity(), this);
        view.setAdapter(mAdapter);
    }

    @Override
    public void onSeasonClick(Season season) {
        Intent intent = new Intent(this.getActivity(), SeasonDetailsActivity.class);
        intent.putExtra(EXTRA_SHOW, mShow);
        intent.putExtra(EXTRA_SEASON, season.number());
        intent.putExtra(EXTRA_RATING, season.rating());
        intent.putExtra(EXTRA_POSTER, season.images().poster().get("full"));
        intent.putExtra(EXTRA_THUMB, season.images().thumb().get("full"));
        startActivity(intent);
    }

    @Override
    public void displaySeasons(List<Season> seasons) {
        mAdapter.updateContents(seasons);
    }
}
