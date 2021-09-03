package com.tsits.tsits_webrtc.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.RemoteException
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import com.tsits.tsits_webrtc.R
import com.tsits.tsits_webrtc.sdk.TSPocVideo
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private val TAG ="LoginActivity"

    private lateinit var mTSPocVideo: TSPocVideo

//    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
//    fun getDeviceId(): String? {
//        var telephony: String=""
//        val tm: TelephonyManager =
//            (this.getSystemService(Context.TELEPHONY_SERVICE) ?: return null) as TelephonyManager
//        if (tm != null) {
//            telephony = tm.getDeviceId().toString()
//            Log.d(TAG,telephony)
//        }
//        return telephony
//    }

//    fun editGetId(){
//        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        account.setText(getDeviceId().toString())
//    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    init {

        init()
    }

    fun init() {
        mTSPocVideo.Init(1, "123", "v1.0", null, null, null, null)
    }
}