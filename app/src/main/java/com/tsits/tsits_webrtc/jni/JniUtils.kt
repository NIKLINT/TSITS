package com.tsits.tsits_webrtc.jni

/**
 * @author YUAN
 * @description:
 * @date :2021/9/10 11:39
 */

class JniUtils {
    external fun stringFromJNI(): String
    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

}


