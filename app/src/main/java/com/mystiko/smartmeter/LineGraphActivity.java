package com.mystiko.smartmeter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.sql.Connection;
import android.annotation.SuppressLint;
//import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import android.widget.Button;
//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class LineGraphActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    String MSerialNum;
    Connection con;
    String un,pass,db,ip;


    public static ArrayList<String> x_axis=new ArrayList<String>();
    public static ArrayList<String> y_axis=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graph);

        Intent intent = getIntent();
        MSerialNum = intent.getExtras().getString("mserial");

        ip = "205.144.171.102";
        db = "DB_A51024_smartmeter";
        un = "DB_A51024_smartmeter_admin";
        pass = "0662231015asd";

        try

        {

            con = connectionclass(un, pass, db, ip);

            String query = "select TIME,kWh from MeterReading where MSerial= '" + MSerialNum.toString() + "' ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                y_axis.add(rs.getString("kWh"));
                x_axis.add(rs.getString("TIME"));

            }


            GraphView graph = (GraphView) findViewById(R.id.graph);
            series = new LineGraphSeries<DataPoint>(data());


            graph.addSeries(series);
            graph.getViewport().setMinX(19);
            graph.getViewport().setMaxX(25);
            graph.getViewport().setMinY(findMin());
            graph.getViewport().setMaxY(findMax());
            graph.getGridLabelRenderer().setHorizontalAxisTitle("time    March");
            graph.getGridLabelRenderer().setVerticalAxisTitle("Usage/kWh");
            graph.getGridLabelRenderer().setNumVerticalLabels(10);
            graph.getGridLabelRenderer().setNumHorizontalLabels(7);

            graph.getViewport().setScrollable(true);
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setBackgroundColor(Color.WHITE);
            graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
            // graph.getViewport().setDrawBorder(true);

        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public double findMax(){
        int n=y_axis.size();
        double max = Double.parseDouble(y_axis.get(0));
        for(int i=1;i<n;i++){
            if((Double.parseDouble(y_axis.get(i))) > max ){
                max = Double.parseDouble(y_axis.get(i));}
        }
        return max;
    }

    public double findMin(){
        int n=y_axis.size();
        double min = Double.parseDouble(y_axis.get(0));
        for(int i=1;i<n;i++){
            if((Double.parseDouble(y_axis.get(i))) < min ){
                min = Double.parseDouble(y_axis.get(i));}
        }
        return min;
    }

    public DataPoint[] data(){
        int n=y_axis.size();     //to find out the no. of data-points
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint((19 + i/96),Double.parseDouble(y_axis.get(i)));
            values[i] = v;
        }
        return values;
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

