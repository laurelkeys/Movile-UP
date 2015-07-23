package com.movile.up.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.adapter.EpisodesAdapter;
import com.movile.up.seriestracker.interfaces.listener.OnEpisodeClickListener;
import com.movile.up.seriestracker.interfaces.view.SeasonDetailsView;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.presenter.SeasonDetailsPresenter;

import java.util.List;

public class SeasonDetailsActivity extends BaseNavigationToolbarActivity implements SeasonDetailsView, OnEpisodeClickListener {

    private static final String TAG = EpisodeDetailsActivity.class.getSimpleName();
    private static final String EXTRA_SHOW = "show";
    private static final String EXTRA_SEASON = "season";
    private static final String EXTRA_EPISODE = "episode";
    private static final String EXTRA_RATING = "rating";
    private static final String EXTRA_POSTER = "poster";
    private static final String EXTRA_THUMB = "thumb";
    private String mShow;
    private Long mSeason;
    private Double mRating;
    private String mPoster;
    private String mThumb;
    public EpisodesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);
        configureToolbar();
        showLoading();
        extractInformationFromExtras();
        new SeasonDetailsPresenter(this, this).loadRemoteEpisodesWithRetrofit(mShow, mSeason);
        ListView view = (ListView) findViewById(R.id.list_view);
        View headerView = getLayoutInflater().inflate(R.layout.season_details_header, null, false);
        view.addHeaderView(headerView, null, false);
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

    private void extractInformationFromExtras() {
        Bundle extras = getIntent().getExtras();
        mShow = extras.getString(EXTRA_SHOW);
        mSeason = extras.getLong(EXTRA_SEASON);
        mRating = extras.getDouble(EXTRA_RATING);
        mPoster = extras.getString(EXTRA_POSTER);
        mThumb = extras.getString(EXTRA_THUMB);
    }

    @Override
    public void displayEpisodes(List<Episode> episodes) {
        mAdapter.updateEpisodes(episodes);
        extractInformationFromExtras();
        ((TextView) findViewById(R.id.season_rating)).setText(mRating.toString());
        Glide
                .with(this)
                .load(mThumb)
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into((ImageView) findViewById(R.id.season_screenshot));
        Glide
                .with(this)
                .load(mPoster)
                .placeholder(R.drawable.season_item_placeholder)
                .centerCrop()
                .into((ImageView) findViewById(R.id.show_screenshot));
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
