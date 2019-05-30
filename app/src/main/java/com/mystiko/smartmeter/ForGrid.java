package com.mystiko.smartmeter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.SimpleAdapter;
public class ForGrid extends AppCompatActivity {
    String MSerialStr;
    GridView gridview;
    ArrayList<String> arrayList;
    String ip, db, un, passwords;
    Connection connect;
    PreparedStatement stmt;
    ResultSet rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridy);
        Intent intent = getIntent();
        MSerialStr = intent.getExtras().getString("mserial");
        ip = "209.132.252.15";
        un = "DB_A492B5_smartauto677_admin";
        passwords = "0662231015asd";
        db = "DB_A492B5_smartauto677";
        gridview = (GridView) findViewById(R.id.gridy);
        connect = CONN(un, passwords, db, ip);
        String query = "select * from Meter where MeterSerial= '"+MSerialStr+"'";
        try {
            connect = CONN(un, passwords, db, ip);
            Statement statement = connect.createStatement();
            rs = statement.executeQuery(query);
            List<Map<String, String>> data = null;
            data = new ArrayList<Map<String, String>>();
            while (rs.next()) {
                Map<String, String> datanum = new HashMap<String, String>();
                datanum.put("A", rs.getString("UserDefinedAddress"));
                datanum.put("B", rs.getString("Csc"));
                datanum.put("C",rs.getString("RelayStatus"));
                datanum.put("D",rs.getString("RelayStatusDate"));
                data.add(datanum);
            }
            String[] from = { "A", "B","C","D" };
            int[] views = { R.id.txtcountry, R.id.txtcontinent,R.id.Relay ,R.id.RalyDate};
            final SimpleAdapter ADA = new SimpleAdapter(ForGrid.this, data, R.layout.templateforgrid, from, views);
            gridview.setAdapter(ADA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @SuppressLint("NewApi")
    private Connection CONN(String _user, String _pass, String _DB,
                            String _server) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnURL = "jdbc:jtds:sqlserver://sql7005.site4now.net;database=DB_A492B5_smartauto677;user=DB_A492B5_smartauto677_admin;password=0662231015asd";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }

    public void backuser(View view) {
        finish();
        Intent n = new Intent(this, UserHome.class);
        n.putExtra("mserial", MSerialStr);
        startActivity(n);

    }
}
