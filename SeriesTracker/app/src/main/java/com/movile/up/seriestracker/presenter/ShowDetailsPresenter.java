package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.interfaces.listener.OnShowDetailsListener;
import com.movile.up.seriestracker.interfaces.view.ShowDetailsSeasonView;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.retrofit.FetchLocalShowDetailsRetrofit;

import java.util.List;

public class ShowDetailsPresenter implements OnShowDetailsListener<Season> {
    private ShowDetailsSeasonView mView;
    private Context mContext;

    public ShowDetailsPresenter(Context context, ShowDetailsSeasonView view) {
        mContext = context;
        mView = view;
    }

    public void loadRemoteSeasonsWithRetrofit(String show) {
        new FetchLocalShowDetailsRetrofit(mContext, this).loadSeasons(show);
    }

    public void OnShowDetailsSuccess(List<Season> seasons) {
        mView.displaySeasons(seasons);
    }
}
