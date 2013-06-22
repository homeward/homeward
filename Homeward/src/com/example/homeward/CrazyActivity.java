package com.example.homeward;



import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
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
		//开启activity
		super.onCreate(savedInstanceState);
		Log.e("onCreate", TAG);
		setContentView(R.layout.activity_crazy);
		
		//设置显示内容
		t1 = (TextView)findViewById(R.id.textView1);
		t1.setText(Param.info);
	}

}
