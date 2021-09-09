package com.tsits.tsits_webrtc.utils

import android.app.Activity
import android.app.Service
import android.os.Vibrator


/**
 * @author YUAN
 * @description:
 * @date :2021/9/7 11:12
 */
interface VibrateUtils {
    //震动milliseconds毫秒
    fun vibrate(activity: Activity, milliseconds: Long) {
        val vib = activity.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
        vib.vibrate(milliseconds)
    }

    //以pattern[]方式震动
    fun vibrate(activity: Activity, pattern: LongArray?, repeat: Int) {
        val vib = activity.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
        vib.vibrate(pattern, repeat)
    }

    //取消震动
    fun virateCancle(activity: Activity) {
        val vib = activity.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
        vib.cancel()
    }
}