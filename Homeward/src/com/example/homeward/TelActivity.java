package com.example.homeward;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

	public class TelActivity extends Activity implements OnClickListener{

		String TAG = "InfoActivity";
		Button b1, b2;
		EditText t1;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			//开启activity
			super.onCreate(savedInstanceState);
			Log.e("onCreate", TAG);
			
			//设置界面
			setContentView(R.layout.activity_tel);
			b1 = (Button) findViewById(R.id.button1);
			b2 = (Button) findViewById(R.id.button2);
			Log.e("onCreate", TAG);
		    b1.setOnClickListener(this);
		    b2.setOnClickListener(this);
		    t1 = (EditText)findViewById(R.id.editText1);
		    t1.setText(Param.tel);
		}
		public void onClick(View src) {
		    switch (src.getId()) {
		    case R.id.button1:
		    	//当按下确定按钮后，存储内容，并返回上一层
		    	Param.tel = t1.getText().toString();
		    	ChooseActivity.user.edit().putString("tel", Param.tel).commit();  
		    	TelActivity.this.finish(); 
		      break;
		    case R.id.button2: 
		    	//当按下取消后，重新读取内容 
		    	TelActivity.this.finish(); 
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