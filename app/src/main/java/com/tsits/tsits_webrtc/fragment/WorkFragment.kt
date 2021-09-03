package com.tsits.tsits_webrtc.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baidu.mapapi.SDKInitializer

import com.tsits.tsits_webrtc.R
import com.tsits.tsits_webrtc.activity.VoiceCallActivity
import com.tsits.tsits_webrtc.sdk.ITSPocVideoCallback
import kotlinx.android.synthetic.main.fragment_work.*


class WorkFragment() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_work, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        HideBottomNavigation()
    }




    private fun HideBottomNavigation() {
        toolbar_navigation?.setOnClickListener {
            var intent = Intent(context!!, VoiceCallActivity::class.java)
            context?.startActivity(intent)
        }



    }
}