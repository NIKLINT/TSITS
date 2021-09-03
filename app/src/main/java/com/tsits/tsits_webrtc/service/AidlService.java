package com.tsits.tsits_webrtc.service;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.hunter.codec.ESCodecJNI;
import com.tsits.tsits_webrtc.aidl_Data;

//Java文件
public class AidlService extends Service {

    private String TAG = "AidlService";

    private ESCodecJNI jni = new ESCodecJNI();

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
//        return null;
        return new aidl_Data.Stub() {
            @Override
            public int setMode(int set) throws RemoteException {
                if (set >= 1 && set <= 18) {
                    jni.switchEsMode(set);
                    Log.d(TAG, "setMode-------------------------->" + jni.switchEsMode(set));
                    set = jni.getEsMode();
                    Log.d(TAG, "setMode-------------------------->" + set);
                    return set;
                } else {
                    return -2;
                }
            }

            @Override
            public int getMode() throws RemoteException {
                int get=jni.getEsMode();
                if (get>= 1 && get <= 18) {
                    Log.d(TAG, "getMode-------------------------->" + jni.getEsMode());
                    return get;
                } else {
                    return -2;
                }
            }
        };
    }
}