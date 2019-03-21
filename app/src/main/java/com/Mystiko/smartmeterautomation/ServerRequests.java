package com.Mystiko.smartmeterautomation;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ServerRequests {
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT=1000*15;
    public static final String ServerAddress="https://final-business.000webhostapp.com/";
    public ServerRequests(Context context){
        progressDialog=new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please Wait...");
    }
    public void StoreUserDataInBackground(User user,GetUserCallBack userCallBack){
        progressDialog.show();
        new StoreUserDataAsyncTask(user,userCallBack).execute();

    }
    public void fetchUserDataInBackground(User user,GetUserCallBack callBack){
        progressDialog.show();
        new fetchUserDataAsyncTask(user,callBack).execute();

    }
    public class StoreUserDataAsyncTask extends AsyncTask<Void,Void,Void>{
        User user;
        GetUserCallBack userCallBack;
        public StoreUserDataAsyncTask(User user,GetUserCallBack userCallBack){
            this.user=user;
            this.userCallBack=userCallBack;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            //arrange data to be sent to the database
            ArrayList<NameValuePair> dataTosend=new ArrayList<>();
            dataTosend.add(new BasicNameValuePair("name",user.name));
            dataTosend.add(new BasicNameValuePair("username",user.username));
            dataTosend.add(new BasicNameValuePair("password",user.password));
            dataTosend.add(new BasicNameValuePair("age",user.age+""));

            HttpParams httpRequestParams=new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,CONNECTION_TIMEOUT);

            HttpClient client=new DefaultHttpClient(httpRequestParams);
            HttpPost post=new HttpPost(ServerAddress+"Register.php");


            try {
                post.setEntity(new UrlEncodedFormEntity(dataTosend) );
                client.execute(post);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            userCallBack.done(null);
            super.onPostExecute(aVoid);
        }
    }

    public class fetchUserDataAsyncTask extends AsyncTask<Void,Void,User> {
        User user;
        GetUserCallBack userCallBack;

        public fetchUserDataAsyncTask(User user, GetUserCallBack userCallBack) {
            this.user = user;
            this.userCallBack = userCallBack;

        }


        @Override
        protected User doInBackground(Void... voids) {
            ArrayList<NameValuePair> dataTosend=new ArrayList<>();
            dataTosend.add(new BasicNameValuePair("username",user.username));
            dataTosend.add(new BasicNameValuePair("password",user.password));

            HttpParams httpRequestParams=new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,CONNECTION_TIMEOUT);

            HttpClient client=new DefaultHttpClient(httpRequestParams);
            HttpPost post=new HttpPost(ServerAddress+"FetchUserData.php");

            User returnedUser=null;

            try {
                post.setEntity(new UrlEncodedFormEntity(dataTosend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);

                if (jObject.length() == 0) {
                    returnedUser = null;
                } else {
                    String name = jObject.getString("name");
                    int age = jObject.getInt("age");

                    returnedUser = new User(name, user.username, user.password, age);
                }

            } catch (UnsupportedEncodingException | JSONException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return returnedUser;
        }
        @Override
        protected void onPostExecute(User returnedUser) {
            progressDialog.dismiss();
            userCallBack.done(returnedUser);
            super.onPostExecute(returnedUser);
        }
    }

}
