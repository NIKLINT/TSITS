package com.tsits.tsits_webrtc.sdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TSXMPPClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * ----测试代码 ----start
     */
    String testString = " This is TSPocVideo.";

    public void DemoTest(int position, String[] strings, int[] ints) {

//        Log4jUtils.d(this, "DemoTest pos = "+ position);
//        Log4jUtils.d(this,TSXMPPClient.getInstance().getTestString());
        TSXMPPClient.getInstance().DemoTest(position, strings, ints);
//        Log4jUtils.d(this, "DemoTest pos = "+ position + "end!");


    }

    public void DemoTestCallback() {

        //调用回调
        if (itsPocVideoCallback != null) {
            itsPocVideoCallback.TSPocVideoCallback_TestFun("TSPocVideoCallback_TestFun");
        }

        TSXMPPClient.setCallBack(tsxmppClientCallback);
        TSXMPPClient.getInstance().DemoTestCallback();

    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }
    /** ----测试代码 ----end */



}