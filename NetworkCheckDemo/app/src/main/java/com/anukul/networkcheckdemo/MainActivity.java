package com.anukul.networkcheckdemo;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button networkStatusBtn;
    private Button networkTypeBtn;
    private CoordinatorLayout coordinatorLayout;
    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        coordinatorLayout = findViewById(R.id.activity_main_coordinatorLayout);
        networkStatusBtn = findViewById(R.id.activity_main_networkStatusBtn);
        networkTypeBtn = findViewById(R.id.activity_main_networkTypeBtn);

        networkStatusBtn.setOnClickListener(this);
        networkTypeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_main_networkStatusBtn:
                checkNetworkStatus();
                break;
            case R.id.activity_main_networkTypeBtn:
                checkNetworkType();
                break;
        }
    }

    private void checkNetworkType() {
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifiInfo.isConnected()){
            informUser("Connected to WiFi");
        }else if (mobileInfo.isConnected()){
            informUser("Connected to Mobile Data");
        }else {
            informUser("No network availabel");
        }

    }

    private void checkNetworkStatus() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){
            informUser("Connected to Internet");
        }else {
            informUser("No internet connection availabel");
        }
    }

    private void informUser(String message){
        Snackbar.make(coordinatorLayout,message,Snackbar.LENGTH_SHORT).show();
    }
}
