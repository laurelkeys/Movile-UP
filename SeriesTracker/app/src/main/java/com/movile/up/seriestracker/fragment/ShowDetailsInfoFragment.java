package com.movile.up.seriestracker.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.ShowsGridActivity;

public class ShowDetailsInfoFragment extends Fragment {

    private String mShowSummary;
    private String mShowStatus;
    private Long mShowYear;
    private String mShowLanguage;
    private String mShowCountry;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.show_details_info_fragment, container, false);
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        extractInformationFromExtras();
        displayShowInfo();
    }

    private void extractInformationFromExtras() {
        Bundle b = this.getArguments();
        mShowSummary = b.getString(ShowsGridActivity.EXTRA_SHOW_INFO_SUMMARY);
        mShowStatus = b.getString(ShowsGridActivity.EXTRA_SHOW_INFO_STATUS);
        mShowYear = b.getLong(ShowsGridActivity.EXTRA_SHOW_INFO_YEAR);
        mShowLanguage = b.getString(ShowsGridActivity.EXTRA_SHOW_INFO_LANGUAGE);
        mShowCountry = b.getString(ShowsGridActivity.EXTRA_SHOW_INFO_COUNTRY);
    }

    private void displayShowInfo() {
        ((TextView) mView.findViewById(R.id.show_details_overview)).setText(mShowSummary.toString());
        String technicalInfo;
        technicalInfo = "Status: " + mShowStatus +
                "\nStarted in: " + mShowYear.toString() +
                "\nCountry: " + mShowCountry +
                "\nLanguage: " + mShowLanguage;
        ((TextView) mView.findViewById(R.id.show_details_technical_info)).setText(technicalInfo);

    }
}
