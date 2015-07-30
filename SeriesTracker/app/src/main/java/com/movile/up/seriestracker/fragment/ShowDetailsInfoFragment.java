package com.movile.up.seriestracker.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.ShowsGridActivity;
import com.movile.up.seriestracker.adapter.InfoRecyclerAdapter;
import com.movile.up.seriestracker.adapter.SeasonRecyclerAdapter;
import com.movile.up.seriestracker.interfaces.view.ShowDetailsInfoView;

public class ShowDetailsInfoFragment extends Fragment implements ShowDetailsInfoView {

    private String mShowSummary;
    private String mShowStatus;
    private Long mShowYear;
    private String mShowLanguage;
    private String mShowCountry;
    private String[] mShowGenres;
    private View mView;
    private InfoRecyclerAdapter mAdapter;

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
        configureRecyclerView();
        displayShowInfo();
        // displayGenres(mShowGenres); // FIXME
    }

    private void configureRecyclerView() {
        RecyclerView view = (RecyclerView) mView.findViewById(R.id.show_details_genres_recycler);
        view.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));

        mAdapter = new InfoRecyclerAdapter(this.getActivity());
        view.setAdapter(mAdapter);
    }

    private void extractInformationFromExtras() {
        Bundle b = this.getArguments();
        mShowSummary = b.getString(ShowsGridActivity.EXTRA_SHOW_INFO_SUMMARY);
        mShowStatus = b.getString(ShowsGridActivity.EXTRA_SHOW_INFO_STATUS);
        mShowYear = b.getLong(ShowsGridActivity.EXTRA_SHOW_INFO_YEAR);
        mShowLanguage = b.getString(ShowsGridActivity.EXTRA_SHOW_INFO_LANGUAGE);
        mShowCountry = b.getString(ShowsGridActivity.EXTRA_SHOW_INFO_COUNTRY);
        mShowGenres = b.getStringArray(ShowsGridActivity.EXTRA_SHOW_INFO_GENRES);
    }


    private void displayShowInfo() {
        extractInformationFromExtras();
        ((TextView) mView.findViewById(R.id.show_details_overview)).setText(mShowSummary);

        String technicalInfo;
        technicalInfo = "Status: " + mShowStatus +
                "\n\nStarted in: " + mShowYear.toString() +
                "\n\nCountry: " + mShowCountry +
                "\n\nLanguage: " + mShowLanguage;
        ((TextView) mView.findViewById(R.id.show_details_technical_info)).setText(technicalInfo);
        /*
        LinearLayout ll = (LinearLayout) mView.findViewById(R.id.show_details_genres);
        for(int i = 0; i < mShowGenres.length; ++i) {
            TextView tv = new TextView(mView.getContext());
            tv.setBackground(getResources().getDrawable(R.drawable.show_information_genre, null));
            tv.setGravity(Gravity.CENTER);
            tv.setText(mShowGenres[i]);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            tv.setTextColor(getResources().getColor(R.color.default_textColor_first));
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llp.setMargins(8, 0, 8, 0);
            tv.setLayoutParams(llp);
            ll.addView(tv);
        }
        */
    }

    @Override
    public void displayGenres(String[] genres) {
        mAdapter.updateContents(genres);
    }
}
