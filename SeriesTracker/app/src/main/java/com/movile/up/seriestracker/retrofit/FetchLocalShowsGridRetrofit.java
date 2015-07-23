package com.movile.up.seriestracker.retrofit;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.listener.OnShowsGridListener;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.remote.service.ShowRemoteService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FetchLocalShowsGridRetrofit {
    private static final String TAG = FetchLocalSeasonDetailsRetrofit.class.getSimpleName();
    private RestAdapter mAdapter;
    private OnShowsGridListener<Show> mCallback;

    public FetchLocalShowsGridRetrofit(Context context, OnShowsGridListener<Show> listener) {
        mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        mCallback = listener;
    }

    public void loadShows() {
        ShowRemoteService service = mAdapter.create(ShowRemoteService.class);
        service.getPopularShows(new Callback<List<Show>>() {
            @Override
            public void success(List<Show> episodes, Response response) {
                mCallback.OnShowsGridSuccess(episodes);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching season_details_episode", error.getCause());
            }
        });
    }
}
