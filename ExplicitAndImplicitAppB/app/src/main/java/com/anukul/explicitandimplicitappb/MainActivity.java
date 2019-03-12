package com.anukul.explicitandimplicitappb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
        private Button sendBtn;

    //String ANUKUL_ACTION = "com.anukul.explicitandimplicitappb.ANUKUL_ACTION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = findViewById(R.id.activity_main_sendBtn);

        sendBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //intent.setAction(ANUKUL_ACTION);
                //intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra("Message","This is Simple message");
                startActivity(intent);
            }
        });
    }
}
