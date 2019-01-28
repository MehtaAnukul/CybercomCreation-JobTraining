package com.anukul.notificationprogressbardemo;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button notificationProgressBtn;
    private Button notificationImgBtn;
    private Button notificationLongTextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationProgressBtn = findViewById(R.id.activitiy_main_notificationProgressBtn);
        notificationImgBtn = findViewById(R.id.activity_main_notificationImgBtn);
        notificationLongTextBtn = findViewById(R.id.activity_main_notificationLongTextBtn);

        notificationProgressBtn.setOnClickListener(this);
        notificationImgBtn.setOnClickListener(this);
        notificationLongTextBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activitiy_main_notificationProgressBtn:
                openNotificationProgress();
                break;
            case R.id.activity_main_notificationImgBtn:
                openNotificationImg();
                break;
            case R.id.activity_main_notificationLongTextBtn:
                openNotificationLongText();
                break;
        }
    }

    private void openNotificationLongText() {
        createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,AppConstant.KAY_CHANNET_ID);
        builder.setSmallIcon(R.drawable.ic_download);
        builder.setContentTitle("LongText Notification");
        builder.setContentText("This is long Text");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.notificationtext)));

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(AppConstant.KAY_NOTIFICATION_ID,builder.build());

    }

    private void openNotificationImg() {
        createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,AppConstant.KAY_CHANNET_ID);
        builder.setSmallIcon(R.drawable.ic_download);
        builder.setContentTitle("Image Notification");
        builder.setContentText("Qutar Airways Img");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.qatar_aieways);
        builder.setLargeIcon(bitmap);
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null));

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(AppConstant.KAY_NOTIFICATION_ID,builder.build());


    }

    private void openNotificationProgress() {
        createNotificationChannel();

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this,AppConstant.KAY_CHANNET_ID);
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

    //it's working 8.0 above version
    private void createNotificationChannel()  {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Personal Notification";
            String description = "Include all the personal notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(AppConstant.KAY_CHANNET_ID,name,importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
