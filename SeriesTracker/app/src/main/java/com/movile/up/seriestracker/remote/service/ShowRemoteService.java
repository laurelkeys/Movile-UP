package com.movile.up.seriestracker.remote.service;

import com.movile.up.seriestracker.model.Season;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

import static com.movile.up.seriestracker.remote.ApiConfiguration.API_KEY;
import static com.movile.up.seriestracker.remote.ApiConfiguration.API_VERSION;

public interface ShowRemoteService {
    @Headers({
            "trakt-api-version: " + API_VERSION,
            "trakt-api-key: " + API_KEY
    })
    @GET("/shows/{show}/seasons?extended=full,images")
    void getSeasons(
            @Path("show") String show,
            Callback<List<Season>> callback);
}
