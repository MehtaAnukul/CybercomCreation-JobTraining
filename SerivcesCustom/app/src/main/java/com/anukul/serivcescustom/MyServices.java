package com.anukul.serivcescustom;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyServices extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Anukul","In onStartCommend, thread id:"+Thread.currentThread().getId());
        //once a services is started...there is no explicit way
        // of stopping a services so you can do stopSelf();
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

}
