package com.example.java2_2;
/**/


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;


public class ChooseActivity extends Activity implements OnTouchListener{

	String TAG = "ChooseActivity";
	ImageButton b1, b2, b3;
	static SharedPreferences user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		user = getSharedPreferences("user", 0);  
		Param.tel = user.getString("tel", Param.tel);
		Param.info = user.getString("info", Param.info);
		Log.e("tel", Param.tel);
		Log.e("info", Param.info);
		super.onCreate(savedInstanceState);
		Log.e("onCreate", TAG);
		setContentView(R.layout.activity_choose);
		b1 = (ImageButton) findViewById(R.id.button1);
		b2 = (ImageButton) findViewById(R.id.button2);
		b3 = (ImageButton) findViewById(R.id.button3);
		b1.setOnTouchListener(this);
		b2.setOnTouchListener(this);
		b3.setOnTouchListener(this);

		b1.setBackgroundResource(R.drawable.telon);
		b2.setBackgroundResource(R.drawable.texton);
		b3.setBackgroundResource(R.drawable.switchon);
	    //bcontrol.setBackgroundResource((LocalService.working)?R.drawable.cclose:R.drawable.copen);   
		/*
	    b1.setOnClickListener(this);
	    b2.setOnClickListener(this);
	    b3.setOnClickListener(this);
		*/
		//startService(new Intent(this, LocalService.class));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_UP){
			switch (v.getId()) {
		    case R.id.button1:
				b1.setBackgroundResource(R.drawable.telon);
		    	stopService(new Intent(this, MusicService.class));
	            Intent intent = new Intent();  
	            intent.setClass(ChooseActivity.this, TelActivity.class);  
	            startActivity(intent);  
		        break;
		    case R.id.button2:
				b2.setBackgroundResource(R.drawable.texton);
	            intent = new Intent();  
	            intent.setClass(ChooseActivity.this, InfoActivity.class);  
	            startActivity(intent);  
			    break;
		    case R.id.button3:
				b3.setBackgroundResource(R.drawable.switchon);
	            intent = new Intent();  
	            intent.setClass(ChooseActivity.this, MainActivity.class);  
	            startActivity(intent);  
			    break;
		    }
		}else if (event.getAction() == MotionEvent.ACTION_DOWN){
			switch (v.getId()) {
		    case R.id.button1:
		    	b1.setBackgroundResource(R.drawable.teloff);
		        break;
		    case R.id.button2:
		    	b2.setBackgroundResource(R.drawable.textoff);
			    break;
		    case R.id.button3:
		    	b3.setBackgroundResource(R.drawable.switchoff);
			    break;
			}
	    }
		return false;
	}

}