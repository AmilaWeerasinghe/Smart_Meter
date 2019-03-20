package com.Mystiko.smartmeterautomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    EditText etUsername,etPassword,etName,etAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etName=(EditText)findViewById(R.id.etName);
        etAge=(EditText)findViewById(R.id.etAge);
        bRegister=(Button)findViewById(R.id.bRegister);
        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bRegister:

                break;
        }
    }
}
