package com.example.homeward;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {
	//为日志工具设置标签
	private static String TAG = "MusicService";
    static boolean working = false;
	//定义音乐播放器变量
	private MediaPlayer mPlayer;
	
	//该服务不存在需要被创建时被调用，不管startService()还是bindService()都会启动时调用该方法
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) { 
		Log.e(TAG, "MusicSerice onCreate()");
		//设置播放曲目
		mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
		
		//设置可以重复播放
		mPlayer.setLooping(true);
		
		//开始播放
		mPlayer.start();
		
		//设置标志
		working = true;
		
		//开启服务
		super.onCreate();
        return START_STICKY;  
    }  
	@Override
	public void onDestroy() {
		Log.e(TAG, "MusicSerice onDestroy()");
		//关闭音乐
		mPlayer.stop();
		
		//设置标志
		working = false;
		
		//狂暴模式关闭
		LocationService.startcrazy = false;
		
		//关闭service
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}