package com.movile.up.seriestracker.service;

import android.app.IntentService;
import android.content.Intent;

import com.movile.up.seriestracker.model.ShowUpdate;
import com.movile.up.seriestracker.receiver.ShowUpdateReceiver;
import com.movile.up.seriestracker.retrofit.FetchLocalShowUpdateRetrofit;

public class UpdatesService extends IntentService {


    public UpdatesService() {
        super(UpdatesService.class.getSimpleName());
    }

    protected void onHandleIntent(Intent intent) {
        Intent i = new Intent("com.movile.up.seriestracker.action.SHOW_UPDATE");

        ShowUpdate update = new FetchLocalShowUpdateRetrofit(this).loadShowUpdate();

        i.putExtra(ShowUpdateReceiver.EXTRA_UPDATE, update);
        sendBroadcast(i);
    }

}
