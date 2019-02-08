package com.anukul.cameravideodemo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button captureVideoBtn;
    private Button playVideoBtn;

    private Uri videoUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        captureVideoBtn = findViewById(R.id.activity_main_captureVideoBtn);
        playVideoBtn = findViewById(R.id.activity_main_playVideoBtn);
        
        captureVideoBtn.setOnClickListener(this);
        playVideoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_main_captureVideoBtn:
                captureVideo();
                break;
            case R.id.activity_main_playVideoBtn:
                playVideo();
                break;
        }
    }

    private void playVideo() {
        Intent playIntent = new Intent(this,DisplayVideoActivity.class);
        playIntent.putExtra(AppConstant.KEY_VIDEO_URI,videoUri.toString());
        startActivity(playIntent);
    }

    private void captureVideo() {
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if(videoIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(videoIntent,AppConstant.KEY_VIDEO_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == AppConstant.KEY_VIDEO_REQUEST_CODE && resultCode == RESULT_OK){
            videoUri = data.getData();
        }
    }
}
