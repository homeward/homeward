package com.example.homeward;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ControlActivity extends Activity implements OnClickListener{
	String TAG = "ControlActivity";
	static ImageButton bcontrol;
	static boolean start = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//标记activity是被启动的
		start = true;
		
		//启动activity
		super.onCreate(savedInstanceState);
		Log.e("onCreate", TAG);
		
		//设置界面
		setContentView(R.layout.activity_control);
		Log.e("onCreate", TAG);
		bcontrol = (ImageButton) findViewById(R.id.button1);  
		Log.e("onCreate", TAG);  
	    Log.e("onCreate", "hehe");
	    bcontrol.setBackgroundResource((LocationService.working)?R.drawable.cclose:R.drawable.copen);//当坐标服务是被打开的时候，按钮应该显示是打开的，否则是显示关闭的
	    Log.e("onCreate", "hehe");
	    bcontrol.setOnClickListener(this);
	    Log.e("onCreate", "hehe");
	}
	public void onClick(View src) {
		switch (src.getId()) {
	    	case R.id.button1:
	    		Log.e("hehe", " " + LocationService.working);
	    		//当坐标服务开始时
			    if (LocationService.working){
			    	//关闭坐标服务
			    	Log.d(TAG, "onClick: stopping srvice");
				    stopService(new Intent(this, LocationService.class));
			    	//改变按钮状态
				    bcontrol.setBackgroundResource(R.drawable.copen);
				    //若MusicService正在播放，则取消播放
				    if (MusicService.working){
				    	stopService(new Intent(this, MusicService.class));
				    }
			    }
	    		//当坐标服务关闭时
			    else{
			    	//开启坐标服务
			    	Log.d(TAG, "onClick: starting srvice");
			    	startService(new Intent(this, LocationService.class));
			    	//改变按钮状态
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

}
