package com.movile.up.seriestracker.retrofit;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.EpisodeRemoteService;
import com.movile.up.seriestracker.interfaces.OnSeasonDetailsListener;
import com.movile.up.seriestracker.model.Episode;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FetchLocalSeasonDetailsRetrofit {
    private static final String TAG = FetchLocalSeasonDetailsRetrofit.class.getSimpleName();
    private RestAdapter mAdapter;
    private OnSeasonDetailsListener<Episode> mCallback;

    public FetchLocalSeasonDetailsRetrofit(Context context, OnSeasonDetailsListener<Episode> listener) {
        mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        mCallback = listener;
    }

    public void loadEpisodes(String show, Long season) {
        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);
        service.getEpisodes(show, season, new Callback<List<Episode>>() {
            @Override
            public void success(List<Episode> episodes, Response response) {
                mCallback.OnSeasonDetailsSuccess(episodes);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching season_details_episode", error.getCause());
            }
        });
    }

}
