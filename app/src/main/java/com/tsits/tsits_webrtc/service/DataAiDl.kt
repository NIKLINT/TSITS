package com.tsits.tsits_webrtc.service

import android.util.Log
import com.tsits.tsits_webrtc.aidl_Data

class DataAiDl : aidl_Data.Stub() {
    //设置数据
    var list = ArrayList<String>()
    override fun setData(s: String) {
        Log.e("MainActivity", s)
        list.add(s)
    }

    override fun getData(): MutableList<String> {
        return list
    }
}