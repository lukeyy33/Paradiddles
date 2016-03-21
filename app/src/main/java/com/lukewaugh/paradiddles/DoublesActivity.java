package com.lukewaugh.paradiddles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DoublesActivity extends AppCompatActivity {
    ViewPager viewPager;
    DoubleSwipeAdapter doubleSwipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doubles);


        Button homeBtn = (Button) findViewById(R.id.btnHome);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        doubleSwipeAdapter = new DoubleSwipeAdapter(this);
        viewPager.setAdapter(doubleSwipeAdapter);

    }

}
