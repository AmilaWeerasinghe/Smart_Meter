package com.Mystiko.smartmeterautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity  {
    Button LogInB;
    EditText UsernameEt,PasswordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       LogInB=(Button)findViewById(R.id.BLogIn);
       UsernameEt=(EditText)findViewById(R.id.EtUserame);
       PasswordEt=(EditText)findViewById(R.id.EtPassword);
    }

    public void OnLogin(View view){
        String username=UsernameEt.getText().toString();
        String password=PasswordEt.getText().toString();
        String type="login ";
        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute(type,username,password);

    }


}
