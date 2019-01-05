package com.anukul.startactivityforresultdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertDataActivity extends AppCompatActivity {
    private EditText nameEd;
    private Button backToMainActivityBtn;

    private String name;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        initView();

        intent = getIntent();

        backToMainActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEd.getText().toString().trim();
                intent.putExtra(AppConstant.KEY_NAME,name);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void initView() {
        nameEd = findViewById(R.id.activity_insertData_nameEd);
        backToMainActivityBtn = findViewById(R.id.activity_insertData_backToMainActivityBtn);

    }
}
