package com.lukewaugh.paradiddles.authClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.lukewaugh.paradiddles.MainActivity;
import com.lukewaugh.paradiddles.R;

public class RegisterActivity extends AppCompatActivity  implements
        View.OnClickListener {

    private Button buttonRegister;
    private EditText textEmail;
    private EditText textPassword;
    private EditText textUsername;
    private TextView goToMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        buttonRegister = (Button) findViewById(R.id.registerBtn);
        goToMain = (TextView) findViewById(R.id.continueWithNoAccount);
        assert goToMain != null;
        goToMain.setOnClickListener(this);


        textEmail = (EditText) findViewById(R.id.emailEditText);
        textPassword = (EditText) findViewById(R.id.loginPassword);
        textUsername = (EditText) findViewById(R.id.registerUsername);


        buttonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())) {
            case R.id.registerBtn:
                String name = textUsername.getText().toString();
                String password = textPassword.getText().toString();
                String email = textEmail.getText().toString();

                User user = new User(name, password, email);
                registerUser(user);

                break;
            case R.id.continueWithNoAccount:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
    private void registerUser(User user) {
        ServerHandler serverHandler = new ServerHandler(this);
        serverHandler.setUserData(user, new GetUser() {
            @Override
            public void processDone(User returnedUser) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }


}
