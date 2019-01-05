package com.anukul.startactivityforresultcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertDataActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText valueOneEd;
    private EditText valueSecEd;
    private Button okBtn;
    private Button cancelBtn;

    private int value1;
    private int value2;
    private int ans;

    private int oprationCode;

    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        initView();

        intent = getIntent();
    }

    private void initView() {
        valueOneEd = findViewById(R.id.activity_insertData_valueOneEd);
        valueSecEd = findViewById(R.id.activity_insertData_valueSecEd);
        okBtn = findViewById(R.id.activity_insertData_okBtn);
        cancelBtn = findViewById(R.id.activity_insertData_cancelBtn);

        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        oprationCode = intent.getIntExtra(AppConstent.KEY_CODE,0);
        switch (v.getId()){
            case R.id.activity_insertData_okBtn:
                performTask(oprationCode);
                break;
            case R.id.activity_insertData_cancelBtn:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }

    private void performTask(int oprationCode) {
        value1 = Integer.parseInt(valueOneEd.getText().toString());
        value2 = Integer.parseInt(valueSecEd.getText().toString());

        switch (oprationCode){
            case 1:
                ans = value1 + value2;
                intent.putExtra(AppConstent.KEY_ADD,ans);
                intent.putExtra(AppConstent.KEY_CODE_RETURN_ADD,1);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case 2:
                ans = value1 - value2;
                intent.putExtra(AppConstent.KEY_SUB,ans);
                intent.putExtra(AppConstent.KEY_CODE_RETURN_SUB,2);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case 3:
                ans = value1 * value2;
                intent.putExtra(AppConstent.KEY_MUL,ans);
                intent.putExtra(AppConstent.KEY_CODE_RETURN_MUL,3);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case 4:
                ans = value1 / value2;
                intent.putExtra(AppConstent.KEY_DIV,ans);
                intent.putExtra(AppConstent.KEY_CODE_RETURN_DIV,4);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
