package com.movile.up.seriestracker.retrofit;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.asynctask.FetchLocalEpisodeDetailsLoader;
import com.movile.up.seriestracker.interfaces.EpisodeRemoteService;
import com.movile.up.seriestracker.interfaces.OnEpisodeDetailsListener;
import com.movile.up.seriestracker.model.Episode;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FetchLocalEpisodeDetailsRetrofit {
    private static final String TAG = FetchLocalEpisodeDetailsLoader.class.getSimpleName();
    private RestAdapter mAdapter;
    private OnEpisodeDetailsListener<Episode> mCallback;

    public FetchLocalEpisodeDetailsRetrofit(Context context, OnEpisodeDetailsListener<Episode> listener) {
        mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        mCallback = listener;
    }

    public void loadEpisode(String show, Long season, Long episode) {
        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);
        service.getEpisodeDetails(show, season, episode, new Callback<Episode>() {
            @Override
            public void success(Episode episode, Response response) {
                mCallback.onEpisodeDetailsSuccess(episode);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode", error.getCause());
            }
        });
    }

}
