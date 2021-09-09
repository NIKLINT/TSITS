package com.tsits.tsits_webrtc.fragment

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tsits.tsits_webrtc.R
import com.tsits.tsits_webrtc.sdk.TSPocVideo
import kotlinx.android.synthetic.main.fragment_work.*

/**
 * @author YUAN
 * @description:
 * @date :2021/9/9 16:21
 */
class WorkFragment : Fragment() {

//    lateinit var tsPocVideo: TSPocVideo
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_work, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

//    fun changeNickName(){
//        change_name_button.setOnClickListener(View.OnClickListener {
//            tsPocVideo.Device_ChangeInfo(change_name.text.toString()) })
//    }
}