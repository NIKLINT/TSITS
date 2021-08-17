package com.tsits.tsits_webrtc.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.EventLog
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tsits.tsits_webrtc.R
import com.tsits.tsits_webrtc.`interface`.MyOnTouchListener
import com.tsits.tsits_webrtc.activity.MainActivity
import kotlinx.android.synthetic.main.activity_message_talking.*
import kotlinx.android.synthetic.main.fragment_message.*
import kotlinx.android.synthetic.main.fragment_microphone.*
import java.time.chrono.ThaiBuddhistEra


class MicrophoneFragment : Fragment(), View.OnTouchListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_microphone, container, false)
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView5?.setOnTouchListener(this)
        imageView6?.setOnTouchListener(this)

    }

    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        if (view?.id == imageView5.id) {
            when (motionEvent?.action) {
                MotionEvent.ACTION_DOWN -> {
                    iv_talking_state.visibility=View.VISIBLE
                    tv_talking_state.visibility=View.VISIBLE
                    iv_green_background.visibility = View.VISIBLE
                    iv_green_background2.visibility = View.VISIBLE
                }
                MotionEvent.ACTION_UP -> {
                    tv_talking_state.visibility=View.GONE
                    iv_talking_state.visibility=View.GONE
                    iv_green_background.visibility = View.GONE
                    iv_green_background2.visibility = View.GONE
                }
            }
            return true
        } else if (view?.id == imageView6.id) {
            when (motionEvent?.action) {
                MotionEvent.ACTION_DOWN -> {
                    tv_talking_state.visibility=View.VISIBLE
                    iv_talking_state.visibility=View.VISIBLE
                    iv_red_background.visibility = View.VISIBLE
                    iv_red_background2.visibility = View.VISIBLE
                }
                MotionEvent.ACTION_UP -> {
                    tv_talking_state.visibility=View.GONE
                    iv_talking_state.visibility=View.GONE
                    iv_red_background.visibility = View.GONE
                    iv_red_background2.visibility = View.GONE
                }
            }
        }
        return true
    }


}