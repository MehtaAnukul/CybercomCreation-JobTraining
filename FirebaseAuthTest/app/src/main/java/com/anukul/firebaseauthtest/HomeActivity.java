package com.anukul.firebaseauthtest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private String userId;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = findViewById(R.id.listView);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        userId = user.getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Toast.makeText(HomeActivity.this, "Successfully signed "+user.getEmail(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(HomeActivity.this, "Successfully signed out", Toast.LENGTH_SHORT).show();
                }
            }
        };

        databaseReference.addValueEventListener(new ValueEventListener() {
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
            ArrayList<String> stringArrayList = new ArrayList<>();
            stringArrayList.add(userModel.getFirstName());
            stringArrayList.add(userModel.getLastName());
            stringArrayList.add(userModel.getEmail());
            stringArrayList.add(userModel.getPhoneNo());
            stringArrayList.add(userModel.getAddress());
            ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,stringArrayList);
            listView.setAdapter(arrayAdapter);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
