package com.anukul.preferencessettingdefaultvalue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;

    private TextView msgDeleteStatusTv;
    private TextView smsDeleteLimitTv;
    private TextView mmsDeleteLimitTv;
    private TextView smsDeliveryReportTv;
    private TextView mmsDeliveryReportTv;
    private Button readSettingBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);

        msgDeleteStatusTv = findViewById(R.id.activity_main_msgDeleteStatusTv);
        smsDeleteLimitTv = findViewById(R.id.activity_main_smsDeleteLimitTv);
        mmsDeleteLimitTv = findViewById(R.id.activity_main_mmsDeleteLimitTv);
        smsDeliveryReportTv = findViewById(R.id.activity_main_smsDeliveryReportTv);
        mmsDeliveryReportTv = findViewById(R.id.activity_main_mmsDeliveryReportTv);

        readSettingBtn = findViewById(R.id.activity_main_readSettingBtn);
        readSettingBtn.setOnClickListener(this);

        PreferenceManager.setDefaultValues(this,R.xml.preferences_group_setting,false);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.appbar_menu_settings:
                startActivity(new Intent(this,SetttingsActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_main_readSettingBtn:
                readSetting();
                break;
        }
    }


    private void readSetting() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        msgDeleteStatusTv.setText("Message Delete Stutas : " +String.valueOf(sharedPreferences.getBoolean(AppContact.PREF_DELETE_OLD_MESSAGE,false)));
        smsDeleteLimitTv.setText("SMS Delete Limit : "+sharedPreferences.getString(AppContact.PREF_SMS_DELETE_LIMIT,"0"));
        mmsDeleteLimitTv.setText("MMS Delete Limit : "+sharedPreferences.getString(AppContact.PREF_MMS_DELETE_LIMIT,"0"));

        smsDeliveryReportTv.setText("SMS Delivery Report : "+String.valueOf(sharedPreferences.getBoolean(AppContact.PREF_SMS_DELIVERY_REPORT,false)));
        mmsDeliveryReportTv.setText("MMS Delivery Report : "+String.valueOf(sharedPreferences.getBoolean(AppContact.PREF_MMS_DELIVERY_REPORT,false)));
    }
}
