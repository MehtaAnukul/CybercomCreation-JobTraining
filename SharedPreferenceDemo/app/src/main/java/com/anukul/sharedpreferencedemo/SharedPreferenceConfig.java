package com.anukul.sharedpreferencedemo;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferenceConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(AppConstent.KEY_LOGIN_PREFERENCE,Context.MODE_PRIVATE);

    }
    public void writeLoginStatus(boolean status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(AppConstent.KEY_LOGIN_STATUS_PREFERENCE,status);
        editor.commit();
    }
    public boolean readLoginStatus(){
        boolean status = false;
        status = sharedPreferences.getBoolean(AppConstent.KEY_LOGIN_STATUS_PREFERENCE,false);
        return status;
    }
}
