package com.anukul.fcmdbdemo;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener /*RadioGroup.OnCheckedChangeListener*/ {
    private TextView headingTv;
    private EditText headingEd;
    private Button submitbtn;
    private RadioButton redRadioBtn;
    private RadioButton blueRadioBtn;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference mHeadingReference = databaseReference.child("heading");
    private DatabaseReference mFontColorReference = databaseReference.child("fontcolor");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headingTv = findViewById(R.id.activity_main_headingTv);
        headingEd = findViewById(R.id.activity_main_headingEd);
        submitbtn = findViewById(R.id.activity_main_submitBtn);
        redRadioBtn = findViewById(R.id.activity_main_redBtn);
        blueRadioBtn = findViewById(R.id.activity_main_blueBtn);

        submitbtn.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_main_submitBtn:
                String heading = headingEd.getText().toString();
                mHeadingReference.setValue(heading);
                headingEd.setText("");
                break;
        }
    }

   /* @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId){
            case R.id.activity_main_redBtn:
                    mFontColorReference.setValue("red");
                break;
            case R.id.activity_main_blueBtn:
                    mFontColorReference.setValue("blue");
                break;
        }

    }*/

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.getValue(String.class) != null){
            String key = dataSnapshot.getKey();

            if(key.equals("heading")){
                String heading = dataSnapshot.getValue(String.class);
                headingTv.setText(heading);
            }else if(key.equals("fontcolor")){
                String color = dataSnapshot.getValue(String.class);

                if(color.equals("red")){
                    headingTv.setTextColor(ContextCompat.getColor(this,R.color.red));
                    redRadioBtn.setChecked(true);
                }else if(color.equals("blue")){
                    headingTv.setTextColor(ContextCompat.getColor(this,R.color.blue));
                    blueRadioBtn.setChecked(true);
                }
            }
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mHeadingReference.addValueEventListener(this);
        mFontColorReference.addValueEventListener(this);
    }

    public void onRadioButtonClicked(View view) {
        switch (view.getId()){
            case R.id.activity_main_redBtn:
                mFontColorReference.setValue("red");
                break;
            case R.id.activity_main_blueBtn:
                mFontColorReference.setValue("blue");
                break;
        }
    }
}
