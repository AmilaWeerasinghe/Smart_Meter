package com.Mystiko.smartmeterautomation;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStore {

    public static final String SP_Name="userdetails";//name for the database
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase=context.getSharedPreferences(SP_Name,0);
    }

    //method to store a user to database
    public void storeUserData(User user){
        SharedPreferences.Editor spEditor=userLocalDatabase.edit();
        spEditor.putString("name",user.name);
        spEditor.putString("username",user.username);
        spEditor.putInt("age",user.age);
        spEditor.putString("password",user.password);
        spEditor.commit();
    }
    //method to get user from database
    public User getLogedInUser(){
        String name=userLocalDatabase.getString("name","");
        int age=userLocalDatabase.getInt("age",-1);
        String username=userLocalDatabase.getString("username","");
        String password=userLocalDatabase.getString("password","");

        User storeduser=new User(name,username,password,age);
        return storeduser;
    }
    public void setUserLoggedIn(boolean LoggedIn){
        SharedPreferences.Editor spEditor=userLocalDatabase.edit();
        spEditor.putBoolean("LoggedIn",LoggedIn);
        spEditor.commit();
    }
    public void ClearUserData(){
        SharedPreferences.Editor spEditor=userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
    public boolean getUserLoggedIn(){
        if(userLocalDatabase.getBoolean("LoggedIn",false)==true){
            return true;
        }
        else{
            return false;
        }
    }

}
