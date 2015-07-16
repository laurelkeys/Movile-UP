package com.movile.up.seriestracker.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.asynctask.FetchLocalEpisodeDetailsLoaderCallback;
import com.movile.up.seriestracker.interfaces.OnEpisodeDetailsListener;
import com.movile.up.seriestracker.model.Episode;


public class EpisodeDetailsActivity extends ActionBarActivity implements OnEpisodeDetailsListener<Episode> {

    private static final String TAG = EpisodeDetailsActivity.class.getSimpleName();

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_details_activity);
        getLoaderManager().initLoader(
                0, null, new FetchLocalEpisodeDetailsLoaderCallback(this, this,
                        "https://api-v2launch.trakt.tv/shows/game-of-thrones/seasons/5/episodes/1?extended=full,images")
        ).forceLoad();
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
    public void onEpisodeDetailsSuccess(Episode episode) {
        ((TextView) findViewById(R.id.episode_details_title)).setText(episode.title());
        ((TextView) findViewById(R.id.episode_details_first_aired)).setText(episode.firstAired());
        ((TextView) findViewById(R.id.episode_details_overview)).setText(episode.overview());
    }
}
