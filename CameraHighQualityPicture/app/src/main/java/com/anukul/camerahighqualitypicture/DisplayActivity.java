package com.anukul.camerahighqualitypicture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class DisplayActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        imageView = findViewById(R.id.activity_display_imageView);

        Bitmap bitmap = BitmapFactory.decodeFile(getIntent().getStringExtra(AppConstant.KEY_IMAGE_PATH));
        imageView.setImageBitmap(bitmap);
    }
}
