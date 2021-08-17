package com.tsits.tsits_webrtc.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tsits.tsits_webrtc.R
import com.tsits.tsits_webrtc.activity.MessageTalkingRoomActivity
import kotlinx.android.synthetic.main.fragment_message.*

class MessageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message,null)
    }
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar_navigation.setOnClickListener(){
            var intent=Intent(context,MessageTalkingRoomActivity::class.java)
            context?.startActivity(intent)
        }
    }

}