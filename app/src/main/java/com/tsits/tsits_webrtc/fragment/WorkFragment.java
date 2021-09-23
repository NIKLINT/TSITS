package com.tsits.tsits_webrtc.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.tsits.pocvideosdk.Stru_GPSSetting;
import com.tsits.pocvideosdk.TSPocVideo;
import com.tsits.pocvideosdk.callback.TSClientCallback;
import com.tsits.tsclientsdk.TSClient;
import com.tsits.tsits_webrtc.R;
import com.tsits.tsits_webrtc.activity.VoiceCallActivity;
import com.tsits.tsits_webrtc.entity.Stru_JanusService;
import com.tsits.tsits_webrtc.entity.Stru_TSRTCService;
import com.tsits.tsits_webrtc.entity.Stru_XMPPService;
import com.tsits.tsits_webrtc.sdk.ITSPocVideoCallback;

import static com.tsits.tsits_webrtc.activity.LoginActivity.REQUEST_READ_PHONE_STATE;

/**
 * @author YUAN
 * @description:
 * @date :2021/9/13 11:20
 */
public class WorkFragment extends Fragment {

    public final static int REQUEST_READ_PHONE_STATE = 1;
    public String DEVICE_ID;
    public int locationID = 0;
    public String version;
    private com.tsits.janusclientsdk.Stu.Stru_JanusService stru_janusService=
            new com.tsits.janusclientsdk.Stu.Stru_JanusService("1111,1111,1111,1111",8888);
    private com.tsits.tsclientsdk.stru.Stru_TSRTCService stru_tsrtcService=
            new com.tsits.tsclientsdk.stru.Stru_TSRTCService("1111,1111,1111,1111",8888);
    private com.tsits.xmppclientsdk.stu.Stru_XMPPService stru_xmppService=
            new com.tsits.xmppclientsdk.stu.Stru_XMPPService("1111,1111,1111,1111",8888,"kkk");
    private Stru_GPSSetting stru_gpsSetting=
            new Stru_GPSSetting((byte)0,(short)1,(short)0,(short)0,(short)0,(short)0,0,1,1);

    private Button btn_change_name;
    private Button btn_id_work;
    private EditText et_change_name;
    private EditText et_id_word;
    private EditText et_versionCode_work;
    private ImageView toolbar_navigation_work;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_work, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Init();
        turnToVoiceCallActivity();
        changeNickName();
        setLocationId();
        getVersionCode(getContext());
        initPhone();
    }

    private void Init() {
        btn_change_name = getActivity().findViewById(R.id.btn_change_name);
        btn_id_work = getActivity().findViewById(R.id.btn_id_work);
        et_change_name = getActivity().findViewById(R.id.et_change_name);
        et_id_word = getActivity().findViewById(R.id.et_id_word);
        et_versionCode_work=getActivity().findViewById(R.id.et_versionCode_work);
        toolbar_navigation_work = getActivity().findViewById(R.id.toolbar_navigation_work);

    }

    private void changeNickName() {
        btn_change_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TSPocVideo.getInstance().Device_ChangeInfo(et_change_name.getText().toString());
            }
        });
    }

    private void turnToVoiceCallActivity() {
        toolbar_navigation_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), VoiceCallActivity.class));
            }
        });
    }

    /*设置设备通信ID*/
    void setLocationId() {
        btn_id_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationID = Integer.parseInt(et_id_word.getText().toString());
            }
        });
    }

//    /*
//    获取设备IMEI
//    * */
//    void getDeviceID(Context context) {
//        String ANDROID_ID = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
//        account.setText(ANDROID_ID);//获取ANDROID_ID号
//
//        int permissionCheck = ContextCompat.checkSelfPermission(this,
//                Manifest.permission.READ_PHONE_STATE);
//        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE},
//                    REQUEST_READ_PHONE_STATE);
//        } else {
//            try {
//                TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
//                DEVICE_ID = telephonyManager.getDeviceId();
//                password.setText(DEVICE_ID);
//            }catch (Exception e){
//                TSClient.g
//            }
//        }
//    }

    /*获取软件版本号*/
    public  String getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        version = packageInfo.versionName;
        et_versionCode_work.setText(version+"");
        return version;
    }

    void initPhone(){
//        TSClient.getInstance().Init(locationID,DEVICE_ID,version,stru_tsrtcService);
//        TSPocVideo.getInstance().Init(locationID,DEVICE_ID,version,stru_janusService,stru_tsrtcService,stru_xmppService,stru_gpsSetting);
    }

}
