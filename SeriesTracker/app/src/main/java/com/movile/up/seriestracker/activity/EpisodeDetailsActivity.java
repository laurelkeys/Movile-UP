package com.movile.up.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.interfaces.EpisodeDetailsView;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.presenter.EpisodeDetailsPresenter;


public class EpisodeDetailsActivity extends BaseNavigationToolbarActivity implements EpisodeDetailsView {

    private static final String TAG = EpisodeDetailsActivity.class.getSimpleName();
    private static final String EXTRA_SHOW = "show";
    private static final String EXTRA_SEASON = "season";
    private static final String EXTRA_EPISODE = "episode";
    private String mShow;
    private Long mSeason;
    private Long mEpisode;

    private EpisodeDetailsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_details_activity);
        configureToolbar();
        showLoading();
        extractInformationFromExtras();
        new EpisodeDetailsPresenter(this, this).loadRemoteEpisodeWithRetrofit(mShow, mSeason, mEpisode);
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
        mEpisode = extras.getLong(EXTRA_EPISODE);
    }

    @Override
    public void displayEpisode(Episode episode) {
        ((TextView) findViewById(R.id.episode_details_title)).setText(episode.title());
        ((TextView) findViewById(R.id.episode_details_first_aired)).setText(episode.firstAired());
        ((TextView) findViewById(R.id.episode_details_overview)).setText(episode.overview());
        Glide
                .with(this)
                .load(episode.images().screenshot().get("full"))
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into((ImageView) findViewById(R.id.highlight_placeholder));

        getSupportActionBar().setTitle("S".concat(mSeason.toString().concat("- Episode ".concat(mEpisode.toString()))));
        hideLoading();
    }
}
