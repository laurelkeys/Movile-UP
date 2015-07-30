package com.movile.up.seriestracker.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
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
    private String mShowSlug;
    private String mShowScreenshot;
    private Double mShowRating;
    private String mShowTitle;
    private String mShowOverview;
    private String mShowStatus;
    private Long mShowYear;
    private String mShowCountry;
    private String mShowLanguage;
    private String[] mShowGenres;
    private FloatingActionButton mFab;
    private Favorite mFavorite;
    private ViewPager mViewPager;
    private FavoriteDAO mDao = new FavoriteDAO(this);

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);
        configureToolbar();
        configureContentPager();
        getSupportActionBar().setTitle(mShowTitle);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.show_details_tab_layout);
        tabLayout.setupWithViewPager(mViewPager);

        displayShow();

        mFab = (FloatingActionButton) findViewById(R.id.show_details_favorite);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavoriteClick();
            }
        });

        mFavorite = mDao.query(mShowSlug);
        if (mFavorite == null) {
            mFab.setImageResource(R.drawable.show_details_favorite_off);
            mFab.setBackgroundTintList(getResources().getColorStateList(R.color.default_background_fifth));
        } else {
            mFab.setImageResource(R.drawable.show_details_favorite_on);
            mFab.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_second));
        }
        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);
        configureToolbar();
        configureContentPager();
        getSupportActionBar().setTitle(mShowTitle);

        displayShow();

        mFab = (FloatingActionButton) findViewById(R.id.show_details_favorite);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavoriteClick();
            }
        });

        mFavorite = mDao.query(mShowSlug);
        if (mFavorite == null) {
            mFab.setImageResource(R.drawable.show_details_favorite_off);
            mFab.setBackgroundTintList(getResources().getColorStateList(R.color.default_background_fifth));
        } else {
            mFab.setImageResource(R.drawable.show_details_favorite_on);
            mFab.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_second));
        }
        */
    }

    private void configureContentPager() {
        extractInformationFromExtras();
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), this,
                mShowSlug, mShowOverview, mShowStatus, mShowYear, mShowCountry, mShowLanguage, mShowGenres));
    }

    private void extractInformationFromExtras() {
        Bundle extras = getIntent().getExtras();
        // mShowModel= (Show) extras.get(ShowsGridActivity.EXTRA_SHOW_MODEL);
        mShowSlug = extras.getString(ShowsGridActivity.EXTRA_SHOW);
        mShowTitle = extras.getString(ShowsGridActivity.EXTRA_SHOW_TITLE);
        mShowRating = extras.getDouble(ShowsGridActivity.EXTRA_SHOW_RATING);
        mShowScreenshot = extras.getString(ShowsGridActivity.EXTRA_SHOW_SCREENSHOT);
        mShowOverview = extras.getString(ShowsGridActivity.EXTRA_SHOW_INFO_SUMMARY);
        mShowStatus = extras.getString(ShowsGridActivity.EXTRA_SHOW_INFO_STATUS);
        mShowYear = extras.getLong(ShowsGridActivity.EXTRA_SHOW_INFO_YEAR);
        mShowCountry = extras.getString(ShowsGridActivity.EXTRA_SHOW_INFO_COUNTRY);
        mShowLanguage = extras.getString(ShowsGridActivity.EXTRA_SHOW_INFO_LANGUAGE);
        mShowGenres = extras.getStringArray(ShowsGridActivity.EXTRA_SHOW_INFO_GENRES);
    }

    private void displayShow() {
        ((TextView) findViewById(R.id.show_details_rating)).setText(mShowRating.toString());
        Glide
                .with(this)
                .load(mShowScreenshot)
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into((ImageView) findViewById(R.id.show_screenshot));
    }

    @Override
    public void onFavoriteClick() {
        final ObjectAnimator scaleX = ObjectAnimator.ofFloat(mFab, "scaleX", 1, 0);
        scaleX.setDuration(200);

        final ObjectAnimator scaleY = ObjectAnimator.ofFloat(mFab, "scaleY", 1, 0);
        scaleY.setDuration(200);

        final ObjectAnimator rotation = ObjectAnimator.ofFloat(mFab, "rotation", 0f, 360f);
        rotation.setDuration(200);

        // AnimatorSet
        AnimatorSet set = new AnimatorSet();
        set.playTogether(rotation, scaleX, scaleY);
        set.setDuration(400);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mFavorite == null) {
                    mFab.setImageResource(R.drawable.show_details_favorite_on);
                    mFab.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_second));
                    mFavorite = new Favorite(mShowSlug, mShowTitle);
                    mDao.save(mFavorite);
                } else {
                    mFab.setImageResource(R.drawable.show_details_favorite_off);
                    mFab.setBackgroundTintList(getResources().getColorStateList(R.color.default_background_fifth));
                    mFavorite = null;
                    mDao.delete(mShowSlug);
                }

                final ObjectAnimator endedScaleX = ObjectAnimator.ofFloat(mFab, "scaleX", 0, 1);
                endedScaleX.setDuration(200);
                endedScaleX.start();

                final ObjectAnimator endedScaleY = ObjectAnimator.ofFloat(mFab, "scaleY", 0, 1);
                endedScaleY.setDuration(200);
                endedScaleY.start();

                final ObjectAnimator endedRotation = ObjectAnimator.ofFloat(mFab, "rotation", 0f, 360f);
                endedRotation.setDuration(200);
                endedRotation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }
}