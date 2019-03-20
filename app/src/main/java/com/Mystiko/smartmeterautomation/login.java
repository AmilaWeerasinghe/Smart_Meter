package com.Mystiko.smartmeterautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity implements View.OnClickListener {

    Button bLogin;
    EditText etUsername,etPassword;
    TextView tvRegisterLink;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        bLogin=(Button)findViewById(R.id.bLogin);
        tvRegisterLink=(TextView)findViewById(R.id.tvRegisterLink);
        bLogin.setOnClickListener(this);
        userLocalStore=new UserLocalStore(this);
    }

    @Override//switch case used to select between many buttons
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bLogin:

                User user=new User(null,null);
                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);
                break;


            case R.id.tvRegisterLink:
                startActivity(new Intent(this,register.class));//go to register page
                break;

        }

    }
}
