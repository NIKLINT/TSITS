package com.tsits.tsits_webrtc.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.hunter.codec.ESCodecJNI
import com.hunter.codec.ESCodecJNI.switchEsMode
import com.tsits.tsits_webrtc.aidl_Data

class ChannelService : Service() {

    private val TAG = "ChannelService"

    //    private var receiver: ScreeOnBroadcastReceiver? = null


    override fun onBind(intent: Intent?): IBinder? {
        Log.i(TAG, "onBind")
//        TODO()
        return object : aidl_Data.Stub() {
            override fun setMode(mode:Int): Int {
                var imode = 0
                if (intent!!.hasExtra("setMode")) {
                    switchEsMode(mode)
                    imode = mode
                    imode = ESCodecJNI.getEsMode()
                    ToastUtil("Audio Mode Set To :$imode\"")
                }
                return imode
            }

            override fun getMode(): Int {
                if (intent!!.hasExtra("getMode")) {
                    mode = ESCodecJNI.getEsMode()
                    ToastUtil("Audio Mode Get To :$mode\"")
                }
                return mode
            }
        }
    }


    override fun onCreate() {
        super.onCreate()
        Log.d("message", "service onCreate")
        ToastUtil("tkdbwfgeouh")

    }

    fun ToastUtil(someToast: String) {
        run { Toast.makeText(applicationContext, someToast, Toast.LENGTH_SHORT).show(); }
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}
