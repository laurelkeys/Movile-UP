package com.movile.up.seriestracker.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.ShowDetailsActivity;
import com.movile.up.seriestracker.activity.ShowsGridActivity;
import com.movile.up.seriestracker.adapter.FavoritesAdapter;
import com.movile.up.seriestracker.interfaces.listener.OnFavoritesClickListener;
import com.movile.up.seriestracker.interfaces.listener.OnFavoritesListener;
import com.movile.up.seriestracker.loader.callback.FavoritesLoaderCallback;

public class FavoritesFragment extends Fragment implements OnFavoritesListener, OnFavoritesClickListener {

    private FavoritesAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favorites_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ListView lvItems = (ListView) getActivity().findViewById(R.id.favorites_list_view);
        mAdapter = new FavoritesAdapter(getActivity(), null, 0, this);
        lvItems.setAdapter(mAdapter);
        getLoaderManager().initLoader(0, null, new FavoritesLoaderCallback(this.getActivity(), this)).forceLoad();
    }

    @Override
    public void OnFavoritesSuccess(Cursor cursor) {
        mAdapter.changeCursor(cursor);
    }

    @Override
    public void onFavoritesClick(String slug) {
        Intent intent = new Intent(this.getActivity(), ShowDetailsActivity.class);
        intent.putExtra(ShowsGridActivity.EXTRA_SHOW, slug);
        startActivity(intent);
    }
}
