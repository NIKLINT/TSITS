package com.tsits.tsits_webrtc.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.hunter.codec.ESCodecJNI.switchEsMode
import com.hunter.codec.ESCodecJNI as ESCodecJNI1


class ScreeOnBroadcastReceiver : BroadcastReceiver() {

    private final var CALLBACK_PACKAGE = "com.cmccpoc"
    override fun onReceive(context: Context?, intent: Intent?) {
//        Toast.makeText(context, "Service接收到广播" + resultData, Toast.LENGTH_SHORT).show()

        //Toast.makeText(context, "Audio Mode",Toast.LENGTH_LONG).show();


        var Mode :Int = 0
        if (intent!!.hasExtra("setMode")) {
            Mode = intent.getIntExtra("setMode", 18)
            val jni:com.hunter.codec.ESCodecJNI
            switchEsMode(Mode)
            val backtoApp=Intent("com.TSITS.AudioService.setMode")
            backtoApp?.setPackage(CALLBACK_PACKAGE)
            backtoApp?.putExtra("setMode", Mode)
            context?.sendBroadcast(backtoApp)
            Toast.makeText(context, "Audio Mode Set To :$Mode", Toast.LENGTH_LONG).show()
        }
        Log.d("AudioReceiver", "Audio Mode")
        if (intent.hasExtra("getMode")){
            val jni = ESCodecJNI1()
            Mode = ESCodecJNI1.getEsMode()
            val backtoApp=Intent("com.TSITS.AudioService.setMode")
            backtoApp?.setPackage(CALLBACK_PACKAGE)
            backtoApp?.putExtra("setMode", Mode)
            context?.sendBroadcast(backtoApp)
            Toast.makeText(context, "Audio Mode Set To :$Mode", Toast.LENGTH_LONG).show()
        }

    }

    }
