package com.tsits.tsits_webrtc.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tsits.tsits_webrtc.R
import kotlinx.android.synthetic.main.fragment_work.*

class VoiceCallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_voice_chat)
        turnToVideo()

    }
    private fun turnToVideo() {
        toolbar_navigation?.setOnClickListener {
            var intent = Intent(this!!, VideoCallActivity::class.java)
            this?.startActivity(intent)
        }

    }
}