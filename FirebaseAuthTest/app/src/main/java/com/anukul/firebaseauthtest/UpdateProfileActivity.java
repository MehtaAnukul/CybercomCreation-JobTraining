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
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;


public class UpdateProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText firstNameEd;
    private EditText lastNameEd;
    private EditText passwordEd;
    private EditText emailEd;
    private EditText phoneNoEd;
    private EditText addressEd;


    private Button updateBtn;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference databaseReferencel;
    private FirebaseDatabase firebaseDatabase;
    private String userId;

    private ImageView profileImg;
    private Uri filePath;
    FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferencel = firebaseDatabase.getReference();

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        initView();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        userId = user.getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //Toast.makeText(UpdateProfileActivity.this, "Successfully signed "+user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    // Toast.makeText(UpdateProfileActivity.this, "Successfully signed out", Toast.LENGTH_SHORT).show();
                }
            }
        };

        databaseReferencel.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            UserModel userModel = new UserModel();
            userModel.setFirstName(ds.child(userId).getValue(UserModel.class).getFirstName());
            userModel.setLastName(ds.child(userId).getValue(UserModel.class).getLastName());
            userModel.setEmail(ds.child(userId).getValue(UserModel.class).getEmail());
            userModel.setPhoneNo(ds.child(userId).getValue(UserModel.class).getPhoneNo());
            userModel.setAddress(ds.child(userId).getValue(UserModel.class).getAddress());
            userModel.setProfileUrl(ds.child(userId).getValue(UserModel.class).getProfileUrl());

            firstNameEd.setText(userModel.getFirstName());
            lastNameEd.setText(userModel.getLastName());
            emailEd.setText(userModel.getEmail());
            phoneNoEd.setText(userModel.getPhoneNo());
            addressEd.setText(userModel.getAddress());

            // Toast.makeText(this, "" + userModel.getProfileUrl(), Toast.LENGTH_SHORT).show();
            int radius = 30;
            Glide.with(UpdateProfileActivity.this)
                    .load(userModel.getProfileUrl())
                    .transform(new RoundedCorners(radius))
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(profileImg);

        }
    }

    private void initView() {
        firstNameEd = findViewById(R.id.activity_updateProfile_firstNameEd);
        lastNameEd = findViewById(R.id.activity_updateProfile_lastNameEd);
        emailEd = findViewById(R.id.activity_updateProfile_emailEd);
        phoneNoEd = findViewById(R.id.activity_updateProfile_phoneNoEd);
        addressEd = findViewById(R.id.activity_updateProfile_addressEd);
        profileImg = findViewById(R.id.activity_updateProfile_icon);
        updateBtn = findViewById(R.id.activity_updateProfile_updateBtn);

        progressDialog = new ProgressDialog(UpdateProfileActivity.this);
        profileImg.setOnClickListener(this);
        updateBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_updateProfile_updateBtn:
                updateData();
                break;
            case R.id.activity_updateProfile_icon:
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
                profileImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateData() {
        final String firstName = firstNameEd.getText().toString();
        final String lastName = lastNameEd.getText().toString();
        final String email = emailEd.getText().toString();
        final String phoneNo = phoneNoEd.getText().toString();
        final String address = addressEd.getText().toString();

        if (TextUtils.isEmpty(firstName)) {
            firstNameEd.setError("Please enter firstName");
        } else if (TextUtils.isEmpty(lastName)) {
            lastNameEd.setError("Please enter lastName");
        } else if (TextUtils.isEmpty(email)) {
            emailEd.setError("Please enter email");
        } else if (TextUtils.isEmpty(phoneNo)) {
            phoneNoEd.setError("please enter phoneNo");
        } else if (TextUtils.isEmpty(address)) {
            addressEd.setError("please enter address");
        } else {

            if (filePath != null) {
                uploadImage();
            } else {
                //progressDialog.dismiss();
            }
        }
    }

    private void uploadImage() {
        final String firstName = firstNameEd.getText().toString();
        final String lastName = lastNameEd.getText().toString();
        final String email = emailEd.getText().toString();
        final String phoneNo = phoneNoEd.getText().toString();
        final String address = addressEd.getText().toString();

        if (filePath != null) {
            progressDialog.setMessage("Update in progress..");
            progressDialog.show();
            final StorageReference sref = storageReference.child("profilePics/" + UUID.randomUUID().toString());

            sref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    sref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            insertData(firstName, lastName, email, phoneNo, address, uri.toString());
                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UpdateProfileActivity.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "Please Select profile Pic", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }

    }

    private void insertData(String firstName, String lastName, String email,
                             String phoneNo, String address, String imgUrl) {

        final String uuid = firebaseAuth.getCurrentUser().getUid();
        if (!uuid.isEmpty()) {
            databaseReferencel.child(AppConstant.FIREBASE_NODE_USERS)
                    .child(uuid).child("firstName").setValue(firstName);
            databaseReferencel.child(AppConstant.FIREBASE_NODE_USERS)
                    .child(uuid).child("lastName").setValue(lastName);
            databaseReferencel.child(AppConstant.FIREBASE_NODE_USERS)
                    .child(uuid).child("email").setValue(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    progressDialog.dismiss();
                    // Toast.makeText(UpdateProfileActivity.this, "Data Changed. ", Toast.LENGTH_SHORT).show();
                }
            });
            databaseReferencel.child(AppConstant.FIREBASE_NODE_USERS)
                    .child(uuid).child("phoneNo").setValue(phoneNo);
            databaseReferencel.child(AppConstant.FIREBASE_NODE_USERS)
                    .child(uuid).child("address").setValue(address);
            databaseReferencel.child(AppConstant.FIREBASE_NODE_USERS)
                    .child(uuid).child("profileUrl").setValue(imgUrl);
              /*databaseReferencel.child(AppConstant.FIREBASE_NODE_USERS)
                    .child(uuid).setValue(new UserModel(firstName,lastName,email,phoneNo,address,imgUrl),
                            new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                    if (databaseError != null) {
                                        Toast.makeText(UpdateProfileActivity.this, "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                                    } else {
                                        //progressDialog.setMessage("Data inserted...wait");
                                        Toast.makeText(UpdateProfileActivity.this, "Data updated", Toast.LENGTH_SHORT).show();
                                        // progressDialog.dismiss();

                                    }
                                }
                            });*/
            firstNameEd.setText("");
            lastNameEd.setText("");
            emailEd.setText("");
            phoneNoEd.setText("");
            addressEd.setText("");
            Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();

            /*new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        Toast.makeText(UpdateProfileActivity.this, "Error :" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateProfileActivity.this, "Data updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            };*/


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.appbar_menu_logout:
                firebaseAuth.signOut();
                final Intent gotoLogin = new Intent(UpdateProfileActivity.this, LoginActivity.class);
                startActivity(gotoLogin);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
