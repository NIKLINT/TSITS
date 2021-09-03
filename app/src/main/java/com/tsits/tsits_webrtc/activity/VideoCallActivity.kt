package com.tsits.tsits_webrtc.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tsits.pocvideosdk.main.TSXMPPClientCallback
import com.tsits.tsits_webrtc.R
import com.tsits.tsits_webrtc.sdk.ITSPocVideoCallback
import com.tsits.tsits_webrtc.sdk.TSPocVideo

class VideoCallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_video_chat)
    }
}