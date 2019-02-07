package com.anukul.runtimepermissiondemo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button requestAllPermissionBtn;
    private Button cameraBtn;
    private Button externalStorageBtn;
    private Button readContactsbtn;

    private PermissionUtil permissionUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionUtil = new PermissionUtil(this);

        requestAllPermissionBtn = findViewById(R.id.activity_main_requestAllPermissionBtn);
        cameraBtn = findViewById(R.id.activity_main_cameraBtn);
        externalStorageBtn = findViewById(R.id.activity_main_externalStorageBtn);
        readContactsbtn = findViewById(R.id.activity_main_readContactBtn);

        requestAllPermissionBtn.setOnClickListener(this);
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
            case AppConstant.KEY_TXT_STORAGE:
                status = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case AppConstant.KEY_TXT_CONTACTS:
                status = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
                break;
        }
        return status;
    }

    //if the permissison not granted
    //request New Permission
    private void requestPermission(int permission) {
        switch (permission) {
            case AppConstant.KEY_TXT_CAMERA:
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA}, AppConstant.KEY_REQUEST_CAMERA);
                break;
            case AppConstant.KEY_TXT_STORAGE:
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, AppConstant.KEY_REQUEST_STORAGE);
                break;
            case AppConstant.KEY_TXT_CONTACTS:
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_CONTACTS}, AppConstant.KEY_REQUEST_CONTACTS);
                break;
        }
    }

    //display permission explanation
    private void showPermissionExplanation(final int permission) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (permission == AppConstant.KEY_TXT_CAMERA) {
            builder.setTitle("Camera Permission Needed..");
            builder.setMessage("This app need to access your device Camera..Please Allow");
        } else if (permission == AppConstant.KEY_TXT_STORAGE) {
            builder.setTitle("Storage Permission Needed..");
            builder.setMessage("This app need to access your device Storage..Please Allow");
        } else if (permission == AppConstant.KEY_TXT_CONTACTS) {
            builder.setTitle("Contacts Permission Needed..");
            builder.setMessage("This app need to access your contacts..Please Allow");
        }

        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (permission == AppConstant.KEY_TXT_CAMERA) {
                    requestPermission(AppConstant.KEY_TXT_CAMERA);
                } else if (permission == AppConstant.KEY_TXT_STORAGE) {
                    requestPermission(AppConstant.KEY_TXT_STORAGE);
                } else if (permission == AppConstant.KEY_TXT_CONTACTS) {
                    requestPermission(AppConstant.KEY_TXT_CONTACTS);
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_requestAllPermissionBtn:
                requestAllPermission();
                break;
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

    private void requestGroupPermission(ArrayList<String> permission){
        //convert permisssion arraylist into string array value using toArray func
        String permissionList[] = new String[permission.size()];
        permission.toArray(permissionList);

        ActivityCompat.requestPermissions(MainActivity.this,permissionList,AppConstant.KEY_REQUEST_GROUP_PERMISSION);
    }
    private void requestAllPermission() {
            ArrayList<String> permissionNeeded = new ArrayList<>();
            ArrayList<String> permissionAvailable = new ArrayList<>();

            permissionAvailable.add(Manifest.permission.CAMERA);
            permissionAvailable.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            permissionAvailable.add(Manifest.permission.READ_CONTACTS);

        //check which r permission is already granted or not
        //if not granted so add permission in permissionNeeded arraylist
        for (String permission : permissionAvailable) {
            if(ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                permissionNeeded.add(permission);
            }

        }
        requestGroupPermission(permissionNeeded);
    }

    private void readContacts() {
        //return true becuase this permission is not granted before
        if (checkPermission(AppConstant.KEY_TXT_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CAMERA)) {
                showPermissionExplanation(AppConstant.KEY_TXT_CONTACTS);
                //return true
                //if the user click don't ask again than return false
            }
            //check request 1st time or not
            else if (!permissionUtil.checkPermissionPreference("contacts")) {
                requestPermission(AppConstant.KEY_TXT_CONTACTS);
                permissionUtil.updatePermissionPreference("contacts");
            } else {
                Toast.makeText(this, "please allow contacts permission in your app setting", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                intent.setData(uri);
                this.startActivity(intent);
            }

        }
        else {
            Toast.makeText(this, "You have contacts permission", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra(AppConstant.KEY_MESSAGE, "Now you can read contact available on this device..");
            startActivity(intent);
        }


    }

    private void rightToStorage() {
        //return true becuase this permission is not granted before
        if (checkPermission(AppConstant.KEY_TXT_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CAMERA)) {
                showPermissionExplanation(AppConstant.KEY_TXT_STORAGE);
                //return true
                //if the user click don't ask again than return false
            }
            //check request 1st time or not
            else if (!permissionUtil.checkPermissionPreference("storage")) {
                requestPermission(AppConstant.KEY_TXT_STORAGE);
                permissionUtil.updatePermissionPreference("storage");
            } else {
                Toast.makeText(this, "please allow storage permission in your app setting", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                intent.setData(uri);
                this.startActivity(intent);
            }

        }
        else {
            Toast.makeText(this, "You have storage permission", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra(AppConstant.KEY_MESSAGE, "Now you can write to the storage of this device..");
            startActivity(intent);
        }

    }

    private void openCamera() {
        //return true becuase this permission is not granted before
        if (checkPermission(AppConstant.KEY_TXT_CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CAMERA)) {
                showPermissionExplanation(AppConstant.KEY_TXT_CAMERA);
                //return true
                //if the user click don't ask again than return false
            }
            //check request 1st time or not
            else if (!permissionUtil.checkPermissionPreference("camera")) {
                requestPermission(AppConstant.KEY_TXT_CAMERA);
                permissionUtil.updatePermissionPreference("camera");
            } else {
                Toast.makeText(this, "please allow camera permission in your app setting", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                intent.setData(uri);
                this.startActivity(intent);
            }

        }
        else {
            Toast.makeText(this, "You have camera permission", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra(AppConstant.KEY_MESSAGE, "You can now take a photo and video..");
            startActivity(intent);
        }

    }
}
