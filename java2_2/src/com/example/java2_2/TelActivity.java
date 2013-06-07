package com.example.java2_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

	public class TelActivity extends Activity implements OnClickListener{

		String TAG = "InfoActivity";
		Button b1, b2;
		EditText t1;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			Log.e("onCreate", TAG);
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
		    	Param.tel = t1.getText().toString();
		    	ChooseActivity.user.edit().putString("tel", Param.tel).commit();  
		      break;
		    case R.id.button2: 
		    	t1.setText(Param.tel);
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