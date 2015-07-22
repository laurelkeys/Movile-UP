package com.movile.up.seriestracker.interfaces.listener;

import com.movile.up.seriestracker.model.Episode;

public interface OnEpisodeDetailsListener<T> {
    void onEpisodeDetailsSuccess(T type);
}
