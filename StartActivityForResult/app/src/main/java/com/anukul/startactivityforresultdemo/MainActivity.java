package com.anukul.startactivityforresultdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView nameTv;
    private Button gotoSecondActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        gotoSecondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoInsertDataActivityintent = new Intent(MainActivity.this,InsertDataActivity.class);
                startActivityForResult(gotoInsertDataActivityintent,100);
            }
        });
    }

    private void initView() {
        nameTv = findViewById(R.id.activity_main_nameTv);
        gotoSecondActivityBtn = findViewById(R.id.activity_main_gotoInsertDataActivityBtn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100){
            if(resultCode == RESULT_OK){
                if(data != null){
                    final String name = data.getStringExtra(AppConstant.KEY_NAME);
                    nameTv.setText(name+"");
                }
            }
        }
    }
}
