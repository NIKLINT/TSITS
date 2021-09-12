package com.tsits.tsits_webrtc.sdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class TSXMPPClient extends AppCompatActivity {

    private ITSPocVideoCallback itsPocVideoCallback;
    private String[] strings=new String[]{"sa","2","1"};
    private int[] ints=new int[]{1,1,1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TSPocVideo.getInstance().DemoTest(1,strings,ints);
        TSPocVideo.getInstance().DemoTestCallback();

    }


}