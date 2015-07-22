package com.movile.up.seriestracker.retrofit;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.listener.OnShowDetailsListener;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.remote.service.ShowRemoteService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FetchLocalShowDetailsRetrofit {
    private static final String TAG = FetchLocalSeasonDetailsRetrofit.class.getSimpleName();
    private RestAdapter mAdapter;
    private OnShowDetailsListener<Season> mCallback;

    public FetchLocalShowDetailsRetrofit(Context context, OnShowDetailsListener<Season> listener) {
        mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        mCallback = listener;
    }

    public void loadSeasons(String show) {
        ShowRemoteService service = mAdapter.create(ShowRemoteService.class);
        service.getSeasons(show, new Callback<List<Season>>() {
            @Override
            public void success(List<Season> seasons, Response response) {
                mCallback.OnShowDetailsSuccess(seasons);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching season_details_episode", error.getCause());
            }
        });
    }
}
