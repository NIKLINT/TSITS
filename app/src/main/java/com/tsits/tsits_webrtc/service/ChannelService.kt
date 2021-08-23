package com.tsits.tsits_webrtc.service

import android.app.Dialog
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.hunter.codec.ESCodecJNI
import com.tsits.tsits_webrtc.activity.MainActivity

class ChannelService : Service() {
    private val TAG = "ChannelService"
    private var receiver: BroadcastReceiver? = null

    override fun onBind(p0: Intent?): IBinder? {
        Log.i(TAG, "onBind")
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("message", "service onCreate")
        registerReceiver()
    }



    private fun registerReceiver() {
        receiver=MyReceiver()
        var intentFilter = IntentFilter()
        intentFilter.addAction("bbb")
        registerReceiver(receiver, intentFilter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    //注册一个广播
    class MyReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "Service接收到广播", Toast.LENGTH_SHORT).show()
        }
    }

//    fun showSingleAlertDialog() {
//        val items =
//            arrayOf("1 POC_UP",
//                "2 POC_DOWN",
//                "3 DMR_UP",
//                "4 DMR_DOWN",
//                "5 POC2DMR",
//                "6 DMR2POC",
//                "7 HP_POC_UP",
//                "8 HP_POC_DOWN",
//                "9 HP_DMR_UP",
//                "10 HP_DMR_DOWN",
//                "11 BT_POC_UP",
//                "12 BT_POC_DOWN",
//                "13 BT_DMR_UP",
//                "14 BT_DMR_DOWN",
//                "15 HP_CALL",
//                "16 HP_MUSIC",
//                "17 HF_CALL",
//                "18 DEFAULT")
//        val alertBuilder = AlertDialog.Builder(this)
//        alertBuilder.setTitle("选择模式")
//        alertBuilder.setSingleChoiceItems(items, 0
//        ) { dialogInterface, i ->
//            Toast.makeText(this@ChannelService, items[i], Toast.LENGTH_SHORT).show()
//            switchMode(i + 1)
//        }
//        alertBuilder.setNegativeButton("取消"
//        ) { dialogInterface, i -> mAlertDialog.dismiss() }
//        mAlertDialog = alertBuilder.create()
//        mAlertDialog.show()
//    }
//
//
//
//    private fun switchMode(flag: Int) {
//        val result = ESCodecJNI.switchEsMode(flag)
//        if (result == 0) {
//            val mode = ESCodecJNI.getEsMode()
//            Toast.makeText(this, "当前模式：" + getModeText(mode), Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "模式切换失败", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun getModeText(mode: Int): String? {
//        var result = ""
//        result = when (mode) {
//            1 -> "1 POC_UP"
//            2 -> "2 POC_DOWN"
//            3 -> "3 DMR_UP"
//            4 -> "4 DMR_DOWN"
//            5 -> "5 POC2DMR"
//            6 -> "6 DMR2POC"
//            7 -> "7 HP_POC_UP"
//            8 -> "8 HP_POC_DOWN"
//            9 -> "9 HP_DMR_UP"
//            10 -> "10 HP_DMR_DOWN"
//            11 -> "11 BT_POC_UP"
//            12 -> "12 BT_POC_DOWN"
//            13 -> "13 BT_DMR_UP"
//            14 -> "14 BT_DMR_DOWN"
//            15 -> "15 HP_CALL"
//            16 -> "16 HP_MUSIC"
//            17 -> "17 HF_CALL"
//            0, 18 -> "DEFAULT"
//            else -> "获取失败"
//        }
//        return result
//    }




    override fun onDestroy() {
        receiver?.let {
            unregisterReceiver(it)
        }
        super.onDestroy()
    }


}