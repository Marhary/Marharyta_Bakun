package com.github.marhary.saveurlife.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.github.marhary.saveurlife.NotificationActivity;
import com.github.marhary.saveurlife.R;
import com.github.marhary.saveurlife.constants.IConstant;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        createNotification(context, IConstant.TIMES_UP, IConstant.PASSED, IConstant.ALERT);
    }

    public void createNotification(Context context, String msg, String msgText, String msgAlert) {

        PendingIntent notificIntent = PendingIntent.getActivity(context, 0, new Intent(context, NotificationActivity.class), 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.note_launcher)
                .setContentTitle(msg)
                .setTicker(msgAlert)
                .setContentText(msgText);

        mBuilder.setContentIntent(notificIntent);

        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);

        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, mBuilder.build());

    }
}
