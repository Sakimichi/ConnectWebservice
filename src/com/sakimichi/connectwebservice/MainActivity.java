package com.sakimichi.connectwebservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	
	private TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        this.tx = (TextView) findViewById(R.id.result);       
        new GetAllCustomerTask().execute(new ApiConnector());
     
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void setTextToTextView(JSONArray jsonArray)
    {
    	
    	  String s  = "";
          for(int i=0; i<jsonArray.length();i++){

              JSONObject json = null;
              try {
                  json = jsonArray.getJSONObject(i);
                  s = s +
                          "Name : "+json.getString("FirstName")+" "+json.getString("LastName")+"\n"+
                          "Age : "+json.getInt("Age")+"\n"+
                          "Mobile Using : "+json.getString("Mobile")+"\n\n";
              } catch (JSONException e) {
                  e.printStackTrace();
              }

          }

          this.tx.setText(s);
    }
    
    private class GetAllCustomerTask extends AsyncTask<ApiConnector, Long, JSONArray>
    {

		@Override
		protected JSONArray doInBackground(ApiConnector... params) {
			// TODO Auto-generated method stub
			
			//it is executed on Background thread
			//return null;
			return params[0].GetAllCustomers();
		}

		@Override
		protected void onPostExecute(JSONArray jsonArray) {
			// TODO Auto-generated method stub
			//it is executed on Main thread
			//super.onPostExecute(result);
			
			setTextToTextView(jsonArray);
		}
    	
    	
    }
   
}
