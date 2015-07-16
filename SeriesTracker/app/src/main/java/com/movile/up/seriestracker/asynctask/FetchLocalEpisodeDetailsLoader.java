package com.movile.up.seriestracker.asynctask;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.model.converter.ModelConverter;
import com.movile.up.seriestracker.model.Episode;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchLocalEpisodeDetailsLoader extends AsyncTaskLoader<Episode> {
    private static final String TAG = FetchLocalEpisodeDetailsLoader.class.getSimpleName();
    private Context mContext;
    private String mUrl;

    public FetchLocalEpisodeDetailsLoader(Context context, String url) {
        super(context);
        mContext = context;
        mUrl = url;
    }

    public Episode loadInBackground() {

        Episode episode = null;
        InputStreamReader reader = null;
        try {
            HttpURLConnection connection = configureConnection(mContext, mUrl);
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream stream = connection.getInputStream();
                reader = new InputStreamReader(stream);
                episode = new ModelConverter().toEpisode(reader);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error loading remote content", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error releasing resource", e);
                }
            }
        }

        return episode;
    }

    private HttpURLConnection configureConnection(Context context, String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setReadTimeout(15000);
        connection.setConnectTimeout(10000);
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("trakt-api-version", "2");
        connection.setRequestProperty("trakt-api-key", "e98f066eea6ffed066d5212e9fd4043bded51262ce7c9e28456b995bbe43f5f7");
        return connection;
    }

}