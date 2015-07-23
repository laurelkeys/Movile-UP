package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.interfaces.listener.OnShowsGridListener;
import com.movile.up.seriestracker.interfaces.view.ShowsGridView;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.retrofit.FetchLocalShowsGridRetrofit;

import java.util.List;

public class ShowsGridPresenter implements OnShowsGridListener<Show> {
    private ShowsGridView mView;
    private Context mContext;

    public ShowsGridPresenter(Context context, ShowsGridView view) {
        mContext = context;
        mView = view;
    }

    public void loadRemoteShowsWithRetrofit() {
        new FetchLocalShowsGridRetrofit(mContext, this).loadShows();
    }

    public void OnShowsGridSuccess(List<Show> shows) {
        mView.displayShows(shows);
    }
}
