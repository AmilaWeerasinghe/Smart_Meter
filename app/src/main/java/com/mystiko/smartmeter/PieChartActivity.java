package com.mystiko.smartmeter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class PieChartActivity extends AppCompatActivity {

    String MSerialNum;
    Connection con;
    String un,pass,db,ip;


    public static ArrayList<String> x_axis=new ArrayList<String>();
    public static ArrayList<String> y_axis=new ArrayList<String>();

    PieChartView pieChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        Intent intent = getIntent();
        MSerialNum = intent.getExtras().getString("mserial");

        ip = "209.132.252.15";
        db = "DB_A492B5_smartauto677";
        un = "DB_A492B5_smartauto677_admin";
        pass = "0662231015asd";

        try {

            con = connectionclass(un, pass, db, ip);

            String query = "select TIME,kWh from MeterReading where MSerial= '" + MSerialNum.toString() + "' ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                y_axis.add(rs.getString("kWh"));
                x_axis.add(rs.getString("TIME"));

            }

            pieChartView = findViewById(R.id.chart);

            List pieData = new ArrayList<>();
            pieData.add(new SliceValue(Float.parseFloat(y_axis.get(0)), Color.BLUE).setLabel("19/March"));
            pieData.add(new SliceValue(Float.parseFloat(y_axis.get(95)), Color.GRAY).setLabel("20/March"));
            pieData.add(new SliceValue(Float.parseFloat(y_axis.get(95*2)), Color.RED).setLabel("21/March"));
            pieData.add(new SliceValue(Float.parseFloat(y_axis.get(95*3)), Color.MAGENTA).setLabel("22/March"));
            pieData.add(new SliceValue(Float.parseFloat(y_axis.get(95*4)), Color.YELLOW).setLabel("23/March"));
            pieData.add(new SliceValue(Float.parseFloat(y_axis.get(95*5)), Color.GREEN).setLabel("24/March"));
            pieData.add(new SliceValue(Float.parseFloat(y_axis.get(94*6)), Color.WHITE).setLabel("25/March"));


            PieChartData pieChartData = new PieChartData(pieData);
            pieChartData.setHasLabels(true).setValueLabelTextSize(14);
            pieChartData.setHasCenterCircle(true).setCenterText1("Usage").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
            pieChartView.setPieChartData(pieChartData);

        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }


    }

    @SuppressLint("NewApi")
    public Connection connectionclass(String user, String password, String database, String server)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            ConnectionURL = "jdbc:jtds:sqlserver://sql7005.site4now.net;database=DB_A492B5_smartauto677;user=DB_A492B5_smartauto677_admin;password=0662231015asd";
//            ConnectionURL = "jdbc:jtds:sqlserver://192.168.1.9;database=msss;instance=SQLEXPRESS;Network Protocol=NamedPipes" ;


            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }

}