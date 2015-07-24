package com.movile.up.seriestracker.retrofit;

import android.content.Context;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.model.ShowUpdate;
import com.movile.up.seriestracker.remote.service.UpdatesRemoteService;

import retrofit.RestAdapter;

public class FetchLocalShowUpdateRetrofit {
    private static final String TAG = FetchLocalShowDetailsRetrofit.class.getSimpleName();
    private RestAdapter mAdapter;

    public FetchLocalShowUpdateRetrofit(Context context) {
        mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_updates)).build();
    }

    public ShowUpdate loadShowUpdate() {
        UpdatesRemoteService service = mAdapter.create(UpdatesRemoteService.class);
        return service.getLatest();
    }
}
