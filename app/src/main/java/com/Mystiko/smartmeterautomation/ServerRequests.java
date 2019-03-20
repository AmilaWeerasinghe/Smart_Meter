package com.Mystiko.smartmeterautomation;

import android.app.ProgressDialog;
import android.content.Context;

public class ServerRequests {
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT=1000*15;
    public static final String ServerAddress="";
    public ServerRequests(Context context){
        progressDialog=new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please Wait...");
    }
    public void StoreUserDataInBackground(){

    }
    public void fetchUserDataInBackgrounf(){
        
    }

}
