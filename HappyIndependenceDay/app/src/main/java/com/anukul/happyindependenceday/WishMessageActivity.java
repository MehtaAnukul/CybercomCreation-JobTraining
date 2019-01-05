package com.anukul.happyindependenceday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class WishMessageActivity extends AppCompatActivity {
    private TextView wishMessageTv;

    private Intent intent;

    private String wishMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_message);
        wishMessageTv = findViewById(R.id.activity_wish_message_Tv);

        intent = getIntent();

        wishMessage = intent.getStringExtra(AppConstant.KEY_WISHMSG);

        wishMessageTv.setText(wishMessage + "");
    }
}
