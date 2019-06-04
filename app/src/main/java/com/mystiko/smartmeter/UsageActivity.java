package com.mystiko.smartmeter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UsageActivity extends AppCompatActivity {

    private Button BtnMove1 ;
    private Button BtnMove2 ;
    private Button BtnMove3 ;
    private Button back;
    String MSerial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage);
        Intent intent = getIntent();
        MSerial = intent.getExtras().getString("mserial");

        BtnMove1 = findViewById(R.id.button1);
        BtnMove1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                moveToActivityLine();
            }
        });

        BtnMove2 = findViewById(R.id.button2);
        BtnMove2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                moveToActivityBar();
            }
        });

        BtnMove3 = findViewById(R.id.button3);
        BtnMove3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                moveToActivityPie();
            }
        });

    }

    private void moveToActivityLine(){
        Intent startNewActivityLine = new Intent(this, LineGraphActivity.class);
        startNewActivityLine.putExtra("mserial",MSerial);
        startActivity(startNewActivityLine);
    }

    private void moveToActivityBar(){
        Intent startNewActivityBar = new Intent(this, BarGraphActivity.class);
        startNewActivityBar.putExtra("mserial",MSerial);
        startActivity(startNewActivityBar);
    }

    private void moveToActivityPie(){
        Intent startNewActivityPie = new Intent(this, PieChartActivity.class);
        startNewActivityPie.putExtra("mserial",MSerial);
        startActivity(startNewActivityPie);
    }

    public void backfromUsage(View view) {
        finish();
        Intent n = new Intent(this, UserHome.class);
        n.putExtra("mserial", MSerial);
        startActivity(n);
    }

}
