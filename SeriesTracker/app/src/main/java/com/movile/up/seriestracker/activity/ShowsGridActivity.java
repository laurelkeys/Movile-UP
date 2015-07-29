package com.movile.up.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.base.BaseNavigationDrawerActivity;
import com.movile.up.seriestracker.adapter.ShowsGridAdapter;
import com.movile.up.seriestracker.interfaces.listener.OnShowClickListener;
import com.movile.up.seriestracker.interfaces.view.ShowsGridView;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.presenter.ShowsGridPresenter;

import java.util.List;

public class ShowsGridActivity extends BaseNavigationDrawerActivity implements ShowsGridView, OnShowClickListener {

    public static final String EXTRA_SHOW_MODEL = "model";
    public static final String EXTRA_SHOW = "show";
    public static final String EXTRA_SHOW_TITLE = "title";
    public static final String EXTRA_SHOW_RATING = "rating";
    public static final String EXTRA_SHOW_SCREENSHOT = "screenshot";
    public static final String EXTRA_SHOW_INFO_SUMMARY = "summary";
    public static final String EXTRA_SHOW_INFO_STATUS = "status";
    public static final String EXTRA_SHOW_INFO_YEAR = "year";
    public static final String EXTRA_SHOW_INFO_LANGUAGE = "language";
    public static final String EXTRA_SHOW_INFO_COUNTRY = "country";
    public static final String EXTRA_SHOW_INFO_GENRES = "genres";
    public static final String EXTRA_FIRST_AIRED = "first-aired";
    private ShowsGridAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shows_grid_activity);
        configureNavigation();
        new ShowsGridPresenter(this, this).loadRemoteShowsWithRetrofit();
        GridView view = (GridView) findViewById(R.id.shows_grid_view);
        mAdapter = new ShowsGridAdapter(this, this);
        view.setAdapter(mAdapter);
    }

    @Override
    public void displayShows(List<Show> shows) {
        mAdapter.updateShows(shows);
    }

    @Override
    public void onShowClick(Show show) {
        Intent intent = new Intent(this, ShowDetailsActivity.class);
        intent.putExtra(EXTRA_SHOW_MODEL, show);
        intent.putExtra(EXTRA_SHOW, show.ids().slug());
        intent.putExtra(EXTRA_SHOW_TITLE, show.title());
        intent.putExtra(EXTRA_SHOW_RATING, show.rating());
        intent.putExtra(EXTRA_SHOW_SCREENSHOT, show.images().thumb().get("full"));
        /*
        intent.putExtra(EXTRA_SHOW_INFO_SUMMARY, show.overview());
        intent.putExtra(EXTRA_SHOW_INFO_STATUS, show.status());
        intent.putExtra(EXTRA_SHOW_INFO_YEAR, show.year());
        intent.putExtra(EXTRA_SHOW_INFO_LANGUAGE, show.language());
        intent.putExtra(EXTRA_SHOW_INFO_COUNTRY, show.country());
        */
        startActivity(intent);
    }
}
