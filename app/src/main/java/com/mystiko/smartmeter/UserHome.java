package com.mystiko.smartmeter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class UserHome extends AppCompatActivity implements View.OnClickListener {
    CardView Bill,Profile,Update,Graph,Contact,Map;
    String MSerialStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Intent intent = getIntent();
        MSerialStr = intent.getExtras().getString("mserial");
        Bill=(CardView)findViewById(R.id.BillCard);
        Profile=(CardView)findViewById(R.id.ProfileCard);
        Update=(CardView)findViewById(R.id.UpdateCard);
        Graph=(CardView)findViewById(R.id.graph);
        Contact=(CardView)findViewById(R.id.ContactPage);
        Map=(CardView)findViewById(R.id.mappage);
        Bill.setOnClickListener(this);
        Profile.setOnClickListener(this);
        Update.setOnClickListener(this);
        Graph.setOnClickListener(this);
        Contact.setOnClickListener(this);
        Map.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==Bill){
            finish();
            Intent j = new Intent(this, UsageActivity.class);
            j.putExtra("mserial",MSerialStr);
            startActivity(j);

        }
        if(view==Profile){
            finish();
            Intent k = new Intent(this, ForGrid.class);
            k.putExtra("mserial", MSerialStr);
            startActivity(k);
        }
        if(view==Update){
            finish();
            Intent l = new Intent(this, UpdatePage.class);
            l.putExtra("mserial", MSerialStr);
            startActivity(l);
        }
        if(view==Graph){
            finish();
            Intent o = new Intent(this, MapsActivity.class);
           // o.putExtra("mserial", MSerialStr);
            startActivity(o);
        }
        if(view==Contact){
            finish();
            Intent q = new Intent(this, navigation.class);
            startActivity(q);
        }
        if(view==Map){
            finish();
            Intent g = new Intent(this, MapsActivity.class);
            startActivity(g);
        }




    }
}
