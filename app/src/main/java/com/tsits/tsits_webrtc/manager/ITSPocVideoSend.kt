package com.tsits.tsits_webrtc.manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tsits.tsits_webrtc.sdk.TSPocVideo

/**
 * @author YUAN
 * @description:
 * @date :2021/9/9 10:22
 */
class ITSPocVideoSend : AppCompatActivity() {

    lateinit var tsPocVideo: TSPocVideo
    lateinit var hardwareInformationUtil: HardwareInformationUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        getInstance()
    }

//    fun getInstance() {
//        val fingerprint = hardwareInformationUtil.fingerprint
//        val locationDeviceID: Int =
//            hardwareInformationUtil.locationDeviceID(this).toString().toInt()
//        val systemVersion=hardwareInformationUtil.systemVersion
//        tsPocVideo.Init(locationDeviceID, fingerprint,systemVersion,)
//    }
}