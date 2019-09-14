package com.mystiko.smartmeter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bill extends AppCompatActivity {

    Connection con;
    String un,pass,db,ip;
    String MSerialNum,time,value;
    // String units,month;

    public static ArrayList<String> units=new ArrayList<String>();
    public static ArrayList<String> price=new ArrayList<String>();
    //public static ArrayList<String> time=new ArrayList<String>();
    private  TextView bill = (TextView) findViewById(R.id.priceView);
    private TextView consumption=(TextView) findViewById(R.id.unitView);
    //private LinearLayout list=(LinearLayout)findViewById(R.id.usageHistory);
    TextView history=(TextView)findViewById(R.id.scrollList);


    // private EditText showPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        Intent intent = getIntent();
        MSerialNum = intent.getExtras().getString("mserial");

        history.setMovementMethod(new ScrollingMovementMethod());
        // price.setText("100");
        ip = "205.144.171.68";
        db = "DB_A46FD9_Database";
        un = "DB_A46FD9_Database_admin";
        pass = "0662231015asd";

        try

        {

            con = connectionclass(un, pass, db, ip);

            String query = "select TIME,Var,kWh from MeterReading where MSerial= '" + MSerialNum.toString() + "' ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                units.add(rs.getString("kWh"));
                price.add(rs.getString("Var"));
                time=rs.getString("TIME");
                value=rs.getString("kWh");
                //time.add(rs.getString("Time"));
                history.setText(time+" "+value);
            }
            //price.setText(String.valueOf(totalAmount()));
            bill.setText(String.valueOf(totalAmount()));
            consumption.setText(String.valueOf(totalUnits()));
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public double totalAmount(){
        int n = price.size();
        double total=0;
        for(int i=0;i<n;i++){
            total=total+Double.parseDouble(price.get(i));
        }
        return total;
    }

    public double totalUnits(){
        int n=units.size();
        double total=0;
        for(int i=0;i<n;i++){
            total=total+(Double.parseDouble(units.get(i+1))-Double.parseDouble(units.get(i)));
        }
        return total;
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
            ConnectionURL = "jdbc:jtds:sqlserver://sql5041.mywindowshosting.com;database=DB_A46FD9_Database;user=DB_A46FD9_Database_admin;password=0662231015asd";
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
    public void back(View view) {
        finish();
        Intent m = new Intent(this, UserHome.class);
        m.putExtra("mserial", MSerialNum);
        startActivity(m);

    }


}
