package com.movile.up.seriestracker.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.adapter.ShowsGridAdapter;
import com.movile.up.seriestracker.interfaces.listener.OnShowClickListener;
import com.movile.up.seriestracker.interfaces.view.ShowsGridView;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.presenter.ShowsGridPresenter;
import com.movile.up.seriestracker.service.UpdatesService;

import java.util.List;

public class ShowsGridActivity extends AppCompatActivity implements ShowsGridView, OnShowClickListener {

    private static final String EXTRA_SHOW = "show";
    private static final String EXTRA_SHOW_TITLE = "title";
    private ShowsGridAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shows_grid_activity);
        new ShowsGridPresenter(this, this).loadRemoteShowsWithRetrofit();
        GridView view = (GridView) findViewById(R.id.shows_grid_view);
        mAdapter = new ShowsGridAdapter(this, this);
        view.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, UpdatesService.class);
        startService(intent);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, new Intent(this, UpdatesService.class), 0);
        AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, 0, 10, pendingIntent);
    }

    @Override
    public void displayShows(List<Show> shows) {
        mAdapter.updateShows(shows);
    }

    @Override
    public void onShowClick(Show show) {
        Intent intent = new Intent(this, ShowDetailsActivity.class);
        intent.putExtra(EXTRA_SHOW, show.ids().slug());
        intent.putExtra(EXTRA_SHOW_TITLE, show.title());
        startActivity(intent);
    }
}
