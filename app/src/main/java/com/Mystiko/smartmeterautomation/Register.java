package com.Mystiko.smartmeterautomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    EditText Name,SurName,Age,Password,UserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void OnReg(View view){
        Name=(EditText)findViewById(R.id.EtName);
        SurName=(EditText)findViewById(R.id.EtSurName);
        Age=(EditText)findViewById(R.id.EtAge);
        UserName=(EditText)findViewById(R.id.EtUserName);
        Password=(EditText)findViewById(R.id.EtPassword);

        String username = UserName.getText().toString();
        String password = Password.getText().toString();
        String age = Age.getText().toString();
        String surname= SurName.getText().toString();
        String name = Name.getText().toString();

        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,name,surname,age, username, password);

    }
}
