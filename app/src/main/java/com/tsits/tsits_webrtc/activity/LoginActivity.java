package com.tsits.tsits_webrtc.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.tsits.pocvideosdk.TSPocVideo;
import com.tsits.tsits_webrtc.R;
import com.tsits.tsits_webrtc.fragment.WorkFragment;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/22 9:29
 */
public class LoginActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final String TAG = "LoginActivity";
    public final static int REQUEST_READ_PHONE_STATE = 1;
    public String DEVICE_ID;
    private EditText account;
    private EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
        getDeviceID(this);
    }



    /*
    获取设备IMEI
    * */
    void getDeviceID(Context context) {
        String ANDROID_ID = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
        account.setText(ANDROID_ID);//获取ANDROID_ID号

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                    REQUEST_READ_PHONE_STATE);
        } else {
            try {
                TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
                DEVICE_ID = telephonyManager.getDeviceId();
                password.setText(DEVICE_ID);
            }catch (Exception e){
                password.setText("null");
            }
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_READ_PHONE_STATE:
//                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                    //TODO
//                }
//                break;
//
//            default:
//                break;
//        }
//
//    }


}
