package com.anukul.swipeviewdemo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private DemoFragmentCollectionAdapter demoFragmentCollectionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.activity_main_viewPager);

        demoFragmentCollectionAdapter = new DemoFragmentCollectionAdapter(getSupportFragmentManager());
        viewPager.setAdapter(demoFragmentCollectionAdapter);

    }
}
