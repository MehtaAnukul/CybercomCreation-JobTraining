package com.anukul.fcmtestapp;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseInstanceIdService extends FirebaseMessagingService {
   private static final String REG_TOKEN = "REG_TOKEN";
    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d(REG_TOKEN,token);

        //.............................
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(AppConstant.KEY_FCM_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstant.KEY_FCM_TOKEN,token);
        editor.commit();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("main","conform");
        String title = remoteMessage.getNotification().getTitle();
        String message = remoteMessage.getNotification().getBody();

        String click_action = remoteMessage.getNotification().getClickAction();
        //Intent intent = new Intent(click_action);
        Intent intent = new Intent(this,MainActivity.class);
        if(remoteMessage.getData().size() > 0){
            String msg = remoteMessage.getData().get("message");
            Bundle bundle = new Bundle();
            bundle.putString(AppConstant.KEY_MESSAGE,msg);
            intent.putExtras(bundle);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,AppConstant.KAY_CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms_notification);
       // builder.setContentTitle("FCM Notification");
       // builder.setContentText(remoteMessage.getNotification().getBody());
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(AppConstant.KAY_CHANNEL_ID,"Default Channel",NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(0,builder.build());




    }
}
