package com.anukul.firebaseauthtest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText firstNameEd;
    private EditText lastNameEd;
    private EditText emailEd;
    private EditText passwordEd;
    private EditText phoneNoEd;
    private EditText addressEd;
    private Button signUpBtn;
    private TextView loginTv;

    private ImageView imageViewIcon;
    private Uri filePath;

    private FirebaseAuth firebaseAuth;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        initView();
    }

    private void initView() {
        firstNameEd = findViewById(R.id.activity_signup_firstNameEd);
        lastNameEd = findViewById(R.id.activity_signup_lastNameEd);
        emailEd = findViewById(R.id.activity_signup_emailEd);
        passwordEd = findViewById(R.id.activity_signup_passwordEd);
        phoneNoEd = findViewById(R.id.activity_signup_phoneNoEd);
        addressEd = findViewById(R.id.activity_signup_addressEd);
        imageViewIcon = findViewById(R.id.activity_signup_icon);

        progressDialog = new ProgressDialog(SignupActivity.this);

        signUpBtn = findViewById(R.id.activity_signup_signupbtn);
        loginTv = findViewById(R.id.activity_signup_alreadyAccount);

        imageViewIcon.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);
        loginTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_signup_signupbtn:
                userRegistration();
                break;
            case R.id.activity_signup_alreadyAccount:
                finish();
                break;
            case R.id.activity_signup_icon:
                chooseImage();
                break;

        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), AppConstant.KEY_PICK_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstant.KEY_PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageViewIcon.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void userRegistration() {


        final String firstName = firstNameEd.getText().toString().trim();
        final String lastName = lastNameEd.getText().toString().trim();
        final String email = emailEd.getText().toString().trim();
        final String password = passwordEd.getText().toString().trim();
        final String phoneNo = phoneNoEd.getText().toString().trim();
        final String address = addressEd.getText().toString().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
                password.isEmpty() || phoneNo.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Please Enter the Details", Toast.LENGTH_SHORT).show();
             progressDialog.dismiss();
        } else {


            if (filePath != null) {

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressDialog.setMessage("Image Uploading...");
                                    progressDialog.show();
                                    uploadImage();

                                    //userDataInsertIntoDatabase(userName,userEmail,userPassword);

                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(SignupActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                progressDialog.dismiss();
                Toast.makeText(this, "Please Select Profile PIC", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void uploadImage() {

        final String firstName = firstNameEd.getText().toString().trim();
        final String lastName = lastNameEd.getText().toString().trim();
        final String email = emailEd.getText().toString().trim();
        final String password = passwordEd.getText().toString().trim();
        final String phoneNo = phoneNoEd.getText().toString().trim();
        final String address = addressEd.getText().toString().trim();


        if (filePath != null) {
            final StorageReference sref = storageReference.child("profilePics/" + UUID.randomUUID().toString());

            sref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    sref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            insertData(firstName, lastName, email, password, phoneNo, address, uri.toString());
                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignupActivity.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "Please Select profile Pic", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }

    }

    private void insertData(String firstName, String lastName, String email,
                            String password, String phoneNo, String address, String imgUrl) {


        databaseReference.child(AppConstant.FIREBASE_NODE_USERS)
                .child(firebaseAuth.getUid())
                .setValue(new UserModel(firstName, lastName, email, password, phoneNo, address, imgUrl),
                        new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                if (databaseError != null) {
                                    Toast.makeText(SignupActivity.this, "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                                } else {
                                    progressDialog.setMessage("Data inserted...wait");
                                    Toast.makeText(SignupActivity.this, "Successfully Signup", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                    Intent gotoLoginActivty = new Intent(SignupActivity.this,
                                            LoginActivity.class);
                                    startActivity(gotoLoginActivty);
                                    firebaseAuth.signOut();
                                    finish();
                                }
                            }
                        });
    }
}
