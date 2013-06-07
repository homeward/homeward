package com.example.java2_2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {
	//为日志工具设置标签
	private static String TAG = "MusicService";
	//定义音乐播放器变量
	private MediaPlayer mPlayer;
	
	//该服务不存在需要被创建时被调用，不管startService()还是bindService()都会启动时调用该方法
	@Override
	public void onCreate() {
		Log.e(TAG, "MusicSerice onCreate()");
		mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
		//设置可以重复播放
		mPlayer.setLooping(true);
		super.onCreate();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		Log.e(TAG, "MusicSerice onStart()");
		mPlayer.start();
		super.onStart(intent, startId);
	}
	@Override
	public void onDestroy() {
		Log.e(TAG, "MusicSerice onDestroy()");
		mPlayer.stop();
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}