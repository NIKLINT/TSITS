package com.tsits.tsits_webrtc.activity

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tsits.tsits_webrtc.R
import kotlinx.android.synthetic.main.fragment_voice_chat.*
import kotlinx.android.synthetic.main.fragment_work.*
import java.io.FileNotFoundException

class VoiceCallActivity : AppCompatActivity() {
    private iv_hand_voice
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_voice_chat)
        turnToVideo()

    }
    private fun turnToVideo() {
        toolbar_navigation_voice_chat?.setOnClickListener {
            var intent = Intent(this!!, VideoCallActivity::class.java)
            this?.startActivity(intent)
        }

    }

    /**
     * 把用户选择的图片显示在imageview中
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //用户操作完成，结果码返回是-1，即RESULT_OK
        if (resultCode == RESULT_OK) {
            //获取选中文件的定位符
            val uri = data!!.data
            Log.e("uri", uri.toString())
            //使用content的接口
            val cr = this.contentResolver
            try {
                //获取图片
                val bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri!!))
                iv_hand_voice.setImageBitmap(bitmap)
            } catch (e: FileNotFoundException) {
                Log.e("Exception", e.message, e)
            }
        } else {
            //操作错误或没有选择图片
            Log.i("MainActivtiy", "operation error")
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}