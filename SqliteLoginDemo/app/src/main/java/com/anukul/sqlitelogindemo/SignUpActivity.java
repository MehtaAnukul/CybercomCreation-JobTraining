package com.anukul.sqlitelogindemo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEd;
    private EditText phoneEd;
    private EditText emailEd;
    private EditText passwordEd;
    private Button signUpBtn;
    private TextView loginTv;

    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Stetho.initializeWithDefaults(this);

        initView();
    }
    private void initView() {
        nameEd = findViewById(R.id.activity_signUp_nameEd);
        phoneEd = findViewById(R.id.activity_signUp_phoneEd);
        emailEd = findViewById(R.id.activity_signUp_emailEd);
        passwordEd = findViewById(R.id.activity_signUp_passwordEd);
        signUpBtn = findViewById(R.id.activity_signUp_signUpBtn);
        loginTv = findViewById(R.id.activity_signUp_loginTv);

       loginTv.setOnClickListener(this);
       signUpBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_signUp_signUpBtn:
                insertContact();
                break;
            case R.id.activity_signUp_loginTv:
                final Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void insertContact() {
       // int conteactId = Integer.parseInt(contactIdEd.getText().toString().trim());

        String name = nameEd.getText().toString().trim();
        String phone = phoneEd.getText().toString().trim();
        String email = emailEd.getText().toString().trim();
        String password = passwordEd.getText().toString().trim();


        if (TextUtils.isEmpty(name)) {
            nameEd.setError("Please enter name");
        } else if (TextUtils.isEmpty(phone)) {
            phoneEd.setError("please enter phoneNo");
        } else if (TextUtils.isEmpty(email)) {
            emailEd.setError("please enter email");
        }else if (TextUtils.isEmpty(password)) {
            passwordEd.setError("please enter password");
        }
        else {


            // ContactModel contactModel = new ContactModel();
            ContactDbHelper contactDbHelper = new ContactDbHelper(this);
            sqLiteDatabase = contactDbHelper.getWritableDatabase();

            contactDbHelper.insertContact(new ContactModel(name ,phone, email,password), sqLiteDatabase);


            contactDbHelper.close();
            nameEd.setText("");
            phoneEd.setText("");
            emailEd.setText("");
            passwordEd.setText("");
            //Toast.makeText(getActivity(), total_time + "Mili Second taken by the record User are Added", Toast.LENGTH_LONG).show();
             Toast.makeText(this, "Contact saved sucessfully", Toast.LENGTH_SHORT).show();

        }
    }
}
