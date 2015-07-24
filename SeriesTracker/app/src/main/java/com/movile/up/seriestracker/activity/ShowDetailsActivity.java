package com.movile.up.seriestracker.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.adapter.ViewPagerAdapter;

public class ShowDetailsActivity extends BaseNavigationToolbarActivity {

    public static final String EXTRA_SHOW = "show";
    public static final String EXTRA_SHOW_TITLE = "title";
    private String mShow;
    private String mShowTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);
        configureToolbar();
        configureContentPager();
        getSupportActionBar().setTitle(mShowTitle);
    }

    private void configureContentPager() {
        extractInformationFromExtras();
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), this, mShow));
    }

    private void extractInformationFromExtras() {
        Bundle extras = getIntent().getExtras();
        mShow = extras.getString(EXTRA_SHOW);
        mShowTitle = extras.getString(EXTRA_SHOW_TITLE);
    }
}
