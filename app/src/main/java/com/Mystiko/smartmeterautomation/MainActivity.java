package com.Mystiko.smartmeterautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bLogout;
    EditText etUsername,etName,etAge;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername=(EditText)findViewById(R.id.etUsername);
        etName=(EditText)findViewById(R.id.etName);
        etAge=(EditText)findViewById(R.id.etAge);
        bLogout=(Button)findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);
        userLocalStore=new UserLocalStore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(authenticate()==true){
            displaydetails();
        }
        else{
            startActivity(new Intent(MainActivity.this,login.class));//go to login class
        }
    }
    public void displaydetails(){
        User user=userLocalStore.getLogedInUser();

        etUsername.setText(user.username);
        etAge.setText(user.age);
        etName.setText(user.name);

    }
    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bLogout:
                userLocalStore.ClearUserData();
                userLocalStore.setUserLoggedIn(false);

                startActivity(new Intent(this,login.class));//go to login page

                break;

        }
    }
}
