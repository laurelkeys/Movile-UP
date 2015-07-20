package com.movile.up.seriestracker.interfaces;

import com.movile.up.seriestracker.model.Episode;

import java.util.List;

public interface SeasonDetailsView {
    void displayEpisodes(List<Episode> episodes);
}
