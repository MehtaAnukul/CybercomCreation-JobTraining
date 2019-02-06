package com.anukul.runtimepermissiondemo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private PermissionUtil permissionUtil;

    private Button cameraBtn;
    private Button externalStorageBtn;
    private Button readContactsbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionUtil = new PermissionUtil(this);

        cameraBtn = findViewById(R.id.activity_main_cameraBtn);
        externalStorageBtn = findViewById(R.id.activity_main_externalStorageBtn);
        readContactsbtn = findViewById(R.id.activity_main_readContactBtn);

        cameraBtn.setOnClickListener(this);
        externalStorageBtn.setOnClickListener(this);
        readContactsbtn.setOnClickListener(this);

    }

    //check permission is already granted or not
    //if permission is already granted so return permission granted
    //if permission is not requested before return permission denied
    private int checkPermission(int permission) {
        int status = PackageManager.PERMISSION_DENIED;
        switch (permission) {
            case AppConstant.KEY_TXT_CAMERA:
                status = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                break;
            case AppConstant.KEY_REQUEST_STORAGE:
                status = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case AppConstant.KEY_REQUEST_CONTACTS:
                status = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS);
                break;
        }
        return status;
    }

    //request New Permission
    private void requestPermission(int permission) {
        switch (permission) {
            case AppConstant.KEY_REQUEST_CAMERA:
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA}, AppConstant.KEY_REQUEST_CAMERA);
                break;
            case AppConstant.KEY_REQUEST_STORAGE:
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, AppConstant.KEY_REQUEST_STORAGE);
                break;
            case AppConstant.KEY_REQUEST_CONTACTS:
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_CONTACTS}, AppConstant.KEY_REQUEST_CONTACTS);
                break;
        }
    }

    private void showPermissionExplanation(final int permission) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (permission == AppConstant.KEY_TXT_CAMERA) {
            builder.setTitle("Camera Permission Needed..");
            builder.setMessage("This app need to access your device Camera..Please Allow");
        }
        else if (permission == AppConstant.KEY_REQUEST_STORAGE) {
            builder.setTitle("Storage Permission Needed..");
            builder.setMessage("This app need to access your device Storage..Please Allow");
        }
        else if (permission == AppConstant.KEY_REQUEST_CONTACTS) {
            builder.setTitle("Contacts Permission Needed..");
            builder.setMessage("This app need to access your contacts..Please Allow");
        }

        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(permission == AppConstant.KEY_TXT_CAMERA){
                    requestPermission(AppConstant.KEY_TXT_CAMERA);
                }else if (permission == AppConstant.KEY_TXT_STORAGE){
                    requestPermission(AppConstant.KEY_TXT_STORAGE);
                }else if(permission == AppConstant.KEY_TXT_CONTACTS){
                    requestPermission(AppConstant.KEY_TXT_CONTACTS);
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_cameraBtn:
                openCamera();
                break;
            case R.id.activity_main_externalStorageBtn:
                rightToStorage();
                break;
            case R.id.activity_main_readContactBtn:
                readContacts();
                break;
        }
    }

    private void readContacts() {

    }

    private void rightToStorage() {
    }

    private void openCamera() {

    }
}
