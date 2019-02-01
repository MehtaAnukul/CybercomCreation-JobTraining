package com.anukul.snackbardemo;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import static com.anukul.snackbardemo.AppConstant.KEY_OFFLINE_MESSAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button clickBtn;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickBtn = findViewById(R.id.activity_main_clickBtn);
        coordinatorLayout = findViewById(R.id.activity_main_coordinatorLayout);
        clickBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_clickBtn:
                displayPopUp();
                break;
        }
    }

    private void displayPopUp() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, KEY_OFFLINE_MESSAGE, Snackbar.LENGTH_SHORT);
        snackbar.show();

        //Snackbar.make(coordinatorLayout, KEY_OFFLINE_MESSAGE, Snackbar.LENGTH_INDEFINITE).show();

    }
}