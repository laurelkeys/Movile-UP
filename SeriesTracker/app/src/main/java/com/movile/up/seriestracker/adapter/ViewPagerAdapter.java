package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.fragment.ShowDetailsInfoFragment;
import com.movile.up.seriestracker.fragment.ShowDetailsSeasonFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public static final int POSITION_INFO_CONTENT = 0;
    public static final int POSITION_SEASON_CONTENT = 1;

    private Context mContext;

    public ViewPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == POSITION_INFO_CONTENT) {
            return new ShowDetailsInfoFragment();
        }
        if (position == POSITION_SEASON_CONTENT) {
            return new ShowDetailsSeasonFragment();
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
