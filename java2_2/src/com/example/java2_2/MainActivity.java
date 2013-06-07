package com.example.java2_2;


import java.util.Calendar;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class MainActivity extends Activity implements OnClickListener, LocationListener {
    LocationManager mLocationManager;
	String TAG = "MainActivity";
	static ImageButton bcontrol;
	static boolean start = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		start = true;
		//Log.e("hehe", "hehe");
		//Param.init();
		//Log.e(Param.tel, Param.info);
		//getApplicationContext().gets
		//SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
		super.onCreate(savedInstanceState);
		Log.e("onCreate", TAG);
		setContentView(R.layout.activity_main);
		//bcontrol = (Button) findViewById(R.id.button1);
		Log.e("onCreate", TAG);
		bcontrol = (ImageButton) findViewById(R.id.button1);  
		Log.e("onCreate", TAG);  

	    //bcontrol.setOnClickListener(this);
	    Log.e("onCreate", "hehe");
	    bcontrol.setBackgroundResource((LocalService.working)?R.drawable.cclose:R.drawable.copen);    
	    
	    Log.e("onCreate", "hehe");
	    bcontrol.setOnClickListener(this);
	    /*
	    bcontrol.setOnTouchListener(new ImageButton.OnTouchListener(){    
            @Override    
            public boolean onTouch(View arg0, MotionEvent arg1) {    
                // TODO Auto-generated method stub    
                if(arg1.getAction() == MotionEvent.ACTION_DOWN){    
                    arg0.setBackgroundResource(R.drawable.cclose);    
                }    
                else if(arg1.getAction() == MotionEvent.ACTION_UP){    
                    arg0.setBackgroundResource(R.drawable.copen);    
                }    
                    
                return false;    
            }
                
        });   
        */
	    Log.e("onCreate", "hehe");
		//startService(new Intent(this, LocalService.class));
	    
		
		
	    //tel info
	    /*
		TelephonyManager phoneMgr=(TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
		try{
			//Log.e("phone number", phoneMgr.getLine1Number());
			Log.e("phone ismi", phoneMgr.getDeviceId());
			
			//phoneMgr.getl
		}catch(Exception e){
			Log.e("phone number", "err " + e.toString());
			Log.e("phone number", "err " + e.getStackTrace().toString());
		}
		*/
	}
	public void onClick(View src) {
		switch (src.getId()) {
	    	case R.id.button1:
	    		Log.e("hehe", " " + LocalService.working);
			    if (LocalService.working){
			    	Log.d(TAG, "onClick: stopping srvice");
				    stopService(new Intent(this, LocalService.class));
				    //stopService(new Intent(this, MusicService.class));
				    bcontrol.setBackgroundResource(R.drawable.copen);
			    }else{
			      Log.d(TAG, "onClick: starting srvice");
			      startService(new Intent(this, LocalService.class));
				    bcontrol.setBackgroundResource(R.drawable.cclose);
			   }
			   break;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onLocationChanged(Location location) {
        if (location != null) {
            Log.v("Location Changed", location.getLatitude() + " and " + location.getLongitude());
            mLocationManager.removeUpdates(this);
        }
    }
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
        Log.v("Location Changed", "fuck");
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
        Log.v("Location Changed", "fuck");
		
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
        Log.v("Location Changed", "fuck");
		
	}

}
