package com.anukul.explicitandimplicitdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button shareBtn;
    private Button sendBtn;

    //String ANUKUL_ACTION = "android.intent.action.SEND";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shareBtn = findViewById(R.id.activity_main_sharePostBtn);
        sendBtn = findViewById(R.id.activity_main_sendMailBtn);

        shareBtn.setOnClickListener(this);
        sendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_main_sharePostBtn:
                sharePost();
                break;
            case R.id.activity_main_sendMailBtn:
                sendMail();
                break;
        }
    }

    private void sendMail() {
        Intent intent = new Intent();
       intent.setAction(Intent.ACTION_SEND);
       // intent.setAction(ANUKUL_ACTION);
        intent.setType("text/plain");
        intent.putExtra("Message","This is a simple message");
        startActivity(intent);
    }

    private void sharePost() {
        Intent intent = new Intent(this,SharePostActivity.class);
        startActivity(intent);
    }
}
