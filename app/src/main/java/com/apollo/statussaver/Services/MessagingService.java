package com.apollo.statussaver.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.WorkerThread;
import android.support.v4.app.NotificationCompat;

import com.apollo.statussaver.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {


    @WorkerThread
    public void onMessageReceived(RemoteMessage message) {
        showNotification(message.getNotification().getTitle());
        showNotification(message.getNotification().getBody());
    }


    public void showNotification(String message) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Status Saver")
                .setSmallIcon(R.drawable.ic_update)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }


}