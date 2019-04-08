package com.mystiko.smartmeter;


import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UpdatePage extends AppCompatActivity {
    private ViewPager viewPager;
    private SlideAdapter myadapter;
    String MSerialStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        myadapter = new SlideAdapter(this);
        viewPager.setAdapter(myadapter);

    }


    public void back1(View view) {
        finish();
        Intent r = new Intent(this, UserHome.class);
        r.putExtra("mserial", MSerialStr);
        startActivity(r);
    }
}
