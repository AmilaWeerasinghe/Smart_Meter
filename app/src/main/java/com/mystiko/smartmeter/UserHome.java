package com.mystiko.smartmeter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class UserHome extends AppCompatActivity implements View.OnClickListener {
    CardView Bill,Profile;
    String MSerialStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Intent intent = getIntent();
        MSerialStr = intent.getExtras().getString("mserial");
        Bill=(CardView)findViewById(R.id.BillCard);
        Profile=(CardView)findViewById(R.id.ProfileCard);
        Bill.setOnClickListener(this);
        Profile.setOnClickListener(this);







    }

    @Override
    public void onClick(View view) {
        if(view==Bill){
            finish();
            Intent j = new Intent(this, BillPage.class);
            j.putExtra("mserial",MSerialStr);
            startActivity(j);

        }
        if(view==Profile){
            finish();
            Intent k = new Intent(this, ProfilePage.class);
            k.putExtra("mserial", MSerialStr);
            startActivity(k);
        }


    }
}
