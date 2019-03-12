package com.anukul.fcmtestapp;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.FirebaseApp;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button sendRegTokenBtn;
    private TextView msgAppearhereTv;
    private String message = "No new message";
    String app_server_url = "http://192.168.43.30/fcmtest/fcm_insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        msgAppearhereTv = findViewById(R.id.activity_main_msgAppearhereTv);
        sendRegTokenBtn = findViewById(R.id.activity_main_sendRegTokenBtn);
        sendRegTokenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRegToken();
            }
        });

        if(getIntent().getExtras() != null){
            message = getIntent().getExtras().getString(AppConstant.KEY_MESSAGE);

            if(message == null){
                message = "No new message";
            }
        }

        msgAppearhereTv.setText("Message :"+message);
    }

    private void sendRegToken() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(AppConstant.KEY_FCM_PREF,Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(AppConstant.KEY_FCM_TOKEN,"");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, app_server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Token send successfully", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Not send !!", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("fcm_token",token);
                return params;
            }
        };
        MySingleTon.getmInstance(MainActivity.this).addToRequestque(stringRequest);
    }
}
