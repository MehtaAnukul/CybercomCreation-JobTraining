package com.anukul.cameravideodemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class DisplayVideoActivity extends AppCompatActivity {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_video_play);

        videoView = findViewById(R.id.activity_display_VideoView);

        Uri videoUri = Uri.parse(getIntent().getExtras().getString(AppConstant.KEY_VIDEO_URI));
        videoView.setVideoURI(videoUri);
        videoView.start();
    }
}
