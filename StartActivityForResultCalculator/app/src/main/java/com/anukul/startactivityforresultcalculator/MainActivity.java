package com.anukul.startactivityforresultcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addBtn;
    private Button subBtn;
    private Button mulBtn;
    private Button divBtn;

    private TextView answerTv;
    private int returnCode;
    private int result;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        addBtn = findViewById(R.id.activity_main_addBtn);
        subBtn = findViewById(R.id.activity_main_subBtn);
        mulBtn = findViewById(R.id.activity_main_mulBtn);
        divBtn = findViewById(R.id.activity_main_divBtn);

        answerTv = findViewById(R.id.activity_main_answerTv);

        addBtn.setOnClickListener(this);
        subBtn.setOnClickListener(this);
        mulBtn.setOnClickListener(this);
        divBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_main_addBtn:
                Toast.makeText(this, "Addition", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,InsertDataActivity.class);
                intent.putExtra(AppConstent.KEY_CODE,1);
                startActivityForResult(intent,100);
                break;
            case R.id.activity_main_subBtn:
                Toast.makeText(this, "Subtraction", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,InsertDataActivity.class);
                intent.putExtra(AppConstent.KEY_CODE,2);
                startActivityForResult(intent,200);
                break;
            case R.id.activity_main_mulBtn:
                Toast.makeText(this, "Multiplication", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,InsertDataActivity.class);
                intent.putExtra(AppConstent.KEY_CODE,3);
                startActivityForResult(intent,300);
                break;
            case R.id.activity_main_divBtn:
                Toast.makeText(this, "Division", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,InsertDataActivity.class);
                intent.putExtra(AppConstent.KEY_CODE,4);
                startActivityForResult(intent,400);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100){
            if(resultCode == RESULT_OK){
                if (data != null){
                    returnCode = data.getIntExtra(AppConstent.KEY_CODE_RETURN_ADD,0);
                    displayOutput(returnCode,data);
                }
            }
        }
        else if(requestCode == 200){
            if(resultCode == RESULT_OK){
                if (data != null){
                    returnCode = data.getIntExtra(AppConstent.KEY_CODE_RETURN_SUB,0);
                    displayOutput(returnCode,data);
                }
            }
        }
        else if(requestCode == 300){
            if(resultCode == RESULT_OK){
                if (data != null){
                    returnCode = data.getIntExtra(AppConstent.KEY_CODE_RETURN_MUL,0);
                    displayOutput(returnCode,data);
                }
            }
        }
        else if(requestCode == 400){
            if(resultCode == RESULT_OK){
                if (data != null){
                    returnCode = data.getIntExtra(AppConstent.KEY_CODE_RETURN_DIV,0);
                    displayOutput(returnCode,data);
                }
            }
        }
    }

    private void displayOutput(int returnCode, Intent data) {

        switch (returnCode){
            case 1:
                result = data.getIntExtra(AppConstent.KEY_ADD,0);
                answerTv.setText(result + "");
                break;
            case 2:
                result = data.getIntExtra(AppConstent.KEY_SUB,0);
                answerTv.setText(result + "");
                break;
            case 3:
                result = data.getIntExtra(AppConstent.KEY_MUL,0);
                answerTv.setText(result + "");
                break;
            case 4:
                result = data.getIntExtra(AppConstent.KEY_DIV,0);
                answerTv.setText(result + "");
                break;
        }
    }
}
