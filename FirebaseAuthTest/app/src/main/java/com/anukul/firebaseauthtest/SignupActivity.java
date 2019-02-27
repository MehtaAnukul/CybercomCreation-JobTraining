package com.anukul.firebaseauthtest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText firstNameEd;
    private EditText lastNameEd;
    private EditText emailEd;
    private EditText passwordEd;
    private EditText phoneNoEd;
    private EditText addressEd;
    private Button signUpBtn;
    private TextView loginTv;

    private FirebaseAuth firebaseAuth;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        initView();
    }

    private void initView() {
        firstNameEd = findViewById(R.id.activity_signup_firstNameEd);
        lastNameEd = findViewById(R.id.activity_signup_lastNameEd);
        emailEd = findViewById(R.id.activity_signup_emailEd);
        passwordEd = findViewById(R.id.activity_signup_passwordEd);
        phoneNoEd = findViewById(R.id.activity_signup_phoneNoEd);
        addressEd = findViewById(R.id.activity_signup_addressEd);

        signUpBtn = findViewById(R.id.activity_signup_signupbtn);
        loginTv = findViewById(R.id.activity_signup_alreadyAccount);

        signUpBtn.setOnClickListener(this);
        loginTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_signup_signupbtn:
                userRegistration();
                break;
            case R.id.activity_signup_alreadyAccount:
                finish();
                break;

        }
    }

    private void userRegistration() {
        final String firstName = firstNameEd.getText().toString().trim();
        final String lastName = lastNameEd.getText().toString().trim();
        final String email = emailEd.getText().toString().trim();
        final String password = passwordEd.getText().toString().trim();
        final String phoneNo = phoneNoEd.getText().toString().trim();
        final String address = addressEd.getText().toString().trim();

        if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
                email.isEmpty() || password.isEmpty() || phoneNo.isEmpty() ||
                address.isEmpty()){
            Toast.makeText(this, "Please Enter the Details", Toast.LENGTH_SHORT).show();
           // progressDialog.dismiss();
        }else {
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                //progressDialog.dismiss();
                                Toast.makeText(SignupActivity.this, "Successfully signUp... ", Toast.LENGTH_SHORT).show();
                                insertData(firstName,lastName,email,password,phoneNo,address);
                                //userDataInsertIntoDatabase(userName,userEmail,userPassword);

                                Intent gotoLoginActivty = new Intent(SignupActivity.this,LoginActivity.class);
                                startActivity(gotoLoginActivty);
                            }else {
                                //progressDialog.dismiss();
                                Toast.makeText(SignupActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void insertData(String firstName, String lastName, String email, String password, String phoneNo, String address) {
        databaseReference.child(AppConstant.FIREBASE_NODE_USERS)
                .child(firebaseAuth.getUid())
                .setValue(new UserModel(firstName,lastName,email,password,phoneNo,address),
                        new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                if (databaseError != null){
                                    Toast.makeText(SignupActivity.this, ""+ databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                }else {
                                    //progressDialog.setMessage("Data inserted...wait");
                                    Toast.makeText(SignupActivity.this, "Data Inserted in Database", Toast.LENGTH_SHORT).show();
                                    //progressDialog.dismiss();
                                }
                            }
                        });
    }
}
