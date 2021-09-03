package com.tsits.tsits_webrtc.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartupBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("arui.alarm.action")){
            /*服务开机自启动*/
            Intent service =new Intent(context,AidlService.class);
            context.startService(service);
        }
    }
}
