package com.lukewaugh.paradiddles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nextBtn, previousBtn, homeBtn;
        ViewFlipper viewFlipper;
        Button singlesBtn, doublesBtn, tripletsBtn,
                flamsBtn, dragsBtn, rollsBtn;

        //Buttons
        nextBtn     = (Button) findViewById(R.id.nextBtn);
        previousBtn = (Button) findViewById(R.id.previousBtn);
        singlesBtn  = (Button) findViewById(R.id.btnSingles);
        doublesBtn  = (Button) findViewById(R.id.btnDoubles);
        tripletsBtn = (Button) findViewById(R.id.btnTriplets);
        flamsBtn    = (Button) findViewById(R.id.btnFlams);
        dragsBtn    = (Button) findViewById(R.id.btnDrags);
        rollsBtn    = (Button) findViewById(R.id.btnRolls);
        //homeBtn     = (Button) findViewById(R.id.btnHome);


        //viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        findViewById(R.id.nextBtn).setOnClickListener(handler);
        findViewById(R.id.previousBtn).setOnClickListener(handler);

        findViewById(R.id.btnSingles).setOnClickListener(handler);
        findViewById(R.id.btnDoubles).setOnClickListener(handler);

        findViewById(R.id.btnTriplets).setOnClickListener(handler);
        findViewById(R.id.btnFlams).setOnClickListener(handler);

        findViewById(R.id.btnDrags).setOnClickListener(handler);
        findViewById(R.id.btnRolls).setOnClickListener(handler);

        //findViewById(R.id.btnHome).setOnClickListener(handler);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.nextBtn:
                    //viewFlipper.showNext();
                    break;
                case R.id.previousBtn:
                    //viewFlipper.showPrevious();
                    break;
                case R.id.btnSingles:
                    setContentView(R.layout.singles_main);
                    break;
                case R.id.btnDoubles:
                    setContentView(R.layout.doubles_main);
                    break;
                case R.id.btnHome:
                    setContentView(R.layout.activity_main);
                    break;
            }

        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MainActivity.this, MainActivity.class));
        finish();
    }

}
