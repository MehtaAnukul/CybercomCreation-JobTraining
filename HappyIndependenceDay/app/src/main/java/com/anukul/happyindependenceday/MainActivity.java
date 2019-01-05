package com.anukul.happyindependenceday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText wishMessageEd;
    private Button sendBtn;

    private String wishMeassge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wishMessageEd = findViewById(R.id.activity_main_wishMessageEd);
        sendBtn = findViewById(R.id.activity_main_sendBtn);

        sendBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        wishMeassge = wishMessageEd.getText().toString().trim();
        Intent gotoWishMesssgeActivity = new Intent(this, WishMessageActivity.class);
        gotoWishMesssgeActivity.putExtra(AppConstant.KEY_WISHMSG, wishMeassge);
        startActivity(gotoWishMesssgeActivity);
    }
}
