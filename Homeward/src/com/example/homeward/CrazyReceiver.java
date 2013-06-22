package com.example.homeward;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class CrazyReceiver extends BroadcastReceiver {  
    @Override  
    public void onReceive(Context context, Intent intent) {  
       // TODO  
    	Log.e("hehe", "fuck");
    	StringBuilder body = new StringBuilder();
        Bundle bundle = intent.getExtras();  
        if (bundle != null) {  
        	//获取短信内容
            Object[] _pdus = (Object[]) bundle.get("pdus");  
            SmsMessage[] message = new SmsMessage[_pdus.length];  
            for (int i = 0; i < _pdus.length; i++) {  
                message[i] = SmsMessage.createFromPdu((byte[]) _pdus[i]);  
            }  
            for (SmsMessage currentMessage : message) {  
                body.append(currentMessage.getDisplayMessageBody());  
            }  
            String smsBody = body.toString();
        	Log.e("hehe", smsBody.substring(0, 8));
            
        	//确认该短信内容是否满足过滤条件  如果前8个字符是HOMEWARD或者其他格式的HOMEWARD则满足过滤条件
            boolean flags_filter = false;  
            String HOMEWARD = "HOMEWARD";
            int i;
            for (i = 0; i < 8; ++i){
            	if (smsBody.length() <= i) break;
            	Log.e("hehe", " " + (int)HOMEWARD.charAt(i) + " " + (smsBody.charAt(i) - 65248));
            	if ((HOMEWARD.charAt(i) == smsBody.charAt(i) - 65248)||(HOMEWARD.charAt(i) == smsBody.charAt(i))) continue;
            	break;
            }
            
            //当满足过滤条件时
            if (i == 8){
                flags_filter = true;  
                Log.v("our", "crazy start");
                ///狂暴开启
                LocationService.startcrazy = true;
                
                //弹出请求帮助界面
	            Intent intent1 = new Intent(context, CrazyActivity.class);
	            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            context.startActivity(intent1); 
	            
	            //开启提示音，注意先判断是否开启
                if (!MusicService.working)context.startService(new Intent(context, MusicService.class));
                

	            //先关闭服务，再重新开启服务，因为狂暴模式下时间间隔是不一样的，所以需要重新设置
                context.stopService(new Intent(context, LocationService.class));
                context.startService(new Intent(context, LocationService.class));

                Log.v("our", "crazy start");
                //因为重新开启了坐标服务，所以ControlActivity的图标要改变，注意首先要判读其是否start否则有意想不到的问题哦
                if (ControlActivity.start) ControlActivity.bcontrol.setBackgroundResource(R.drawable.cclose);

                Log.v("our", "crazy start");
            }  
            

            //当不满足过滤条件时，把这条短信放行，交给其他程序负责
            if (flags_filter) {  
                this.abortBroadcast();  
            }  
        }  
    }
} 