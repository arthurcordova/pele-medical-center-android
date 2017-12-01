package com.mobway.pelemedicalcenter.fcm;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mobway.pelemedicalcenter.R;

/**
 * Created by acstapassoli on 08/10/17.
 */

public class PeleMedicalCenterFirebaseMessagingService extends FirebaseMessagingService {

    public static final String TAG = "FCM_MESSAGE";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Intent intent = new Intent(this, LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle("Pele Medical Center");
        notificationBuilder.setContentText(remoteMessage.getNotification().getBody());
        notificationBuilder.setSmallIcon(R.drawable.ic_notification_logo);
        notificationBuilder.setAutoCancel(true);
//        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setVibrate(new long[] { 500, 500, 500});

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}
