package com.lukewaugh.paradiddles.authClasses;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.lukewaugh.paradiddles.MainActivity;
import com.lukewaugh.paradiddles.R;


public class LoginActivity extends AppCompatActivity  implements
        View.OnClickListener {

    Button buttonLogin;
    EditText username, password;

    UserLocal userLocal;
    AccountActivity accountActivity = new AccountActivity();

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


    @Override
    protected void onStart() {
        super.onStart();
        if (authUser()){
            displayUserDetails();
        } else {
            //startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

    }

    private void displayUserDetails() {
        User user = userLocal.getLoggedInUser();
        accountActivity.usernameText.setText(user.username);
        //Can't display int directly, have to pass string too
        accountActivity.ageText.setText(user.age + "");
        accountActivity.emailText.setText(user.email);

    }

    //Will return true if user is logged int
    private boolean authUser() {
        return userLocal.getUserLoggedIn();
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
        dialogBuilder.setMessage("Incorrect User Details.");
        dialogBuilder.setPositiveButton("Ok.", null);
        dialogBuilder.show();
    }
    private void logUserIn(User returnedUser) {
        userLocal.storeUserData(returnedUser);
        userLocal.setLoggedInUser(true);

        startActivity(new Intent(this, AccountActivity.class));

    }
}

