package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.ShowDetailsActivity;
import com.movile.up.seriestracker.activity.ShowsGridActivity;
import com.movile.up.seriestracker.fragment.ShowDetailsInfoFragment;
import com.movile.up.seriestracker.fragment.ShowDetailsSeasonFragment;
import com.movile.up.seriestracker.model.Show;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public static final int POSITION_INFO_CONTENT = 0;
    public static final int POSITION_SEASON_CONTENT = 1;

    private Show mShowModel;
    public String mShow;
    private String mOverview;
    private String mStatus;
    private Long mYear;
    private String mCountry;
    private String mLanguage;
    private Context mContext;
    private String[] mGenres;

    /*
    public ViewPagerAdapter(FragmentManager fragmentManager, Context context, Show showModel) {
        super(fragmentManager);
        mContext = context;
        mShowModel = showModel;
        mShow = mShowModel.ids().slug();
    }*/

    public ViewPagerAdapter(FragmentManager manager, Context context, String show, String overview, String status, Long year, String country, String language, String[] genres) {
        super(manager);
        mContext = context;
        mShow = show;
        mOverview = overview;
        mStatus = status;
        mYear = year;
        mCountry = country;
        mLanguage = language;
        mGenres = genres;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == POSITION_INFO_CONTENT) {
            Bundle b = new Bundle();
            b.putString(ShowsGridActivity.EXTRA_SHOW_INFO_SUMMARY, mOverview);
            b.putString(ShowsGridActivity.EXTRA_SHOW_INFO_STATUS, mStatus);
            b.putLong(ShowsGridActivity.EXTRA_SHOW_INFO_YEAR, mYear);
            b.putString(ShowsGridActivity.EXTRA_SHOW_INFO_LANGUAGE, mLanguage);
            b.putString(ShowsGridActivity.EXTRA_SHOW_INFO_COUNTRY, mCountry);
            b.putStringArray(ShowsGridActivity.EXTRA_SHOW_INFO_GENRES, mGenres);

            ShowDetailsInfoFragment fragment = new ShowDetailsInfoFragment();
            fragment.setArguments(b);
            return fragment;
        }
        if (position == POSITION_SEASON_CONTENT) {
            Bundle b = new Bundle();
            b.putString(ShowsGridActivity.EXTRA_SHOW, mShow);
            ShowDetailsSeasonFragment fragment = new ShowDetailsSeasonFragment();
            fragment.setArguments(b);
            return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == POSITION_INFO_CONTENT) {
            return mContext.getString(R.string.navigation_info_label);
        }
        if (position == POSITION_SEASON_CONTENT) {
            return mContext.getString(R.string.navigation_season_label);
        }
        return null;
    }

}
