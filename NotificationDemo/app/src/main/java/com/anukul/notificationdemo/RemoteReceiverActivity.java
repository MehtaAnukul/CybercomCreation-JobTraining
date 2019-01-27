package com.anukul.notificationdemo;

import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RemoteReceiverActivity extends AppCompatActivity {
    private TextView remoteMegTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_receiver);

        remoteMegTv = findViewById(R.id.activitiy_remoteReceiver_remoteTv);

        Bundle remoteReply = RemoteInput.getResultsFromIntent(getIntent());

        if(remoteReply != null){
            String message = remoteReply.getCharSequence(AppConstant.KEY_TEXT_REPLY).toString();
            remoteMegTv.setText(message);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(AppConstant.KAY_NOTIFICATION_ID);
    }
}
