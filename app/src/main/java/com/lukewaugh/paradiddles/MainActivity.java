package com.lukewaugh.paradiddles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] drawerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        listView = (ListView) findViewById(R.id.drawerList);
        drawerItems = getResources().getStringArray(R.array.navDrawerItems);

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, drawerItems));



        Button singlesBtn  = (Button) findViewById(R.id.btnSingles);
        singlesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SinglesActivity.class);
                startActivityForResult(intent, 0);

            }
        });
        Button doublesBtn  = (Button) findViewById(R.id.btnDoubles);
        doublesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DoublesActivity.class);
                startActivityForResult(intent, 0);

            }
        });
        Button tripletsBtn = (Button) findViewById(R.id.btnTriplets);
        tripletsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TripletsActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        Button flamsBtn = (Button) findViewById(R.id.btnFlams);
        flamsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FlamsActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        Button dragsBtn = (Button) findViewById(R.id.btnDrags);
        dragsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DragsActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        Button rollsBtn    = (Button) findViewById(R.id.btnRolls);
        rollsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RollsActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

}
