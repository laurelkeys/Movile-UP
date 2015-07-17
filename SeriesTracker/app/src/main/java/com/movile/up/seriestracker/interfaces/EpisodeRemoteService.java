package com.movile.up.seriestracker.interfaces;

import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.remote.ApiConfiguration;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

import static com.movile.up.seriestracker.remote.ApiConfiguration.*;

public interface EpisodeRemoteService {
    @Headers({
            "trakt-api-version: " + API_VERSION,
            "trakt-api-key: " + API_KEY
    })
    @GET("/shows/{show}/seasons/{season}/episodes/{episode}?extended=full,images")
    void getEpisodeDetails(
            @Path("show") String show,
            @Path("season") Long season,
            @Path("episode") Long episode,
            Callback<Episode> callback);
}
