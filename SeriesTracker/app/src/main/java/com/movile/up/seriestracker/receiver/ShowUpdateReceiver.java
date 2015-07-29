package com.movile.up.seriestracker.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.ShowDetailsActivity;
import com.movile.up.seriestracker.activity.ShowsGridActivity;
import com.movile.up.seriestracker.model.ShowUpdate;
import com.movile.up.seriestracker.util.FormatUtil;

public class ShowUpdateReceiver extends BroadcastReceiver {

    private static final String TAG = BroadcastReceiver.class.getSimpleName();
    public static final String EXTRA_UPDATE = "update";
    public static final String PREFERENCES_NAME = "show_update_preference";
    public static final String KEY_LAST_UPDATE = "show_update_key";
    private ShowUpdate mShowUpdate;
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        mShowUpdate = (ShowUpdate) extras.get(EXTRA_UPDATE);
        mContext = context;
        Log.d(TAG, mShowUpdate.message());

        Intent i = new Intent(mContext, ShowDetailsActivity.class);
        i.putExtra(ShowsGridActivity.EXTRA_SHOW, mShowUpdate.show());
        i.putExtra(ShowsGridActivity.EXTRA_SHOW_TITLE, mShowUpdate.title());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
        stackBuilder.addParentStack(ShowDetailsActivity.class);
        stackBuilder.addNextIntent(i);

        PendingIntent action = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        if ((preferences.getString(KEY_LAST_UPDATE, null) == null) ||
                (preferences.getString(KEY_LAST_UPDATE, null).compareTo(FormatUtil.formatDate(FormatUtil.formatDate(mShowUpdate.date()))) < 1)) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle(mShowUpdate.title())
                    .setContentText(mShowUpdate.message())
                    .setContentIntent(action)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(mShowUpdate.message()));

            Notification notification = builder.build();

            NotificationManager manager =
                    (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, notification);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(KEY_LAST_UPDATE, FormatUtil.formatDate(FormatUtil.formatDate(mShowUpdate.date())));
            editor.commit();
        }
    }
}
