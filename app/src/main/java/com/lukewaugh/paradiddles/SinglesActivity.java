package com.lukewaugh.paradiddles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SinglesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singles);


        Button homeBtn = (Button) findViewById(R.id.btnHome);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        SingleSwipeAdapter singleSwipeAdapter = new SingleSwipeAdapter(this);
        viewPager.setAdapter(singleSwipeAdapter);

    }

}
