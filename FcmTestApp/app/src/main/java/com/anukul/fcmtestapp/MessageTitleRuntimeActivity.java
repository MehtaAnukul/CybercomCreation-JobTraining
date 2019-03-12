package com.anukul.fcmtestapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MessageTitleRuntimeActivity extends AppCompatActivity {
    private TextView messageTitleTv;
    private TextView messageTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //...........video5
        //..........
        setContentView(R.layout.activity_message_title_runtime);
        LocalBroadcastManager.getInstance(this).registerReceiver(mHandler,new IntentFilter("com.anukul.fcmtestapp_FCM_MESSAGE"));

        messageTitleTv = findViewById(R.id.activity_megTitleRuntime_titleTv);
        messageTv = findViewById(R.id.activity_megTitleRuntime_noNewMsgTv);

        if(getIntent().getExtras()!= null){
            for(String key : getIntent().getExtras().keySet()){
                if(key.equals("title")){
                    messageTitleTv.setText(getIntent().getExtras().getString(key));
                }else if(key.equals("message")){
                    messageTv.setText(getIntent().getExtras().getString(key));
                }
            }
        }
    }

    //....video5
    private BroadcastReceiver mHandler = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String title = intent.getStringExtra("title");
            String message = intent.getStringExtra("message");
            messageTitleTv.setText(title);
            messageTv.setText(message);

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mHandler);
    }

    //video5 */
}
