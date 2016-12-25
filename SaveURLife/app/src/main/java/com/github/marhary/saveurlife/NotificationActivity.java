package com.github.marhary.saveurlife;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.github.marhary.saveurlife.notification.AlertReceiver;
import com.github.marhary.saveurlife.notification.MoreInfoNotification;

import java.util.GregorianCalendar;

public class NotificationActivity extends AppCompatActivity {

    Button showNotificationBut;
    Button stopNotificationBut;
    Button alertButton;

    NotificationManager notificationManager;

    boolean isNotificActive = false;

    int notifID = 33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        showNotificationBut = (Button) findViewById(R.id.showNotificationBut);
        stopNotificationBut = (Button) findViewById(R.id.stopNotificationBut);
        alertButton = (Button) findViewById(R.id.alertButton);

    }

    public void showNotification(View view) {

        NotificationCompat.Builder notificBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.notify_title))
                .setContentText(getString(R.string.notify_message))
                .setTicker(getString(R.string.notify_alert))
                .setSmallIcon(R.mipmap.note_launcher);

        Intent moreInfoIntent = new Intent(this, MoreInfoNotification.class);

        TaskStackBuilder tStackBuilder = TaskStackBuilder.create(this);
        tStackBuilder.addParentStack(MoreInfoNotification.class);
        tStackBuilder.addNextIntent(moreInfoIntent);

        PendingIntent pendingIntent = tStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        notificBuilder.setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notifID, notificBuilder.build());

        isNotificActive = true;
    }

    public void stopNotification(View view) {

        if (isNotificActive){
            notificationManager.cancel(notifID);
        }
    }

    public void setAlarm(View view){

        Long alertTime = new GregorianCalendar().getTimeInMillis()+5*1000;

        Intent alertIntent = new Intent(this, AlertReceiver.class);

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime, PendingIntent.getBroadcast(this, 1, alertIntent, PendingIntent.FLAG_CANCEL_CURRENT));
    }
}
