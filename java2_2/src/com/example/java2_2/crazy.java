package com.example.java2_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class crazy extends BroadcastReceiver {  
    @Override  
    public void onReceive(Context context, Intent intent) {  
       // TODO  
    	Log.e("hehe", "fuck");
    	StringBuilder body = new StringBuilder();// 短信内容  
        StringBuilder number = new StringBuilder();// 短信发件人  
        Bundle bundle = intent.getExtras();  
        if (bundle != null) {  
            Object[] _pdus = (Object[]) bundle.get("pdus");  
            SmsMessage[] message = new SmsMessage[_pdus.length];  
            for (int i = 0; i < _pdus.length; i++) {  
                message[i] = SmsMessage.createFromPdu((byte[]) _pdus[i]);  
            }  
            for (SmsMessage currentMessage : message) {  
                body.append(currentMessage.getDisplayMessageBody());  
                number.append(currentMessage.getDisplayOriginatingAddress());  
            }  
            String smsBody = body.toString();  
            String smsNumber = number.toString();  
            if (smsNumber.contains("+86")) {  
                smsNumber = smsNumber.substring(3);  
            }  
        	Log.e("hehe", smsBody.substring(0, 8));
            // 第二步:确认该短信内容是否满足过滤条件  
            boolean flags_filter = false;  
            String HOMEWARD = "HOMEWARD";
            int i;
            for (i = 0; i < 8; ++i){
            	if (smsBody.length() <= i) break;
            	Log.e("hehe", " " + (int)HOMEWARD.charAt(i) + " " + (smsBody.charAt(i) - 65248));
            	if ((HOMEWARD.charAt(i) == smsBody.charAt(i) - 65248)||(HOMEWARD.charAt(i) == smsBody.charAt(i))) continue;
            	break;
            }
            if (i == 8){// 屏蔽10086发来的短信  
                flags_filter = true;  
                Log.v("our", "crazy start");
///狂暴开启
                LocalService.startcrazy = true;
	             Intent intent1 = new Intent(context, CrazyActivity.class);
	             intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	             context.startActivity(intent1); 
                context.startService(new Intent(context, MusicService.class));
                context.stopService(new Intent(context, LocalService.class));
                context.startService(new Intent(context, LocalService.class));

                Log.v("our", "crazy start");
                if (MainActivity.start) MainActivity.bcontrol.setBackgroundResource(R.drawable.cclose);

                Log.v("our", "crazy start");
            }  
            // 第三步:取消  
            if (flags_filter) {  
                this.abortBroadcast();  
            }  
        }  
    }
} 