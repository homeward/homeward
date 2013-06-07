package com.example.java2_2;


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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class CrazyActivity extends Activity {
    LocationManager mLocationManager;
	String TAG = "MainActivity";
	Button breturn;
	static ImageButton bcontrol;
	static String info;
	TextView t1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("onCreate", TAG);
		setContentView(R.layout.activity_crazy);
		t1 = (TextView)findViewById(R.id.textView1);
		t1.setText(Param.info);
	}

}
