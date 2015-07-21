package com.movile.up.seriestracker.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.adapter.ViewPagerAdapter;

public class ShowDetailsActivity extends BaseNavigationToolbarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);
        configureContentPager();
    }

    private void configureContentPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), this));
    }
}
