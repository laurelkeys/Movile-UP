package com.movile.up.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.adapter.EpisodesAdapter;
import com.movile.up.seriestracker.interfaces.OnContentClickListener;
import com.movile.up.seriestracker.interfaces.SeasonDetailsView;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.presenter.SeasonDetailsPresenter;

import java.util.List;

public class SeasonDetailsActivity extends BaseNavigationToolbarActivity implements SeasonDetailsView, OnContentClickListener {

    private static final String TAG = EpisodeDetailsActivity.class.getSimpleName();
    private static final String EXTRA_SHOW = "show";
    private static final String EXTRA_SEASON = "season";
    private static final String EXTRA_EPISODE = "episode";
    private String mShow = "house-of-cards";
    private Long mSeason = 2l;
    private List<Episode> mEpisodes;
    private OnContentClickListener listener;
    public EpisodesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);
        configureToolbar();
        showLoading();

        new SeasonDetailsPresenter(this, this).loadRemoteEpisodesWithRetrofit(mShow, mSeason);
        ListView view = (ListView) findViewById(R.id.list_view);
        //view.addHeaderView(headerView, null, false);
        //view.setEmptyView(emptyView);
        //view.addFooterView(footerView, null, false);
        mAdapter = new EpisodesAdapter(this, this);
        view.setAdapter(mAdapter);
        Log.d(TAG, "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState()");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState()");
    }

    @Override
    public void displayEpisodes(List<Episode> episodes) {
        mAdapter.updateEpisodes(episodes);
        getSupportActionBar().setTitle("Season ".concat(mSeason.toString()));
        hideLoading();
    }

    @Override
    public void onEpisodeClick(Episode episode) {
        Intent intent = new Intent(this, EpisodeDetailsActivity.class);
        intent.putExtra(EXTRA_SHOW, mShow);
        intent.putExtra(EXTRA_SEASON, mSeason);
        intent.putExtra(EXTRA_EPISODE, episode.number());
        startActivity(intent);
    }
}
