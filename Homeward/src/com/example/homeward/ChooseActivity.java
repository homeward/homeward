package com.example.homeward;
/**/


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;


public class ChooseActivity extends Activity implements OnTouchListener{
	String TAG = "ChooseActivity";
	ImageButton b1, b2, b3;
	static SharedPreferences user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//读取各人信息
		user = getSharedPreferences("user", 0);  
		Param.tel = user.getString("tel", Param.tel);
		Param.info = user.getString("info", Param.info);
		Log.e("tel", Param.tel);
		Log.e("info", Param.info);
		
		//启动activity
		super.onCreate(savedInstanceState);
		Log.e("onCreate", TAG);
		
		//设置界面和监听
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
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//当松开按钮的时候
		if (event.getAction() == MotionEvent.ACTION_UP){
			switch (v.getId()) {
		    case R.id.button1:
		    	//首先改变背景
				b1.setBackgroundResource(R.drawable.telon);
	            
		    	//跳转到输入电话号码的界面
		    	Intent intent = new Intent();  
	            intent.setClass(ChooseActivity.this, TelActivity.class);  
	            startActivity(intent);  
	            
		        break;
		    case R.id.button2:
		    	//首先改变背景
				b2.setBackgroundResource(R.drawable.texton);
				

		    	//跳转到输入个人信息的界面
	            intent = new Intent();  
	            intent.setClass(ChooseActivity.this, InfoActivity.class);  
	            startActivity(intent);  
	            
			    break;
		    case R.id.button3:
		    	//首先改变背景
				b3.setBackgroundResource(R.drawable.switchon);

				
		    	//跳转到控制获取坐标功能开关的界面
	            intent = new Intent();  
	            intent.setClass(ChooseActivity.this, ControlActivity.class);  
	            startActivity(intent);  
	            
			    break;
		    }
		}
		//当按下按钮的时候
		else if (event.getAction() == MotionEvent.ACTION_DOWN){
			switch (v.getId()) {
		    case R.id.button1:
		    	//首先改变背景
		    	b1.setBackgroundResource(R.drawable.teloff);
		        break;
		    case R.id.button2:
		    	//首先改变背景
		    	b2.setBackgroundResource(R.drawable.textoff);
			    break;
		    case R.id.button3:
		    	//首先改变背景
		    	b3.setBackgroundResource(R.drawable.switchoff);
			    break;
			}
	    }
		return false;
	}

}