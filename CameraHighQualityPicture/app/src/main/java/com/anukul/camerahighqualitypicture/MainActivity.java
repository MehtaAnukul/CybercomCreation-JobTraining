package com.anukul.camerahighqualitypicture;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button captureImgBtn;
    private Button displayImgBtn;

    String currentImagePath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        captureImgBtn = findViewById(R.id.activity_main_captureImgBtn);
        displayImgBtn = findViewById(R.id.activity_main_displayImgBtn);
        
        captureImgBtn.setOnClickListener(this);
        displayImgBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_main_captureImgBtn:
                captureImage();
                break;
            case R.id.activity_main_displayImgBtn:
                displayImage();
                break;
        }
    }

    private void displayImage() {
        Intent intent = new Intent(this,DisplayActivity.class);
        intent.putExtra(AppConstant.KEY_IMAGE_PATH,currentImagePath);
        startActivity(intent);

    }

    private void captureImage() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(cameraIntent.resolveActivity(getPackageManager()) != null){
            File imageFile = null;

            try {
                imageFile = getImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(imageFile != null){
                Uri imageUri = FileProvider.getUriForFile(this,"com.example.android.fileprovider",imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(cameraIntent,AppConstant.KEY_IMAGE_REQUESTCODE);
            }
        }
    }

    private File getImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "jpg_"+timeStamp+"_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File imageFile = File.createTempFile(imageName,".jpg",storageDir); //this method thow the io exception
        currentImagePath = imageFile.getAbsolutePath();
        return imageFile;
    }
}
