package com.Mystiko.smartmeterautomation;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
            case R.id.btvRegisterLink:
                startActivity(new Intent(this,register.class));//go to register page
                break;
            case R.id.bLogin:
                String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();

                User user=new User(null,null);

                authenticate(user);

                break;




        }

    }

    private void authenticate(User user){
        ServerRequests serverRequests=new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(user, new GetUserCallBack() {
            @Override
            public void done(User returnedUser) {
                if(returnedUser==null){
                    showErrorMsg();
                }
                else{
                    logUserIn(returnedUser);
                }

            }
        });


    }
    private  void showErrorMsg(){
        AlertDialog.Builder dialogBulider=new AlertDialog.Builder(login.this);
        dialogBulider.setMessage("Incorrect User details");
        dialogBulider.setPositiveButton("OK",null);
        dialogBulider.show();
    }
    private void logUserIn(User returneduser){
        userLocalStore.storeUserData(returneduser);
        userLocalStore.setUserLoggedIn(true);

        startActivity(new Intent(this,MainActivity.class));

    }
}
