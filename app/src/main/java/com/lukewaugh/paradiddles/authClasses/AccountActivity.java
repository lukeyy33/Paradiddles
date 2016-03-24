package com.lukewaugh.paradiddles.authClasses;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lukewaugh.paradiddles.MainActivity;
import com.lukewaugh.paradiddles.R;
import com.lukewaugh.paradiddles.SettingsActivity;


public class AccountActivity extends AppCompatActivity implements
        View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {


    UserLocal userLocal;
    TextView usernameText, emailText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_management);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, myToolbar, R.string.navigationDrawerOpen, R.string.navigationDrawerClose);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        usernameText = (TextView) findViewById(R.id.accManagementUsername);
        emailText = (TextView) findViewById(R.id.accManagementEmail);

        userLocal = new UserLocal(this);
    }


    @Override
    public void onClick(View v) {
        switch ((v.getId())) {
            case R.id.logoutBtn:
                userLocal.clearData();
                userLocal.setLoggedInUser(false);

                startActivity(new Intent(this, MainActivity.class));
                break;

        }
    }

    private void displayUserDetails() {
        User user = userLocal.getLoggedInUser();
        usernameText.setText(user.username);
        emailText.setText(user.email);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (authUser()){
            displayUserDetails();
        } else {
            //startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

    }

    //Will return true if user is logged int
    private boolean authUser() {
        return userLocal.getUserLoggedIn();
    }

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            int id = item.getItemId();
            switch (id) {
                case R.id.settings:
                    startActivity(new Intent(this, SettingsActivity.class));
                    break;
                case R.id.account:
                /*Todo made a drop down for a login &
                    register option
                */
                    startActivity(new Intent(this, LoginActivity.class));
                    break;
                case R.id.home:
                    startActivity(new Intent(this, MainActivity.class));
                    break;
            }
            return true;
        }
}
