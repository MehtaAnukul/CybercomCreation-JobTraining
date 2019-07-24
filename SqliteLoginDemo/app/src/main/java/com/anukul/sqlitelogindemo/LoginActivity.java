package com.anukul.sqlitelogindemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText loginEmailEd;
    private EditText loginPasswordEd;
    private Button loginBtn;
    private TextView signUpTv;

    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        sqLiteOpenHelper = new ContactDbHelper(this);
        sqLiteDatabase = sqLiteOpenHelper.getReadableDatabase();

        loginEmailEd = findViewById(R.id.activity_login_emailEd);
        loginPasswordEd = findViewById(R.id.activity_login_passwordEd);
        loginBtn = findViewById(R.id.activity_login_loginBtn);
        signUpTv = findViewById(R.id.activity_login_signUpTv);

        signUpTv.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_login_loginBtn:
                login();
                break;
            case R.id.activity_login_signUpTv:
                final Intent intent = new Intent(this,SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void login() {
        String loginEmail = loginEmailEd.getText().toString().trim();
        String loginPassword = loginPasswordEd.getText().toString().trim();


        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ContactDbConstant.CONTACT_TABALE_NAME + " WHERE " + ContactDbConstant.CONTACT_COLUMN_EMAIL + "=? AND " + ContactDbConstant.CONTACT_COLUMN_PASSWORD + "=?" , new String[]{loginEmail,loginPassword});
        Log.e("LoginActivity",""+cursor);
        if(cursor != null){
            if(cursor.getCount() > 0){
                cursor.moveToNext();
                Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
