package com.anukul.runtimepermissiondemo;

import android.content.Context;
import android.content.SharedPreferences;

public class PermissionUtil {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public PermissionUtil(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(AppConstant.KEY_PERMISSION_PREFERENCE,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void updatePermissionPreference(String permission){
        switch (permission){
            case "camera":
                editor.putBoolean(AppConstant.KEY_PERMISSION_CAMERA,true);
                editor.commit();
                break;
            case "storage":
                editor.putBoolean(AppConstant.KEY_PERMISSION_STORAGE,true);
                editor.commit();
                break;
            case "contacts":
                editor.putBoolean(AppConstant.KEY_PERMISSION_CONTACTS,true);
                editor.commit();
                break;
        }
    }

    public boolean checkPermissionPreference(String permission){
        boolean isShow = false;
        switch (permission){
            case "camera":
                isShow = sharedPreferences.getBoolean(AppConstant.KEY_PERMISSION_CAMERA,false);
                break;
            case "storage":
                isShow = sharedPreferences.getBoolean(AppConstant.KEY_PERMISSION_STORAGE,false);
                break;
            case "contacts":
                isShow = sharedPreferences.getBoolean(AppConstant.KEY_PERMISSION_CONTACTS,false);
                break;
        }
        return isShow;
    }
}
