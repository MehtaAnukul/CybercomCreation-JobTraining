package com.anukul.firebaseauthtest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UpdateProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText firstNameEd;
    private EditText lastNameEd;
    private EditText emailEd;
    private EditText phoneNoEd;
    private EditText addressEd;
    private Button updateBtn;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference databaseReferencel;
    private FirebaseDatabase firebaseDatabase;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferencel = firebaseDatabase.getReference();

        initView();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        userId = user.getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    //Toast.makeText(UpdateProfileActivity.this, "Successfully signed "+user.getEmail(), Toast.LENGTH_SHORT).show();
                }else{
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
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            UserModel userModel = new UserModel();
            userModel.setFirstName(ds.child(userId).getValue(UserModel.class).getFirstName());
            userModel.setLastName(ds.child(userId).getValue(UserModel.class).getLastName());
            userModel.setEmail(ds.child(userId).getValue(UserModel.class).getEmail());
            userModel.setPhoneNo(ds.child(userId).getValue(UserModel.class).getPhoneNo());
            userModel.setAddress(ds.child(userId).getValue(UserModel.class).getAddress());
            //userModel.setFirstName(ds.child(userId).getValue(UserModel.class).getFirstName());

            firstNameEd.setText(userModel.getFirstName());
            lastNameEd.setText(userModel.getLastName());
            emailEd.setText(userModel.getEmail());
            phoneNoEd.setText(userModel.getPhoneNo());
            addressEd.setText(userModel.getAddress());


        }
    }

    private void initView() {
        firstNameEd = findViewById(R.id.activity_updateProfile_firstNameEd);
        lastNameEd = findViewById(R.id.activity_updateProfile_lastNameEd);
        emailEd = findViewById(R.id.activity_updateProfile_emailEd);
        phoneNoEd = findViewById(R.id.activity_updateProfile_phoneNoEd);
        addressEd = findViewById(R.id.activity_updateProfile_addressEd);

        updateBtn = findViewById(R.id.activity_updateProfile_updateBtn);

        updateBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_updateProfile_updateBtn:
                updateData();
                break;
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
        }
        else{
            final String uuid = firebaseAuth.getCurrentUser().getUid();
            if(!uuid.isEmpty()){
                databaseReferencel.child(AppConstant.FIREBASE_NODE_USERS)
                        .child(uuid).child("firstName").setValue(firstName);
                databaseReferencel.child(AppConstant.FIREBASE_NODE_USERS)
                        .child(uuid).child("lastName").setValue(lastName);
                databaseReferencel.child(AppConstant.FIREBASE_NODE_USERS)
                        .child(uuid).child("email").setValue(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       // Toast.makeText(UpdateProfileActivity.this, "Data Changed. ", Toast.LENGTH_SHORT).show();
                    }
                });
                databaseReferencel.child(AppConstant.FIREBASE_NODE_USERS)
                        .child(uuid).child("phoneNo").setValue(phoneNo);
                databaseReferencel.child(AppConstant.FIREBASE_NODE_USERS)
                        .child(uuid).child("address").setValue(address);

                firstNameEd.setText("");
                lastNameEd.setText("");
                emailEd.setText("");
                phoneNoEd.setText("");
                addressEd.setText("");
                Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();

                new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if(databaseError != null){
                            Toast.makeText(UpdateProfileActivity.this, "Error :" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(UpdateProfileActivity.this, "Data updated", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                };



            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.appbar_menu_logout:
                firebaseAuth.signOut();
                final Intent gotoLogin = new Intent(UpdateProfileActivity.this,LoginActivity.class);
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
