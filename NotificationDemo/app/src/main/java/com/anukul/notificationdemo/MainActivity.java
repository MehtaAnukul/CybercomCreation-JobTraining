package com.anukul.notificationdemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button notificationBtn;
    private Button progressNotificationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationBtn = findViewById(R.id.activitiy_main_notificationBtn);
        progressNotificationBtn = findViewById(R.id.activity_main_progressbarNotificationBtn);

        notificationBtn.setOnClickListener(this);
        progressNotificationBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activitiy_main_notificationBtn:
                showNotificationOnStatusBar();
                break;
            case R.id.activity_main_progressbarNotificationBtn:
                showProgressBarNotification();
                break;
        }
    }

    private void showProgressBarNotification() {
        createNotificationChannel();
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this,AppConstant.KAY_CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_download);
        builder.setContentTitle("Image Download");
        builder.setContentText("Download in Progress");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        final NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        final int max_progress = 100;
        int current_progress = 0;
        builder.setProgress(max_progress,current_progress,true);

        notificationManagerCompat.notify(AppConstant.KAY_NOTIFICATION_ID,builder.build());

        Thread thread = new Thread(){
            @Override
            public void run() {
                int count = 0;

                try {
                    while (count <= 100){
                        count = count + 10;
                        sleep(1000);
                        //builder.setProgress(max_progress,count,false);
                        //notificationManagerCompat.notify(AppConstant.KAY_NOTIFICATION_ID,builder.build());

                    }
                    builder.setContentText("Download Compleate");
                    builder.setProgress(0,0,false);
                    notificationManagerCompat.notify(AppConstant.KAY_NOTIFICATION_ID,builder.build());

                }catch (InterruptedException e){ }
            }
        };
        thread.start();
    }

    //dispalyNotification 8.0 and above so required CHANNEL_ID or register Channel_Id
    // it's work in 7.0 api and lower version
    //In android version lower than 8.0 varsion we can set priority using setPriority
    private void showNotificationOnStatusBar() {
        createNotificationChannel();

        Intent landingActivityIntent = new Intent(this,LandingActivity.class);
        landingActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //perpal navigation of activity when user open the activity from the notification
        PendingIntent lendingPendingIntent = PendingIntent.getActivity(this,10,landingActivityIntent,PendingIntent.FLAG_ONE_SHOT);

        Intent yesActivityIntent = new Intent(this,YesActivity.class);
        yesActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent yesActivityPendingIntent = PendingIntent.getActivity(this,10,yesActivityIntent,PendingIntent.FLAG_ONE_SHOT);

        Intent noActivityIntent = new Intent(this,NoActivity.class);
        noActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent noActivityPendingIntent = PendingIntent.getActivity(this,10,noActivityIntent,PendingIntent.FLAG_ONE_SHOT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,AppConstant.KAY_CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms_notification);
        builder.setContentTitle("Notification");
        builder.setContentText("Hello how r u");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);
        builder.setContentIntent(lendingPendingIntent);
        builder.addAction(R.drawable.ic_sms_notification,"YES",yesActivityPendingIntent);
        builder.addAction(R.drawable.ic_sms_notification,"NO",noActivityPendingIntent);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            RemoteInput remoteInput = new RemoteInput.Builder(AppConstant.KEY_TEXT_REPLY).setLabel("Reply").build();

            Intent replyIntent = new Intent(this,RemoteReceiverActivity.class);
            replyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent replyPandingIntent = PendingIntent.getActivity(this,10,replyIntent,PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_sms_notification,"Reply"
                    ,replyPandingIntent).addRemoteInput(remoteInput).build();
            builder.addAction(action);
        }

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
        notificationManagerCompat.notify(AppConstant.KAY_NOTIFICATION_ID,builder.build());
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Personal Notification";
            String description = "Include all the personal notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(AppConstant.KAY_CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
