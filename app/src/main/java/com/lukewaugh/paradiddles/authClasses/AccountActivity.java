package com.lukewaugh.paradiddles.authClasses;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lukewaugh.paradiddles.MainActivity;
import com.lukewaugh.paradiddles.R;



public class AccountActivity extends AppCompatActivity implements View.OnClickListener {

    Button logoutButton, loginButton;
    UserLocal userLocal;
    TextView usernameText, emailText, ageText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_management);

        logoutButton = (Button) findViewById(R.id.logoutBtn);
        logoutButton.setOnClickListener(this);

        loginButton = (Button) findViewById(R.id.accountLoginBtn);
        loginButton.setOnClickListener(this);

        usernameText = (TextView) findViewById(R.id.accManagementUsername);
        emailText = (TextView) findViewById(R.id.accManagementEmail);
        ageText = (TextView) findViewById(R.id.accManagementAge);
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
            case R.id.accountLoginBtn:
                Intent intent = new Intent (AccountActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
