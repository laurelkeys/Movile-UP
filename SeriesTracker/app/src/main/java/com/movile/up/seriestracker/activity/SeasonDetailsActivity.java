package com.movile.up.seriestracker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.adapter.SeasonDetailsAdapter;
import com.movile.up.seriestracker.interfaces.OnContentClickListener;
import com.movile.up.seriestracker.interfaces.SeasonDetailsView;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.presenter.SeasonDetailsPresenter;

import java.util.List;

public class SeasonDetailsActivity extends AppCompatActivity implements SeasonDetailsView {

    private static final String TAG = EpisodeDetailsActivity.class.getSimpleName();
    private List<Episode> mEpisodes;
    private OnContentClickListener listener;
    public SeasonDetailsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);
        new SeasonDetailsPresenter(this, this).loadRemoteEpisodesWithRetrofit();
        ListView view = (ListView) findViewById(R.id.list_view);
        //view.addHeaderView(headerView, null, false);
        //view.setEmptyView(emptyView);
        //view.addFooterView(footerView, null, false);

        mAdapter = new SeasonDetailsAdapter(this, listener);
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
    }
}
