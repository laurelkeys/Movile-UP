package com.movile.up.seriestracker.remote.service;

import com.movile.up.seriestracker.model.ShowUpdate;

import retrofit.http.GET;

public interface UpdatesRemoteService {

    @GET("/latestUpdate.json")
    ShowUpdate getLatest();

}