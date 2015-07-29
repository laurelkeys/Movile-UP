package com.movile.up.seriestracker.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.adapter.ViewPagerAdapter;
import com.movile.up.seriestracker.database.dao.FavoriteDAO;
import com.movile.up.seriestracker.interfaces.listener.OnFavoriteClickListener;
import com.movile.up.seriestracker.model.Favorite;
import com.movile.up.seriestracker.model.Show;

public class ShowDetailsActivity extends BaseNavigationToolbarActivity implements OnFavoriteClickListener {

    private Show mShowModel;
    private String mShow;
    private String mScreenshot;
    private Double mRating;
    private String mTitle;
    private String mOverview;
    private String mStatus;
    private Long mYear;
    private String mCountry;
    private String mLanguage;
    private String[] mGenres;
    private FloatingActionButton mFab;
    private Favorite mFavorite;
    private FavoriteDAO mDao = new FavoriteDAO(this);

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);
        configureToolbar();
        configureContentPager();
        getSupportActionBar().setTitle(mTitle);

        displayShow();

        mFab = (FloatingActionButton) findViewById(R.id.show_details_favorite);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavoriteClick();
            }
        });

        mFavorite = mDao.query(mShow);
        if (mFavorite == null) {
            mFab.setImageResource(R.drawable.show_details_favorite_off);
            mFab.setBackgroundTintList(getResources().getColorStateList(R.color.default_background_fifth));
        } else {
            mFab.setImageResource(R.drawable.show_details_favorite_on);
            mFab.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_second));
        }
    }

    private void configureContentPager() {
        extractInformationFromExtras();
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), this,
                mShow, mOverview, mStatus, mYear, mCountry, mLanguage, mGenres));
    }

    private void extractInformationFromExtras() {
        Bundle extras = getIntent().getExtras();
        // mShowModel= (Show) extras.get(ShowsGridActivity.EXTRA_SHOW_MODEL);
        mShow = extras.getString(ShowsGridActivity.EXTRA_SHOW);
        mTitle = extras.getString(ShowsGridActivity.EXTRA_SHOW_TITLE);
        mRating = extras.getDouble(ShowsGridActivity.EXTRA_SHOW_RATING);
        mScreenshot = extras.getString(ShowsGridActivity.EXTRA_SHOW_SCREENSHOT);
        mOverview = extras.getString(ShowsGridActivity.EXTRA_SHOW_INFO_SUMMARY);
        mStatus = extras.getString(ShowsGridActivity.EXTRA_SHOW_INFO_STATUS);
        mYear = extras.getLong(ShowsGridActivity.EXTRA_SHOW_INFO_YEAR);
        mCountry = extras.getString(ShowsGridActivity.EXTRA_SHOW_INFO_COUNTRY);
        mLanguage = extras.getString(ShowsGridActivity.EXTRA_SHOW_INFO_LANGUAGE);
        mGenres = extras.getStringArray(ShowsGridActivity.EXTRA_SHOW_INFO_GENRES);
    }

    private void displayShow() {
        ((TextView) findViewById(R.id.show_details_rating)).setText(mRating.toString());
        Glide
                .with(this)
                .load(mScreenshot)
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into((ImageView) findViewById(R.id.show_screenshot));
    }

    @Override
    public void onFavoriteClick() {
        if (mFavorite == null) {
            mFab.setImageResource(R.drawable.show_details_favorite_on);
            mFab.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_second));
            mFavorite = new Favorite(mShow, mTitle);
            mDao.save(mFavorite);
        } else {
            mFab.setImageResource(R.drawable.show_details_favorite_off);
            mFab.setBackgroundTintList(getResources().getColorStateList(R.color.default_background_fifth));
            mFavorite = null;
            mDao.delete(mShow);
        }
    }
}
