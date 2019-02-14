package com.anukul.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.anukul.broadcastdemo.app.ContactDbConstant;

public class NumberReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            ContactDbHelper contactDbHelper = new ContactDbHelper(context);
            SQLiteDatabase sqLiteDatabase = contactDbHelper.getWritableDatabase();
            contactDbHelper.insertNumber(number,sqLiteDatabase);
            contactDbHelper.close();
        }
        Intent intent1 = new Intent(ContactDbConstant.UPDATE_UI_FILTER);
        context.sendBroadcast(intent);
    }
}
