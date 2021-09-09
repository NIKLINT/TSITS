package com.tsits.tsits_webrtc.`interface`

import android.app.Service
import android.content.Context
import android.telephony.TelephonyManager
import android.view.MotionEvent
import java.security.Provider


open interface MyOnTouchListener {
    open fun onTouch(ev:MotionEvent):Boolean



}