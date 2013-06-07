package com.example.java2_2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.Log;


import java.util.Calendar;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LocalService extends Service implements LocationListener {
    LocationManager mLocationManager;
    static boolean working = false;
    static int soft = 15 * 1000;//30 * 60 * 1000;
    static int crazy = 5 * 1000;//'210 * 1000;
    static boolean startcrazy = false;
	@Override
	public void onLocationChanged(Location location) {
		Log.v("Location Changed", "LocationChanged");
        if (location != null) {
            //mLocationManager.removeUpdates(this);
        	sendLocation(location);
            Log.v("Location Changed", location.getLatitude() + " and " + location.getLongitude());
            //mLocationManager.req
            //mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5 * 60 * 1000, 0, this);
        }
    }
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
        Log.v("Location Changed", "Disabled");
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
        Log.v("Location Changed", "Enabled");
		
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
        Log.v("Location Changed", "StatusChanged");
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	public void sendLocation(Location location){
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://kwc-oliver.com/submit.php");
		   try {
		        // Add your data
			    Log.e("http", String.valueOf(location.getLatitude()));
			    Log.e("http", String.valueOf(location.getLongitude()));
			    Log.e("http", String.valueOf(location.getAccuracy()));
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("tel", Param.tel));
		        nameValuePairs.add(new BasicNameValuePair("latitude", String.valueOf(location.getLatitude())));
		        nameValuePairs.add(new BasicNameValuePair("longitude", String.valueOf(location.getLongitude())));
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        
		        
		        // Execute HTTP Post Request
		        httpclient.execute(httppost);
		        Log.e("hehe", "haha");
		        Log.e("hehe", httppost.getEntity().toString());
		    } catch (ClientProtocolException e) {
		        // TODO Auto-generated catch block
				Log.e("http", e.toString());
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
				Log.e("http", e.toString());
		    }
	}
	public void setLisener(){
        Log.e("localService", "setLisener");
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		Log.e("provider", "GPS " + mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER));
        if (gps) mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, startcrazy?crazy:soft, 0, this);
        else mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, startcrazy?crazy:soft, 0, this);
	}
    public int onStartCommand(Intent intent, int flags, int startId) { 
        Log.e("StartCommand", "StartCommand"); 
    	if (!working){
	        working = true;
	        setLisener();
    	}
        return START_STICKY;  
    }  
    public void onDestroy() {  
    	working = false;
    	mLocationManager.removeUpdates(this);
        super.onDestroy();  
        Log.e("localService", "Destroy");
    }  

}

