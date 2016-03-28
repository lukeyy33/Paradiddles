package com.lukewaugh.paradiddles.authClasses;

import com.lukewaugh.paradiddles.R;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.EditText;




public class LoginActivity extends AppCompatActivity  implements
                                            View.OnClickListener {

    private Button buttonLogin;
    private EditText username;
    private EditText password;

    private UserLocal userLocal;






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.loginPassword);
        buttonLogin = (Button) findViewById(R.id.loginBtn);

        buttonLogin.setOnClickListener(this);

        userLocal = new UserLocal(this);
    }


    @Override
    public void onClick(View v) {
        switch ((v.getId())) {
            case R.id.loginBtn:
                String newUser = username.getText().toString();
                String newPassword = password.getText().toString();
                User user = new User(newUser, newPassword);

                authenticateDetails(user);
                break;
        }
    }

    private void authenticateDetails(User user) {
        ServerHandler serverRequests = new ServerHandler(this);
        serverRequests.getUserData(user, new GetUser() {
            @Override
            public void processDone(User returnedUser) {
                if (returnedUser == null) {
                    errorMessage();
                } else {
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void errorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
        dialogBuilder.setMessage("Incorrect Username or Password.");
        dialogBuilder.setPositiveButton("Ok.", null);
        dialogBuilder.show();
    }
    private void logUserIn(User returnedUser) {
        userLocal.storeUserData(returnedUser);
        userLocal.setLoggedInUser(true);

        startActivity(new Intent(this, AccountActivity.class));
    }
}

