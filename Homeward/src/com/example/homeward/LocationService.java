package com.example.homeward;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class LocationService extends Service implements LocationListener {
    LocationManager mLocationManager;
    static boolean working = false;
    static int soft = 15 * 1000;//30 * 60 * 1000;//普通模式发送坐标的时间间隔
    static int crazy = 5 * 1000;//'210 * 1000;//狂暴模式发送坐标的时间间隔
    static boolean startcrazy = false;
	@Override
	public void onLocationChanged(Location location) {
		Log.v("Location Changed", "LocationChanged");
        if (location != null) {
        	//发送坐标信息
        	sendLocation(location);
            Log.v("Location Changed", location.getLatitude() + " and " + location.getLongitude());
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
		//获取http请求
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://kwc-oliver.com/submit.php");
		   try {
		        // Add your data
			    Log.e("http", String.valueOf(location.getLatitude()));
			    Log.e("http", String.valueOf(location.getLongitude()));
			    Log.e("http", String.valueOf(location.getAccuracy()));
			    
			    //设置发送信息
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("tel", Param.tel));
		        nameValuePairs.add(new BasicNameValuePair("latitude", String.valueOf(location.getLatitude())));
		        nameValuePairs.add(new BasicNameValuePair("longitude", String.valueOf(location.getLongitude())));
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        
		        
		        // Execute HTTP Post Request
		        //发送HTTP请求
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
        //获取LocationManager
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        //检查GPS是否可用
        boolean gps = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		Log.e("provider", "GPS " + mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER));
        
		//如果GPS可以用，则使用GPS
		if (gps) mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, startcrazy?crazy:soft, 0, this);

		//否则，使用基站定位
		else mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, startcrazy?crazy:soft, 0, this);
	}
    public int onStartCommand(Intent intent, int flags, int startId) { 
        Log.e("StartCommand", "crazy: " + startcrazy); 
    	if (!working){
    		//设置标记
	        working = true;
	        
	        //设置监听
	        setLisener();
    	}
        return START_STICKY;  
    }  
    public void onDestroy() { 
    	//设置开启标记
    	working = false;
    	
    	//去除GPS监听
    	mLocationManager.removeUpdates(this);
    	
    	//关闭服务
        super.onDestroy();  
        Log.e("localService", "Destroy");
    }  

}

