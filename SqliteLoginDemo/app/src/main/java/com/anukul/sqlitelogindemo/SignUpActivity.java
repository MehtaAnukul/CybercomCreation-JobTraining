package com.anukul.sqlitelogindemo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {

    private EditText nameEd;
    private EditText lastNameEd;
    private EditText phoneEd;
    private EditText emailEd;
    private EditText passwordEd;
    private RadioGroup radioGroup;
    private Button signUpBtn;
    private TextView loginTv;
    private String gender;

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
        lastNameEd = findViewById(R.id.activity_signUp_lastNameEd);
        phoneEd = findViewById(R.id.activity_signUp_phoneEd);
        emailEd = findViewById(R.id.activity_signUp_emailEd);
        passwordEd = findViewById(R.id.activity_signUp_passwordEd);
        radioGroup = findViewById(R.id.activity_signUp_radioGroup);
        signUpBtn = findViewById(R.id.activity_signUp_signUpBtn);
        loginTv = findViewById(R.id.activity_signUp_loginTv);

       loginTv.setOnClickListener(this);
       signUpBtn.setOnClickListener(this);

       radioGroup.setOnCheckedChangeListener(this);

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
                finish();
                break;
        }
    }
    private void insertContact() {
       // int conteactId = Integer.parseInt(contactIdEd.getText().toString().trim());

        String name = nameEd.getText().toString().trim();
        String lastName = lastNameEd.getText().toString().trim();
        String phone = phoneEd.getText().toString().trim();
        String email = emailEd.getText().toString().trim();
        String password = passwordEd.getText().toString().trim();


        if (TextUtils.isEmpty(name)) {
            nameEd.setError("Please enter name");
        }else if (TextUtils.isEmpty(lastName)) {
            lastNameEd.setError("please enter lastName");
        }  else if (TextUtils.isEmpty(phone)) {
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

            contactDbHelper.insertContact(new ContactModel(name,lastName,phone,email,password,gender), sqLiteDatabase);


            contactDbHelper.close();
            nameEd.setText("");
            lastNameEd.setText("");
            phoneEd.setText("");
            emailEd.setText("");
            passwordEd.setText("");
            //Toast.makeText(getActivity(), total_time + "Mili Second taken by the record User are Added", Toast.LENGTH_LONG).show();
             Toast.makeText(this, "Contact saved sucessfully", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        final RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());

        if (radioButton.getText().equals("Male")) {
            gender = radioButton.getText().toString();
            Toast.makeText(this, "Male"+gender, Toast.LENGTH_SHORT).show();
        } else if (radioButton.getText().equals("Female")){
            gender = radioButton.getText().toString();
            Toast.makeText(this, "Female"+gender, Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "" + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
}
