package com.movile.up.seriestracker.remote.service;

import com.movile.up.seriestracker.model.Episode;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

import static com.movile.up.seriestracker.remote.ApiConfiguration.API_KEY;
import static com.movile.up.seriestracker.remote.ApiConfiguration.API_VERSION;

public interface SeasonRemoteService {
    @Headers({
            "trakt-api-version: " + API_VERSION,
            "trakt-api-key: " + API_KEY
    })
    @GET("/shows/{show}/seasons/{season}?extended=full,images")
    void getEpisodes(
            @Path("show") String show,
            @Path("season") Long season,
            Callback<List<Episode>> callback);
}
