package com.tsits.tsits_webrtc.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

public class CycleBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent mintent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(mintent.getAction())) {
            //启动完成
            Intent intent = new Intent(context, StartupBroadcast.class);
            intent.setAction("arui.alarm.action");
            PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
            long firsttime = SystemClock.elapsedRealtime();
            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            //10秒发送一个广播，不停发送广播
            am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firsttime, 10 * 1000, sender);
        }
    }
}
